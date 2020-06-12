package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class MemberInfoPrinter {

	private MemberDao memDao;
	private MemberPrinter printer;

	public int printMemberInfo(String email, String pwd) {
		Member member = memDao.selectByEmail(email);
		if (member == null) {
			System.out.println("이메일 데이터가 존재하지 않습니다.");
			System.out.println("다시 시도해주세요\n");
			return 0;
		}
		if (!member.getPassword().equals(pwd)) {
			//System.out.println(member.getPassword()+"\n"+pwd);
			System.out.println("비밀번호가 틀렸습니다.");
			System.out.println("다시 시도해주세요\n");
			return 0;
		}
		printer.print(member);
		System.out.println();
		return 1;
	}

	@Autowired
	public void setMemberDao(MemberDao memberDao) {
		this.memDao = memberDao;
	}

	@Autowired
	@Qualifier("printer")
	public void setPrinter(MemberPrinter printer) {
		this.printer = printer;
	}

}
