package kr.or.ddit.board.controller;

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

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.file.FileUploadRequestWrapper;
import kr.or.ddit.file.PartWrapper;
import kr.or.ddit.hint.InsertHint;
import kr.or.ddit.hint.UpdateHint;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.validator.GeneralValidator;
import kr.or.ddit.vo.AttatchVO;
import kr.or.ddit.vo.BoardVO;

@CommandHandler
public class BoardUpdateController {
	IBoardService service = new BoardServiceImpl();
	
	@URIMapping("/board/boardUpdate.do")
	public String getForm(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String bo_noStr = req.getParameter("what");
		if(!StringUtils.isNumeric(bo_noStr)) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST,"글 번호 이상함");
			return null;
		}
		int bo_no = Integer.parseInt(bo_noStr);
		
		BoardVO board = service.readBoard(bo_no);
		req.setAttribute("board", board);
		
		return "board/boardForm";
	}
	
	@URIMapping(value="/board/boardUpdate.do",method=HttpMethod.POST)
	public String update(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {
		
		BoardVO board = new BoardVO();
		req.setAttribute("board", board);
		try {
			BeanUtils.populate(board, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	
		//instance체크, partWrapper받아오기 list로. 메타,2진데이터 처리.  attatch테이블에 레코드가 생성(attatchVO partWrapper 하나당)
		if(req instanceof FileUploadRequestWrapper) {
			List<PartWrapper> bo_file = ((FileUploadRequestWrapper) req).getPartWrappers("bo_file");
			board.setBo_file(bo_file);
		}
		
		Map<String, List<CharSequence>> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		GeneralValidator validator = new GeneralValidator();
		boolean valid = validator.validate(board, errors, UpdateHint.class);
		String goPage =null;
		String message = null;
		ServiceResult result = null;
		if (valid) {
					result = service.modifyBoard(board);
				switch (result) {
				case OK:
					goPage = "redirect:/board/boardView.do?what="+board.getBo_no(); 
					break;
				case INVALIDPASSWORD:
					message = "비번오류";
					goPage = "/board/boardForm"; 
					break;
					
				default: // FAIL
					message = "서버 오류, 잠시뒤 다시 .";
					goPage = "/board/boardForm";
					break;
				}
			} else {
				goPage = "/board/boardForm";
			}
		
		req.setAttribute("message", message);//request에 저장
		
		return goPage;
	}
	
	
}
