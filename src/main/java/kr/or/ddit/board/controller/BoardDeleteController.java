package kr.or.ddit.board.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.naming.directory.SearchResult;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.vo.BoardVO;

@CommandHandler
public class BoardDeleteController {
	IBoardService service = new BoardServiceImpl();
	
	@URIMapping(value="/board/boardDelete.do",method=HttpMethod.POST)
	public String delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		BoardVO board = new BoardVO();
		try {
			BeanUtils.populate(board, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		
		String goPage =null;
		String message = null;
		ServiceResult result = service.removeBoard(board);
				switch (result) {
				case OK:
					message = "삭제 완료";
					goPage = "redirect:/board/boardList.do"; 
					break;
				case INVALIDPASSWORD: //인증은 대다수 redirection
					message = "비번오류";
					goPage = "redirect:/board/boardView.do?what="+board.getBo_no();
					break;
					
				default: // FAIL
					message = "서버 오류, 잠시뒤 다시 .";
					goPage = "redirect:/board/boardView.do?what="+board.getBo_no();
					break;
				}
			
		
		req.getSession().setAttribute("message", message);//request에 저장
		//session scope는 flash방식으로 삭제! -jsp에서
		
		return goPage;
	}
}
