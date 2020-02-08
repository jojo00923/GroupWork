package kr.or.ddit.member.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.file.FileUploadRequestWrapper;
import kr.or.ddit.file.PartWrapper;
import kr.or.ddit.hint.UpdateHint;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.validator.GeneralValidator;
import kr.or.ddit.vo.MemberVO;

@CommandHandler
public class MemberUpdateController {
	IMemberService service = MemberServiceImpl.getInstance();
	//1.요청받기
	
	@URIMapping(value="/member/memberUpdate.do", method=HttpMethod.POST)
	public String update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//2.요청분석
		MemberVO member = new MemberVO();
		req.setCharacterEncoding("UTF-8");
		req.setAttribute("member", member); //call by reference 검증 및 수정실패를 대비해서 입력했던 데이터를 저장해놓음
		// reflection으로 vo에 담아줌
		try {//beanutils라이브러리 넣음
			BeanUtils.populate(member, req.getParameterMap());
			// = member.setMem_id(req.getParameter("mem_id")); ......
		} catch (IllegalAccessException | InvocationTargetException e) {// checked
			throw new RuntimeException(e);
		}

		if(req instanceof FileUploadRequestWrapper) {//이미지 직접 db에 넣기위해 요청에서 이미지를 꺼내야함. Wrapper로 바이트배열 확보 후 mem_img에게 줌
			PartWrapper imageFile = ((FileUploadRequestWrapper) req).getPartWrapper("mem_image");
			member.setMem_image(imageFile);
		}
		
		Map<String, List<CharSequence>> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		//3. 검증
		GeneralValidator validator = new GeneralValidator();
		boolean valid = validator.validate(member, errors, UpdateHint.class);
		String goPage = null;
		String message = null;
		HttpSession session = req.getSession();//null일리가 없다.
		if(valid) {//검증 통과
			//4. service로직사용
			ServiceResult result = service.modifyMember(member);
			
			//5. scope로 공유
			switch (result) {// invalidpassword , ok, fail
			case OK: // mypage redirect 이동 (굳이 데이터 가져갈 필요 없음)
				goPage = "redirect:/mypage.do";
				break;
			case INVALIDPASSWORD: // mypage redirect(값을 가지고 갈 필요가 없음)
				goPage = "redirect:/mypage.do";
				message = "비밀번호 오류";
				session.setAttribute("message", message);//redirect니까 세션에 저장
				break;
			case FAIL: // mypage (서버의 잘못이라 데이터를 그대로 유지시켜야함)
				//6. 뷰설정
				goPage = "/member/mypage";
				message = "서버 오류";
				req.setAttribute("message", message);//request에 저장
				break;
			}
		}else {//검증실패시 그 정보를 다시 가지고 간다.
			goPage = "/member/mypage";
		}
		
		return goPage;

	}

}
