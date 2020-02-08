package kr.or.ddit.web02;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.utils.TemplateUtils;

@WebServlet(urlPatterns = "/02/imageForm.do",
		initParams = {@WebInitParam(name = "imagePath",
						value = "/kr/or/ddit/test")})
public class ImageFormServlet extends HttpServlet{
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
		//1.템플릿 읽기
		StringBuffer html = 
				TemplateUtils.readTemplate("/kr/or/ddit/web02/imageForm.tmpl");//클래스패스리소스 src,res = classpath
		//2.구멍 치환
		String[] imageNames = folder.list((dir, name)->{ //마임텍스트로 이미지 확장자 구별
				//name.endsWith(".jpg") || name.endsWith(".gif") 
				String mime = servletContext.getMimeType(name);//현재파일명의 확장자 추출해서 마임텍스트 return
				return mime!=null && mime.startsWith("image/");

				/*folder.list(new FilenameFilter() {
			//한번 쓰고 버리는 익명 객체
			//1:1매칭구조(functional interface) - 익명객체를 람다식으로 바꿀수 있는 특징
			@Override
			public boolean accept(File dir, String name) {
				return false;
			}
		})*/
				
			});
		
		String ptrn = "<option value='%1$s'>%1$s</option>\n";
		StringBuffer option = new StringBuffer();
		for(String imageName : imageNames) {
			option.append(String.format(ptrn, imageName));
		}
		String contents = html.toString().replace("%option", option.toString());

		//3.응답 전송
		resp.setContentType("text/html;charset=UTF-8");
		try(//closable객체선언
				PrintWriter out = resp.getWriter();
		){
			out.println(contents);
		}
	}
}
