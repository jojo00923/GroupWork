package kr.or.ddit.web06_model2.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.vo.DataBasePropertyVO;

public class DataBasePropertyDAOImpl implements IDataBasePropertyDAO{
	public List<DataBasePropertyVO> selectDataBasePropertyList(Map<String, Object> model) {
		try(
				// 3. 
				Connection conn = ConnectionFactory.getConnection();
				// 4. 
				Statement stmt = conn.createStatement();
			){
				
				StringBuffer sql = new StringBuffer();
				sql.append(" SELECT PROPERTY_NAME, PROPERTY_VALUE, DESCRIPTION ");
				sql.append(" FROM DATABASE_PROPERTIES ");
				// 5.
				ResultSet rs = stmt.executeQuery(sql.toString());
				ResultSetMetaData rsmd = rs.getMetaData();
				String[] headers = new String[rsmd.getColumnCount()];
				for(int idx=1; idx<=headers.length; idx++){
					headers[idx-1] = rsmd.getColumnName(idx);			
				}
				// 6.
				List<DataBasePropertyVO> list = new ArrayList<>();
				while(rs.next()){
					DataBasePropertyVO vo = new DataBasePropertyVO();
					list.add(vo);
					vo.setProperty_name( rs.getString(1) );
					vo.setProperty_value(rs.getString("PROPERTY_VALUE"));
					vo.setDescription(rs.getString("DESCRIPTION"));
				}
				//Map<String, Object> model = new HashMap<String, Object>();
				model.put("headers", headers);
				model.put("list", list);
				//Call by Reference로 넘김 - map과 list가 return된다?
				return list;

			}catch(SQLException e){
				throw new RuntimeException(e);
			}
	}
}
