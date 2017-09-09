package com.cloudST.controller;
 
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cloudST.model.Privilegios;
import com.cloudST.model.Usuario;
import com.cloudST.repository.PrivilegiosRepository;
import com.cloudST.repository.UsuarioRepository;
 

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PrivilegiosRepository privilegiosRepository;

	@PostMapping("/login")
	public String login(Model model, HttpServletRequest request){
		
		HttpSession session = request.getSession();
		String userName = request.getParameter("userName").toString();
        String password = request.getParameter("password").toString();

        Usuario usuario = usuarioRepository.findByUser(userName);
	
        if(usuario.getUsername()==null){
        	model.addAttribute("Msg","User does not exist, create one.");
        	return "index";
        }
        
		if(!usuario.getPassword().equals(password)){
			model.addAttribute("Msg", "Password or invalid entered Username");
			return "index";
		}
		
		if(!usuario.getStatus()){
			model.addAttribute("Msg", "User removed, contact with us");
			return "index";
		}
		
		session.setAttribute("idUserSession", usuario.getIdUsuario());

		Privilegios privilegios = privilegiosRepository.findByIdUser(usuario.getIdUsuario());
    	
    	session.setAttribute("permisos", privilegios.getTipo());
    	
    	return "welcome";
	}
	
	
	@GetMapping ("/logout")
	public String logOut(Model model, HttpServletRequest request){
		
		HttpSession session = request.getSession();
		session.removeAttribute("idUserSession");
		session.removeAttribute("permisos");
		return "index";
	}
	
	
	@GetMapping("/user")
	public String userProfile(Model model, HttpServletRequest request){
		Usuario usuario = new Usuario();
		
		HttpSession session = request.getSession();
		usuario = usuarioRepository.findOne((Integer) session.getAttribute("idUserSession"));
		model.addAttribute("usuario",usuario);
		return "usuario";
	}
	
	
	@GetMapping("/editUser")
	public String editProfile(Model model,  HttpServletRequest request){
		HttpSession session = request.getSession();
		
		Usuario usuario = new Usuario();
		
		if(request.getParameter("idUser") == null){
			usuario = usuarioRepository.findOne((Integer)session.getAttribute("idUserSession"));
			
		}else{
			usuario = usuarioRepository.findOne(Integer.parseInt(request.getParameter("idUser")));
			model.addAttribute("idUser",request.getParameter("idUser"));
		}
		
		model.addAttribute("usuario", usuario);
		return "editUsuario";
	}
	
	
	@PostMapping("/userEdit")
	public String newValueUser(Model model, HttpServletRequest request){
		HttpSession session = request.getSession();
		
		Integer idUser = 0;
		
		Usuario usuarioAux = new Usuario();
		
		String nombre = request.getParameter("name").toString();
        String email = request.getParameter("email").toString();
		
        	usuarioAux.setEmail(email.toLowerCase());
        	usuarioAux.setNombre(nombre.toLowerCase());
		
        	if(request.getParameter("idUser") == null){
        		String password = request.getParameter("password").toString();
        		String password2 = request.getParameter("password2").toString();
        		idUser =(Integer)session.getAttribute("idUserSession");
	        
        		if(password!=""){
        			if(!password.equals(password2)){
        				model.addAttribute("Msg","Passwords do not match");
	     		   
        				return "redirect:/editUser";
        			}
        			usuarioAux.setPassword(password2);
			
        		}else{
        			idUser = Integer.parseInt(request.getParameter("idUser"));
        			model.addAttribute("idUser",request.getParameter("idUser"));
        			String validoJsp = request.getParameter("valido");
        			boolean valido = false;
			
        			if(validoJsp=="true"){
        				valido = true;
        			}
			
        			usuarioAux.setUsername(request.getParameter("username"));
        			usuarioAux.setValido(valido);
			
        			model.addAttribute("idUser", idUser);
        		}
		
        	}
        	Usuario usuarioSave = usuarioRepository.findOne(idUser);
        
        
        	if(!(usuarioAux.getPassword() == null) && usuarioSave.getPassword() != usuarioAux.getPassword()){
        		usuarioSave.setPassword(usuarioAux.getPassword());
        	}
        //
        	if(!(usuarioAux.getUsername()== null) && usuarioSave.getUsername()!=usuarioAux.getUsername()){
        		usuarioSave.setUsername(usuarioAux.getUsername());
        	}
        	if(usuarioSave.getValido() != usuarioAux.getValido()){
        		usuarioSave.setValido(usuarioAux.getValido());
        	}
        
        	if(usuarioSave.getEmail() != usuarioAux.getEmail()){
        		usuarioSave.setEmail(usuarioAux.getEmail());
        		usuarioSave.setValido(false);
        	}
        
        	if(usuarioSave.getNombre() != usuarioAux.getNombre()){
        		usuarioSave.setNombre(usuarioAux.getNombre());
        	}
       
        	usuarioRepository.save(usuarioSave);
       
        	model.addAttribute("Msg","User has been successfully modified");
        	return "redirect:/userEdit";

	}
	
	
	@GetMapping("/newUser")
	public String newUser(Model model){
		return "addUsuario";
	}
	
	
	@PostMapping("/userAdd")
	public String userAdd(Model model, HttpServletRequest request){
		Usuario usuario = new Usuario();
		
		HttpSession session = request.getSession();
		
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		String username = request.getParameter("userName");
		String email = request.getParameter("email");
		
		if(!password.equals(password2)){
			model.addAttribute("Msg", "Passwords doesn't mach");
			return "addUsuario";
		}
		
		if(usuarioRepository.findByEmail(email)!=null){
			model.addAttribute("Msg", "The email you entered is currently in use");
			return "addUsuario";
		}
		
		if(usuarioRepository.findByUser(username)!=null){
			model.addAttribute("Msg", "The username you entered is currently in use");
			return "addUsuario";
		}
		
		usuario.setNombre(request.getParameter("name").toLowerCase());
		usuario.setPassword(password);
		usuario.setUsername(username);
		usuario.setEmail(email);
		
		usuario.setStatus(true);
		usuario.setValido(false);
		
		Date fechaInicio = new java.util.Date(); //fecha actual
		Timestamp sqlTimestamp = new Timestamp(fechaInicio.getTime());//en milisegundos
		fechaInicio = new Date(sqlTimestamp.getTime());
		usuario.setFechaInicio(fechaInicio);
		usuarioRepository.save(usuario);
		
		Privilegios privilegio = new Privilegios(fechaInicio, 0, true, usuarioRepository.findByUser(username).getIdUsuario());
		privilegiosRepository.save(privilegio);
		
		model.addAttribute("Msg", "User successfully added");
		
		if(session.getAttribute("idUserSession")==null){return "index";}
		
		return "welcome";
	}
	
	
	@GetMapping("/userList")
	public String listUsers(Model model){
		ArrayList<Usuario> listUsuario = (ArrayList<Usuario>) usuarioRepository.findAll();
			
		model.addAttribute("usuarios", listUsuario);	
		return "listaUser";
	}


	@GetMapping("/delAdminUser")
	public String deleteAdminUser(Model model, HttpServletRequest request){
		Usuario usuario = usuarioRepository.findOne(Integer.parseInt(request.getParameter("idUser")));
		usuario.setStatus(false);
		
		usuarioRepository.save(usuario);
		
		return "redirect:/userList";
	}
}
