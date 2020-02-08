package kr.or.ddit.file;

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


/**
 * 파일 업로드 여부를 확인(contentType헤더에서)하고, 
 * 업로드 되는 요청이라면 request를 requestWrapper로 변경
 *
 */

public class FileUploadCheckFilter implements Filter{
	private static Logger logger = 
			LoggerFactory.getLogger(FileUploadCheckFilter.class);
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("{}필터 객체 생성",getClass().getSimpleName());
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {//최종적으로 톰캣에게 예외던져짐
		logger.info("2시작",getClass().getSimpleName());
		HttpServletRequest req = (HttpServletRequest) request;
		String bodyMime = req.getContentType(); //바디의마임리턴
		boolean isUploaded = bodyMime!=null && bodyMime.startsWith("multipart/");
		HttpServletRequest passReq = req;//원본전달할때
		if(isUploaded) {
			passReq = new FileUploadRequestWrapper(req); //wrapper생성
		}
		chain.doFilter(passReq, response);
		logger.info("2끝",getClass().getSimpleName());
	}

	@Override
	public void destroy() {
		logger.info("{}필터 객체 소멸",getClass().getSimpleName());
	}

}
