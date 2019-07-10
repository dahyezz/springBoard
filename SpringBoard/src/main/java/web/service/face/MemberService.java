package web.service.face;

import web.dto.Member;

public interface MemberService {

	/**
	 * 로그인 성공/실패 체크
	 * 
	 * @param member - 로그인 하려는 회원 객체
	 * @return boolean - 로그인 성공/실패 여부
	 */
	public boolean login(Member member);

	/**
	 * 회원가입 처리
	 * 
	 * @param member - 회원가입 하려는 회원 객체
	 */
	public void join(Member member);

	/**
	 * 로그인 완료된 회원의 정보 가져오기
	 * 
	 * @param member - 로그인 성공한 회원이 id, pw 정보만 있는 객체
	 * @return Member - 로그인 성공한 회원의 모든 정보가 있는 객체
	 */
	public Member getMemberInfo(Member member);

}
