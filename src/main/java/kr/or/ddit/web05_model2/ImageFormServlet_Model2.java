package kr.or.ddit.web05_model2;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.utils.CookieUtils;


@WebServlet(urlPatterns = "/model2/imageForm.do",
		initParams = {@WebInitParam(name = "imagePath",
						value = "/kr/or/ddit/test")})
public class ImageFormServlet_Model2 extends HttpServlet{
	File folder; //kr.or.ddit.test폴더(클래스패스리소스로 접근)  폴더로 접근하기위해서는 .대신 /
	
	ServletContext servletContext; //마임텍스트 꺼내오기
	
	//파라미터잡기
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config); //config - web.xml의 정보를 캡슐화한것
		String fsp = getClass().getResource(config.getInitParameter("imagePath")).getFile(); //파일시스템상의 절대경로 가져오기
		folder = new File(fsp);
		servletContext = getServletContext();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*
		 * Cookie[] cookies = req.getCookies(); String imageName = null;
		 * if(cookies!=null) { for(Cookie tmp:cookies) {
		 * if("imageCookie".equals(tmp.getName())) { imageName = tmp.getValue(); break;
		 * } } }
		 */
		
		String imageName = new CookieUtils(req).getCookieValue("imageCookie");
		
		
		//2.구멍 치환
		String[] imageNames = folder.list((dir, name)->{ 
				//name.endsWith(".jpg") || name.endsWith(".gif") 
				String mime = servletContext.getMimeType(name);
				return mime!=null && mime.startsWith("image/");
			});
		req.setAttribute("imageNames", imageNames);
		req.setAttribute("imageName", imageName);
		req.getRequestDispatcher("/WEB-INF/views/imageForm.jsp").include(req, resp);
	
	}
}
