package kr.or.ddit.mvc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface IViewProcessor {
	/**
	 * view layer의 위치 공통성을 활용.
	 * @param prefix
	 */
	public void setPrefix(String prefix);
	
	/**
	 * view layer 파일의 확장자 공통성을 활용.
	 * @param suffix
	 */
	public void setSuffix(String suffix);
	
	/**
	 * 뷰네임을 이용해서 view layer로 이동하는 코드를 가짐.
	 * 뷰네임이 "redirect:"으로 시작하면 redirect로 이동.
	 * 
	 * @param viewName
	 * @param req
	 * @param resp
	 */
	public void viewProcess(String viewName, 
			HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException; 
}
