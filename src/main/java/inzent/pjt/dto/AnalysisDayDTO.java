package inzent.pjt.dto;

public class AnalysisDayDTO {
	private String date;
	private int order_cnt;
	private int pol_cnt;
	private int org_price;
	private int dc_price;
	private int total_price;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getOrder_cnt() {
		return order_cnt;
	}
	public void setOrder_cnt(int order_cnt) {
		this.order_cnt = order_cnt;
	}
	public int getPol_cnt() {
		return pol_cnt;
	}
	public void setPol_cnt(int pol_cnt) {
		this.pol_cnt = pol_cnt;
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
		return "AnalysisDayVo [date=" + date + ", order_cnt=" + order_cnt + ", pol_cnt=" + pol_cnt + ", org_price="
				+ org_price + ", dc_price=" + dc_price + ", total_price=" + total_price + "]";
	}
	
	
}
