package kr.or.ddit.member.service;

import kr.or.ddit.vo.MemberVO;

public interface IAuthenticateService {
	
	/**인증로직
	 * @param inputMember
	 * @return UserNotFoundException , INVALIDPASSWORD, 인증에 성공한 MemberVO
	 */
	public Object authenticate(MemberVO inputMember);
}
