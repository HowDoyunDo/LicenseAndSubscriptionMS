package inzent.pjt.vo;

import java.util.Arrays;

public class PriceQuestionVo {
  private int no;
  private String email;
  private String name;
  private String co_name;
  private String co_tel;
  private String standard;
  private int count;
  private String contents;
  private String reg_date;
  private String status;
  private int[] product;
  private String pname;
  
  public String getPname() {
    return pname;
  }
  public void setPname(String pname) {
    this.pname = pname;
  }
  public int[] getProduct() {
    return product;
  }
  public void setProduct(int[] product) {
    this.product = product;
  }
  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
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
  public String getCo_tel() {
    return co_tel;
  }
  public void setCo_tel(String co_tel) {
    this.co_tel = co_tel;
  }
  public String getStandard() {
    return standard;
  }
  public void setStandard(String standard) {
    this.standard = standard;
  }
  public int getCount() {
    return count;
  }
  public void setCount(int count) {
    this.count = count;
  }
  public String getContents() {
    return contents;
  }
  public void setContents(String contents) {
    this.contents = contents;
  }
  public String getReg_date() {
    return reg_date;
  }
  public void setReg_date(String reg_date) {
    this.reg_date = reg_date;
  }
  public String getStatus() {
    return status;
  }
  public void setStatus(String status) {
    this.status = status;
  }
  @Override
  public String toString() {
    return "PriceQuestionVo [no=" + no + ", email=" + email + ", name=" + name + ", co_name="
        + co_name + ", co_tel=" + co_tel + ", standard=" + standard + ", count=" + count
        + ", contents=" + contents + ", reg_date=" + reg_date + ", status=" + status + ", product="
        + Arrays.toString(product) + "]";
  }
}
