package kr.or.ddit.web07_fileupload.filter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 블라인드 대상자 정보 읽어오기 - properties파일로 대체
 * 게시판을 사용하는 클라이언트를 대상으로 한 처리.
 * 블라인드 된 사유가 있는 경우, /12/blind.jsp로 이동(redirect)
 * 클라이언트의 기본 언어로 메시지 처리.
 */
public class BlindFilter implements Filter{
	private static Logger logger = LoggerFactory.getLogger(BlindFilter.class);
	Map<String, String> blindMap;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		blindMap = new LinkedHashMap<>();
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//언어에 따른 요청을 읽어들이려면 initXX
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		ResourceBundle bundle = 
				ResourceBundle.getBundle("kr.or.ddit.web07_fileupload.filter.blind",Locale.ENGLISH); 
		
		//client의 IP
		String clientIP = request.getRemoteAddr();

		//IP로 블라인드 대상 여부 판단
		boolean blind = bundle.containsKey(clientIP);
		System.out.println(clientIP);
		if(blind) {
			//대상
			String reason = bundle.getString(clientIP);
			req.getSession().setAttribute("reason", reason);
			resp.sendRedirect(req.getContextPath()+"/12/blind.jsp");
		}else {
			//비대상
			chain.doFilter(request, response);
		}
		
		
	}

	@Override
	public void destroy() {
		logger.info("{}필터 객체 소멸",getClass().getSimpleName());
	}

}
