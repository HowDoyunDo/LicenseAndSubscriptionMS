package inzent.pjt.dto;

public class ProductDTO {
	private int no;
	private String name;
	private String comments;
	private int price;
	private String reg_date;
	private String product_image;
	private String info_image;
	private int category_no;
	private String title;
	private String category_image;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public String getProduct_image() {
		return product_image;
	}
	public void setProduct_image(String product_image) {
		this.product_image = product_image;
	}
	public String getInfo_image() {
		return info_image;
	}
	public void setInfo_image(String info_image) {
		this.info_image = info_image;
	}
	public int getCategory_no() {
		return category_no;
	}
	public void setCategory_no(int category_no) {
		this.category_no = category_no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCategory_image() {
		return category_image;
	}
	public void setCategory_image(String category_image) {
		this.category_image = category_image;
	}
	@Override
	public String toString() {
		return "ProductDTO [no=" + no + ", name=" + name + ", comments=" + comments + ", price=" + price + ", reg_date="
				+ reg_date + ", product_image=" + product_image + ", info_image=" + info_image + ", category_no="
				+ category_no + ", title=" + title + ", category_image=" + category_image + "]";
	}
	
	
}
