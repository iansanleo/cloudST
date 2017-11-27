package com.cloudST.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.cloudST.utiles.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cloudST.model.File;
import com.cloudST.service.FileService;
import com.cloudST.service.PrivilegeService;
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
	@Autowired
	private PrivilegeService privilegeService;

	private static String UPLOADED_FOLDER="//home//pi//CloudSTtemp//";
    //private static String UPLOADED_FOLDER = "C://temp//";
	
    /*
       windows 
       new File("d:/yourApplicationName/" + name + "-uploaded")

        linux
    	new File("/home/pi/CloudSTtemp/" + name + "-uploaded")
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
            String date =DateUtils.actualDate().toString();
            date=date.replaceAll(":", ".");
            date=date.replaceAll(" ","");
            
            Path path = Paths.get(UPLOADED_FOLDER + date +"@"+file.getOriginalFilename());
			Files.write(path, bytes);

            model.addAttribute("Msg",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");       
           
            double bytesA =  file.getSize();
			double kilobytes = (bytesA / 1024);
			double megabytes = (kilobytes / 1024);
			megabytes= Math.round(megabytes*100)/100;
			
			if(megabytes<0.1){megabytes=0.1;}

			if(fullMaxStorageUser((Integer)session.getAttribute("idUserSession"))){
				model.addAttribute("Msg","All the space corresponding to your plan is in use, delete resources or upgrade to the payment plan");
				return "file";
			}

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

    
    private boolean fullMaxStorageUser(Integer idUser){
    	double totalSize = 0;
    	ArrayList<File> listFile = fileService.allUserFiles(idUser);
    	for(int i=0;i<listFile.size();i++){
    		if(listFile.get(i).getStatus()){
    			totalSize=+listFile.get(i).getSize();
    		}
    	}
    	if(totalSize >(1+privilegeService.actualType(idUser))*10){
    		return true;
    	}else return false;
    }
}