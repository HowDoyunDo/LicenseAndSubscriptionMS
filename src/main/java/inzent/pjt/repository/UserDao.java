package inzent.pjt.repository;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import inzent.pjt.vo.CancelVo;
import inzent.pjt.vo.ChangeVo;
import inzent.pjt.vo.OrderVo;
import inzent.pjt.vo.UserVo;

@Repository
public class UserDao {
  @Autowired
  SqlSession session;
  
  public List<UserVo> userList(UserVo vo){
    List<UserVo> list = session.selectList("userList", vo);
    
    return list;
  }
  
  public UserVo userInfo(UserVo vo) {
    return session.selectOne("userInfo", vo);
  }
  
  public List<CancelVo> cancelList(CancelVo vo){
    List<CancelVo> list = session.selectList("cancelList", vo);
    
    return list;
  }
  
  public List<ChangeVo> changeList(ChangeVo vo){
    List<ChangeVo> list = session.selectList("changeList", vo);
    
    return list;
  }
  
  public List<OrderVo> orderList(OrderVo vo){
    List<OrderVo> list = session.selectList("orderList", vo);
    
    return list;
  }
  
  public OrderVo orderInfo(OrderVo vo) {
    return session.selectOne("orderInfo", vo);
  }
  
  public UserVo myInfo(UserVo vo) {
    return session.selectOne("myInfo", vo);
  }
  
  public void changeUserInfo(UserVo vo) {
    session.update("changeUserInfo", vo);
  }
  
  public int chkPw(UserVo vo) {
    return session.selectOne("chkPw", vo);
  }
}