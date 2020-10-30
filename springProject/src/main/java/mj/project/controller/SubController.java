package mj.project.controller;

import java.lang.reflect.Member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import mj.project.domain.MemberVO;
import mj.project.domain.ReplyVO;
import mj.project.service.SubService;

@RestController
@Log4j
@SessionAttributes("userInfo")
public class SubController {
	
	@Setter(onMethod_ = @Autowired)
	private SubService service;
	
	@PostMapping(value = "/like/{post_no}", produces = "application/json; charset=utf8")
	public ResponseEntity<Integer> likeOn(@PathVariable("post_no") String post_no, @SessionAttribute("userInfo") MemberVO member) {
		log.info("like On");
		return new ResponseEntity<Integer>(service.likeOnOff(0,post_no, member.getMember_no()), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/like/{post_no}", produces="application/json; charset=utf-8")
	public ResponseEntity<Integer> likeOff(@PathVariable("post_no") String post_no,  @SessionAttribute("userInfo") MemberVO member) {
		log.info("like Off");
		return new ResponseEntity<Integer>(service.likeOnOff(1,post_no, member.getMember_no()), HttpStatus.OK);
	}
	
	@PostMapping(value="/bookmark/{post_no}", produces="application/json; charset=utf-8")
	public ResponseEntity<String> bookmarkOn(@PathVariable("post_no") String post_no, @SessionAttribute("userInfo") MemberVO member) {
		log.info("bookmark On");
		return service.bookmarkOnOff(0, post_no, member.getMember_no())==1? new ResponseEntity<String>("success", HttpStatus.OK)
				: new ResponseEntity<String>("error", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@DeleteMapping(value="/bookmark/{post_no}", produces="application/json; charset=utf-8")
	public ResponseEntity<String> bookmarkOff(@PathVariable("post_no") String post_no, @SessionAttribute("userInfo") MemberVO member) {
		log.info("bookmark Off");
		return service.bookmarkOnOff(1, post_no, member.getMember_no())==1? new ResponseEntity<String>("success", HttpStatus.OK)
				: new ResponseEntity<String>("error", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping(value="/reply/{post_no}", produces="application/json; charset=utf-8")
	public ResponseEntity<ReplyVO> replyPost(@RequestBody ReplyVO vo, @PathVariable("post_no") String post_no, @SessionAttribute("userInfo") MemberVO member){
		log.info("Reply POST VO: "+vo);
		
		vo.setPost_no(post_no);
		vo.setMember_no(member.getMember_no());
		vo.setUserid(member.getUserid());
		
		service.replyInsert(vo);
		
		return new ResponseEntity<ReplyVO>(vo, HttpStatus.OK);
	}
	
	@PostMapping(value = "/replyLike/{reply_no}", produces = "application/json; charset=utf8")
	public ResponseEntity<String> replyLikeOn(@PathVariable("reply_no") String reply_no, @SessionAttribute("userInfo") MemberVO member) {
		log.info("replyLike On");
		return service.rplikeOnOff(0, reply_no, member.getMember_no()) == 1? new ResponseEntity<>("success", HttpStatus.OK) : new ResponseEntity<>("error", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@DeleteMapping(value = "/replyLike/{reply_no}", produces="application/json; charset=utf-8")
	public ResponseEntity<String> replyLikeOff(@PathVariable("reply_no") String reply_no,  @SessionAttribute("userInfo") MemberVO member) {
		log.info("replyLike Off");
		return service.rplikeOnOff(1, reply_no, member.getMember_no()) == 1? new ResponseEntity<>("success", HttpStatus.OK) : new ResponseEntity<>("error", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping(value = "/onFollow/{tg_no}", produces="applicaion/json; charset=utf-8")
	public ResponseEntity<String> followOn(@PathVariable("tg_no") String tg_no, @SessionAttribute("userInfo") MemberVO member){
		log.info("follow On");
		return service.followOnOff(0, tg_no, member.getMember_no()) == 1? new ResponseEntity<>("success", HttpStatus.OK) : new ResponseEntity<>("error", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@DeleteMapping(value="/offFollow/{tg_no}", produces="application/json; charset=utf-8")
	public ResponseEntity<String> followOff(@PathVariable("tg_no") String tg_no, @SessionAttribute("userInfo") MemberVO member){
		log.info("follow Off");
		return service.followOnOff(1, tg_no, member.getMember_no()) == 1? new ResponseEntity<>("success", HttpStatus.OK) : new ResponseEntity<>("error", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

}
