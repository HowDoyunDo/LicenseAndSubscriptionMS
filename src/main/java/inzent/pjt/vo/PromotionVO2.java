package inzent.pjt.vo;

import java.util.ArrayList;

public class PromotionVO2 {
	private int promotion_no;
	private int product_no;
	private String product_name;
	private String title;
	private int discount;
	private String start_date;
	private String start_date_time;
	private String end_date;
	private String end_date_time;
	private boolean type;
	private String reg_date;
	private ArrayList<ProductVO> selectedList; // 프로모션 등록 시 선택한 제품들

	public int getPromotion_no() {
		return promotion_no;
	}

	public void setPromotion_no(int promotion_no) {
		this.promotion_no = promotion_no;
	}

	public int getProduct_no() {
		return product_no;
	}

	public void setProduct_no(int product_no) {
		this.product_no = product_no;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getStart_date_time() {
		return start_date_time;
	}

	public void setStart_date_time(String start_date_time) {
		this.start_date_time = start_date_time;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public String getEnd_date_time() {
		return end_date_time;
	}

	public void setEnd_date_time(String end_date_time) {
		this.end_date_time = end_date_time;
	}

	public boolean isType() {
		return type;
	}

	public void setType(boolean type) {
		this.type = type;
	}

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	public ArrayList<ProductVO> getSelectedList() {
		return selectedList;
	}

	public void setSelectedList(ArrayList<ProductVO> selectedList) {
		this.selectedList = selectedList;
	}

	@Override
	public String toString() {
		return "PromotionVO [promotion_no=" + promotion_no + ", product_no=" + product_no + ", product_name="
				+ product_name + ", title=" + title + ", discount=" + discount + ", start_date=" + start_date
				+ ", start_date_time=" + start_date_time + ", end_date=" + end_date + ", end_date_time=" + end_date_time
				+ ", type=" + type + ", reg_date=" + reg_date + ", selectedList=" + selectedList + "]";
	}

}
