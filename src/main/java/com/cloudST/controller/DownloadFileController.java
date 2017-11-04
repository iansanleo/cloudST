package com.cloudST.controller; 
 
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cloudST.model.File;
import com.cloudST.service.FileService; 
 
@Controller 
public class DownloadFileController { 
 
  @Autowired 
  private FileService fileService; 
   
  
  @GetMapping("/download") 
    public void downloadPDFResource( Model model, HttpServletRequest request, 
                                     HttpServletResponse response) 
    {
        int idFile = Integer.parseInt(request.getParameter("idFile"));
        File archive = fileService.findByIdFile(idFile);
        Path file = Paths.get("", preparePath(archive.getSysName()));
        if (Files.exists(file)) 
        { 
            response.setContentType(archive.getType()); 
            response.addHeader("Content-Disposition", "attachment; filename="+ preparePath(archive.getSysName()));
            try 
            { 
                Files.copy(file, response.getOutputStream()); 
                response.getOutputStream().flush(); 
            } 
            catch (IOException ex) { 
                 ex.printStackTrace(); 
            } 
        } 
        //return " ";
    }


  	private String preparePath(String path) {
	
  		return path.replace("/", "//");
  	}
} 