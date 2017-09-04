package com.cloudST.controller;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.Date;

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
import com.cloudST.model.Transaccion;
import com.cloudST.repository.ArchivoRepository;
import com.cloudST.repository.TransaccionRespository;

@Controller
public class UploadFileController {
	
	@Autowired
	private ArchivoRepository archivoRepository;
	@Autowired
	private TransaccionRespository transaccionRepository;

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
            
            Archivos archivo = new Archivos();

            archivo.setIdUsuario((Integer) session.getAttribute("idUser"));
            archivo.setNombreOri(file.getOriginalFilename());
            archivo.setNombreSys(path.toString());
            archivo.setStatus(true);
            
            //
            byte[] rno = file.getBytes();
            int tamanyo = rno[0];
            double tamanyoMb = tamanyo/1024;
            
            archivo.setTamanyo(tamanyoMb);  
            archivo.setString(file.getContentType());
            
            archivoRepository.save(archivo);
            
            
            Integer idArchivo = 0;            
            for (Archivos archivoElem : archivoRepository.findAll()) {
    			if (archivoElem.getIdUsuario() == session.getAttribute("idUser")){
    				idArchivo = archivoElem.getIdArchivo();
    			}
    		}
            
            //Preparacion transaccion
            Transaccion transaccion = new Transaccion();
            
            Date fechaInicio = new java.util.Date(); //fecha actual
    		Timestamp sqlTimestamp = new Timestamp(fechaInicio.getTime());//en milisegundos
    		fechaInicio = new Date(sqlTimestamp.getTime());
    		
            transaccion.setFecha(fechaInicio);
            transaccion.setIdArchivo(idArchivo);
            transaccion.setidUsuario((Integer)session.getAttribute("idUser"));
            
            transaccion.setTipo("upload");
            
            transaccionRepository.save(transaccion);
            
            
            
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