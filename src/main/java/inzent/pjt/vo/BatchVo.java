package inzent.pjt.vo;

public class BatchVo {
	private int no;
	private int batch_period_days;
	private int login_limit_months;
	private int login_limit_days;
	private int login_limit_hours;
	private String recent_batch;
	private String next_batch;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getBatch_period_days() {
		return batch_period_days;
	}
	public void setBatch_period_days(int batch_period_days) {
		this.batch_period_days = batch_period_days;
	}
	public int getLogin_limit_months() {
		return login_limit_months;
	}
	public void setLogin_limit_months(int login_limit_months) {
		this.login_limit_months = login_limit_months;
	}
	public int getLogin_limit_days() {
		return login_limit_days;
	}
	public void setLogin_limit_days(int login_limit_days) {
		this.login_limit_days = login_limit_days;
	}
	public int getLogin_limit_hours() {
		return login_limit_hours;
	}
	public void setLogin_limit_hours(int login_limit_hours) {
		this.login_limit_hours = login_limit_hours;
	}
	public String getRecent_batch() {
		return recent_batch;
	}
	public void setRecent_batch(String recent_batch) {
		this.recent_batch = recent_batch;
	}
	public String getNext_batch() {
		return next_batch;
	}
	public void setNext_batch(String next_batch) {
		this.next_batch = next_batch;
	}
	@Override
	public String toString() {
		return "BatchVo [no=" + no + ", batch_period_days=" + batch_period_days + ", login_limit_months="
				+ login_limit_months + ", login_limit_days=" + login_limit_days + ", login_limit_hours="
				+ login_limit_hours + ", recent_batch=" + recent_batch + ", next_batch=" + next_batch + "]";
	}

	
}
