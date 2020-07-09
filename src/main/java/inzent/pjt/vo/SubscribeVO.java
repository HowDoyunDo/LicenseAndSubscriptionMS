package inzent.pjt.vo;

import java.util.ArrayList;

public class SubscribeVO {
	private int no;
	private String policy_title;
	private String standard;
	private int max_count;
	private int price;
	private boolean visible;
	private String url;
	private String reg_date;
	private ArrayList<ProductVO> selectedList; // 정책 등록 시 선택한 제품들
	private int policy_no; // 정책 등록 시 필요한 정책번호
	private String error_message ;
	
	
	public String getError_message() {
		return error_message;
	}

	public void setError_message(String error_message) {
		this.error_message = error_message;
	}

	public int getPolicy_no() {
		return policy_no;
	}

	public void setPolicy_no(int policy_no) {
		this.policy_no = policy_no;
	}

	public ArrayList<ProductVO>  getSelectedList() {
		return selectedList;
	}
	
	public void setSelectedList( ArrayList<ProductVO> selectedList) {
		this.selectedList = selectedList;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getPolicy_title() {
		return policy_title;
	}

	public void setPolicy_title(String policy_title) {
		this.policy_title = policy_title;
	}

	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
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
		return "SubscribeVO [no=" + no + ", policy_title=" + policy_title + ", standard=" + standard + ", max_count="
				+ max_count + ", price=" + price + ", visible=" + visible + ", url=" + url + ", reg_date=" + reg_date
				+ "]";
	}

}
