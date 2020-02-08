package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.UserNotFoundException;
import kr.or.ddit.member.dao.IMemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import sun.net.www.protocol.http.AuthScheme;

public class MemberServiceImpl implements IMemberService {
	IMemberDAO memberDAO = MemberDAOImpl.getInstance(); //싱글톤이라 계속 같은 객체가 돌아오기 때문에 전역으로.
	IAuthenticateService authService = AuthenticateServiceImpl.getInstance();
	
	private static IMemberService instance;
	
	public static IMemberService getInstance(){
		if(instance==null) instance = new MemberServiceImpl();
		return instance;
	}
	
	
	@Override
	public ServiceResult createMember(MemberVO member) {
		ServiceResult result = null;
		Object authResult = memberDAO.selectMember(member.getMem_id());
		if(authResult != null) {
			result = ServiceResult.PKDUPLICATED;
		}else {
			int rowcnt = memberDAO.insertMember(member); 
			if(rowcnt>0) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAIL;
			}
		}
		
		return result;
	}

	@Override
	public int readMemberCount(PagingVO pagingVO) {
		return memberDAO.selectMemberCount(pagingVO);
	}

	
	@Override
	public List<MemberVO> readMemberList(PagingVO pagingVO) {
		List<MemberVO> memberList = memberDAO.selectMemberList(pagingVO);
		for(MemberVO member: memberList) {
			System.out.println(member.getMem_mail()+"발송");
		}
		return memberList;
	}

	@Override
	public MemberVO readMember(String mem_id) {
		MemberVO member = memberDAO.selectMember(mem_id);
		if(member==null) {
			throw new UserNotFoundException(); //예외의 형태로 호출자에게 넘김
		}
		return member;
	}

	@Override
	public ServiceResult modifyMember(MemberVO member) {
		ServiceResult result = null;
		Object authResult = authService.authenticate(member);
		if(authResult instanceof ServiceResult) {//not exist or invalid
			result = (ServiceResult) authResult;
			
		}else {//return으로 memberVO면 수정가능
			int rowcnt = memberDAO.updateMember(member);
			if(rowcnt >0) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAIL;
			}
		}
		return result;
	}

	@Override
	public ServiceResult removeMember(MemberVO member) {
		ServiceResult result = null;
		Object authResult = authService.authenticate(member);//인증로직 사용함으로써 결합력 낮춤
		if(authResult instanceof MemberVO) {//인증에 성공했을 때
			int rowcnt = memberDAO.deleteMember(member.getMem_id());
			if(rowcnt>0) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAIL;
			}
		}else {//없는경우,비번 틀린경우
			result = (ServiceResult)authResult; //비번오류
		}
		
		return result;
	}


	
}
