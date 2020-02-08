package kr.or.ddit.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//ibatis와 같은 기능을 수행
public class SimpleDataMapper {
	public static List queryForList(ResultSet rs, Class resultClass) throws SQLException {
		//제일먼저 list생성
		List list = new ArrayList();
		while(rs.next()) {
			list.add(queryForObject(rs, resultClass));
		}
		return list;
	}
	
	
	public static Object queryForObject(ResultSet rs, Class resultClass) throws SQLException { //실행되고나면 memeberVO가 만들어진다.
		//member.setMem_id(rs.getString("MEM_ID"));
		try {
			Object result = resultClass.newInstance(); //기본생성자이용
			Field[] fields = resultClass.getDeclaredFields();
			for(Field tmp : fields) {
				String propName = tmp.getName();
				//setter
				PropertyDescriptor pd = new PropertyDescriptor(propName, resultClass);
				Method setter = pd.getWriteMethod();
				Object value = null;
				try {
					if(tmp.getType().equals(String.class)) {
						value = rs.getString(propName); //컬럼명=필드명
					}else if(tmp.getType().equals(Integer.class)) {
						value = rs.getInt(propName);
					}
				}catch (Exception e) {
					//해당컬럼이 없는 경우
					continue;
				}
				setter.invoke(result, value);
			}
			return result;
		}catch (Exception e) {
			throw new SQLException(e);
		}
	}

}
