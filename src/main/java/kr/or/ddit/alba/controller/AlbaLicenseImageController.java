package kr.or.ddit.alba.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.alba.service.AlbaServiceImpl;
import kr.or.ddit.alba.service.IAlbaService;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.vo.LicAlbaVO;
import oracle.sql.BLOB;

@CommandHandler
public class AlbaLicenseImageController {
	
	IAlbaService service = new AlbaServiceImpl();
	
	@URIMapping("/alba/licenseImage.do")
	public String licenseImage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException{
		
		LicAlbaVO licAlba = new LicAlbaVO();
		req.setAttribute("licAlba", licAlba);
		String accept = req.getHeader("Accept");
		
		String al_id = req.getParameter("who");
		String lic_code = req.getParameter("code");
		
		if(StringUtils.isBlank(al_id) || StringUtils.isBlank(lic_code)) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		
		licAlba.setAl_id(al_id);
		licAlba.setLic_code(lic_code);
		
		// DB에서 이미지 불러오기
//		byte[] lic_image = service.readImage(licAlba);
//		AlbaVO readImage = service.readImage(licAlba);
		Map<String, Object> readImage = service.readImage(licAlba);
		BLOB lic_image = (BLOB) readImage.get("LIC_IMAGE");
		int blobLength = (int) lic_image.length(); 
		byte[] blobAsBytes = lic_image.getBytes(1, blobLength);
		System.out.println(readImage.get("LIC_IMAGE"));
		System.out.println(lic_image);
		
		Set<String> keySet = readImage.keySet();
		for(String key : keySet) {
			System.out.println("key:" + key);
		}
		licAlba.setLic_image(blobAsBytes);
		System.out.println("lic_image" + lic_image);
		System.out.println("getLic_image" + licAlba.getLic_image());
		System.out.println("getImgBase64" + licAlba.getImgBase64());
		
		// json 파일로 내보내기
		if(StringUtils.containsIgnoreCase(accept, "json")) {
			resp.setContentType("application/json;charset=UTF-8");
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(resp.getWriter(), licAlba);
			return null;
		}else {
			String view = "alba/albaView";
			return view;
		}
	}
	
}
