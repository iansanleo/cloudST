package com.cloudST.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cloudST.model.User;
import com.cloudST.service.EmailService;
import com.cloudST.service.FileService;
import com.cloudST.service.PrivilegeService;
import com.cloudST.service.UserService;
import com.cloudST.service.exception.UserException;
 

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private PrivilegeService privilegeService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private FileService fileService;

	@PostMapping("/login")
	public String login(Model model, HttpServletRequest request){
		HttpSession session = request.getSession();
		String userName = request.getParameter("userName").toString();
        String password = request.getParameter("password").toString();

		User user = null;
		try {
			user = userService.authentication(userName, password);
		} catch (UserException e) {
			model.addAttribute(e.getMessage());
			return "index";
		}
		
		session.setAttribute("idUserSession", user.getIdUser());
    	session.setAttribute("permissions", privilegeService.findByIdUser(user.getIdUser()).getType());
    	
    	return "welcome";
	}
	
	@GetMapping ("/logout")
	public String logOut(Model model, HttpServletRequest request){
		HttpSession session = request.getSession();
		session.removeAttribute("idUserSession");
		session.removeAttribute("permissions");
		return "index";
	}
	
	@GetMapping("/user")
	public String userProfile(Model model, HttpServletRequest request){
		HttpSession session = request.getSession();
		Integer idUser = (Integer) session.getAttribute("idUserSession");
		
		model.addAttribute("user",userService.findById(idUser));
		
		return "user";
	}
	
	@GetMapping("/editUser")
	public String editProfile(Model model,  HttpServletRequest request){
		HttpSession session = request.getSession();
		Integer idUser;
		
		if(request.getParameter("idUser") == null){
			idUser = (Integer) session.getAttribute("idUserSession");
			
		}else{
			idUser = Integer.parseInt(request.getParameter("idUser"));
			model.addAttribute("idUser",idUser);
			model.addAttribute("typeUser",privilegeService.findByIdUser(idUser).getType());

		}
		
		model.addAttribute("user", userService.findById(idUser));
		return "editUser";
	}
	
	@PostMapping("/userEdit")
	public String newValueUser(Model model, HttpServletRequest request){
		HttpSession session = request.getSession();
		
		String name = request.getParameter("name").toString();
		String email = request.getParameter("email").toString();
		Integer type=0;
		if(request.getParameter("type")!=null){
		   type = Integer.parseInt(request.getParameter("type"));
		}

		try {
			if(request.getParameter("idUser") == null){
				Integer idUser = (Integer) session.getAttribute("idUserSession"); 
				userService.update(idUser, name, email, request.getParameter("password").toString(),
						request.getParameter("password2").toString());
			}else{
				Integer idUser = Integer.parseInt(request.getParameter("idUser"));
				userService.updateAdmin(idUser,name, email, request.getParameter("username").toString(),
						request.getParameter("valid").toString());	
				privilegeService.update(idUser,type);
			}
		} catch (UserException e) {
			model.addAttribute("Msg",e.getMessage());
			return "redirect:/editUser";
		}
		model.addAttribute("Msg","User has been successfully modified");
		if(request.getParameter("idUser") == null){
			return "redirect:/user";
		}else{
			return "redirect:/userList";
		}
	}
	
	
	@GetMapping("/newUser")
	public String newUser(Model model){
		return "addUser";
	}
	
	@PostMapping("/userAdd")
	public String userAdd(Model model, HttpServletRequest request){
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		String username = request.getParameter("userName");
		String email = request.getParameter("email");
		String name = request.getParameter("name").toLowerCase();

		try {
			
			User user = userService.create(username, name, email, password, password2);
		    privilegeService.create(0, user.getIdUser());

			model.addAttribute("Msg", "User successfully added");

			return "redirect:/valid?idUser="+user.getIdUser();
		} catch (UserException e) {
			model.addAttribute("Msg", e.getMessage());
			return "addUser";
		}
	}
	 
	 
	@GetMapping("/userList")
	public String listUsers(Model model, HttpServletRequest request){
		 model.addAttribute("users", userService.listUser());
		 return "listUser";
	}
	  
	  @GetMapping("/delAdminUser")
	  public String deleteAdminUser(Model model, HttpServletRequest request){ 
		  userService.delete(Integer.parseInt(request.getParameter("idUser")));
		  fileService.deleteAllFilesUser(Integer.parseInt(request.getParameter("idUser")));
		  privilegeService.deleteUser(Integer.parseInt(request.getParameter("idUser")));
		  return "redirect:/userList";
	  }
	  
	  @GetMapping("/validate")
	  public String validate(Model model, HttpServletRequest request){
		  Integer idUser =(Integer.parseInt(request.getParameter("valid")));
		  
		  User user = userService.findById(idUser);
		  
		  user.setValid(true);
		  userService.save(user);
		  
		  return "redirect:/";
	  }
	  
	  @GetMapping("/valid")
	  public String validateMail(Model model, HttpServletRequest request){
		User user  = userService.findById(Integer.parseInt(request.getParameter("idUser")));
		String appUrl = request.getScheme() + "://" + request.getServerName();
		
		SimpleMailMessage validEmail = new SimpleMailMessage();
		validEmail.setFrom("support@cloudST.com");
		validEmail.setTo(user.getEmail());
		validEmail.setSubject("Validate Mail");
		validEmail.setText("To validate your mail, click the link below:\n" + appUrl
				+ ":8080/validate?valid=" + user.getIdUser());
		
		emailService.sendEmail(validEmail);
	  return "redirect:/";
	  }
	  
	 
}
