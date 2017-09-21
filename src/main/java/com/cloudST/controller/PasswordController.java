package com.cloudST.controller;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cloudST.model.User;
import com.cloudST.service.EmailService;
import com.cloudST.service.UserService;

@Controller
public class PasswordController {

	@Autowired
	private UserService userService;

	@Autowired
	private EmailService emailService;
	
	@GetMapping(value = "/forgot")
	public String displayForgotPasswordPage() {
		return "forgotMail";
    }
    
	@PostMapping(value = "/forgot")
	public String processForgotPasswordForm(Model model, HttpServletRequest request) {

		User usuario = userService.findUserByEmail(request.getParameter("userEmail"));

		if (!usuario.getName().isEmpty()) {
						 
			usuario.setResetToken(UUID.randomUUID().toString());

			userService.save(usuario);

			String appUrl = request.getScheme() + "://" + request.getServerName();
			
			SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
			passwordResetEmail.setFrom("support@cloudST.com");
			passwordResetEmail.setTo(usuario.getEmail());
			passwordResetEmail.setSubject("Password Reset Request");
			passwordResetEmail.setText("To reset your password, click the link below:\n" + appUrl
					+ ":8080/reset?token=" + usuario.getResetToken());
			
			emailService.sendEmail(passwordResetEmail);

			model.addAttribute("Msg", "A password reset link has been sent to " + request.getParameter("userEmail"));
		}
		return "redirect:/";
	}

	@GetMapping(value = "/reset")
	public String displayResetPasswordPage(Model model, HttpServletRequest request) {
		
		User user = userService.findUserByResetToken(request.getParameter("token"));

		if (!user.getName().isEmpty()) {
			model.addAttribute("resetToken", request.getParameter("token"));
		} else {
			model.addAttribute("Msg", "Oops!  This is an invalid password reset link.");
		}

		return "passReset";
	}

	@PostMapping(value = "/reset")
	public String setNewPassword(Model model, HttpServletRequest request) {

		System.out.println(request.getParameter("token"));
		User user = userService.findUserByResetToken(request.getParameter("token"));

		System.out.println("pero que mierda!");
		if (!user.getName().isEmpty()) { 
             
			if(!request.getParameter("password").equals(request.getParameter("password2"))) {
				model.addAttribute("Msg", "Passwords doesn't mach");
				return "PassReset";
			}
			user.setPassword(request.getParameter("password"));
			user.setResetToken(null);

			userService.save(user);

			model.addAttribute("Msg", "You have successfully reset your password.  You may now login.");

			return "redirect:/";
			
		} else {
			model.addAttribute("Msg", "Oops!  This is an invalid password reset link.");
			return "redirect:/";	
		}
   }
   
    // Going to reset page without a token redirects to login page
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public String handleMissingParams(MissingServletRequestParameterException ex) {
		return "redirect:/";
	}
}