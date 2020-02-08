package kr.or.ddit.mvc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 컨트롤러 클래스의 특정 요청을 처리할 수 있는 핸들러 메소드에 사용
 * 특정 요청 매핑방법(주소, http method)
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface URIMapping {
	String value(); //required 
	HttpMethod method() default HttpMethod.GET;
}
