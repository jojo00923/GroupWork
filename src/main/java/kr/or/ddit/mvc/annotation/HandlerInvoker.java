package kr.or.ddit.mvc.annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HandlerInvoker implements IHandlerInvoker {

	@Override
	public String invokeHandler(URIMappingInfo mappingInfo, HttpServletRequest req, HttpServletResponse resp) {
		//핸들러 정보 꺼내기
		Object handler = mappingInfo.getCommandHandler(); 
		//메소드 호출
		Method handlerMethod = mappingInfo.getHandlerMethod();
		try {
			String viewName = (String) handlerMethod.invoke(handler, req, resp);//실행
			return viewName;
		} catch (Exception e) {//모든예외를 잡음
			throw new RuntimeException(e);//frontcontroller-->server순으로 예외가 넘겨짐
		}
	}

}
