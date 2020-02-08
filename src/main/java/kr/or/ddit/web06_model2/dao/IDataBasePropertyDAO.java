package kr.or.ddit.web06_model2.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.DataBasePropertyVO;

public interface IDataBasePropertyDAO {
	public List<DataBasePropertyVO> selectDataBasePropertyList(Map<String, Object> model) ;
}
