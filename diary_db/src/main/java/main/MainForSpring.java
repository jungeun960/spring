package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.AppCtx;
import spring.ChangeAddressService;
import spring.ChangeFileService;
import spring.ChangePasswordService;
import spring.ChangePhoneService;
import spring.ChangeTextService;
import spring.ChangeTitleService;
import spring.DiaryInfoPrinter;
import spring.DiaryRegisterService;
import spring.Diary_date_Printer;
import spring.Diary_simple_Printer;
import spring.Diary_title_Printer;
import spring.DuplicateMemberException;
import spring.Member;
import spring.MemberInfoPrinter;
import spring.MemberListPrinter;
import spring.MemberNotFoundException;
import spring.MemberPrinter;
import spring.MemberRegisterService;
import spring.RegisterRequest;
import spring.RegisterRequest_diary;
import spring.WrongIdPasswordException;


public class MainForSpring {

	private static ApplicationContext ctx = null;
	
	public static void main(String[] args) throws IOException {
		ctx = new AnnotationConfigApplicationContext(AppCtx.class);
		//콘솔에서 입력받기 위해 system.in을 이용해서 bufferdreader 생성 
		BufferedReader reader = 
				new BufferedReader(new InputStreamReader(System.in));
		System.out.println("다이어리 시스템 개발");
		
		while (true) {	
			System.out.println("┏━━━━━━━━━━━━━━━━┓");
			System.out.println("     1. 로그인 ");
			System.out.println("     2. 회원가입");
			System.out.println("     3. exit");
			System.out.println("┗━━━━━━━━━━━━━━━━┛");
			System.out.println("명령어를 입력하세요:");
			// readline을 이용하여 콘솔엥서 한줄을 입력받는다.
    
			String command = reader.readLine();
			if(command.equalsIgnoreCase("1")) {
				System.out.println("이메일를(을) 입력하세요 : ");
				String email = reader.readLine();
				System.out.println("비밀번호를(을) 입력하세요 : ");
				String pwd = reader.readLine();
				// readline을 이용하여 콘솔엥서 한줄을 입력받는다.
				int i = processLoginCommand(email,pwd);
				if(i==1) {
					while(true) {
						System.out.println("┏━━━━━━━ 메인 화면 ━━━━━━┓");
						System.out.println("     1. 다이어리 작성 ");
						System.out.println("     2. 다이어리 검색");
						System.out.println("     3. 다이어리 목록");
						System.out.println("     4. 다이어리 수정");
						System.out.println("     5. 내 정보 수정");
						System.out.println("     6. 로그아웃");
						System.out.println("┗━━━━━━━━━━━━━━━━━━━━━┛");
						System.out.println("명령어를 입력하세요:");
						String command2 = reader.readLine();
						
						if(command2.equalsIgnoreCase("1")) {
							System.out.println("제목를(을) 입력하세요 : ");
							String title = reader.readLine();
							System.out.println("내용를(을) 입력하세요 : ");
							String text = reader.readLine();
							System.out.println("첨부파일 경로를(을) 입력하세요 : ");
							String file = reader.readLine();
							processDiaryCommand(email, title, text, file);
						}
						else if(command2.equalsIgnoreCase("2")) {
							System.out.println("┏━━━━━━━━━━━ 검색 ━━━━━━━━━━━━┓");
							System.out.println("     1. 다이어리 제목 검색 ");
							System.out.println("     2. 다이어리 기간 검색");
							System.out.println("     3. 뒤로가기");
							System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
							System.out.println("명령어를 입력하세요:");
							String command4 = reader.readLine();
							if(command4.equalsIgnoreCase("1")) {
								System.out.println("제목를(을)  입력해주세요");
								String title = reader.readLine();
								processDiary_title(email,title);
							}
							else if(command4.equalsIgnoreCase("2")) {
								System.out.println("기간를(을)  입력해주세요");
								System.out.println("ex) 0000년 00월 00일");
								String date = reader.readLine();
								processDiary_date(email,date);
							}
							else if(command4.equalsIgnoreCase("3")) {
							}
						}
						else if(command2.equalsIgnoreCase("3")) {
							processDiaryList(email);
						}
						else if(command2.equalsIgnoreCase("4")) {
							System.out.println("┏━━━━━━━ 다이어리 제목 ━━━━━━┓");
							processDiarysimple(email);
							System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━┛");
							System.out.println("수정할 ID값 를(을) 입력해주세요");
							String c_ID = reader.readLine();
							System.out.println("수정할 내용을 선택해주세요");
							System.out.println("┏━━━━━━━ 다이어리 수정 ━━━━━━┓");
							System.out.println("     1. 제목 변경");
							System.out.println("     2. 내용 변경");
							System.out.println("     3. 첨부파일 경로 변경");
							System.out.println("     4. 뒤로가기");
							System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━┛");
							System.out.println("명령어를 입력하세요:");
							String command5 = reader.readLine();
							if(command5.equalsIgnoreCase("1")) {
								System.out.println("변결할 제목를(을) 입력하세요 ");
								String ch_title = reader.readLine();
								processChange_title_Command(c_ID, ch_title);
								
							}
							else if(command5.equalsIgnoreCase("2")) {
								System.out.println("변경할 내용를(을) 입력하세요 : ");
								String ch_text = reader.readLine();
								processChange_text_Command(c_ID, ch_text);
							}
							else if(command5.equalsIgnoreCase("3")) {
								System.out.println("변경할 첨부파일 경로를(을) 입력하세요 : ");
								String ch_file = reader.readLine();
								processChange_file_Command(c_ID, ch_file);
							}
							else if(command5.equalsIgnoreCase("4")) {
								
							}
						}
						else if(command2.equalsIgnoreCase("5")) {
							System.out.println("┏━━━━━━━ 정보 수정 ━━━━━━┓");
							System.out.println("     1. 비밀번호 변경");
							System.out.println("     2. 연락처 변경");
							System.out.println("     3. 주소 변경");
							System.out.println("     4. 뒤로가기");
							System.out.println("┗━━━━━━━━━━━━━━━━━━━━━┛");
							System.out.println("명령어를 입력하세요:");
							String command3 = reader.readLine();
							if(command3.equalsIgnoreCase("1")) {
								System.out.println("현재 비밀번호를(을) 입력하세요 ");
								String p_pwd = reader.readLine();
								System.out.println("변경할 비밀번호를(을) 입력하세요 : ");
								String c_pwd = reader.readLine();
								processChangeCommand(email, p_pwd,c_pwd);
							}
							else if(command3.equalsIgnoreCase("2")) {
								System.out.println("변경할 연락처를(을) 입력하세요 : ");
								String c_phone = reader.readLine();
								processChange_phone_Command(email, c_phone);
								
							}
							else if(command3.equalsIgnoreCase("3")) {
								System.out.println("변경할 주소를(을) 입력하세요 : ");
								String c_address = reader.readLine();
								processChange_address_Command(email, c_address);
							}
							else if(command3.equalsIgnoreCase("4")) {
								
							}
							
						}
						else if(command2.equalsIgnoreCase("6")) {
							System.out.println("로그아웃 합니다.");
							break;
						}
						
					}
				}
				
			}
			else if(command.equalsIgnoreCase("2")) {
				System.out.println("이메일를(을) 입력하세요 : ");
				String email = reader.readLine();
				System.out.println("비밀번호를(을) 입력하세요 : ");
				String pwd1 = reader.readLine();
				System.out.println("비밀번호를(을) 다시 입력하세요 : ");
				String pwd2 = reader.readLine();
				System.out.println("이름를(을) 입력하세요 : ");
				String name = reader.readLine();
				System.out.println("연락처를(을) 입력하세요 : ");
				String phonenum = reader.readLine();
				System.out.println("주소를(을) 입력하세요 : ");
				String address = reader.readLine();
				processNewCommand(email,pwd1,pwd2,name,phonenum,address);
//				processListCommand();
				
			}
			else if(command.equalsIgnoreCase("3")) {
				// exit면 프로그램 종료
				System.out.println("종료합니다.");
				break;
			}

			
//			} else if (command.equals("list")) {
//				processListCommand();
//				continue;
//			} else if (command.startsWith("info ")) {
//				processInfoCommand(command.split(" "));
//				continue;
//			// 잘못 입력할 경우 도움말을 출력해주는 printhelp 메서드 실행 
//			printHelp();
			
		}
	}

	private static void processNewCommand(String email,String pwd1,String pwd2,String name,String phonenum,String address) throws IOException {

		//스프링 컨테이너로부터 이름이 memberRegSvc인 빈 객체를 구한다.
		MemberRegisterService regSvc = 
				ctx.getBean("memberRegSvc", MemberRegisterService.class);
		RegisterRequest req = new RegisterRequest();
		
		req.setEmail(email);
		req.setName(name);
		req.setPassword(pwd1);
		req.setConfirmPassword(pwd2);
		req.setPhonenum(phonenum);
		req.setAddress(address);
		
		
		if (!req.isPasswordEqualToConfirmPassword()) {
			System.out.println("암호와 확인이 일치하지 않습니다.");
			System.out.println("다시 시도하여 주세요\n");
			return;
		}
		try {
			regSvc.regist(req);
			System.out.println("회원가입에 성공하였습니다.");
			System.out.println("로그인을 하고 다이어리를 이용해보세요.\n");
		} catch (DuplicateMemberException e) {
			System.out.println("이미 존재하는 이메일입니다.");
			System.out.println("다른 이메일 주소를 사용해주시길 바랍니다.\n");
		}
	}
	
	private static void processDiaryCommand(String email,String title, String text, String file) throws IOException {
		//스프링 컨테이너로부터 이름이 dirayRegSvc인 빈 객체를 구한다.
		DiaryRegisterService regSvc = 
				ctx.getBean("diaryRegSvc", DiaryRegisterService.class);
		RegisterRequest_diary req = new RegisterRequest_diary();
		req.setEmail(email);
		req.setTitle(title);
		req.setText(text);
		req.setFile(file);
		
		regSvc.regist(req);
		System.out.println("새글이 저장되었습니다.\n");
		
	}
	
	private static int processLoginCommand(String email, String pwd) {
		// 지정한 이메일에 대한 정보 출력
	
		MemberInfoPrinter infoPrinter = 
				ctx.getBean("infoPrinter", MemberInfoPrinter.class);
		int i = infoPrinter.printMemberInfo(email,pwd);
		return i;
	}
	
	private static void processDiaryList(String email) {
		// 지정한 이메일에 대한 정보 출력
		DiaryInfoPrinter infoPrinter = 
				ctx.getBean("infoPrinter1", DiaryInfoPrinter.class);
		infoPrinter.printDiaryInfo(email);
	}
	
	private static void processDiary_title(String email, String title) {
		// 지정한 이메일에 해당 제목에 대한 다이어리 출력
		Diary_title_Printer infoPrinter = 
				ctx.getBean("infoPrinter2", Diary_title_Printer.class);
		infoPrinter.printDiaryTitle(email,title);
	}
	
	private static void processDiary_date(String email, String date) {
		// 지정한 이메일에 해당 기간에 대한 다이어리 출력
		Diary_date_Printer infoPrinter = 
				ctx.getBean("infoPrinter3", Diary_date_Printer.class);
		infoPrinter.printDiaryDate(email,date);
	}
	
	private static void processDiarysimple(String email) {
		// 지정한 이메일에 해당 기간에 대한 다이어리 출력
		Diary_simple_Printer infoPrinter = 
				ctx.getBean("infoPrinter4", Diary_simple_Printer.class);
		infoPrinter.printDiarySimple(email);
	}
	
	
	private static void processChangeCommand(String email, String p_pwd, String c_pwd) {
		// 유저 암호 변경 
		
		ChangePasswordService changePwdSvc =
				ctx.getBean("changePwdSvc", ChangePasswordService.class);
		try {
			changePwdSvc.changePassword(email,p_pwd, c_pwd);
			System.out.println("암호를(을) 변경했습니다.\n");
		} catch (WrongIdPasswordException e) {
			System.out.println("이메일과 암호가 일치하지 않습니다.\n");
		}
	}
	
	private static void processChange_phone_Command(String email, String c_phone) {
		// 유저 번호 변경 
		
		ChangePhoneService changePhoneSvc =
				ctx.getBean("changePhoneSvc", ChangePhoneService.class);
		try {
			changePhoneSvc.changePhone(email,c_phone);
			System.out.println("연락처를(을) 변경했습니다.\n");
		} catch (WrongIdPasswordException e) {
		}
	}
	
	private static void processChange_address_Command(String email, String c_address) {
		// 유저 주소 변경 
		
		ChangeAddressService changeAddressSvc =
				ctx.getBean("changeAddressSvc", ChangeAddressService.class);
		try {
			changeAddressSvc.changeAddress(email,c_address);
			System.out.println("주소를(을) 변경했습니다.\n");
		} catch (WrongIdPasswordException e) {
		}
	}
	
	private static void processChange_title_Command(String c_ID, String ch_title) {
		
		ChangeTitleService changeTitleSvc =
				ctx.getBean("changeTitleSvc", ChangeTitleService.class);
		try {
			changeTitleSvc.changeTitle(c_ID,ch_title);
			System.out.println("제목를(을) 변경했습니다.\n");
		} catch (WrongIdPasswordException e) {
		}
	}
	
	private static void processChange_text_Command(String c_ID, String ch_text) {
		
		ChangeTextService changeTextSvc =
				ctx.getBean("changeTextSvc", ChangeTextService.class);
		try {
			changeTextSvc.changeText(c_ID,ch_text);
			System.out.println("내용를(을) 변경했습니다.\n");
		} catch (WrongIdPasswordException e) {
		}
	}
	
	private static void processChange_file_Command(String c_ID, String ch_file) {
		
		ChangeFileService changeFileSvc =
				ctx.getBean("changeFileSvc", ChangeFileService.class);
		try {
			changeFileSvc.changeFile(c_ID,ch_file);
			System.out.println("첨부파일 경로를(을) 변경했습니다.\n");
		} catch (WrongIdPasswordException e) {
		}
	}
	
	private static void processListCommand() {
		// 회원 리스트 출력
		MemberListPrinter listPrinter = 
				ctx.getBean("listPrinter", MemberListPrinter.class);
		listPrinter.printAll();
	}

	private static void processInfoCommand(String[] arg) {
		// 지정한 이메일에 대한 정보 출력
		if (arg.length != 2) {
			//printHelp();
			return;
		}
		MemberInfoPrinter infoPrinter = 
				ctx.getBean("infoPrinter", MemberInfoPrinter.class);
		//infoPrinter.printMemberInfo(arg[1]);
	}
	

}