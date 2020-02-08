package kr.or.ddit.board.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.AttatchVO;
import kr.or.ddit.vo.BoardVO;

public class AttatchDAOImpl implements IAttatchDAO {
	SqlSessionFactory sqlSessionFactory = 
			CustomSqlSessionFactoryBuilder.getSqlSessionFactory();

	@Override
	public int insertAttatch(BoardVO board, SqlSession sqlSession) {
		IAttatchDAO mapper = sqlSession.getMapper(IAttatchDAO.class); //mapper 프록시생성
		int rowcnt = mapper.insertAttatch(board);
		//sqlSession.commit(); 원자성xxx
		return rowcnt;
	}
	
	@Override
	public int insertAttatch(BoardVO board) {
		return 0;
	}

	@Override
	public AttatchVO selectAttatch(int att_no) {
		return null;
	}

	@Override
	public int deleteAttatch(BoardVO board) {
		return 0;
	}

	@Override
	public int deleteAttatch(BoardVO board, SqlSession sqlSession) {
		IAttatchDAO mapper = sqlSession.getMapper(IAttatchDAO.class); //mapper 프록시생성
		int rowcnt = mapper.deleteAttatch(board);
		return rowcnt;
	}


}
