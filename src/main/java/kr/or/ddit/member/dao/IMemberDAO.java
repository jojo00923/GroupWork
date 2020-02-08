package kr.or.ddit.member.dao;

import java.util.List;

import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;

/**
 * 회원관리(CRUD)에서 사용할 Persistence Layer
 *
 */
public interface IMemberDAO {
	
	/**신규 회원 등록
	 * @param member 등록할 회원 정보
	 * @return 등록성공(rowcnt>0) 실패(rowcnt<=0)
	 */
	public int insertMember(MemberVO member); 
	
	/**
	 * 한명의 회원 조회
	 * @param mem_id 조회할 회원의 PK
	 * @return 존재하지 않으면, null반환.
	 */
	public MemberVO selectMember(String mem_id);
	
	/**
	 * 페이징 처리를 위한 totalRecord조회
	 * @param pagingVO TODO
	 * @return
	 */
	public int selectMemberCount(PagingVO pagingVO);
	
	/**회원 목록 조회
	 * @param pagingVO TODO
	 * @return 존재하지 않는다면 size() = 0. null이 아님
	 */
	public List<MemberVO> selectMemberList(PagingVO pagingVO);
	
	/**회원 정보 수정
	 * @param member 수정할 정보
	 * @return 등록성공(rowcnt>0) 실패(rowcnt<=0)
	 */
	public int updateMember(MemberVO member);
	
	/**회원 정보 삭제
	 * @param mem_id 삭제할 pk
	 * @return 등록성공(rowcnt>0) 실패(rowcnt<=0)
	 */
	public int deleteMember(String mem_id);


}
