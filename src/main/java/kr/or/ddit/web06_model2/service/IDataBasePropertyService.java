package kr.or.ddit.web06_model2.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.DataBasePropertyVO;
//interface 목적 : 협업, 실행코드의 캡슐화
public interface IDataBasePropertyService {
	public List<DataBasePropertyVO> readDataBasePropertyList(Map<String,Object> model);
}
