package inzent.pjt.vo;

public class PromotionVO {
	 private int promotion_no;
	 private int product_no;
	 private String product_name;
	 private String title; 
	 private int discount;
	 private String start_date;
	 private String end_date;
	 private boolean type;
	 private String reg_date;
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
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public boolean getType() {
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
	@Override
	public String toString() {
		return "PromotionVO [promotion_no=" + promotion_no + ", product_no=" + product_no + ", product_name="
				+ product_name + ", title=" + title + ", discount=" + discount + ", start_date=" + start_date
				+ ", end_date=" + end_date + ", type=" + type + ", reg_date=" + reg_date + "]";
	}
	 
	 
}
