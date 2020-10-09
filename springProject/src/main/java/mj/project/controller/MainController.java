package mj.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/main/*")
public class MainController {
	
	@GetMapping("/mainHome")
	public void mainHome() {
		log.info("/main/mainHome controller 접속");
	}
	
	@GetMapping("/write")
	public void getWrite() { }
}
