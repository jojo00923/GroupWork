package kr.or.ddit.web07_fileupload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import kr.or.ddit.file.FileUploadRequestWrapper;
import kr.or.ddit.file.PartWrapper;

//@WebServlet("/fileUpload")
//@MultipartConfig // - request안에 part를 채워서 넣어줌
public class FileUploadServlet extends HttpServlet{//mulpartconfig를 frontcontroller에게 전달
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		//1.일반 텍스트 데이터 꺼내기 - ex) Board_jsp에 넣을 데이터
		String[] param = req.getParameterValues("param");//같은이름의 파라미터가 여러개일때
		System.out.println(param[0]); //이제 쿼리스트링이 존재하지 않음. 파라미터 꺼내기 위해 파트를 파싱
		//그러나! 3.0에서는 multipart에서도 파라미터를 쓸 수 있도록  wrapper 지원이 됨 (인코딩해주기)
		
		//Part part1 = req.getPart("param"); //input태그의 name속성값으로 파트정보 꺼내오기
		//System.out.println(part1);
		
		String realPath = getServletContext().getRealPath("/12");//filesystempath잡기
		File saveFolder = new File(realPath);//웹리소스 형태로 저장위치잡고 (내부서버 tmp0\wtpwebapps\에서 저장여부확인가능)
		
		//현재 req가 wrapper인지확인
		if(req instanceof FileUploadRequestWrapper) {
			FileUploadRequestWrapper wrapper = (FileUploadRequestWrapper) req;
			List<PartWrapper> fileWrappers = wrapper.getPartWrappers("uploadFile");
			if(fileWrappers!=null && fileWrappers.size()>0) {
				//파일업로드가 될 수 있는 경우
				for(PartWrapper fileWrapper :fileWrappers) {
					fileWrapper.saveFile(saveFolder);
					System.out.println(fileWrapper.toString());
				}
			}
			
		}
		
		//2. 파일 업로드
		
		
		
		
		//1) 이진데이터 저장(middle tier) - 톰캣이 위치한 파일시스템에
		
		
		//2) 파일 메타데이터 처리 ex) db Attatch에 넣을 데이터
		
		
		
		
		
	}	
}
