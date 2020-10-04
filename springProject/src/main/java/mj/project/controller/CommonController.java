package mj.project.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import mj.project.domain.MemberVO;
import mj.project.service.MemberService;


@Controller
@Log4j
public class CommonController {
	
	@Setter(onMethod_= @Autowired)
	private MemberService service;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "home";
	}
	
	@GetMapping("/signup")
	public void signup() { }
	
	@PostMapping("/signup")
	public String signup(MemberVO member) {
		log.info("========================");
		log.info("회원가입: "+member);
		if(member.getName() == null) member.setName("");
		log.info(">>>");
		// 회원가입 성공
		if(service.signup(member)) {
			return "successSignup";
		}
		
		return "home";
	}
	
	@GetMapping("/customLogin")
	public void loginInput(String error, String logout, Model model) {
		log.info("error: " + error);
		log.info("logout: " + logout);
		
		//if(error != null) model.addAttribute("error", "Login Error Check Your Account");
		
		if(logout != null) model.addAttribute("logout", "Logout!!");
		
	}
	
	@RequestMapping(value="/successLogin")
	public void successLogin() { }
	
	@GetMapping("/accessError")
	public void accessDenied(Authentication auth, Model model) {
		log.info("access Denied: "+auth);
		model.addAttribute("msg", "Access Denied");
	}
	
	@GetMapping("/customLogout")
	public void getLogout() {
		log.info("custom logout");
	}
	
	@PostMapping("/customLogout")
	public String postLogout() {
		log.info("post custom logout");
		return "customLogin";
	}
	
}
