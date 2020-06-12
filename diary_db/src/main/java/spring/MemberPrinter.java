package spring;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;

public class MemberPrinter {
	private DateTimeFormatter dateTimeFormatter;
	
	public MemberPrinter() {
		dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
	}
	
	public void print(Member member) {
		if (dateTimeFormatter == null) {
			System.out.printf("\n\n 환영합니다.\n");
			System.out.println("┏━━━━ 회원 정보 ━━━━━┓");
			System.out.printf("     이메일 : %s \n",member.getEmail());
			System.out.printf("     이름 : %s \n",member.getName());
			System.out.printf("     연락처 : %s \n",member.getPhonenum());
			System.out.printf("     주소 : %s \n",member.getAddress());
			System.out.printf("     등록일 : %tF\n",member.getRegisterDateTime());
			System.out.println("┗━━━━━━━━━━━━━━━━┛");
			
		} else {
			System.out.printf("\n\n 환영합니다.\n");
			System.out.println("┏━━━━━━━━ 회원 정보 ━━━━━━━━━┓");
			System.out.printf("     이메일 : %s \n",member.getEmail());
			System.out.printf("     이름 : %s \n",member.getName());
			System.out.printf("     연락처 : %s \n",member.getPhonenum());
			System.out.printf("     주소 : %s \n",member.getAddress());
			System.out.printf("     등록일 : %s\n",dateTimeFormatter.format(member.getRegisterDateTime()));
			System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━┛");

		}
	}
	
	@Autowired(required = false)
	public void setDateFormatter(DateTimeFormatter dateTimeFormatter) {
		this.dateTimeFormatter = dateTimeFormatter;
	}

	
}
