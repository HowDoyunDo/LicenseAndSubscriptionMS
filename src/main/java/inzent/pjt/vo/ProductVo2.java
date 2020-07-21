package inzent.pjt.vo;

public class ProductVo2 {
  private int no;
  private int category_no;
  private String name;
  private String comments;
  private int price;
  private String reg_date;
  private String title;
  private String product_image;
  private String info_image;
  
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
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public int getCategory_no() {
    return category_no;
  }
  public void setCategory_no(int category_no) {
    this.category_no = category_no;
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
  
}
