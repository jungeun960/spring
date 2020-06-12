package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class ChangeTitleService {// 비밀번호 변경

	// 의존 자동 주입
	//@Autowired
	private DiaryDao diaryDao;

	@Transactional
	public void changeTitle(String ID, String newtitle) {
		
		Diary diary = diaryDao.selectByid(Integer.parseInt(ID));
		//Diary diary = diaryDao.selectBytitle(title);
		
		System.out.println("이전 제목 : "+ diary.getTitle());
		diary.changeTitle(newtitle);
	
		diaryDao.update_title(diary);
		System.out.println("변경 후 제목 : "+diary.getTitle());
	}

	public void setMemberDao(DiaryDao diaryDao) {
		this.diaryDao = diaryDao;
	}

}
