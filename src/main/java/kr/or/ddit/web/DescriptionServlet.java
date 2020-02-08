package kr.or.ddit.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet ??
 * : 자바 기반의 확장 CGI방식으로 웹 요청을 처리할 수 있는 객체에 대한 명세(spec)
 * 1. 개발 단계
 * 1) HttpServlet을 상속받은 자바 객체 정의 -> 필요한 메소드 overriding
 * 2) 컴파일 -> /WEB-INF/classes(classpath)에 배포
 * 3) WAS(Servlet container, tomcat)에 서블릿 등록.
 * 		web.xml -> servlet -> servlet-name, servlet-class
 * 4) 웹상의 요청(command)에 의해 특정 서블릿이 동작할 수 있도록 mapping설정.
 * 		web.xml -> servlet-mapping -> servlet-name, url-pattern
 * 5) startup
 * 
 * 2.WAS(Servlet container)의 역할
 * 	: container 내부에 등록된 servlet의  lifecycle 관리.
 * 	1) 설정이 없는 한 등록된 서블릿의 객체를 최초의 요청이 들어왔을 때 생성
 *  2) web.xml에 서블릿을 등록할 때의 설정을 캡슐화해서 서블릿 하나당 servletConfig
 *     객체를 1:1로 관리함.
 * 
 * 3.Servlet의 메서드(callback) 종류
 *  1) lifecycle callback : 생명주기 내에서 단 한번(singleton)만 호출 
 *  					EX) init(생성직후), destroy(garbage되기 직전)
 *  2) request callback : mapping URI로 발생하는 요청에 대해 반복 호출
 *  					EX) service, do~계열 메서드. 
 *
 *
 */
public class DescriptionServlet extends HttpServlet{
	
	public DescriptionServlet() {
		super();
		System.out.println(getClass().getName()+"생성");
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		//라이프사이클은 반드시 super코드 살려둠
		super.init(config);
		System.out.println(getClass().getSimpleName()+"초기화");
		//String value = config.getInitParameter("testParam");
		Enumeration<String> names = config.getInitParameterNames();
		while (names.hasMoreElements()) {//enumeration
			String name = (String) names.nextElement();
			String value = config.getInitParameter(name);
			System.out.println(value);
		}
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//super코드 남겨놨을 때 405에러페이지가 출력된다.
		//url로 요청이 발생한 이벤트가 발생하면 톰캣이 자동으로 doget호출 , 즉 콜백메서드
		//리로딩되자 보자
		try(
			PrintWriter out = resp.getWriter();
		){
			out.println(getServletContext().hashCode());
		}
		
	}
	
	@Override
	public void destroy() {
		//이 경우의 super는 아무기능이없다.
		System.out.println(getClass().getSimpleName()+"소멸");
	}
	
	
}









