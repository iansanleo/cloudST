package com.cloudST.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cloudST.model.Usuario;
import com.cloudST.service.PrivilegiosService;
import com.cloudST.service.UsuarioService;
import com.cloudST.service.exception.UsuarioException;
 

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private PrivilegiosService privilegiosService;

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
		
		session.setAttribute("idUserSession", usuario.getIdUsuario());
    	
    	session.setAttribute("permisos", privilegiosService.findByIdUser(usuario.getIdUsuario()).getTipo());
    	
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
		Integer idUser = (Integer) session.getAttribute("idUserSession");
		usuario = usuarioService.findById(idUser);
		model.addAttribute("usuario",usuario);
		return "usuario";
	}
	
	@GetMapping("/editUser")
	public String editProfile(Model model,  HttpServletRequest request){
		HttpSession session = request.getSession();
		Integer idUser = (Integer) session.getAttribute("idUserSession");
		Usuario usuario = usuarioService.findById(idUser);
		model.addAttribute("usuario", usuario);
		return "editUsuario";
		
		
		/*     @PostMapping("/userEdit")
 
  public String newValueUser(Model model, HttpServletRequest request){
 
    HttpSession session = request.getSession();
          
		 *  Integer idUser = 0;
 
    
 
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
 
		 *           if(!(usuarioAux.getPassword() == null) && usuarioSave.getPassword() != usuarioAux.getPassword()){
 
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
 
		 */
		
	}
	
	@PostMapping("/userEdit")
	public String newValueUser(Model model, HttpServletRequest request){
		HttpSession session = request.getSession();
		
		
		String nombre = request.getParameter("name").toString();
		String email = request.getParameter("email").toString();

		try {
			if(request.getParameter("idUser") == null){
				Integer idUser = (Integer) session.getAttribute("idUserSession"); 
				usuarioService.update(idUser, nombre, email, request.getParameter("password").toString(),
						request.getParameter("password2").toString());
			}else{
				Integer idUser = Integer.parseInt(request.getParameter("idUser"));
				usuarioService.updateAdmin(idUser,nombre, email, request.getParameter("username").toString(),
						request.getParameter("valido").toString());
			}
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
		    privilegiosService.create(0, usuario.getIdUsuario());

			model.addAttribute("Msg", "User successfully added");

			if(session.getAttribute("idUserSession")==null){return "index";}

			return "welcome";
		} catch (UsuarioException e) {
			model.addAttribute("Msg", e.getMessage());
			return "addUsuario";
		}
	}
	 
	 
	@GetMapping("/userList")
	public String listUsers(Model model, HttpServletRequest request){
		
	 	 ArrayList<Usuario> listUsuario = usuarioService.listaUsuario();
		 model.addAttribute("usuarios", listUsuario);
		 return "listaUser";
	}
	  
	  @GetMapping("/delAdminUser")
	  public String deleteAdminUser(Model model, HttpServletRequest request){
		  
		  usuarioService.delete(Integer.parseInt(request.getParameter("idUserSession")));
		  		
		  return "redirect:/userList";
	  }
}
