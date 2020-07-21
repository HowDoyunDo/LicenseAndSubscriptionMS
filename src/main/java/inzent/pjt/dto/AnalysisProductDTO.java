package inzent.pjt.dto;

public class AnalysisProductDTO {
	private int policy_no;
	private String policy_title;
	private char standard;
	private boolean visible;
	private int price;
	private int order_cnt;
	private int org_price;
	private int dc_price;
	private int total_price;
	
	public int getPolicy_no() {
		return policy_no;
	}
	public void setPolicy_no(int policy_no) {
		this.policy_no = policy_no;
	}
	public String getPolicy_title() {
		return policy_title;
	}
	public void setPolicy_title(String policy_title) {
		this.policy_title = policy_title;
	}
	public char getStandard() {
		return standard;
	}
	public void setStandard(char standard) {
		this.standard = standard;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getOrder_cnt() {
		return order_cnt;
	}
	public void setOrder_cnt(int order_cnt) {
		this.order_cnt = order_cnt;
	}
	public int getOrg_price() {
		return org_price;
	}
	public void setOrg_price(int org_price) {
		this.org_price = org_price;
	}
	public int getDc_price() {
		return dc_price;
	}
	public void setDc_price(int dc_price) {
		this.dc_price = dc_price;
	}
	public int getTotal_price() {
		return total_price;
	}
	public void setTotal_price(int total_price) {
		this.total_price = total_price;
	}
	@Override
	public String toString() {
		return "AnalysisProductDTO [policy_no=" + policy_no + ", policy_title=" + policy_title + ", standard="
				+ standard + ", visible=" + visible + ", price=" + price + ", order_cnt=" + order_cnt + ", org_price="
				+ org_price + ", dc_price=" + dc_price + ", total_price=" + total_price + "]";
	}
	
	
}
