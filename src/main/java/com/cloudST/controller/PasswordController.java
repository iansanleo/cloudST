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

import com.cloudST.model.Usuario;
import com.cloudST.service.EmailService;
import com.cloudST.service.UsuarioService;

@Controller
public class PasswordController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private EmailService emailService;

	//@Autowired
	//private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	// Display forgotPassword page
	@GetMapping(value = "/forgot")
	public String displayForgotPasswordPage() {
		return "forgotMail";
    }
    
    // Process form submission from forgotPassword page
	@PostMapping(value = "/forgot")
	public String processForgotPasswordForm(Model model, HttpServletRequest request) {

		// Lookup user in database by e-mail
		Usuario usuario = usuarioService.findUserByEmail(request.getParameter("userEmail"));

		if (!usuario.getNombre().isEmpty()) {
						
			// Generate random 36-character string token for reset password 
			usuario.setResetToken(UUID.randomUUID().toString());

			// Save token to database
			usuarioService.save(usuario);

			String appUrl = request.getScheme() + "://" + request.getServerName();
			
			// Email message
			SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
			passwordResetEmail.setFrom("support@demo.com");
			passwordResetEmail.setTo(usuario.getEmail());
			passwordResetEmail.setSubject("Password Reset Request");
			passwordResetEmail.setText("To reset your password, click the link below:\n" + appUrl
					+ "/reset?token=" + usuario.getResetToken());
			
			emailService.sendEmail(passwordResetEmail);

			// Add success message to view
			model.addAttribute("Msg", "A password reset link has been sent to " + request.getParameter("userEmail"));
		}
		return "forgotPassword";
	}

	
	// Display form to reset password
	@GetMapping(value = "/reset")
	public String displayResetPasswordPage(Model model, HttpServletRequest request) {
		
		Usuario usuario = usuarioService.findUserByResetToken(request.getParameter("token"));

		if (!usuario.getNombre().isEmpty()) { // Token found in DB
			model.addAttribute("resetToken", request.getParameter("token"));
		} else { // Token not found in DB
			model.addAttribute("Msg", "Oops!  This is an invalid password reset link.");
		}

		return "PassReset";
	}

	// Process reset password form
	@PostMapping(value = "/reset")
	public String setNewPassword(Model model, HttpServletRequest request) {

		// Find the user associated with the reset token
		Usuario usuario= usuarioService.findUserByResetToken(request.getParameter("token"));

		// This should always be non-null but we check just in case
		if (!usuario.getNombre().isEmpty()) { 
            
			// Set new password    
            //usuario.setPassword(bCryptPasswordEncoder.encode(requestParams.get("password")));
			if(!request.getParameter("password").equals(request.getParameter("password2"))) {
				model.addAttribute("Msg", "Passwords doesn't mach");
				return "PassReset";
			}
			usuario.setPassword(request.getParameter("password"));
			// Set the reset token to null so it cannot be used again
			usuario.setResetToken(null);

			// Save user
			usuarioService.save(usuario);

			// In order to set a model attribute on a redirect, we must use
			// RedirectAttributes
			model.addAttribute("Msg", "You have successfully reset your password.  You may now login.");

			return "redirect:/";
			
		} else {
			model.addAttribute("Msg", "Oops!  This is an invalid password reset link.");
			return "";	
		}
   }
   
    // Going to reset page without a token redirects to login page
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public String handleMissingParams(MissingServletRequestParameterException ex) {
		return "redirect:/";
	}
}