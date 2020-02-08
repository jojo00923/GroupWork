package kr.or.ddit.web07_fileupload.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@WebFilter("/*") //톰캣이 관리할 수 있도록 등록 및 매핑, 모든요청이 필터링 되게끔
public class Filter02 implements Filter{
	private static Logger logger = LoggerFactory.getLogger(Filter02.class);
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {//싱글톤
		logger.info("{}필터 객체 생성",getClass().getSimpleName());
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		logger.info("{}요청 필터링- Filter02", req.getRequestURI());
		chain.doFilter(request, response); //★다음필터로 넘어감. 다음필터가 없으면 front로 넘어간다.
		
		//메소드가 스택메모리로 관리되기 때문에 req,resp의 필터순서가 반대임. 
		logger.info("{}응답 필터링- Filter02", response.getBufferSize());
	}

	@Override
	public void destroy() {//한 번 미만 실행됨
		logger.info("{}필터 객체 소멸",getClass().getSimpleName());
	}

}
