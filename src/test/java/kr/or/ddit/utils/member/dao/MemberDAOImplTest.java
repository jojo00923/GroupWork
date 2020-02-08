package kr.or.ddit.utils.member.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.member.dao.IMemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.vo.MemberVO;

public class MemberDAOImplTest {
	IMemberDAO memberDAO;
	MemberVO member;

	@Before
	public void setUp() throws Exception {
		memberDAO = MemberDAOImpl.getInstance();
		//member = MemberVO.builder()
		//		.mem_id("a001")
		//		.mem_pass("asdfasdf")
		//		.mem_name("이은대")
		//		.build();
		member = new MemberVO();
		member.setMem_id("a001");
		member.setMem_pass("asdfasdf");
		member.setMem_name("이은대");
		member.setMem_zip("111-111");
		member.setMem_add1("대전 대흥동");
		member.setMem_add2("500번지");
		member.setMem_mail("aa@naver.com");
		member.setMem_memorialday("1990-10-05");
	}

	@Test
	public void testSelectMember() {
		MemberVO savedMember = memberDAO.selectMember("a001");
		assertNotNull(savedMember);
	}

//	@Test(expected = RuntimeException.class)
	@Test
	public void testInsertMember() {
		member.setMem_id("a002");
		int rowcnt = memberDAO.insertMember(member);
		assertEquals(1, rowcnt);
	}

	@Test
	public void testSelectMemberList() {
		//List<MemberVO> memberList = memberDAO.selectMemberList(pagingVO);
		//assertNotNull(memberList);
		//assertNotEquals(0, memberList.size());
	}

//	@Test(expected = RuntimeException.class)
	@Test
	public void testUpdateMember() {
		int rowcnt = memberDAO.updateMember(member);
		assertEquals(1, rowcnt);
	}

	@Test
	public void testDeleteMember() {
		int rowcnt = memberDAO.deleteMember("a001");
		assertEquals(1, rowcnt);
		rowcnt = memberDAO.deleteMember("a0sadsadf01");
		assertEquals(0, rowcnt);
	}

}












