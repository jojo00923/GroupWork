package kr.or.ddit.mvc.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.utils.ReflectionUtils;

/**
 * 1. 특정 패키지 내의 모든 핸들러에 대한 정보를 수집하고 map으로 관리.
 * 		parseBasePackage
 * 2. handlerMap을 바탕으로 특정 요청을 처리할 수 있는 핸들러 검색.
 * 		findCommandHandler
 */
public class HandlerMapper implements IHandlerMapper {
	private static Logger logger = LoggerFactory.getLogger(HandlerMapper.class); 
	
	private Map<URIMappingCondition, URIMappingInfo> handlerMap;
	
	public HandlerMapper(String...basePackages){//베이스패키지 아래의 클래스들 트래이싱
		handlerMap = new LinkedHashMap<>();
		if(basePackages==null || basePackages.length==0) return;
		parseBasePackage(basePackages);
	}
	
	private void parseBasePackage(String...basePackages) {
		Map<Class<?>, Annotation> resultMap = 
				ReflectionUtils.getClassesWithAnnotationAtBasePackages(CommandHandler.class, basePackages);
		//파싱
		for(Entry<Class<?>,Annotation> entry :resultMap.entrySet()) {
			//클래스까지 찾음. 이제 메서드 찾기
				Class<?> handlerType = entry.getKey(); 
			Map<Method, Annotation>	methodMap =
				ReflectionUtils.getMethodsWithAnnotationAtClass
					(handlerType, URIMapping.class, String.class, HttpServletRequest.class, HttpServletResponse.class);
			if(methodMap.size()==0) continue;
			Object commandHandler;//어떤 객체가 생성될지 몰라서 object
			try {
				commandHandler = handlerType.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				//기본생성자가 없을 때
				throw new RuntimeException(e);
			}
			
			
			for(Entry<Method, Annotation> mtdentry:methodMap.entrySet()) {
				Method handlerMethod = mtdentry.getKey();
				URIMapping mtdAnnotation = (URIMapping) mtdentry.getValue();
				String uri = mtdAnnotation.value();
				HttpMethod httpMethod = mtdAnnotation.method();
				URIMappingCondition mappingCondition = new URIMappingCondition(uri, httpMethod);
				URIMappingInfo mappingInfo = new URIMappingInfo(mappingCondition, commandHandler, handlerMethod);
				
				handlerMap.put(mappingCondition,mappingInfo);
				logger.info("{} : {}",mappingInfo,mappingCondition); //각 객체의 toString이 출력
			}
		
		}//for문의 끝
	}

	@Override
	public URIMappingInfo findCommandHandler(HttpServletRequest req) {
		String uri = getRelativeContextPath(req);
		HttpMethod method = HttpMethod.valueOf(req.getMethod().toUpperCase());//문자열에 해당하는 상수 리턴
		URIMappingCondition key = new URIMappingCondition(uri, method);
		return handlerMap.get(key);
	}

	private String getRelativeContextPath(HttpServletRequest req) {
		int cPLength = req.getContextPath().length();
		String uri = req.getRequestURI().split(";")[0];
		uri = uri.substring(cPLength);
		return uri;
	}

}
