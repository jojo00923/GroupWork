package kr.or.ddit.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
/**
 * Factory Object[Method] Pattern(객체를 대신 생성해줌)
 */
public class ConnectionFactory {
	static String url;
	static String user;
	static String password;
	static DataSource dataSource;//BDS로 하면 종속되어버린다.
	
	static{	
		ResourceBundle bundle = ResourceBundle.getBundle("kr.or.ddit.db.dbInfo"); 
		//java에서 사용되는 properties 파일의 내용을 읽어올수 있다. 언어에 따른 번들 데이터를 가지고있다.
		//basename - classpath표기방식
		//객체 접근시에 classpath 리소스사용(/ 대신 .)
		String driverClassName = bundle.getString("driverClassName");
		url = bundle.getString("url");
		user = bundle.getString("user");
		password = bundle.getString("password");
		
		// 2. 클래스는 메모리에 로딩이 딱 한 번 되기때문에 garbage되지 않는다. 한 번만 호출해도 됨 
		//try {
		//	Class.forName(driverClassName);
		//} catch (ClassNotFoundException e1) {
		//	throw new RuntimeException(e1);
		//}
		
		BasicDataSource bds = new BasicDataSource();
		dataSource = bds;
		bds.setDriverClassName(driverClassName);
		bds.setUrl(url);
		bds.setUsername(user);
		bds.setPassword(password); //커넥션생성
		//풀링정책결정
		int initialSize = Integer.parseInt(bundle.getString("initialSize"));
		int maxTotal = Integer.parseInt(bundle.getString("maxTotal"));
		long maxWait = Long.parseLong(bundle.getString("maxWait"));
		bds.setInitialSize(initialSize); 
		bds.setMaxTotal(maxTotal);
		bds.setMaxWaitMillis(maxWait);
		
	}
	
	public static Connection getConnection() throws SQLException{//예외를 컨트롤러가 가져감
				//oracle이 아닌 것도 build할 필요없이 만들기 위해 properties로 분리
				//properties 자체가 build할 필요없는 하나의 객체. --> classpath아래(res에 생성)
				//return DriverManager.getConnection(url, user, password);
		return dataSource.getConnection();
				
	}
}
