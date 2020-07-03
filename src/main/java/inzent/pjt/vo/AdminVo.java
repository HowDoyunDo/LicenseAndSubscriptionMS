package inzent.pjt.vo;

public class AdminVo {
  String email;
  String password;
  String name;
  String co_number;
  
  public String getCo_number() {
    return co_number;
  }
  public void setCo_number(String co_number) {
    this.co_number = co_number;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
}