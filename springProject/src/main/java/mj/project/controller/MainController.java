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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import mj.project.domain.AttachFileDTO;
import mj.project.domain.MemberVO;
import mj.project.domain.PostVO;
import mj.project.service.MainService;

@Controller
@Log4j
@RequestMapping("/main/")
public class MainController implements ServletContextAware{
	
	@Setter(onMethod_ = @Autowired)
	private MainService service;
	
	private ServletContext servletContext;
	
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	
	@GetMapping("/mainHome")
	public String mainHome(HttpSession session, Authentication auth, Model model) {
		log.info("/main/mainHome controller 접속");
		if(auth==null && session.getAttribute("s_userInfo")==null) return "redirect:/customLogin?logout";
		// 모든 게시글 가져오기
		List<PostVO> mainPosts = service.readPosts();
		model.addAttribute("posts", mainPosts);
		return "/main/mainHome";
	}
	
	@GetMapping("/write")
	public void getWrite() { 
		log.info("MainController getWrite");
	}
	@PostMapping("/write")
	public void postWrite(HttpSession session, MultipartFile nfile, MultipartHttpServletRequest request) throws Exception {
		log.info("MainController postWrite");
		PostVO vo = new PostVO();
		vo.setContents(request.getParameter("contents"));
		
		MemberVO member = new MemberVO();
		if(session.getAttribute("g_userInfo")!=null) member = (MemberVO) session.getAttribute("g_userInfo");
		else if(session.getAttribute("s_userInfo")!=null)member = (MemberVO) session.getAttribute("s_userInfo");
		else throw new Exception();
		vo.setMember_no(member.getMember_no());
		
		List<AttachFileDTO> file_list = new ArrayList<AttachFileDTO>();
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

			AttachFileDTO attachDTO = new AttachFileDTO();
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
		service.writePost(vo);
		
	}
	
	
/*	
	@PostMapping("/write")
	public String postWrite(HttpSession session, MultipartFile[] mpfiles, MultipartHttpServletRequest request) throws Exception {
		log.info("MainController postWrite");
		UploadVO vo = new UploadVO();
		vo.setContents(request.getParameter("contents"));
		
		MemberVO member = new MemberVO();
		if(session.getAttribute("g_userInfo")!=null) member = (MemberVO) session.getAttribute("g_userInfo");
		else if(session.getAttribute("s_userInfo")!=null)member = (MemberVO) session.getAttribute("s_userInfo");
		else throw new Exception();
		vo.setMember_no(member.getMember_no());
		
		List<AttachFileDTO> file_list = new ArrayList<AttachFileDTO>();
		String uploadFolder = "C:\\spring_project\\minstgram_uploadFile";
		String uploadFolderPath = getFolder();
		File uploadPath = new File(uploadFolder, uploadFolderPath);
		log.info("upload path: "+uploadPath);
		
		if(!uploadPath.exists()) uploadPath.mkdirs();
		
		for (MultipartFile multipartFile : mpfiles) {
			AttachFileDTO attachDTO = new AttachFileDTO();
			log.info("------------------------------------------------------");
			log.info("Upload File Size: " + multipartFile.getSize());
			log.info("Upload File Name: " + multipartFile.getOriginalFilename());
			
			String uploadFileName = multipartFile.getOriginalFilename();
			// IE의 경우 파일의 전체 경로가 전송되므로 파일이름만 남기는 코드
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\")+1);
			log.info("only file name: "+uploadFileName);
			attachDTO.setFileName(uploadFileName);
			
			UUID uuid = UUID.randomUUID();
			uploadFileName = uuid.toString()+"_"+uploadFileName;
			
			try {
				File saveFile = new File(uploadPath, uploadFileName);
				multipartFile.transferTo(saveFile);
				attachDTO.setUuid(uuid.toString());
				attachDTO.setUploadPath(uploadFolderPath);
				
				// 섬네일 생성
				log.info("Thumnail 생성");
				FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath, "s_"+uploadFileName));
				Thumbnailator.createThumbnail(multipartFile.getInputStream(), thumbnail, 100, 100);
				
				thumbnail.close();
				
				file_list.add(attachDTO);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		vo.setAttachDTO(file_list);
		System.out.println(">> "+vo);
		//if(service.writePost(vo)) return "mainHome";
		
		return "write";
	}
*/
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String str = sdf.format(date);
		
		return str.replace("-", File.separator);
	}


}






