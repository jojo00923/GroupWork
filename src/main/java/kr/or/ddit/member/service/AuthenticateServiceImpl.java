package kr.or.ddit.member.service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.UserNotFoundException;
import kr.or.ddit.member.dao.IMemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.vo.MemberVO;

public class AuthenticateServiceImpl implements IAuthenticateService {
	IMemberDAO memberDAO = MemberDAOImpl.getInstance();
	private static AuthenticateServiceImpl instance;
	
	public static AuthenticateServiceImpl getInstance() {
		if(instance ==null) instance = new AuthenticateServiceImpl();
			return instance;
		
	}
	
	@Override
	public Object authenticate(MemberVO inputMember) {//object?
		Object result = null;
		MemberVO savedMember = memberDAO.selectMember(inputMember.getMem_id());
		if(savedMember!=null) {
			String inputPass = inputMember.getMem_pass();
			String savedPass = savedMember.getMem_pass();
			if("Y".equals(savedMember.getMem_delete())) {
				result = ServiceResult.REMOVED;
			}else if(savedPass.equals(inputPass)) {
				//result = ServiceResult.OK;
				result = savedMember;
				
			}else {
				result = ServiceResult.INVALIDPASSWORD;
			}
		}else {//null일때
//			result = ServiceResult.NOTEXIST;
			throw new UserNotFoundException(inputMember.getMem_id()+"존재하지 않음");
			
		}
		return result;
	}

}
