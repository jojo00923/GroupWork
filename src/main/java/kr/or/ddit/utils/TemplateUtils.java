package kr.or.ddit.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

public class TemplateUtils {
	public static StringBuffer readTemplate(String filePath) throws IOException {//절대경로를 받아온다.
		//static메서드는 getclass X 
		URL targetURL = TemplateUtils.class.getResource(filePath); 
		File targetFile = new File(targetURL.getFile()); 
		FileReader reader = new FileReader(targetFile);
		BufferedReader bufReader = new BufferedReader(reader); 
		StringBuffer html =	new StringBuffer(); 
		String tmp = null;
		while ((tmp = bufReader.readLine()) != null) { 
			html.append(tmp + "\n"); 
			
		}
		return html;
	}
}
