package com.oscar.todolist.authent;

public class LoginResponseDTO {
	
	private String token;
	private String email;
	private String firstName;
	private String lastNname;
	private int id;

	public LoginResponseDTO() {
		super();
	}	
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastNname() {
		return lastNname;
	}

	public void setLastNname(String lastNname) {
		this.lastNname = lastNname;
	}

	public int getId() {
		return id;
	}
	public void setId(int i) {
		this.id = i;
	}
}
