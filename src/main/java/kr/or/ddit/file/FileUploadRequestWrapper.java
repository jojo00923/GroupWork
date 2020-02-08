package kr.or.ddit.file;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.Part;

import org.apache.commons.lang3.StringUtils;

public class FileUploadRequestWrapper extends HttpServletRequestWrapper {
	private Map<String , List<PartWrapper>> partWrapperMap; //하나의 이름으로 여러개의 p.w를 잡을 수 있게 
	public FileUploadRequestWrapper(HttpServletRequest request) throws IOException, ServletException {
		super(request);
		partWrapperMap = new LinkedHashMap<>();
		//현재 원본요청에 포함된 모든 part꺼내기. 갯수만큼 wrapper생성 후 map에 저장
		parseRequest(request);
	}
	
	private void parseRequest(HttpServletRequest request) throws IOException, ServletException {
		Collection<Part> parts = request.getParts();
		
		Iterator<Part> it = parts.iterator();
		while (it.hasNext()) {
			Part part = (Part) it.next();
			String contentType = part.getContentType();
			if(contentType==null) {//파일이 아님. 문자파트임
				continue;
			}
			
			String partName = part.getName(); //키로잡음
		
			PartWrapper wrapper = new PartWrapper(part);
			if (StringUtils.isBlank(wrapper.getFilename())) continue; //비어있는파트 addXX

			//같은이름으로 input태그 두개이상이면 컬렉션으로 생성해야함
			List<PartWrapper> already = partWrapperMap.get(partName); //이미들어가있던 리스트
			if(already==null) {
				already = new ArrayList<PartWrapper>();
				partWrapperMap.put(partName, already);
			}
			
			already.add(wrapper);//이미 있던 리스트이면 add
		}
	}
	
	public Map<String, List<PartWrapper>> getPartWrapperMap(){
		return partWrapperMap;
	}

	public PartWrapper getPartWrapper(String partName) {//하나밖에 못가져감
		List<PartWrapper> wrappers = partWrapperMap.get(partName);
		if(wrappers!=null&&wrappers.size()>0) {
			return wrappers.get(0);
		}else {
			return null;
		}
	}
	
	
	public List<PartWrapper> getPartWrappers(String partName){
		return partWrapperMap.get(partName);
	}
	
	public Enumeration<String> getPartNames(){
		Iterator<String> it = partWrapperMap.keySet().iterator();
		return new Enumeration<String>() {//익명객체로 iterator를 enum으로 사용하기

			@Override
			public boolean hasMoreElements() {//hasnext
				return it.hasNext();
			}

			@Override
			public String nextElement() {//next
				return it.next();
			}
			
		};
	}
	
	public void deleteAll() throws IOException{//임시데이터 지우기
		for(Entry<String,List<PartWrapper>> entry :partWrapperMap.entrySet()) {
			List<PartWrapper> wrappers = entry.getValue();
			for(PartWrapper tmp : wrappers) {
				tmp.delete();
			}
		}
	}
}
