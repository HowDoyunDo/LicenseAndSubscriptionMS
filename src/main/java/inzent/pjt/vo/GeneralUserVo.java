package inzent.pjt.vo;

import com.github.ckpoint.toexcel.annotation.ExcelHeader;

public class GeneralUserVo {
	int no;
	
	@ExcelHeader(headerName = "이메일(접속 ID)")
	String email;
	@ExcelHeader(headerName = "비밀번호")
	String password;
	@ExcelHeader(headerName = "이름")
	String name;
	@ExcelHeader(headerName = "부서명")
	String dept_name;
	
	String last_login;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDept_name() {
		return dept_name;
	}
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	public String getLast_login() {
		return last_login;
	}
	public void setLast_login(String last_login) {
		this.last_login = last_login;
	}
	
	@Override
	public String toString() {
		return "GeneralUserVo [no=" + no + ", email=" + email + ", password=" + password + ", name=" + name
				+ ", dept_name=" + dept_name + ", last_login=" + last_login + "]";
	}
	
}
