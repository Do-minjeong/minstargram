package mj.project.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.sql.CommonDataSource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/main/")
public class MainController {
	
	@GetMapping("/mainHome")
	public void mainHome() {
		log.info("/main/mainHome controller 접속");
	}
	
	@GetMapping("/write")
	public void getWrite() { 
		log.info("MainController getWrite");
	}
	
	@PostMapping("/write")
	public void postWrite(MultipartFile[] mpfile, MultipartHttpServletRequest request) {
		String uploadFolder = "C:\\spring_project\\minsgram_uploadFile";
		log.info("MainController postWirte");
		String text = request.getParameter("text");
		log.info("text: "+text);
		// File 다루기
		for (MultipartFile multipartFile : mpfile) {
			log.info("--------------------------------------------------------");
			log.info("Upload File Name: " + multipartFile.getOriginalFilename());
			log.info("Upload File Size: " + multipartFile.getSize());
			
			File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());
			try {
				multipartFile.transferTo(saveFile);
			} catch (IllegalStateException | IOException e) {
				log.error(e.getMessage());
			}
		}
	}
}






