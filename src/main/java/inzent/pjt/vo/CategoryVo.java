package inzent.pjt.vo;

public class CategoryVo {
  private int no;
  private String title;
  private String reg_date;
  private String ca_title;
  
  public String getCa_title() {
    return ca_title;
  }
  public void setCa_title(String ca_title) {
    this.ca_title = ca_title;
  }
  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getReg_date() {
    return reg_date;
  }
  public void setReg_date(String reg_date) {
    this.reg_date = reg_date;
  }
}
