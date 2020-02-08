package kr.or.ddit.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.utils.CookieUtils;
import kr.or.ddit.utils.CookieUtils.TextType;

public class ClasspathImageServlet extends HttpServlet{

	File folder;//전역변수. 
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		//web.xml에 등록하기
		//파라미터꺼내서 폴더위치잡기
		String imagePath = config.getInitParameter("imagePath");//value가저장됨
		URL folderURL = ClasspathImageServlet.class.getResource(imagePath); //동적경로. 클래스패스.
		folder = new File(folderURL.getFile()); //파라미터에 넣을 절대경로가 필요
		System.out.println(folder.getAbsolutePath());
	}
	
	//스트리밍서비스 제공을 위한 서비스
	//이미지 읽고, 내보내기
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		//1.읽을 이미지에 대한 미디어 객체생성
		//2.미디어에 맞는 스트림 개방
		//3.이차스트림개방(option)
		String image = req.getParameter("image");
		if(image==null || image.trim().length()==0) {//image라는 파라미터가 안넘어온것
			//null,white space check
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		File imageFile = new File(folder,image); //parent,child   parent 객체 폴더의 child 라는 파일에 대한 File 객체를 생성한다.
		if(!imageFile.exists()) {
			//이미지가 없을때
			//응답상태코드 전송
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		
		FileInputStream fis = null;
		OutputStream os = null;
		
		String mime = getServletContext().getMimeType(image);//여러가지 확장자를 고려해서..
		if(mime==null || !mime.startsWith("image/")) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}
		resp.setContentType(mime); //마임텍스트(전송테이터의 타입을 결정)는 출력스트림 개방하기전에!
		
		//쿠키생성(생성은 여기,사용경로-pagemodulizationServlet)
		/*Cookie imageCookie = new Cookie("imageCookie",image);
		imageCookie.setPath(req.getContextPath());
		imageCookie.setMaxAge(60*60*24*3);
		resp.addCookie(imageCookie);*/
		
		Cookie imageCookie = CookieUtils.createCookie(
				"imageCookie", image, req.getContextPath(), TextType.PATH, 60*60*24*3);
		
		try {
		//문자가 아닌 이미지이기 때문에 reader대신 inputstream
		fis = new FileInputStream(imageFile);
		//내보낼 출력스트림이 필요 (반대쪽 클라이언트가 불특정다수임. 내가 생성하는게 아니라 받아옴)
		os = resp.getOutputStream(); //바이트로 처리해야하기때문에. return타입 servletoutputstream
		//반복횟수를 줄이기 위해 버퍼사용, 사이즈 결정
		byte[] buffer = new byte[1024]; //1kb
		int length = -1;
		
			//이제읽고쓰고반복 end of file을 만날때까지(= -1)
			while((length = fis.read(buffer)) != -1) { //한번읽어서 buffer에 넣음. 읽어들인 위치값이 유지되어야함. 길이값이 저장됨.
				//버퍼안의 내용을 기록
				os.write(buffer,0,length); 
				//읽어들인만큼만 내보내야함 0 - 앞부분부터 기록, length - 읽어들인만큼
			}
		}finally {
			if(fis!=null)
			fis.close();
			if(os!=null)
			os.close(); //close는 항상 되어야함 finally가 필요
		}
	}
	
}
