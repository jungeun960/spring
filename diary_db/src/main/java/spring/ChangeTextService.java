package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class ChangeTextService {// 비밀번호 변경

	// 의존 자동 주입
	//@Autowired
	private DiaryDao diaryDao;

	@Transactional
	public void changeText(String ID, String newtext) {
		Diary diary = diaryDao.selectByid(Integer.parseInt(ID));
		//Diary diary = diaryDao.selectBytitle(title);
	
		System.out.println("이전 내용 : "+ diary.getText());
		diary.changeText(newtext);
	
		diaryDao.update_text(diary);
		System.out.println("변경 후 내용 : "+diary.getText());
	}

	public void setMemberDao(DiaryDao diaryDao) {
		this.diaryDao = diaryDao;
	}

}
