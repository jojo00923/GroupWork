package kr.or.ddit.web05_model2;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/contextbrowse.do")
public class ContextBrowsingServlet extends HttpServlet{
	ServletContext application;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	 	application = getServletContext();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
//		1. 요청분석
		String rootUrl = req.getParameter("browsingURL");
		if(StringUtils.isBlank(rootUrl)) {
			rootUrl = "";
		}
		String contextRootRP = application.getRealPath("");
		System.out.println(contextRootRP);
//		2. 로직 운영
//		String content = "컨텐츠";
//		req.setAttribute("contents", content);
		String folderRP = application.getRealPath(rootUrl);
		File folder = new File(folderRP);
		int status = 200;
		if(!folder.exists()) {
			status = HttpServletResponse.SC_NOT_FOUND;
		}else {
			if(folder.isFile()) {
				status = HttpServletResponse.SC_BAD_REQUEST;
			}
		}
		if(status==200) {
			File[] listFiles = folder.listFiles();
			// wrapper pattern , object adapter pattern
			FileWrapper[] listWrappers = new FileWrapper[listFiles.length];
			int idx = 0;
			for(File tmp : listFiles) {
				listWrappers[idx++] = new FileWrapper(tmp, application);
			}
			
			String accept = req.getHeader("Accept");
			if(StringUtils.containsIgnoreCase(accept, "json")) {//대소문자 구별x , accept에 json이 포함되어있으면 true반환
				//비동기
				resp.setContentType("application/json;charset=UTF-8");
				
				//자바빈규약을 바탕으로 마샬링,직렬화 한꺼번에
				ObjectMapper mapper= new ObjectMapper();//잭슨이용시
				mapper.writeValue(resp.getWriter(),listWrappers); //listWrappers를 JSON으로 변환
									//출력스트림
			}else {//동기 (맨처음에는 accept가 json이 아니어서 여기로 옴)
				req.setAttribute("listWrappers", listWrappers);
				String view = "/WEB-INF/views/browser/browseView.jsp";
				req.getRequestDispatcher(view).forward(req, resp);
			}
			
		}else {
			resp.sendError(status);
		}
	}
}















