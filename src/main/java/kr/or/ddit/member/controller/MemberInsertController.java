
package kr.or.ddit.member.controller;

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
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.file.FileUploadRequestWrapper;
import kr.or.ddit.file.PartWrapper;
import kr.or.ddit.hint.InsertHint;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.validator.GeneralValidator;
import kr.or.ddit.vo.MemberVO;
@CommandHandler
public class MemberInsertController {
	IMemberService service = MemberServiceImpl.getInstance();
	
	@URIMapping("/member/memberInsert.do")
	public String viewForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		return "member/memberForm";
	}

	@URIMapping(value="/member/memberInsert.do",method=HttpMethod.POST)
	public String insert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MemberVO member = new MemberVO(); //command object (명령에 대한 정보를 다 가지고 있음)
		req.setCharacterEncoding("UTF-8");
		req.setAttribute("member", member); 
		try {
			BeanUtils.populate(member, req.getParameterMap()); //name과 vo 변수 이름이 같아야함
		} catch (IllegalAccessException | InvocationTargetException e) {// checked
			throw new RuntimeException(e);
		}
		
		if(req instanceof FileUploadRequestWrapper) {//이미지 직접 db에 넣기위해 요청에서 이미지를 꺼내야함. Wrapper로 바이트배열 확보 후 mem_img에게 줌
			PartWrapper imageFile = ((FileUploadRequestWrapper) req).getPartWrapper("mem_image");
			member.setMem_image(imageFile);
		}
		
		Map<String, List<CharSequence>> errors = new HashMap<>(); //한번에 여러개의 메세지
		req.setAttribute("errors", errors); 
		GeneralValidator validator = new GeneralValidator();
		boolean valid = validator.validate(member, errors, InsertHint.class);
		String goPage = null;
		String message = null;
		HttpSession session = req.getSession();//null일리가 없다.
		if(valid) {//검증 통과
			//2. 로직 선택
			ServiceResult result = service.createMember(member);
			//3. 로직의 실행 결과에 따른 분기 페이지 선택
			switch (result) {
				case OK: // login redirect 이동 (굳이 데이터 가져갈 필요 없음)
					goPage = "redirect:/";
					break;
				case PKDUPLICATED: // 아이디,errors를 가지고 memberForm
					goPage = "/member/memberForm";
					message = "아이디 중복";
					break;
				case FAIL: // memberForm (서버의 잘못이라 데이터를 그대로 유지시켜야함)
					//6. 뷰설정
					goPage = "/member/memberForm";
					message = "서버 오류";
					break;
			}
		}else {//검증실패시(필수항목누락 등..) 그 정보(member,errors,message)를 다시 가지고 간다.
			goPage = "/member/memberForm";
		}
		
		//4.모델 데이터 공유
		req.setAttribute("message", message);//request에 저장
		
		//5.해당 뷰로 이동
		return goPage;

	}


	
}
