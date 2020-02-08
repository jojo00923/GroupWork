package kr.or.ddit.prod.controller;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
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
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.prod.dao.IOthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.ProdVO;

@CommandHandler
public class ProdInsertController{
	IOthersDAO othersDAO = new OthersDAOImpl(); 
	IProdService service = new ProdServiceImpl();
	
	private void addAttribute(HttpServletRequest req){
		//분류코드리스트,거래처리스트 가져오기
		
		req.setAttribute("lprodList", othersDAO.selectLprodList());
		req.setAttribute("buyerList", othersDAO.selectBuyerList());
	}
	
	@URIMapping("/prod/prodInsert.do")
	public String insertForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		addAttribute(req);
		String view = "prod/prodForm";
		return view;
	}
	
	@URIMapping(value="/prod/prodInsert.do",method=HttpMethod.POST)
	public String insert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		addAttribute(req); //실패해서 돌아가면 거래처,카테고리 목록 유지하기 위해
		
		ProdVO prod = new ProdVO();
		req.setAttribute("prod", prod);
		try {
			BeanUtils.populate(prod, req.getParameterMap());//prod_lgu와 prod_buyer를 pagingVO에 담아서 보낸다.
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		
		//이미지를 middle-tier에(/prodImages)저장 후 경로생김. 그걸 prod_img에 줘야함
		String saveFolderUrl = "/prodImages";
		String realPath = req.getServletContext().getRealPath(saveFolderUrl); //진짜경로
		File saveFolder = new File(realPath);
		if(!saveFolder.exists()) {//prodImages폴더가없다면
			saveFolder.mkdirs(); //계층구조상의 모든경로를 다 뒤져서 만듬
		}
		if(req instanceof FileUploadRequestWrapper) {//업로드가 됨
			PartWrapper imageFile = ((FileUploadRequestWrapper) req).getPartWrapper("prod_image");
			//prod_image 클라이언트로부터 받기위해,  prod_img db에 넣기위한 이름
			if(imageFile!=null && StringUtils.contains(imageFile.getMime(), "image")) {
				//이미지인지 마임으로 체크
				
				imageFile.saveFile(saveFolder);
				prod.setProd_img(imageFile.getSavename());
			}
		}
		
		
		
		
		Map<String, String> errors = new HashMap<String, String>();
		req.setAttribute("errors", errors); 
		boolean valid = validate(prod, errors);
		String goPage =null;
		String message = null;
		ServiceResult result = null;
		if (valid) {
//			2. 로직 선택 service.createprod(prod)
					result = service.createProd(prod);
//			3. 로직의 실행 결과에 따른 분기 페이지 선택
				switch (result) {
				case OK:
					goPage = "redirect:/prod/prodView.do?what="+prod.getProd_id(); //id가 채워져있다.
					break;
				default: // FAIL
					message = "서버 오류, 잠시뒤 다시 .";
					goPage = "prod/prodForm";
					break;
				}
			} else {
				goPage = "prod/prodForm";
			}
		
		req.setAttribute("message", message);//request에 저장
		
		return goPage;
		
		
	}
	
	private boolean validate(ProdVO prod, Map<String, String> errors) {
		boolean valid = true;
		if (StringUtils.isBlank(prod.getProd_name())) {valid = false;errors.put("prod_name", "상품명 누락");}
		if (StringUtils.isBlank(prod.getProd_lgu())) {valid = false;errors.put("prod_lgu", "상품분류코드 누락");}
		if (StringUtils.isBlank(prod.getProd_buyer())) {valid = false;errors.put("prod_buyer", "거래처코드 누락");}
		if (prod.getProd_cost()==null || prod.getProd_cost()<=0) {valid = false;errors.put("prod_cost", "구매가 누락");}
		if (prod.getProd_price()==null || prod.getProd_price()<=0) {valid = false;errors.put("prod_price", "판매가 누락");}
		if (prod.getProd_sale()==null || prod.getProd_sale()<=0) {valid = false;errors.put("prod_sale", "세일가 누락");}
		if (StringUtils.isBlank(prod.getProd_outline())) {valid = false;errors.put("prod_outline", "간략정보 누락");}
		if (StringUtils.isBlank(prod.getProd_detail())) {valid = false;errors.put("prod_detail", "상세정보 누락");}
		if (StringUtils.isBlank(prod.getProd_img())) {valid = false;errors.put("prod_img", "상품이미지경로 누락");}
		if (prod.getProd_totalstock()==null || prod.getProd_totalstock()<=0) {valid = false;errors.put("prod_totalstock", "상품재고 누락");}
		if (StringUtils.isBlank(prod.getProd_insdate())) {valid = false;errors.put("prod_insdate", "상품입고일 누락");}
		if (prod.getProd_properstock()==null || prod.getProd_properstock()<=0) {valid = false;errors.put("prod_properstock", "상품적정재고 누락");}
		if (StringUtils.isBlank(prod.getProd_size())) {valid = false;errors.put("prod_size", "상품크기 누락");}
		if (StringUtils.isBlank(prod.getProd_color())) {valid = false;errors.put("prod_color", "상품색상 누락");}
		if (StringUtils.isBlank(prod.getProd_delivery())) {valid = false;errors.put("prod_delivery", "배송방법 누락");}
		if (StringUtils.isBlank(prod.getProd_unit())) {valid = false;errors.put("prod_unit", "상품단위 누락");}
		if (prod.getProd_qtyin()==null || prod.getProd_qtyin()<=0) {valid = false;errors.put("prod_qtyin", "상품입고량 누락");}
		if (prod.getProd_qtysale()==null || prod.getProd_qtysale()<=0) {valid = false;errors.put("prod_qtysale", "상품판매량 누락");}
		if (prod.getProd_mileage()==null || prod.getProd_mileage()<=0) {valid = false;errors.put("prod_mileage", "상품마일리지 누락");}

		return valid;
	}
}
