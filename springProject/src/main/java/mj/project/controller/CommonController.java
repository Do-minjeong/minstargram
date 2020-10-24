package mj.project.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.scribejava.core.model.OAuth2AccessToken;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import mj.project.domain.MemberVO;
import mj.project.naverLogin.NaverLoginBO;
import mj.project.service.MemberService;


@Controller
@Log4j
public class CommonController {

	@Setter(onMethod_= @Autowired)
	private MemberService service;

	@Setter(onMethod_= @Autowired)
	private NaverLoginBO naverLoginBO;
	private String apiResult = null;
	
	@RequestMapping(value = {"/", "/home"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String home(HttpServletRequest request, HttpSession session, Authentication auth, Model model) {
		log.info(">>>> home Method auth: "+auth);
		log.info(">>>> home Method session: "+session.getAttribute("userInfo"));
		System.out.println("home 진입");
		String username = "";
		if(auth == null && session.getAttribute("userInfo") == null) {
			return "redirect:customLogin";
		} else if(auth != null && session.getAttribute("userInfo")==null) {
			UserDetails ud = (UserDetails) auth.getPrincipal();
			username = ud.getUsername();
			MemberVO vo = service.readMemberInfo(username);
			vo.setLogin_type_no(1);
			session.setAttribute("userInfo", vo);
		}
		return "home";
	}

	@GetMapping("/signup")
	public void signup(HttpSession session, Model model) { 
		// 네이버 아이디로 인증 url을 생성하기 위해 naverLoginBO 클래스의 getAuthorizationUrl 메소드 호출
		String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);

		log.info("회원가입 네이버: "+naverAuthUrl);

		// 네이버
		model.addAttribute("url", naverAuthUrl);	
		
	}

	@PostMapping("/signup")
	public String signup(MemberVO member) {
		log.info("========================");
		log.info("회원가입: "+member);
		if(member.getName() == null) member.setName("");
		member.setLogin_type_no(1);
		// 회원가입 성공
		if(service.signup(member)) {
			return "successSignup";
		}

		return "home";
	}

	@RequestMapping(value="/customLogin", method= {RequestMethod.GET, RequestMethod.POST})
	public String loginInput(String error, String logout, Authentication auth, HttpSession session, Model model) {
		String username = "";
		System.out.println("loginInput 메소드 > login auth: "+ auth);
		System.out.println("loginInput 메소드 > login session: "+session.getAttribute("userInfo"));
		if(auth == null && session.getAttribute("userInfo")==null) {
			log.info("error: " + error);
			log.info("logout: " + logout);

			if(error != null) model.addAttribute("error", "Login Error Check Your Account");

			if(logout != null) model.addAttribute("logout", "Logout!!");

			// 네이버 아이디로 인증 url을 생성하기 위해 naverLoginBO 클래스의 getAuthorizationUrl 메소드 호출
			String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);

			log.info("네이버: "+naverAuthUrl);

			// 네이버
			model.addAttribute("url", naverAuthUrl);	
			return "customLogin";
		}
		return "home";
	}
	
	@RequestMapping(value="/customLogin/callback", method= {RequestMethod.GET,RequestMethod.POST})
	public String callback(Model model, @RequestParam String code, @RequestParam String state, HttpSession session) throws IOException, ParseException{
		System.out.println("callback 메소드");
		String url = "";
		OAuth2AccessToken oauthToken = naverLoginBO.getAccessToken(session, code, state);
		// 로그인 사용자 정보를 읽어온다.
		apiResult = naverLoginBO.getUserProfile(oauthToken);
		//session.setAttribute("apiResult", apiResult);
		System.out.println("result: "+apiResult);
		
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObj = (JSONObject) jsonParser.parse(apiResult);
		String user_info = String.valueOf(jsonObj.get("response"));
		jsonObj = (JSONObject) jsonParser.parse(user_info);
		
		String username = String.valueOf(jsonObj.get("email"));
		// 등록된 정보가 있을 경우
		if(service.usernameCheck(username)) {
			if(!service.userIdCheck(username)) 	url = "redirect:/home";
			else url = updateUserId(username, model);
		} else {
			service.signup(new MemberVO(username, String.valueOf(jsonObj.get("name")), "<REGISTER_REQUIRED>", 2, String.valueOf(jsonObj.get("id"))));
			url = updateUserId(username, model);
		}
		MemberVO vo = service.readMemberInfo(username);
		vo.setLogin_type_no(2);
		session.setAttribute("userInfo", vo);
		return url;
	}
	
	private String updateUserId(String username, Model model) {
		model.addAttribute("username",username);		
		return "updateUserID";
	}

	@GetMapping("/updateUserID")
	public void getUpdateUserId() {}
	
	@PostMapping("/updateUserID")
	public String postUpdateUserID(String username, String userid, Model model) {
		if(service.updateUserID(username, userid)) {
			model.addAttribute("id_update", 1);
			return "home";
		}
		else {
			model.addAttribute("id_update", 0);
			return "updateUserID";
		}
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
	public String customLogout(HttpSession session) {
		log.info("post custom logout");
		System.out.println("post custom logout");
		sessionLogout(session);
		return "redirect:customLogin?logout";
	}
	
	@PostMapping("/socialLogout")
	public String sessionLogout(HttpSession session) {
		log.info(">> session logout: "+session.getAttribute("userInfo"));
		session.invalidate();
		return "redirect:customLogin?logout";
	}


}
