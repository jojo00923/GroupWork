package kr.or.ddit.alba.controller;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.alba.dao.AlbaDAOImpl;
import kr.or.ddit.alba.dao.IAlbaDAO;
import kr.or.ddit.alba.dao.LicAlbaDAOImpl;
import kr.or.ddit.alba.service.AlbaServiceImpl;
import kr.or.ddit.alba.service.IAlbaService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.file.FileUploadRequestWrapper;
import kr.or.ddit.file.PartWrapper;
import kr.or.ddit.hint.InsertHint;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.validator.GeneralValidator;
import kr.or.ddit.vo.AlbaVO;
import kr.or.ddit.vo.LicAlbaVO;

@CommandHandler
public class AlbaInsertController {
	
	IAlbaService service = new AlbaServiceImpl();
	IAlbaDAO dao = new AlbaDAOImpl();
	
	private void addAttribute(HttpServletRequest req){
		
		req.setAttribute("gradeList", dao.selectGrade());
		req.setAttribute("licenseList", dao.selectLIC());
	}
	
	@URIMapping("/alba/albaInsert.do")
	public String insertForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		addAttribute(req);
		return "alba/albaForm";
	}
	
	@URIMapping(value = "/alba/albaInsert.do", method = HttpMethod.POST)
	public String insert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
		req.setCharacterEncoding("UTF-8");
		AlbaVO alba = new AlbaVO();
		req.setAttribute("alba", alba);
		
		try {
			BeanUtils.populate(alba, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		
		
		String saveFolderUrl = "/albaImages";
		String realPath = req.getServletContext().getRealPath(saveFolderUrl);
		File saveFolder = new File(realPath);
		if(!saveFolder.exists()) saveFolder.mkdirs();
		if(req instanceof FileUploadRequestWrapper) {
			List<PartWrapper> imageFiles = ((FileUploadRequestWrapper) req).getPartWrappers("lic_image");
			for(PartWrapper imageFile : imageFiles) {
				if(imageFile!=null && StringUtils.contains(imageFile.getMime(), "image")) {
					imageFile.saveFile(saveFolder);
					alba.getLicAlba().setLic_image(imageFile.getSavename());
				}
			}
		}
		
		
		Map<String, List<CharSequence>> errors = new HashMap<>(); //한번에 여러개의 메세지
		req.setAttribute("errors", errors); 
		GeneralValidator validator = new GeneralValidator();
		boolean valid = validator.validate(alba, errors, InsertHint.class);
		String goPage = null;
		String message = null;
		HttpSession session = req.getSession();
		if(valid) {
			ServiceResult result = service.createAlba(alba);
			switch (result) {
				case OK:
					goPage = "redirect:/alba/albaView.do?who="+alba.getAl_id();
					break;
				case PKDUPLICATED:
					goPage = "/alba/albaForm";
					message = "아이디 중복";
					break;
				case FAIL:
					goPage = "/alba/albaForm";
					message = "서버 오류";
					break;
			}
		}else {
			goPage = "/alba/albaForm";
		}
		req.setAttribute("message", message);//request에 저장
		return goPage;
	}
}








