package spring;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

public class MemberRegisterService { // 회원 가입
	
	@Autowired
	private MemberDao memberDao;

	public MemberRegisterService() {
	}
	// 의존 객체 직접 생성하는 대신 생성자를 통해 의존 객체를 전달받는다.
	public MemberRegisterService(MemberDao memberDao) {
		// 주입받은 객체를 필드에 할당
		this.memberDao = memberDao;
	}

	public Long regist(RegisterRequest req) throws IOException {
		// 주입받은 의존 객체의 메서드를 사용 
		Member member = memberDao.selectByEmail(req.getEmail());
		if (member != null) {// 같은 이메일을 가진 회원이 존재하면 익셉션 발생
			throw new DuplicateMemberException("dup email " + req.getEmail());
		}
		Member newMember = new Member( // 같은 이메일을 가진 회원이 없으면 삽입
				req.getEmail(), req.getPassword(), req.getName(), 
				LocalDateTime.now(), req.getPhonenum(), req.getAddress());
		memberDao.insert(newMember);
		return newMember.getId();
	}
//	public Long regist(RegisterRequest req) throws IOException {
//	      // 주입받은 의존 객체의 메서드를 사용 
//	      Member member = memberDao.selectByEmail(req.getEmail());
//	      if (member != null) {// 같은 이메일을 가진 회원이 존재하면 익셉션 발생
//	         throw new DuplicateMemberException("dup email " + req.getEmail());
//	      }
//	      Member newMember = new Member( // 같은 이메일을 가진 회원이 없으면 삽입
//	            req.getEmail(), req.getPassword(), req.getName(), 
//	            LocalDateTime.now(), req.getPhonenum(), req.getAddress());
//	      memberDao.insert(newMember);
//	      member_save(req);
//	      return newMember.getId();
//	   }
//	   public void member_save(RegisterRequest req) throws IOException {
//	      BufferedWriter bw = new BufferedWriter(new FileWriter("사용자정보.txt",true));
//	      PrintWriter pw = new PrintWriter(bw,true);
//	      
//	      pw.write("Email : "+ req.getEmail() + "\n"
//	            + "Name : "+ req.getName() + "\n"
//	            + "RegistTime : "+ LocalDateTime.now() + "\n"
//	            + "Phonenum : "+ req.getPhonenum() + "\n"
//	            + "Address : " + req.getAddress() + "\n\n");
//	      pw.flush();
//	      pw.close();
//	   }

}
