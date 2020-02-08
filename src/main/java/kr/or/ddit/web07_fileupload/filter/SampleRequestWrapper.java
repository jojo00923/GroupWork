package kr.or.ddit.web07_fileupload.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class SampleRequestWrapper extends HttpServletRequestWrapper{
	//HttpServletRequestWrapper의 상위 request
	
	public SampleRequestWrapper(HttpServletRequest request) {
		super(request);
	}
	
	@Override
	public String getParameter(String name) {
		if("what".equals(name)) {
			return "1";
		}
		return super.getParameter(name);
	}
	
}
