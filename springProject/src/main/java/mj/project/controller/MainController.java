package mj.project.controller;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fasterxml.jackson.core.JsonParser;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import mj.project.domain.AttachFileVO;
import mj.project.domain.MemberVO;
import mj.project.domain.PostVO;
import mj.project.domain.ProfileVO;
import mj.project.service.MainService;

@Controller
@Log4j
@RequestMapping("/main/")
@SessionAttributes("userInfo")
public class MainController implements ServletContextAware{

	@Setter(onMethod_ = @Autowired)
	private MainService service;
	
	private ServletContext servletContext;
	
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	
	@GetMapping("/mainHome")
	public String mainHome(@SessionAttribute("userInfo") MemberVO member , Authentication auth, Model model) throws Exception {
		log.info("/main/mainHome controller 접속!!");
		// 모든 게시글 가져오기
		//MemberVO member = (MemberVO) session.getAttribute("userInfo");
		log.info("Login USER : "+member);
		List<PostVO> mainPosts = service.readPosts(member.getMember_no());
		model.addAttribute("posts", mainPosts);
		return "/main/mainHome";
	}
	
	@GetMapping("/write")
	public void getWrite() { 
		log.info("MainController getWrite");
	}
	
	@PostMapping("/write")
	public void postWrite(@SessionAttribute("userInfo") MemberVO member, MultipartFile nfile, MultipartHttpServletRequest request) throws Exception {
		log.info("MainController postWrite");
		PostVO vo = new PostVO();
		String contents = request.getParameter("contents");
		vo.setContents(contents);
		
		vo.setMember_no(member.getMember_no());
		
		List<AttachFileVO> file_list = new ArrayList<AttachFileVO>();
		String uploadFolder = servletContext.getRealPath("/resources/uploadImage");
		String uploadFolderPath = getFolder();
		File uploadPath = new File(uploadFolder, uploadFolderPath);
		log.info("upload path: "+uploadPath);
		
		if(!uploadPath.exists()) uploadPath.mkdirs();
		
		Map<String, MultipartFile> fileMap = request.getFileMap();
		Iterator file_ir = fileMap.keySet().iterator();
		
		System.out.println("fileMap: "+fileMap);
		while(file_ir.hasNext()) {
			String key = (String) file_ir.next();
			MultipartFile multipartFile = fileMap.get(key);

			AttachFileVO attachDTO = new AttachFileVO();
			log.info("------------------------------------------------------");
			log.info("Upload File Size: " + multipartFile.getSize());
			log.info("Upload File Name: " + multipartFile.getOriginalFilename());
			
			String uploadFileName = multipartFile.getOriginalFilename();
			// IE의 경우 파일의 전체 경로가 전송되므로 파일이름만 남기는 코드
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\")+1);
			log.info("only file name: "+uploadFileName);
			
			UUID uuid = UUID.randomUUID();
			uploadFileName = uuid.toString()+"_"+uploadFileName;
			attachDTO.setFile_name(uploadFileName);
			attachDTO.setFile_size(multipartFile.getSize());
			attachDTO.setFile_url(uploadPath.toString());
			
			File saveFile = new File(uploadPath, uploadFileName);
			multipartFile.transferTo(saveFile);
			file_list.add(attachDTO);
		}
		vo.setAttachList(file_list);
		log.info(">>" + vo);
		service.postWrite(vo);
		
	}
	
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String str = sdf.format(date);
		
		return str.replace("-", File.separator);
	}
	
	@GetMapping("/profile")
	public void goProfile(String member_no, @SessionAttribute("userInfo") MemberVO member, Model model) {
		ProfileVO vo = service.readProfile(member_no, member.getMember_no());
		model.addAttribute("profile", vo);
	}
	
	@PostMapping(value = "/getPost", produces = "application/json; charset=utf-8")
	public ResponseEntity<PostVO> getPost(@RequestBody String params, @SessionAttribute("userInfo") MemberVO member) throws ParseException {
		log.info("getPost메소드 진입");
		
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObj = (JSONObject) jsonParser.parse(params);
		String post_no = String.valueOf(jsonObj.get("post_no"));
		
		PostVO postvo = service.getPost(post_no, member.getMember_no());
		return new ResponseEntity<PostVO>(postvo, HttpStatus.OK);
	}
	
	@PostMapping(value="/memberPosts", produces = "application/json; charset=utf-8")
	public ResponseEntity<List<PostVO>> memberPosts(@RequestBody String params) throws ParseException{
		log.info(" member Posts ");
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObj = (JSONObject) jsonParser.parse(params);
		String member_no = String.valueOf(jsonObj.get("tg_no"));
		
		List<PostVO> postList = service.memberPosts(member_no);
		
		return new ResponseEntity<List<PostVO>>(postList, HttpStatus.OK);
	}
	
	@PostMapping(value = "/bookmarkPosts", produces = "application/json; charset=utf-8")
	public ResponseEntity<List<PostVO>> bookmarkPosts(@RequestBody String params) throws ParseException{
		log.info("bookmarks Posts ");
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObj = (JSONObject) jsonParser.parse(params);
		String member_no = String.valueOf(jsonObj.get("tg_no"));
		
		List<PostVO> postList = service.bookmarkPosts(member_no);
		return new ResponseEntity<List<PostVO>>(postList, HttpStatus.OK);
	}
}






