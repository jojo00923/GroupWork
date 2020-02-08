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


/**
 * 필터 : Decoration Filter Pattern의 적용
 * 요청과 응답에 대한 전후처리를 담당하는 재사용 가능한 객체
 * 필터의 관리 기능을 WAS가 담당
 * 사용단계
 * 1. Filter의 구현체 정의
 * 	  1) lifecycle : init(FilterConfig-web.xml에 필터 등록시의 여러가지 옵션정보들), destroy 
 *    2) request filter : doFilter(req,resp,chain)
 *    				- 흐름을 제어하는 과정에서 다음 필터나 최종자원으로 제어를 이동시킬 때 chain.doFilter(req,resp,chain)사용
 * 2. 필터 등록
 * 	  1) web.xml에 등록 : filter, filter-mapping -> WAS는 등록된 필터로 FilterChain을 생성함.
 * 						-FilterChain내의 필터링 순서는 등록한 순서를 따라감.
 *    2) @WebFilter 이용 : 필터링 되는 순서가 모호함.
 * 3. 필터링할 요청과의 매핑 : ex) /*, *do   
 */
public class Filter01 implements Filter{
	private static Logger logger = LoggerFactory.getLogger(Filter01.class);
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {//싱글톤
		logger.info("{}필터 객체 생성",getClass().getSimpleName());
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		logger.info("{}요청 필터링 - Filter01", req.getRequestURI());
		chain.doFilter(request, response); //★다음필터로 넘어감. 다음필터가 없으면 front로 넘어간다.
		
		//메소드가 스택메모리로 관리되기 때문에 req,resp의 필터순서가 반대임. 
		logger.info("{}응답 필터링- Filter01", response.getBufferSize());
	}

	@Override
	public void destroy() {//한 번 미만 실행됨
		logger.info("{}필터 객체 소멸",getClass().getSimpleName());
	}

}
