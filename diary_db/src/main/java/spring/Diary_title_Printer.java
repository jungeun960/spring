package spring;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class Diary_title_Printer {

	private DiaryDao diaryDao;
	private DiaryPrinter printer;

	
	public void printDiaryTitle(String email, String title) {
		//Collection<Diary> diarys = diaryDao.selectAll();
		Collection<Diary> diarys = diaryDao.selectAll_title(title, email);
		if (diarys == null) {
			System.out.println("데이터가 존재하지 않습니다.");
			System.out.println("다이어리를 작성해주세요\n");
			return;
		}
		
		// 해당 이메일 & 제목 다이어리만 뽑기
//		diarys.forEach( m -> {
//			if(m.getEmail().equals(email)&&m.getTitle().equals(title)) {printer.print(m);
//			}});
		
		// 리스트 전부 뽑기
		//System.out.println("리스트 전부 뽑기");
		diarys.forEach( m -> {printer.print(m);});
		
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
