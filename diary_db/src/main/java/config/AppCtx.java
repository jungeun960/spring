package config;

import java.sql.Driver;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import spring.ChangeAddressService;
import spring.ChangeFileService;
import spring.ChangePasswordService;
import spring.ChangePhoneService;
import spring.ChangeTextService;
import spring.ChangeTitleService;
import spring.DiaryDao;
import spring.DiaryInfoPrinter;
import spring.DiaryPrinter;
import spring.DiaryRegisterService;
import spring.Diary_date_Printer;
import spring.Diary_simple_Printer;
import spring.Diary_title_Printer;
import spring.MemberDao;
import spring.MemberInfoPrinter;
import spring.MemberListPrinter;
import spring.MemberPrinter;
import spring.MemberRegisterService;

// 스프링 설정 클래스
@Configuration
@EnableTransactionManagement
public class AppCtx {

	private final int MAX_SIZE = 10 * 1024 * 1024;
	 
	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		DataSource ds = new DataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		//jdbc:mysql://localhost/spring5fs?characterEncoding=utf8&amp;serverTimezone=UTC
		ds.setUrl("jdbc:mysql://localhost/spring5fs?characterEncoding=utf8&serverTimezone=UTC");
		ds.setUsername("spring5");
		ds.setPassword("spring5");
		ds.setInitialSize(2);
		ds.setMaxActive(100);
		ds.setTestWhileIdle(true);
		ds.setMinEvictableIdleTimeMillis(60000 * 3);
		ds.setTimeBetweenEvictionRunsMillis(10 * 1000);
		return ds;
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		DataSourceTransactionManager tm = new DataSourceTransactionManager();
		tm.setDataSource(dataSource());
		return tm;
	}

//	@Bean(name = "multipartResolver")
//	public CommonsMultipartResolver multipartResolver() {
//	    CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
//	    multipartResolver.setMaxUploadSize(100000);
//	    return multipartResolver;
//	}
	
	@Bean
	public MemberDao memberDao() {
		return new MemberDao(dataSource());
	}
	
	@Bean
	public DiaryDao diaryDao() {
		return new DiaryDao(dataSource());
	}
	
	// changepasswordservice에 autowired로 자동 주입 기능을 사용하여
	// pwdScv.setMemberDao(memberDao()); 안씀
	// @bean 메서드에서 의존을 주입하지 않아도 스프링이 @autowired가 붙은 필드에
	// 해당 타입의 빈 객체를 찾아서 주입한다.
	@Bean
	public MemberRegisterService memberRegSvc() {
		return new MemberRegisterService();
	}
	
	@Bean
	public DiaryRegisterService diaryRegSvc() {
		return new DiaryRegisterService();
	}
	
	@Bean
	public ChangePasswordService changePwdSvc() {
		ChangePasswordService pwdSvc = new ChangePasswordService();
		pwdSvc.setMemberDao(memberDao());
		return pwdSvc;
//		return new ChangePasswordService();
	}
	
	@Bean
	public ChangePhoneService changePhoneSvc() {
		ChangePhoneService pwdSvc = new ChangePhoneService();
		pwdSvc.setMemberDao(memberDao());
		return pwdSvc;
		//return new ChangePhoneService();
	}
	
	@Bean
	public ChangeAddressService changeAddressSvc() {
		ChangeAddressService pwdSvc = new ChangeAddressService();
		pwdSvc.setMemberDao(memberDao());
		return pwdSvc;
		//return new ChangeAddressService();
	}
	
	@Bean
	public ChangeTitleService changeTitleSvc() {
		ChangeTitleService pwdSvc = new ChangeTitleService();
		pwdSvc.setMemberDao(diaryDao());
		return pwdSvc;
		//return new ChangeTitleService();
	}
	
	@Bean
	public ChangeTextService changeTextSvc() {
		ChangeTextService pwdSvc = new ChangeTextService();
		pwdSvc.setMemberDao(diaryDao());
		return pwdSvc;
		//return new ChangeTextService();
	}
	
	@Bean
	public ChangeFileService changeFileSvc() {
		ChangeFileService pwdSvc = new ChangeFileService();
		pwdSvc.setMemberDao(diaryDao());
		return pwdSvc;
		//return new ChangeFileService();
	}
	
	// @Qualifier 자동 주입 대상 빈을 한정할 수 있다.
	@Bean
	@Qualifier("printer")
	public MemberPrinter memberPrinter1() {
		return new MemberPrinter();
	}
	
	@Bean
	@Qualifier("diaryprinter")
	public DiaryPrinter diaryPrinter1() {
		return new DiaryPrinter();
	}
	
	
	@Bean
	public MemberListPrinter listPrinter() {
		return new MemberListPrinter();
	}
	
	@Bean
	public MemberInfoPrinter infoPrinter() {
		MemberInfoPrinter infoPrinter = new MemberInfoPrinter();
		return infoPrinter;
	}
	
	@Bean
	public DiaryInfoPrinter infoPrinter1() {
		DiaryInfoPrinter infoPrinter = new DiaryInfoPrinter();
		return infoPrinter;
	}
	
	@Bean
	public Diary_title_Printer infoPrinter2() {
		Diary_title_Printer infoPrinter = new Diary_title_Printer();
		return infoPrinter;
	}
	
	@Bean
	public Diary_date_Printer infoPrinter3() {
		Diary_date_Printer infoPrinter = new Diary_date_Printer();
		return infoPrinter;
	}
	
	@Bean
	public Diary_simple_Printer infoPrinter4() {
		Diary_simple_Printer infoPrinter = new Diary_simple_Printer();
		return infoPrinter;
	}

	
}
