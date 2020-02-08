package kr.or.ddit.mvc.annotation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IHandlerInvoker {
	/**
	 * 해당 요청을 처리할 수 있는 핸들러 직접 호출
	 * @param mappingInfo
	 * @param req
	 * @param resp
	 * @return
	 */
	public String invokeHandler(URIMappingInfo mappingInfo, 
			HttpServletRequest req, HttpServletResponse resp); //메소드 호출 
}
