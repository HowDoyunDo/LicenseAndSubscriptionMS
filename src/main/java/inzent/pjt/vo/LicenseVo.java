package inzent.pjt.vo;

public class LicenseVo {
	int no;
	int order_no;
	int user_admin_no;
	int policy_no;
	String license_key;
	int current_count;
	int max_count;
	String start_date;
	String end_date;
	char activation;
	String policy_title;
	char standard;
	
	public char getStandard() {
		return standard;
	}
	public void setStandard(char standard) {
		this.standard = standard;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getOrder_no() {
		return order_no;
	}
	public void setOrder_no(int order_no) {
		this.order_no = order_no;
	}
	public int getUser_admin_no() {
		return user_admin_no;
	}
	public void setUser_admin_no(int user_admin_no) {
		this.user_admin_no = user_admin_no;
	}
	public int getPolicy_no() {
		return policy_no;
	}
	public void setPolicy_no(int policy_no) {
		this.policy_no = policy_no;
	}
	public String getLicense_key() {
		return license_key;
	}
	public void setLicense_key(String license_key) {
		this.license_key = license_key;
	}
	public int getCurrent_count() {
		return current_count;
	}
	public void setCurrent_count(int current_count) {
		this.current_count = current_count;
	}
	public int getMax_count() {
		return max_count;
	}
	public void setMax_count(int max_count) {
		this.max_count = max_count;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public char getActivation() {
		return activation;
	}
	public void setActivation(char activation) {
		this.activation = activation;
	}
	public String getPolicy_title() {
		return policy_title;
	}
	public void setPolicy_title(String policy_title) {
		this.policy_title = policy_title;
	}
	
}
