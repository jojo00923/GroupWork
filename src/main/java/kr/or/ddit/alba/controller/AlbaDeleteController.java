package kr.or.ddit.alba.controller;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.alba.service.AlbaServiceImpl;
import kr.or.ddit.alba.service.IAlbaService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.vo.AlbaVO;

@CommandHandler
public class AlbaDeleteController {
	IAlbaService service = new AlbaServiceImpl();
	
	@URIMapping(value="/alba/albaDelete.do",method=HttpMethod.GET)
	public String delete(HttpServletRequest req, HttpServletResponse resp) {
		
		String al_id = req.getParameter("what");
		
		String goPage =null;
		String message = null;
		ServiceResult result = service.removeAlba(al_id);
				switch (result) {
				case OK:
					message = "삭제 완료";
					goPage = "redirect:/alba/albaList.do"; 
					break;
				
				default: // FAIL
					message = "서버 오류, 잠시뒤 다시 .";
					goPage = "redirect:/alba/albaView.do?who="+ al_id;
					break;
				}
			
		
		req.getSession().setAttribute("message", message);
		
		return goPage;
	
	
	}
}
