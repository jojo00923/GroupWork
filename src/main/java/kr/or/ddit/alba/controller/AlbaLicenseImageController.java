package kr.or.ddit.alba.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.alba.service.AlbaServiceImpl;
import kr.or.ddit.alba.service.IAlbaService;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.URIMapping;

@CommandHandler
public class AlbaLicenseImageController {
	
	IAlbaService service = new AlbaServiceImpl();
	
	@URIMapping("/alba/licenseImage.do")
	public String licenseImage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
		// DB에서 이미지 불러오기
		// json 파일로 내보내기 
		
		return null;
	}
	
}
