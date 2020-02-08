package kr.or.ddit.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.file.FileUploadRequestWrapper;
import kr.or.ddit.file.PartWrapper;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.utils.CookieUtils;
import kr.or.ddit.vo.BoardVO;

@CommandHandler
public class ImageUploadController {
	// /board/imageUpload.do, partname: upload
	// 2) 메타데이터 : fileName, url -> 마샬링해서 json으로 
	
	@URIMapping(value="/board/imageUpload.do" , method = HttpMethod.POST)
	public String upload(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		//파트데이터 꺼내기
		int uploaded = 0; //업로드된것
		String fileName = null;
		String url = null; //ckeditor가 필요로 함. img태그의 src에서 씀. clientside 절대경로 형태가 됨.
		String message = "이미지 업로드 실패"; //실패했을때 메세지
		if(req instanceof FileUploadRequestWrapper) {
			PartWrapper image = ((FileUploadRequestWrapper) req).getPartWrapper("upload");
			if(image!=null) {
				String saveFolderUrl = "/boardImages";
				String folderPath = req.getServletContext().getRealPath(saveFolderUrl); //진짜경로
				File saveFolder = new File(folderPath);
				
				if(!saveFolder.exists()) {
					saveFolder.mkdirs(); 
				}
				
				// 1) 2진데이터처리 : /boardImages에 저장 웹리소스  
				image.saveFile(saveFolder);
				uploaded = 1;
				fileName = image.getFilename(); //원본파일명가져옴
				url = req.getContextPath() + saveFolderUrl + "/" +  image.getSavename(); //내가만드는것
				message = null; //업로드여부판단가능
			}
			
			
			
		}
		
		Map<String, Object> resultMap = new HashMap<>(); 
		resultMap.put("uploaded",uploaded);
		resultMap.put("fileName",fileName);
		resultMap.put("url",url);
		
		if(message!=null) {
			resultMap.put("error.message",message);
		}
		
		//json으로 내보내기
		resp.setContentType("application/json;charset=UTF-8");
		ObjectMapper mapper = new ObjectMapper(); 
		mapper.writeValue(resp.getWriter(), resultMap);//맵대상으로 마샬링+직렬화 
		
		return null;
		
	}
}
