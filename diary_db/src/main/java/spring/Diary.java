package spring;

import java.time.LocalDateTime;

public class Diary {

	private Long id;
	private String email;
	private LocalDateTime registerDateTime;
	private String title;
	private String text;
	private String file;
	
	public Diary(String email, LocalDateTime regDateTime, 
			String title, String text, String file) {
		this.email = email;
		this.registerDateTime = regDateTime;
		this.title = title;
		this.text = text;
		this.file = file;
	}
	
	void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
	
	public String getEmail() {
		return email;
	}

	public String getTitle() {
		return title;
	}

	public String getText() {
		return text;
	}
	
	public String getFile() {
		return file;
	}
	
	public LocalDateTime getRegisterDateTime() {
		return registerDateTime;
	}
	
	public void changeTitle(String newtitle) {
		this.title = newtitle;
	}
	
	public void changeText(String newtext) {
		this.text = newtext;
	}
	
	public void changeFile(String newfile) {
		this.file = newfile;
	}
}
