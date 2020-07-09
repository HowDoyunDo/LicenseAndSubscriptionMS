package inzent.pjt.vo;

public class SubscribeChangeVO {
	private int no; 
	private int license_no;
	private int policy_no;
	private int month_count;
	private String start_date;
	private String end_date;
	private String status;
	private String change_date;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getLicense_no() {
		return license_no;
	}
	public void setLicense_no(int license_no) {
		this.license_no = license_no;
	}
	public int getPolicy_no() {
		return policy_no;
	}
	public void setPolicy_no(int policy_no) {
		this.policy_no = policy_no;
	}
	public int getMonth_count() {
		return month_count;
	}
	public void setMonth_count(int month_count) {
		this.month_count = month_count;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getChange_date() {
		return change_date;
	}
	public void setChange_date(String change_date) {
		this.change_date = change_date;
	}
	@Override
	public String toString() {
		return "SubscribeChangeVO [no=" + no + ", license_no=" + license_no + ", policy_no=" + policy_no
				+ ", month_count=" + month_count + ", start_date=" + start_date + ", end_date=" + end_date + ", status="
				+ status + ", change_date=" + change_date + "]";
	}
	
	

}
