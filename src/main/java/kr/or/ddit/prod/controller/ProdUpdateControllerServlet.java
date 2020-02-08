package kr.or.ddit.prod.controller;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.file.FileUploadRequestWrapper;
import kr.or.ddit.file.PartWrapper;
import kr.or.ddit.hint.UpdateHint;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.prod.dao.IOthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.validator.GeneralValidator;
import kr.or.ddit.vo.ProdVO;

//@WebServlet("/prod/prodUpdate.do")
@CommandHandler
public class ProdUpdateControllerServlet extends HttpServlet{
	IProdService service = new ProdServiceImpl();
	IOthersDAO othersDAO = new OthersDAOImpl(); 
	
	private void addAttribute(HttpServletRequest req){
		req.setAttribute("lprodList", othersDAO.selectLprodList());
		req.setAttribute("buyerList", othersDAO.selectBuyerList());
	}
	@URIMapping("/prod/prodUpdate.do")
	public String updateForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		addAttribute(req);
		String prod_id = req.getParameter("what");
		if(StringUtils.isBlank(prod_id)) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		
		ProdVO prod = new ProdVO();
		prod = service.readProd(prod_id);
		req.setAttribute("prod", prod);
		
		return "prod/prodForm";
	}
	
	@URIMapping(value="/prod/prodUpdate.do",method = HttpMethod.POST)
	public String update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		addAttribute(req); //실패해서 돌아가면 거래처,카테고리 목록 유지하기 위해
		
		ProdVO prod = new ProdVO();
		req.setAttribute("prod", prod);
		try {
			BeanUtils.populate(prod, req.getParameterMap());//prod_lgu와 prod_buyer를 pagingVO에 담아서 보낸다.
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		
		String saveFolderUrl = "/prodImages";
		String realPath = req.getServletContext().getRealPath(saveFolderUrl); //진짜경로(내부서버)
		
		File saveFolder = new File(realPath);
		if(!saveFolder.exists()) {//prodImages폴더가없다면
			saveFolder.mkdirs(); //계층구조상의 모든경로를 다 뒤진다.
		}
		if(req instanceof FileUploadRequestWrapper) {//업로드가 됨
			PartWrapper imageFile = ((FileUploadRequestWrapper) req).getPartWrapper("prod_image");
			//prod_image 클라이언트로부터 받기위해,  prod_img db에 넣기위한 이름
			if(imageFile!=null && StringUtils.contains(imageFile.getMime(), "image")) {//이미지인지 마임으로 체크
				imageFile.saveFile(saveFolder);
				prod.setProd_img(imageFile.getSavename()); //prod_img는 등록시에만 검증!(수정하고싶지않은 client도 있기때문에) group hint이용
			}
		}
		
		Map<String, List<CharSequence>> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		GeneralValidator validator = new GeneralValidator();
		boolean valid = validator.validate(prod, errors, UpdateHint.class);
		String goPage = null;
		String message = null;
		if (valid) {
//		2. 로직 선택 service.modifyProd(prod)
			ServiceResult result = service.modifyProd(prod);
//		3. 로직의 실행 결과에 따른 분기 페이지 선택
			switch (result) {
			case OK:
				goPage = "redirect:/prod/prodView.do?what="+prod.getProd_id();
				break;
			default: // FAIL
				message = "서버 오류, 잠시뒤 다시 .";
				goPage = "prod/prodForm";
				break;
			}
		} else {
			goPage = "prod/prodForm";
		}
		req.setAttribute("message", message);
//		4. 모델 데이터 공유
		return goPage;
	}
}
