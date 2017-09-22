package com.cloudST.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cloudST.model.File;
import com.cloudST.service.FileService;
import com.cloudST.service.TransactionService;
import com.cloudST.service.UserService;

@Controller
public class UploadFileController {
	
	@Autowired
	private FileService fileService;
	@Autowired
	private TransactionService transactionService;
	@Autowired
	private UserService userService;

    private static String UPLOADED_FOLDER = "C://temp//";
    /*
       windows 
       new File("d:/yourApplicationName/" + name + "-uploaded")

        linux
    	new File("/home/yourApplicationName/" + name + "-uploaded")
     */

    @PostMapping("/uploadFile")
    public String singleFileUpload(Model model, @RequestParam("file") MultipartFile file,
    		HttpServletRequest request) {

    	HttpSession session = request.getSession();
    	
        if (file.isEmpty()) {
            model.addAttribute("Msg", "Please select a file to upload");
            return "upload";
        }

        try {
        	if(!userService.findById((Integer) session.getAttribute("idUserSession")).getValid()){
        	 model.addAttribute("Msg","You must validate your email before uploading a file");
        	 return "upload";
        	}
        	
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename()+"-uploaded-"+ session.getAttribute("idUserSession"));
            Files.write(path, bytes);

            model.addAttribute("Msg",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");       
           
            double bytesA =  file.getSize();
			double kilobytes = (bytesA / 1024);
			double megabytes = (kilobytes / 1024);
			megabytes= Math.round(megabytes*100)/100;

            File fileCreate = fileService.create(file.getOriginalFilename(), path.toString(), megabytes, file.getContentType(), (Integer) session.getAttribute("idUserSession"));
            
            transactionService.createUpload(fileCreate.getIdFile(), (Integer)session.getAttribute("idUserSession"));
          
            model.addAttribute("Msg","You successfully uploaded '" + file.getOriginalFilename() + "'");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "upload";
    }

    @GetMapping("/uploadForm")
    public String uploadJSP(){
    	return "upload";
    }

}