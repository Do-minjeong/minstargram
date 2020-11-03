package mj.project.common;

import java.util.ArrayList;
import java.util.List;

import mj.project.domain.TagVO;
import mj.project.mapper.MainMapper;

public class CommonFunction {
	
	public List<TagVO> tagSplit(String contents, MainMapper mapper) {
		System.out.println("contents : "+contents);
		int hashStart = 0, hashEnd = 0;
		int hashSpace = 0, hashEnter = 0;
		int maxLength = contents.length();
		String hashTag = "";
		List<TagVO> tag_list = new ArrayList<TagVO>();
		
		while(true) {
			hashStart = contents.indexOf("#", hashStart);
			if(hashStart == -1) break;
			
			hashSpace = contents.indexOf(" ", hashStart)==-1? maxLength: contents.indexOf(" ", hashStart);
			hashEnter = contents.indexOf("<br/>", hashStart)==-1? maxLength : contents.indexOf("<br/>", hashStart);
			hashEnd = Math.min(hashSpace, hashEnter);
			
			hashTag = contents.substring(hashStart, hashEnd);
			System.out.println(">>"+hashTag);
			TagVO tagvo = mapper.tagTest(hashTag);

			if(tagvo == null) {
				tagvo = new TagVO(hashTag);
				mapper.tagInsert(tagvo);
			}
			
			tag_list.add(tagvo);
			hashStart = hashEnd;
		}
		
		return tag_list;
	}
	
	public String addTagContents(String contents) {
		StringBuffer sb = new StringBuffer(contents);
		int hashStart = 0, hashEnd = 0;
		int hashSpace = 0, hashEnter = 0;
		String hashTag = "";
		String newString = "";
		while(true) {
			hashStart = sb.indexOf("#", hashStart);
			if(hashStart == -1) break;
			hashSpace = sb.indexOf(" ", hashStart)==-1? sb.length(): sb.indexOf(" ", hashStart);
			hashEnter = sb.indexOf("<br/>", hashStart)==-1? sb.length() : sb.indexOf("<br/>", hashStart);
			hashEnd = Math.min(hashSpace, hashEnter);
			
			hashTag = sb.substring(hashStart, hashEnd);
			newString = "<a href='/main/search?tag="+hashTag+"'>"+hashTag+"</a>";
			sb = sb.replace(hashStart, hashEnd, newString);
			hashStart = hashEnd+newString.length()-3;
		}
		System.out.println(">>"+sb);
		return sb.toString();
	}

}
