package kr.or.ddit.board.controller;


import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.file.FileUploadRequestWrapper;
import kr.or.ddit.file.PartWrapper;
import kr.or.ddit.hint.InsertHint;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.validator.GeneralValidator;
import kr.or.ddit.vo.AttatchVO;
import kr.or.ddit.vo.BoardVO;

@CommandHandler
public class BoardInsertController {
	IBoardService service = new BoardServiceImpl();
	
	@URIMapping("/board/boardInsert.do")
	public String insertForm(HttpServletRequest req, HttpServletResponse resp) {
		
		return "board/boardForm";
	}
	
	@URIMapping(value="/board/boardInsert.do", method=HttpMethod.POST)
	public String insert(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		BoardVO board = new BoardVO();
		try {
			BeanUtils.populate(board, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		
		//instance체크, partWrapper받아오기 list로. 메타,2진데이터 처리.  attatch테이블에 레코드가 생성(attatchVO partWrapper 하나당)
		if(req instanceof FileUploadRequestWrapper) {
			List<PartWrapper> bo_file = ((FileUploadRequestWrapper) req).getPartWrappers("bo_file");
			board.setBo_file(bo_file); //메타데이터는 자동으로 attatchList로 만들어짐
		}
		
		Map<String, List<CharSequence>> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		GeneralValidator validator = new GeneralValidator();
		boolean valid = validator.validate(board, errors, InsertHint.class);
		String goPage =null;
		String message = null;
		ServiceResult result = null;
		if (valid) {
//			2. 로직 선택 service.createprod(prod)
				result = service.createBoard(board);
//			3. 로직의 실행 결과에 따른 분기 페이지 선택
				switch (result) {
				case OK:
					goPage = "redirect:/board/boardView.do?what="+board.getBo_no(); 
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
