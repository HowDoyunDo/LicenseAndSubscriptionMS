package inzent.pjt.vo;

public class PolicyVo {
	int no;
	String policy_title;
	char standard;
	int max_count;
	int price;
	boolean visible;
	String url;
	String reg_date;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getPolicy_title() {
		return policy_title;
	}
	public void setPolicy_title(String title) {
		this.policy_title = title;
	}
	public char getStandard() {
		return standard;
	}
	public void setStandard(char standard) {
		this.standard = standard;
	}
	public int getMax_count() {
		return max_count;
	}
	public void setMax_count(int max_count) {
		this.max_count = max_count;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	
	@Override
	public String toString() {
		return "PolicyVo [no=" + no + ", title=" + policy_title + ", standard=" + standard + ", max_count=" + max_count
				+ ", price=" + price + ", visible=" + visible + ", url=" + url + ", reg_date=" + reg_date + "]";
	}
}
