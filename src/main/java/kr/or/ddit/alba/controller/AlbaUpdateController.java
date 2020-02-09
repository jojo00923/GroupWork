package kr.or.ddit.alba.controller;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.alba.dao.AlbaDAOImpl;
import kr.or.ddit.alba.dao.IAlbaDAO;
import kr.or.ddit.alba.service.AlbaServiceImpl;
import kr.or.ddit.alba.service.IAlbaService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.file.FileUploadRequestWrapper;
import kr.or.ddit.file.PartWrapper;
import kr.or.ddit.hint.UpdateHint;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.validator.GeneralValidator;
import kr.or.ddit.vo.AlbaVO;

@CommandHandler
public class AlbaUpdateController {
	IAlbaService service = new AlbaServiceImpl();
	IAlbaDAO dao = new AlbaDAOImpl();

	private void addAttribute(HttpServletRequest req) {

		req.setAttribute("gradeList", dao.selectGrade());
		req.setAttribute("licenseList", dao.selectLIC());
	}

	@URIMapping("/alba/albaUpdate.do")
	public String updateForm(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		addAttribute(req);
		String al_id = req.getParameter("what");

		if (StringUtils.isBlank(al_id) || StringUtils.isNumeric(al_id)) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "번호 이상함");
			return null;
		}

		AlbaVO alba = service.readAlba(al_id);
		req.setAttribute("alba", alba);

		return "alba/albaForm";

	}
	@URIMapping(value="/alba/albaUpdate.do",method = HttpMethod.POST)
	public String update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		addAttribute(req); //실패해서 돌아가면 거래처,카테고리 목록 유지하기 위해
		
		AlbaVO alba = new AlbaVO();
		req.setAttribute("alba", alba);
		try {
			BeanUtils.populate(alba, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		
		return null;
	}
}
