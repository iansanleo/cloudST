package com.cloudST.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cloudST.model.File;
import com.cloudST.service.FileService;
import com.cloudST.service.TransactionService;
import com.cloudST.service.exception.FileException;

@Controller
public class FileController {
	
	@Autowired
	private FileService fileService;
	@Autowired
	private TransactionService transactionService;
	
	@GetMapping("/resources")
	public String listResources(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();

		ArrayList<File> listFiles = fileService.allUserFiles((Integer)session.getAttribute("idUserSession"));

		model.addAttribute("files", listFiles);
		return "file";
	}
	
	@GetMapping("/deleteResource")
	public String deleteFile(Model model, HttpServletRequest request){
		HttpSession session = request.getSession();
		
		try{
			fileService.delete(Integer.parseInt(request.getParameter("idFile")));
			transactionService.createDelete(Integer.parseInt(request.getParameter("idFile")), (Integer)session.getAttribute("idUserSession"));
			return "redirect:/resources";
		} catch (FileException e) {
			model.addAttribute(e.getMessage());
			return "redirect:/resources";
		}
	}

}

