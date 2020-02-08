package kr.or.ddit.web05_model2;

import java.io.File;

import javax.servlet.ServletContext;

public class FileWrapper extends File{
	private String contextRootRP;

	public FileWrapper(File file, ServletContext application) {
		super(file.getAbsolutePath()); //file의 생성자
		this.contextRootRP = application.getRealPath("");
		contextRootRP = contextRootRP.substring(0, contextRootRP.length()-1);//마지막에 역슬래시있어서
	}
	
	public String getClassName() {
		return isDirectory()?"dir":"file";
	}
	
	public String getId() {
		String id = getAbsolutePath().substring(contextRootRP.length()); //length위치부터 끝까지 자르기 => \01
		id = id.replace(File.separatorChar, '/');// 역슬래시를 /로 변환
		
		return id;
	}
}















