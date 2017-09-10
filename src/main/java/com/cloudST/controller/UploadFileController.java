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

import com.cloudST.model.Archivos;
import com.cloudST.service.ArchivoService;
import com.cloudST.service.TransaccionService;

@Controller
public class UploadFileController {
	
	@Autowired
	private ArchivoService archivoService;
	@Autowired
	private TransaccionService transaccionService;

    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "C://temp//";
    /*
       If you are using windows here is an example for you:

    	new File("d:/yourApplicationName/" + name + "-uploaded")

    	If you are using linux here is an example for you:

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

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

            model.addAttribute("Msg",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");       
            

           
            double bytesA = ((CharSequence) file).length();
			double kilobytes = (bytesA / 1024);
			double megabytes = (kilobytes / 1024);
            
            Archivos archivo = archivoService.create(file.getOriginalFilename(), path.toString(), megabytes, file.getContentType(), (Integer) session.getAttribute("idUserSession"));
            
            transaccionService.createUpload(archivo.getIdArchivo(), (Integer)session.getAttribute("idUserSession"));
          
            model.addAttribute("Msg",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");
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