package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class ChangeFileService {// 비밀번호 변경

	// 의존 자동 주입
	//@Autowired
	private DiaryDao diaryDao;

	@Transactional
	public void changeFile(String ID, String newfile) {
		Diary diary = diaryDao.selectByid(Integer.parseInt(ID));
		//Diary diary = diaryDao.selectBytitle(title);
	
		System.out.println("이전 경로 : "+ diary.getFile());
		diary.changeFile(newfile);
	
		diaryDao.update_file(diary);
		System.out.println("변경 후 경로 : "+diary.getFile());
	}

	public void setMemberDao(DiaryDao diaryDao) {
		this.diaryDao = diaryDao;
	}

}
