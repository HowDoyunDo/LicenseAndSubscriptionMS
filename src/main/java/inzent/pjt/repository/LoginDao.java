package inzent.pjt.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import inzent.pjt.vo.LoginVo;

@Repository
public class LoginDao {
  
  @Autowired
  private SqlSession sqlSession;
  
  public List<LoginVo> loginList(){
    List<LoginVo> loginList = sqlSession.selectList("login.getLoginList");
    return loginList;
  }
}