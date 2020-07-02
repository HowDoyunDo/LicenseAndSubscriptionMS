package inzent.pjt.repository;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import inzent.pjt.vo.AdminVo;
import inzent.pjt.vo.UserVo;

@Repository
public class LoginDao {

  @Autowired
  SqlSession session;
  
  // 회원가입
  public void signUp(UserVo vo) {
    int a = session.selectOne("signUp", vo);
    
    if(a == 0) {
      session.insert("signUpM",vo);
    }
    else {
      session.insert("signUpS", vo);
    }
  }
  
  // 고객관리자 로그인
  public List<UserVo> loginChk(UserVo vo) {
    
    List<UserVo> list;
    
    list = session.selectList("loginChk", vo);
    
    return list;
  }
  
  // 시스템관리자 로그인
  public List<AdminVo> adminloginChk(AdminVo vo) {
    
    List<AdminVo> list;
    
    list = session.selectList("adminloginChk", vo);
    
    return list;
  }
  
  // 이메일 중복 체크
  public String emailChk(UserVo vo) {
    int a = session.selectOne("emailChk", vo);
    int b = session.selectOne("emailChk2", vo);
    if(a == 0 & vo.getEmail() != "" & b == 0) {
      return "s";
    }
    else {
      return "f";
    }
  }
}