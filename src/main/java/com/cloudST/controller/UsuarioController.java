package com.cloudST.controller;

import com.cloudST.model.Privilegios;
import com.cloudST.model.Usuario;
import com.cloudST.repository.PrivilegiosRepository;
import com.cloudST.service.UsuarioService;
import com.cloudST.service.exception.UsuarioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
 

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private PrivilegiosRepository privilegiosRepository;

	@PostMapping("/login")
	public String login(Model model, HttpServletRequest request){
		
		HttpSession session = request.getSession();
		String userName = request.getParameter("userName").toString();
        String password = request.getParameter("password").toString();

		Usuario usuario = null;
		try {
			usuario = usuarioService.authentication(userName, password);
		} catch (UsuarioException e) {
			model.addAttribute(e.getMessage());
			return "index";
		}
		
		session.setAttribute("idUser", usuario.getIdUsusario());
		
		Privilegios privilegios = privilegiosRepository.findByIdUser(usuario.getIdUsusario());
    	
    	session.setAttribute("permisos", privilegios.getTipo());
    	
    	return "welcome";
	}
	
	@GetMapping ("/logout")
	public String logOut(Model model, HttpServletRequest request){
		
		HttpSession session = request.getSession();
		session.removeAttribute("idUser");
		session.removeAttribute("permisos");
		return "index";
	}
	
	@GetMapping("/user")
	public String userProfile(Model model, HttpServletRequest request){
		Usuario usuario = new Usuario();
		
		HttpSession session = request.getSession();
		Integer idUser = (Integer) session.getAttribute("idUser");
		usuario = usuarioService.findById(idUser);
		model.addAttribute("usuario",usuario);
		return "usuario";
	}
	
	@GetMapping("/editUser")
	public String editProfile(Model model,  HttpServletRequest request){
		HttpSession session = request.getSession();
		Integer idUser = (Integer) session.getAttribute("idUser");
		Usuario usuario = usuarioService.findById(idUser);
		model.addAttribute("usuario", usuario);
		return "editUsuario";
	}
	
	@PostMapping("/userEdit")
	public String newValueUser(Model model, HttpServletRequest request){
		HttpSession session = request.getSession();
		Integer idUser = (Integer) session.getAttribute("idUser");
		String nombre = request.getParameter("name").toString();
		String email = request.getParameter("email").toString();
		String password = request.getParameter("password").toString();
		String password2 = request.getParameter("password2").toString();

		try {
			usuarioService.update(idUser, nombre, email, password, password2);
		} catch (UsuarioException e) {
			model.addAttribute("Msg",e.getMessage());
			return "redirect:/editUser";
		}
		model.addAttribute("Msg","User has been successfully modified");
		return "redirect:/user" ;
	}
	
	
	@GetMapping("/newUser")
	public String newUser(Model model){
		return "addUsuario";
	}
	
	@PostMapping("/userAdd")
	public String userAdd(Model model, HttpServletRequest request){
		HttpSession session = request.getSession();
		
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		String username = request.getParameter("userName");
		String email = request.getParameter("email");
		String name = request.getParameter("name").toLowerCase();

		try {
			Usuario usuario = usuarioService.create(username, name, email, password, password2);
			Privilegios privilegio = new Privilegios(usuario.getFechaInicio(), 0, true, usuario.getIdUsusario());
			privilegiosRepository.save(privilegio);

			model.addAttribute("Msg", "User successfully added");

			if(session.getAttribute("idUser")==null){return "index";}

			return "welcome";
		} catch (UsuarioException e) {
			model.addAttribute("Msg", e.getMessage());
			return "addUsuario";
		}
	}
	
}
