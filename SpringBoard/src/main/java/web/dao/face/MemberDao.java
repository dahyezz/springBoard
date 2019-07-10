package web.dao.face;

import web.dto.Member;

public interface MemberDao {

	/**
	 * 로그인 시도하려는 회원 count
	 * 
	 * @param member
	 * @return
	 */
	public int selectCntById(Member member);

	/**
	 * 회원가입 처리
	 * 
	 * @param member
	 */
	public void insertMember(Member member);

	/**
	 * id로 멤버 조회
	 * 
	 * @param member - 조회하려는 멤버 id
	 * @return Member - 조회 결고 ㅏ객체
	 */
	public Member selectMemberById(Member member);


}
