package spring;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;

public class DiaryPrinter {
	private DateTimeFormatter dateTimeFormatter;
	
	public DiaryPrinter() {
		dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
	}
	
	public void print(Diary diary) {
		if (dateTimeFormatter == null) {
			System.out.println("┏━━━━━━━━ 다이어리 ━━━━━━━━━┓");
			System.out.printf("     제목 : %s \n",diary.getTitle());
			System.out.printf("     내용 : %s \n",diary.getText());
			System.out.printf("     첨부파일 경로 : %s \n",diary.getFile());
			System.out.printf("     등록일 : %tF\n",diary.getRegisterDateTime());
			System.out.printf("     작성자 : %s \n",diary.getEmail());
			System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━┛");
			
		} else {
			System.out.println("┏━━━━━━━━ 다이어리 ━━━━━━━━━┓");
			System.out.printf("     제목 : %s \n",diary.getTitle());
			System.out.printf("     내용 : %s \n",diary.getText());
			System.out.printf("     첨부파일 경로 : %s \n",diary.getFile());
			System.out.printf("     등록일 : %s\n",dateTimeFormatter.format(diary.getRegisterDateTime()));
			System.out.printf("     작성자 : %s \n",diary.getEmail());
			System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━┛");

		}
	}
	
	@Autowired(required = false)
	public void setDateFormatter(DateTimeFormatter dateTimeFormatter) {
		this.dateTimeFormatter = dateTimeFormatter;
	}

	
}
