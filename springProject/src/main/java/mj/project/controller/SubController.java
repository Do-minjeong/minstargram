package mj.project.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import mj.project.common.CommonFunction;
import mj.project.domain.MemberVO;
import mj.project.service.SubService;

@RestController
@Log4j
public class SubController {
	
	@Setter(onMethod_ = @Autowired)
	private SubService service;
	
	@Setter(onMethod_ = @Autowired)
	private CommonFunction cf;
	
	
	@PostMapping(value = "/like/{post_no}", produces = "application/json; charset=utf8")
	public ResponseEntity<Integer> likeOn(@PathVariable("post_no") String post_no, HttpSession session) throws Exception {
		MemberVO member = cf.getSession(session);
		return new ResponseEntity<Integer>(service.likeOn(post_no, member.getMember_no()), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/like/{post_no}", produces="application/json; charset=utf-8")
	public ResponseEntity<Integer> likeOff(@PathVariable("post_no") String post_no, HttpSession session){
		
		return new ResponseEntity<Integer>(2, HttpStatus.OK);
	}
	

}
