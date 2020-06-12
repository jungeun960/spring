package spring;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

public class DiaryRegisterService { // 회원 가입
	
	@Autowired
	private DiaryDao diaryDao;

	public DiaryRegisterService() {
	}
	// 의존 객체 직접 생성하는 대신 생성자를 통해 의존 객체를 전달받는다.
	public DiaryRegisterService(DiaryDao diaryDao) {
		// 주입받은 객체를 필드에 할당
		this.diaryDao = diaryDao;
	}

	public Long regist(RegisterRequest_diary req) throws IOException {
		// 주입받은 의존 객체의 메서드를 사용 
		Diary newDiary = new Diary( // 같은 이메일을 가진 회원이 없으면 삽입
				req.getEmail(), LocalDateTime.now(), req.getTitle(), req.getText(), req.getFile());
		diaryDao.insert(newDiary);
		return newDiary.getId();
	}
}
