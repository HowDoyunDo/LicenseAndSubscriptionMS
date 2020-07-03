package inzent.pjt.vo;

public class UserVo {
  private int no;                   // 사용자 식별 번호
  private String email;             // 사용자 로그인 이메일
  private String password;          // 사용자 로그인 비밀번호
  private String name;              // 사용자 이름
  private String co_name;           // 사용자 회사명
  private String co_location;       // 사용자 회사주소
  private String co_tel;            // 사용자 회사 대표번호
  private String co_number;         // 사용자 사업자 번호
  private String manager_type;      // 사용자가 마스터관리자(M)인지 서브관리자(S)인지 구별
  private String reg_date;          // 사용자 가입일
  
  
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
  public String getCo_name() {
    return co_name;
  }
  public void setCo_name(String co_name) {
    this.co_name = co_name;
  }
  public String getCo_location() {
    return co_location;
  }
  public void setCo_location(String co_location) {
    this.co_location = co_location;
  }
  public String getCo_tel() {
    return co_tel;
  }
  public void setCo_tel(String co_tel) {
    this.co_tel = co_tel;
  }
  public String getCo_number() {
    return co_number;
  }
  public void setCo_number(String co_number) {
    this.co_number = co_number;
  }
  public String getManager_type() {
    return manager_type;
  }
  public void setManager_type(String manager_type) {
    this.manager_type = manager_type;
  }
  public String getReg_date() {
    return reg_date;
  }
  public void setReg_date(String reg_date) {
    this.reg_date = reg_date;
  }
  
  
}