package spring;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class DiaryInfoPrinter {

	private DiaryDao diaryDao;
	private DiaryPrinter printer;

	
	public void printDiaryInfo(String email) {
		//Collection<Diary> diarys = diaryDao.selectAll();
		Collection<Diary> diarys = diaryDao.selectAll_email(email);
		if (diarys == null) {
			System.out.println("데이터가 존재하지 않습니다.");
			System.out.println("다이어리를 작성해주세요\n");
			return;
		}
		
		// 해당 이메일 다이어리만 뽑기
//		diarys.forEach( m -> {
//			if(m.getEmail().equals(email)) {printer.print(m);
//			//System.out.println("작성자"+m.getEmail()+" 현재 로그인 :" +email);
//			}});
		
		// 리스트 전부 뽑기
		//System.out.println("리스트 전부 뽑기");
		diarys.forEach( m -> {printer.print(m);});
		
		
///		Diary diary = diaryDao.selectBytitle(email);
//		printer.print(diary);
		System.out.println();
		
	}
	

	@Autowired
	public void setMemberDao(DiaryDao diaryDao) {
		this.diaryDao = diaryDao;
	}

	@Autowired
	@Qualifier("diaryprinter")
	public void setPrinter(DiaryPrinter printer) {
		this.printer = printer;
	}

}
