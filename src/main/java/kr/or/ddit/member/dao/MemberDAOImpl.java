package kr.or.ddit.member.dao;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.utils.SimpleDataMapper;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;

public class MemberDAOImpl implements IMemberDAO {
	//싱글톤패턴 - 시스템부하 감소
	private static MemberDAOImpl instance;
	//mybatis를 의존해야 해서 객체 생성
	SqlSessionFactory sqlSessionFactory = 
			CustomSqlSessionFactoryBuilder.getSqlSessionFactory(); //싱글톤 받아옴
	
	public static MemberDAOImpl getInstance() {
		if(instance == null) {
			instance = new MemberDAOImpl();
		}
		return instance;
	}

	@Override
	public MemberVO selectMember(String mem_id) {//장점 : 쿼리아이디설정x,타입안정성,다운캐스팅x 
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			//mapper 프록시
			//mybatis가 구현한 가짜 구현체
			IMemberDAO mapper = sqlSession.getMapper(IMemberDAO.class);
			return mapper.selectMember(mem_id);
		}
	}
	@Override
	public int insertMember(MemberVO member) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession(true); //자동커밋설정 첫번째방법
		){
				IMemberDAO mapper = sqlSession.getMapper(IMemberDAO.class);
				return mapper.insertMember(member);
		}
	}
	
	@Override
	public int selectMemberCount(PagingVO pagingVO) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();//세션만들기.
			){
				IMemberDAO mapper = sqlSession.getMapper(IMemberDAO.class);
				return mapper.selectMemberCount(pagingVO);
				
			}
	}

	@Override
	public List<MemberVO> selectMemberList(PagingVO pagingVO) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();//세션만들기.
		){
			//세션대상으로 필요한 것 찾아서 쓰기					//타입안정성x
			//return sqlSession.selectList("kr.or.ddit.member.dao.IMemberDAO.selectMemberList",pagingVO); 
			//파라미터 : namespace.id , 파라미터
			IMemberDAO mapper = sqlSession.getMapper(IMemberDAO.class);
			return mapper.selectMemberList(pagingVO);
			
		}
	}

	@Override
	public int updateMember(MemberVO member) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();
		){
				//mapper 프록시방식
				IMemberDAO mapper = sqlSession.getMapper(IMemberDAO.class);
				//return mapper.updateMember(member);
				int rowcnt = mapper.updateMember(member);
				sqlSession.commit();//자동커밋설정 두번째방법
				return rowcnt;
		}
	}

	@Override
	public int deleteMember(String mem_id) {//자식데이터가 있으므로 삭제대신 탈퇴여부 컬럼을 변경해준다.
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession(true);
		){
				IMemberDAO mapper = sqlSession.getMapper(IMemberDAO.class);
				//내부적으로 인터페이스(IMemberDAO.class)에 해당하는 구현체(객체)를 생성
				return mapper.deleteMember(mem_id);//어떤메서드를 사용할지 적어주면 결과가 리턴됨. 
		}
		
	}


}
