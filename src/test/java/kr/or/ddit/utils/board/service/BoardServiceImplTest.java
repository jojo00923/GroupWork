package kr.or.ddit.utils.board.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;

public class BoardServiceImplTest {
	IBoardService service = new BoardServiceImpl();
	
	PagingVO<BoardVO> pagingVO;
	BoardVO board;
	@Before
	public void setUp() throws Exception {
		pagingVO = new PagingVO<BoardVO>();
		pagingVO.setCurrentPage(1);
		pagingVO.setSearchVO(new SearchVO("title", "이은대"));
		
		board = new BoardVO();
		board.setBo_title("제목");
		board.setBo_writer("작성자");
		board.setBo_pass("1111");
		board.setBo_ip("111.111.111.111");
		board.setBo_content("내용");
	}

	@Test
	public void testCreateBoard() {
		ServiceResult result = service.createBoard(board);
		assertEquals(ServiceResult.OK, result);
	}

	@Test
	public void testReadBoardList() {
		List<BoardVO> boardList = service.readBoardList(pagingVO);
		assertNotEquals(0, boardList.size());
	}

	@Test
	public void testReadBoardCount() {
		int totalRecord = service.readBoardCount(pagingVO);
		assertNotEquals(0, totalRecord);
	}

	@Test
	public void testReadBoard() {
		BoardVO board = service.readBoard(400);
		assertNotNull(board);
	}

	@Test
	public void testModifyBoard() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveBoard() {
		fail("Not yet implemented");
	}

	@Test
	public void testIncrementLike() {
		fail("Not yet implemented");
	}

}
