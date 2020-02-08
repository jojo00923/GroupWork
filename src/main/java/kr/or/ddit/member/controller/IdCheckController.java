package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.UserNotFoundException;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.vo.MemberVO;

@CommandHandler
public class IdCheckController{
	IMemberService service=  MemberServiceImpl.getInstance();
	
	@URIMapping(value="/member/checkId.do",method = HttpMethod.POST)
	public String idCheck(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1.요청 정보 받기
		String id = req.getParameter("id");
		String message = req.getParameter("message");
		//2.검증
		if(StringUtils.isBlank(id)) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}
		//3.로직 실행 또는 dao이용(중복여부만 확인하기 때문에!)
		ServiceResult result = null;
		try {
			MemberVO savedMember = service.readMember(id);//null이면 exception이 발생하기 때문에 try- catch
			result = ServiceResult.PKDUPLICATED;
			message = "아이디 중복";
		}catch(UserNotFoundException e) {
			result = ServiceResult.OK; //property명으로 가져가야함
			message = "사용가능";
		}
		//마샬링(자바->json으로만들기)
		//원본객체를 맵으로 만들기
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("status",result.name());//enum은 이름과 똑같은 값을 가진다.
		resultMap.put("message",message);
		
		//마임설정(Accept헤더,Content-Type)
		resp.setContentType("application/json;charset=UTF-8");
		
		ObjectMapper mapper = new ObjectMapper(); //마샬링할 때 write, 언마샬링 read를 사용
		mapper.writeValue(resp.getWriter(), resultMap);//마샬링,직렬화 한번에
		
		return message;
	}
}
