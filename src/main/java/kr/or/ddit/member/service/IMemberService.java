package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;

/**
 * 회원관리(CRUD)를 위한 Business Logic Layer
 * 
 */
public interface IMemberService {
	/**
	 * 신규 회원 가입(등록)
	 * @param member
	 * @return PKDUPLICATED, OK, FAIL (가입성공,실패,아이디중복의 경우의 수)
	 * enum으로 리턴
	 */
	public ServiceResult createMember(MemberVO member);
	
	
	/**
	 * @param pagingVO TODO
	 * @return
	 */
	public int readMemberCount(PagingVO pagingVO);
	/**
	 * 회원목록 조회
	 * @param pagingVO TODO
	 * @return
	 * 
	 */
	public List<MemberVO> readMemberList(PagingVO pagingVO);
	
	/**
	 * 회원정보 상세조회
	 * @param mem_id 조회할 회원의 아이디 
	 * @return 검색결과가 없을 수 있다. 
	 * (나의 비즈니스로직 안에서만 발생하는 예외를 위해 커스텀exception(UserNotFoundException) 사용)
	 */
	public MemberVO readMember(String mem_id);

	/**
	 * 회원정보 수정
	 * @param member
	 * @return 존재하지 않는다면, UserNotfoundexception 발생,
	 *  	invalidpassword , ok, fail
	 */
	public ServiceResult modifyMember(MemberVO member);
	
	/**
	 * 회원 탈퇴
	 * @param member
	 * @return 존재하지 않는다면, UserNotfoundexception 발생,
	 *  	invalidpassword , ok, fail
	 */
	public ServiceResult removeMember(MemberVO member);




}
