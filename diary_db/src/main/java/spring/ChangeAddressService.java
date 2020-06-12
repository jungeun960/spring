package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class ChangeAddressService {// 비밀번호 변경

	// 의존 자동 주입
	//@Autowired
	private MemberDao memberDao;

	@Transactional
	public void changeAddress(String email, String newaddress) {
		Member member = memberDao.selectByEmail(email);
		if (member == null)
			throw new MemberNotFoundException();

		System.out.println("이전 주소 : "+member.getAddress());
		member.changeAddress(newaddress);
		
		memberDao.update2(member);
		System.out.println("변경 후 주소 : "+member.getAddress());
	}

	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

}
