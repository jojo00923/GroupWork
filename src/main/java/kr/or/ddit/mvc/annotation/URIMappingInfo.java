package kr.or.ddit.mvc.annotation;

import java.lang.reflect.Method;

/**
 * 어떤 요청과 그 요청을 처리할 수 있는 핸들러(객체와 메소드로 구성)에 대한 정보 캡슐화
 *
 */
public class URIMappingInfo {
	private URIMappingCondition mappingCondition; //어떤 요청인지 정보
	private Object commandHandler; //처리할 핸들러. 다양성 보장을 위해 object
	private Method handlerMethod;//메소드
	
	
	public URIMappingInfo(URIMappingCondition mappingCondition, Object commandHandler, Method handdlerMethod) {
		super();
		this.mappingCondition = mappingCondition;
		this.commandHandler = commandHandler;
		this.handlerMethod = handdlerMethod;
	}


	public URIMappingCondition getMappingCondition() {//immutable객체운영.(setter는 xx)
		return mappingCondition;
	}


	public Object getCommandHandler() {
		return commandHandler;
	}


	public Method getHandlerMethod() {
		return handlerMethod;
	}


	@Override
	public String toString() {
		return String.format("%s.%s",commandHandler.getClass().getName(),handlerMethod.getName()); 
											//핸들러객체,핸들러메소드 정보
	}
	
	
	
}
