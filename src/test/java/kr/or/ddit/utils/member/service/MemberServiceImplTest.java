package kr.or.ddit.utils.member.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.UserNotFoundException;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

public class MemberServiceImplTest {
	IMemberService service;
	MemberVO member;
	MemberVO delMember;
	@Before
	public void setUp() throws Exception {
		service = MemberServiceImpl.getInstance();
		member = new MemberVO();
		member.setMem_id("a001");
		member.setMem_pass("asdfasdf");
		member.setMem_name("이은대");
		member.setMem_zip("111-111");
		member.setMem_add1("대전 대흥동");
		member.setMem_add2("500번지");
		member.setMem_mail("aa@naver.com");
		member.setMem_memorialday("1990-10-05");
		
		delMember = new MemberVO();
		delMember.setMem_id("a001");
		delMember.setMem_pass("asdfasdf");
	}

	@Test
	public void testCreateMember() {
		ServiceResult result = service.createMember(member);
		assertEquals(ServiceResult.PKDUPLICATED, result);
	}

	@Test
	public void testReadMemberList() {
		//List<MemberVO> memberList = service.readMemberList(pagingVO);
		//assertNotEquals(0, memberList.size());
	}

	@Test(expected = UserNotFoundException.class)
	public void testReadMember() {
		MemberVO savedMember = service.readMember("asfasfd");
	}

//	@Test(expected = UserNotFoundException.class)
//	@Test(expected = RuntimeException.class)
//	@Test
	public void testModifyMember() {
		ServiceResult result = service.modifyMember(member);
//		assertEquals(ServiceResult.INVALIDPASSWORD, result);
		assertEquals(ServiceResult.OK, result);
	}

//	TDD(Test Driven Development)
//	@Test(expected = UserNotFoundException.class)
//	@Test
	public void testRemoveMember() {
		ServiceResult result = service.removeMember(delMember);
		assertEquals(ServiceResult.OK, result);
	}

}







