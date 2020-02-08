package kr.or.ddit.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 쿠키 생성과 핸들링을 지원하는 유틸리티
 *
 */
public class CookieUtils {
	public static String encoding = "UTF-8";
	private Map<String, Cookie> cookieMap;
	private HttpServletRequest request;
	
	public CookieUtils(HttpServletRequest request) {
		super();
		this.request = request;
		Cookie[] cookies = request.getCookies();
		cookieMap = new LinkedHashMap<>();
		if(cookies!=null) {
			for(Cookie tmp : cookies) {
				cookieMap.put(tmp.getName(), tmp);
			}
		}
	}
	
	public Map<String, Cookie> getCookieMap() {
		return cookieMap;
	}
	
	/**
	 * @param name 검색할 쿠키명
	 * @return 검색된 쿠키, 존재하지 않으면, null반환.
	 */
	public Cookie getCookie(String name){
		return cookieMap.get(name);
	}
	
	/**
	 * @param name 검색할 쿠키명
	 * @return 검색된 쿠키의 값, 없으면, null반환, 있으면, UTF-8
	 * @throws IOException
	 */
	public String getCookieValue(String name) throws IOException{
		Cookie cookie = getCookie(name);
		String value = null;
		if(cookie!=null) {
			value = URLDecoder.decode(cookie.getValue(), encoding);
		}
		return value;
	}
	
	/**
	 * 쿠키 생성
	 * @param name 쿠키명
	 * @param value 쿠키값(기본 인코딩 UTF-8)
	 * @return 생성된 쿠키 객체
	 * @throws IOException
	 */
	public static Cookie createCookie(String name, String value) throws IOException {
		value = URLEncoder.encode(value, encoding);
		Cookie cookie = new Cookie(name, value);
		return cookie;
	}
	
	public static enum TextType{DOMAIN, PATH}
	
	/**
	 * 쿠키 생성
	 * @param name 쿠키명
	 * @param value 쿠키값(기본 인코딩 UTF-8)
	 * @param text 도메인이나 경로로 사용될 텍스트
	 * @param kind 텍스트 사용 속성을 결정할 기준
	 * @return 생성된 쿠키 객체
	 * @throws IOException
	 */
	public static Cookie createCookie(String name, String value, String text, TextType kind) throws IOException{
		Cookie cookie = createCookie(name, value);
		if(TextType.DOMAIN.equals(kind)) {
			cookie.setDomain(text);
		}else if(TextType.PATH.equals(kind)){
			cookie.setPath(text);
		}
		return cookie;
	}
	
	/**
	 * 쿠키 생성
	 * @param name 쿠키명
	 * @param value 쿠키값(기본 인코딩 UTF-8)
	 * @param domain 도메인
	 * @param path 경로
	 * @return 생성된 쿠키
	 * @throws IOException
	 */
	public static Cookie createCookie(String name, String value, 
			String domain, String path) throws IOException{
		Cookie cookie = createCookie(name, value);
		cookie.setDomain(domain);
		cookie.setPath(path);
		return cookie;
	}
	
	/**
	 * @param name 쿠키명
	 * @param value 쿠키값(기본 인코딩 UTF-8)
	 * @param maxAge 초단위 만료시간
	 * @return
	 * @throws IOException 
	 */
	public static Cookie createCookie(String name, String value, int maxAge) throws IOException{
		Cookie cookie = createCookie(name, value);
		cookie.setMaxAge(maxAge);
		return cookie;
	}
	
	/**
	 * 쿠키 생성
	 * @param name 쿠키명
	 * @param value 쿠키값(기본 인코딩 UTF-8)
	 * @param text 도메인이나 경로로 사용될 텍스트
	 * @param kind 텍스트 사용 속성을 결정할 기준
	 * @param maxAge
	 * @return 생성된 쿠키 객체
	 * @throws IOException
	 */
	public static Cookie createCookie(String name, String value, 
			String text, TextType kind, int maxAge) throws IOException{
		Cookie cookie = createCookie(name, value, text, kind);
		cookie.setMaxAge(maxAge);
		return cookie;
	}
	
	/**
	 * 쿠키 생성
	 * @param name 쿠키명
	 * @param value 쿠키값(기본 인코딩 UTF-8)
	 * @param domain 도메인
	 * @param path 경로
	 * @param maxAge
	 * @return 생성된 쿠키
	 * @throws IOException
	 */
	public static Cookie createCookie(String name, String value, 
			String domain, String path, int maxAge) throws IOException{
		Cookie cookie = createCookie(name, value, domain, path);
		cookie.setMaxAge(maxAge);
		return cookie;
	}
}











