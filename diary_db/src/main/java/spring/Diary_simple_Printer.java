package spring;

import java.time.format.DateTimeFormatter;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class Diary_simple_Printer {

	private DiaryDao diaryDao;
	private DiaryPrinter printer;
	//private int i=1;
	
	public void printDiarySimple(String email) {
		//Collection<Diary> diarys = diaryDao.selectAll();
		Collection<Diary> diarys = diaryDao.selectAll_email(email);
		
		// 해당 이메일 다이어리만 뽑기
		System.out.println();
		diarys.forEach( m -> {
				System.out.println("    ID값 : "+ m.getId() + "   / 제목 : "+m.getTitle());
				//i++;
		});
//		diarys.forEach( m -> {
//			if(m.getEmail().equals(email)) {
//				System.out.println("    "+ i + "번째 : "+m.getTitle());
//				i++;
//		}});
		//i=1;		
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
