package kr.or.ddit.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.w3c.dom.ranges.RangeException;

public class CustomSqlSessionFactoryBuilder {//factory는 싱글톤
	private static SqlSessionFactory sqlSessionFactory;
	
	static {//팩토리를 만들기 위해서는 설정파일을 읽어야 한다. 한번 수행
		String resource = "kr/or/ddit/mybatis/Configuration.xml";
		try (
			Reader reader = Resources.getResourceAsReader(resource);
		){
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
	}
	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
}
