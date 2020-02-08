package kr.or.ddit.web07_fileupload.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CharacterEncodingFilter implements Filter{//모든필터보다 가장 앞에서 동작★
	private static Logger logger = LoggerFactory.getLogger(CharacterEncodingFilter.class);
	private String encoding;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		encoding = filterConfig.getInitParameter("encoding");
		if(encoding==null) encoding = "UTF-8";
		logger.info("{}필터 객체 생성",getClass().getSimpleName());	
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		logger.info("1번시작",getClass().getSimpleName());
		request.setCharacterEncoding(encoding);
		chain.doFilter(request, response);
		logger.info("1번끝",getClass().getSimpleName());
	}

	@Override
	public void destroy() {
		logger.info("{}필터 객체 소멸",getClass().getSimpleName());
	}

}
