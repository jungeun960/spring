package spring;

import java.time.LocalDateTime;

public class Member {

	private Long id;
	private String email;
	private String password;
	private String name;
	private LocalDateTime registerDateTime;
	private String phonenum;
	private String address;

	public Member(String email, String password, 
			String name, LocalDateTime regDateTime, String phonenum, String address) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.registerDateTime = regDateTime;
		this.phonenum = phonenum;
		this.address = address;
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

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}
	
	public String getPhonenum() {
		return phonenum;
	}
	
	public String getAddress() {
		return address;
	}

	public LocalDateTime getRegisterDateTime() {
		return registerDateTime;
	}

	public void changePassword(String oldPassword, String newPassword) {
		if (!password.equals(oldPassword))
			throw new WrongIdPasswordException();
		this.password = newPassword;
	}
	
	public void changePhone(String newPhone) {
		this.phonenum = newPhone;
	}
	
	public void changeAddress(String newaddress) {
		this.address = newaddress;
	}
}
