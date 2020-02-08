package kr.or.ddit.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;

import org.junit.Test;

public class CookieUtilsTestCase {

	@Test
	public void test() throws IOException {
		Cookie cookie = CookieUtils.createCookie("testCookie", "한글");//junit이 예외를 가져감
		assertNotNull(cookie);
		String value = cookie.getValue();
		assertNotEquals("한글", value);
		assertEquals(URLEncoder.encode("한글", "UTF-8"), value);
		assertEquals("한글", URLDecoder.decode(value,"UTF-8"));
		//모든 assertion이 성공해야지만 test 성공
		
	}

}
