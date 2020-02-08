package kr.or.ddit.web06_model2.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.DataBasePropertyVO;
import kr.or.ddit.web06_model2.dao.DataBasePropertyDAOImpl;
import kr.or.ddit.web06_model2.dao.IDataBasePropertyDAO;

public class DataBasePropertyServiceImpl implements IDataBasePropertyService{
	IDataBasePropertyDAO dao = new DataBasePropertyDAOImpl();

	@Override
	public List<DataBasePropertyVO> readDataBasePropertyList(Map<String, Object> model) {
		List<DataBasePropertyVO> list = dao.selectDataBasePropertyList(model);
		for(DataBasePropertyVO vo : list) {
			String tmp = vo.getProperty_value()==null?null:vo.getProperty_value().toLowerCase();
			vo.setProperty_value(tmp);
		}
		
		return list; 
	}



	
	
	
	
	
}
