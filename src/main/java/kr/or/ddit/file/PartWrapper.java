package kr.or.ddit.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import javax.servlet.http.Part;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString(exclude = "part")
@Getter
@Setter
public class PartWrapper {
	
	public PartWrapper(Part part){//생성자
		this.part = part;
		//file.exe 
		filename = getOriginalName(part);
		//다른사람과 겹치지 않는 새로운 이름을 만들기위해
		savename= UUID.randomUUID().toString();//★확장자 없이
		filesize = part.getSize();//현재파일의 사이즈
		mime = part.getContentType(); //현재파일의 마임
		fancySize = FileUtils.byteCountToDisplaySize(filesize);
	}

	private Part part;
	private String filename;
	private String savename;
	private long filesize;
	private String mime;
	private String fancySize;
	
	public byte[] getBytes() throws IOException {
		try(
			InputStream inputStream = part.getInputStream();
		){
			return IOUtils.toByteArray(inputStream);
		}
		
	}
	
	public void saveFile(File saveFolder) throws IOException{//파라미터 - 저장위치
		//저장명 = savename
		File saveFile = new File(saveFolder,savename); //저장하기
		try(
				InputStream input = part.getInputStream();
				// outputStream생성
				FileOutputStream output = new FileOutputStream(saveFile);
		){
			IOUtils.copy(input,output);
		}
		
	}
	
	public void delete() throws IOException {
		part.delete(); //파일이저장된 영역을 비워줌
	}
	
	private String getOriginalName(Part part) {
		String disposition = part.getHeader("Content-Disposition");
		int fromIndex = disposition.indexOf("filename=");
		int qtFirst = disposition.indexOf('"', fromIndex);
		int qtLast = disposition.indexOf('"', qtFirst+1); //여기까지는 원본파일명 잡기
		return disposition.substring(qtFirst+1, qtLast);
	}
}
