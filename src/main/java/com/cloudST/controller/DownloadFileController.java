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

import com.cloudST.model.Archive;
import com.cloudST.service.ArchiveService;

@Controller
public class DownloadFileController {

	@Autowired
	private ArchiveService archiveService;
	
	//http://localhost:8080/springmvcexample/download/pdf/sample.pdf
	@GetMapping("/download")
    public void downloadPDFResource( Model model, HttpServletRequest request,
                                     HttpServletResponse response)
    {   int idFile = Integer.parseInt(request.getParameter("idFile"));
		Archive archive = archiveService.findByIdFile(idFile);
        String dataDirectory = archive.getSysName();
        Path file = Paths.get(dataDirectory, archive.getOriName());
        if (Files.exists(file))
        {
            response.setContentType("application/pdf");
            response.addHeader("Content-Disposition", "attachment; filename="+archive.getOriName());
            try
            {
                Files.copy(file, response.getOutputStream());
                response.getOutputStream().flush();
            }
            catch (IOException ex) {
                 ex.printStackTrace();
            }
        }
    }
}
