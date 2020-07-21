package inzent.pjt.vo;

public class CancelVo {
  private int no;
  private int license_no;
  private int order_no;
  private String cancel_date;
  private int user_admin_no;
  private String policy_title;
  private String name;
  private String co_name;
  
  public String getPolicy_title() {
    return policy_title;
  }
  public void setPolicy_title(String policy_title) {
    this.policy_title = policy_title;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getCo_name() {
    return co_name;
  }
  public void setCo_name(String co_name) {
    this.co_name = co_name;
  }
  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public int getLicense_no() {
    return license_no;
  }
  public void setLicense_no(int license_no) {
    this.license_no = license_no;
  }
  public int getOrder_no() {
    return order_no;
  }
  public void setOrder_no(int order_no) {
    this.order_no = order_no;
  }
  public String getCancel_date() {
    return cancel_date;
  }
  public void setCancel_date(String cancel_date) {
    this.cancel_date = cancel_date;
  }
  public int getUser_admin_no() {
    return user_admin_no;
  }
  public void setUser_admin_no(int user_admin_no) {
    this.user_admin_no = user_admin_no;
  }
}
