package kr.or.ddit.web07_fileupload.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class FakeFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
			//요청상태변경 --> wrapper
			HttpServletRequest req = (HttpServletRequest) request;
			SampleRequestWrapper wrapper = new SampleRequestWrapper(req);
			chain.doFilter(wrapper, response);
	}

	@Override
	public void destroy() {
		
	}

}
