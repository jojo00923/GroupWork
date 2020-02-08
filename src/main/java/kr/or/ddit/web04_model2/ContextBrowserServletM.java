package kr.or.ddit.web04_model2;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

@WebServlet("/browse/contextBrowsing.do")
public class ContextBrowserServletM extends HttpServlet{
	
	ServletContext application;
	@Override
	public void init() throws ServletException {
		super.init();
		application = getServletContext();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1.요청분석
		String param = req.getParameter("path");
		
		if(StringUtils.isBlank(param)) {//null일 때
			param = "";
		}
		String path = application.getRealPath(param);
								//서버에 대한 정보를 갖고 있음
		//getRealpath - 현재 서버에 따라 경로가 바뀐다.
		//getRealpath - webapp까지의 경로(webstudy01)
		File folder = new File(path);
		
		if(!folder.exists()) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		
		File[] list = folder.listFiles();//하위파일들을 가져온다.
		
		req.setAttribute("list", list);
		req.setAttribute("param", param);
		//2.로직운영
		req.getRequestDispatcher("/WEB-INF/views/browse/browseViewM.jsp").forward(req, resp);
	}
}
