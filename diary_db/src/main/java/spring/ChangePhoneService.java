package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class ChangePhoneService {// 비밀번호 변경

	private MemberDao memberDao;

	@Transactional
	public void changePhone(String email, String newphone) {
		Member member = memberDao.selectByEmail(email);
		if (member == null)
			throw new MemberNotFoundException();

		System.out.println("이전 연락처 : "+member.getPhonenum());
		member.changePhone(newphone);
		
		memberDao.update1(member);
		System.out.println("변경 후 연락처 : "+member.getPhonenum());
	}

	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

}
