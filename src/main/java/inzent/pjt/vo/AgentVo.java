package inzent.pjt.vo;

public class AgentVo {
	private int no;
	private int user_no;
	private String agent_name;
	private String mac_address;
	private String ip_address;
	private String email;
	private String password;
	private String name;
	private String dept_name;
	private String last_login;
	private boolean activation;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getUser_no() {
		return user_no;
	}
	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
	public String getAgent_name() {
		return agent_name;
	}
	public void setAgent_name(String agent_name) {
		this.agent_name = agent_name;
	}
	public String getMac_address() {
		return mac_address;
	}
	public void setMac_address(String mac_address) {
		this.mac_address = mac_address;
	}
	public String getIp_address() {
		return ip_address;
	}
	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public boolean isActivation() {
		return activation;
	}
	public void setActivation(boolean activation) {
		this.activation = activation;
	}
	
	@Override
	public String toString() {
		return "AgentVo [no=" + no + ", user_no=" + user_no + ", agent_name=" + agent_name + ", mac_address="
				+ mac_address + ", ip_address=" + ip_address + ", email=" + email + ", password=" + password + ", name="
				+ name + ", dept_name=" + dept_name + ", last_login=" + last_login + "]";
	}
}
