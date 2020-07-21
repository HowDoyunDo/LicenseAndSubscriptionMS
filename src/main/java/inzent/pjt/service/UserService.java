package inzent.pjt.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import inzent.pjt.repository.UserDao;
import inzent.pjt.vo.CancelVo;
import inzent.pjt.vo.ChangeVo;
import inzent.pjt.vo.OrderVo;
import inzent.pjt.vo.UserVo;

@Service
public class UserService {
  @Autowired
  UserDao userdao;
  
  public List<UserVo> userList(UserVo vo){
    return userdao.userList(vo);
  }
  
  public UserVo userInfo(UserVo vo) {
    return userdao.userInfo(vo);
  }
  
  public List<CancelVo> cancelList(CancelVo vo){
    return userdao.cancelList(vo);
  }
  
  public List<ChangeVo> changeList(ChangeVo vo){
    return userdao.changeList(vo);
  }
  
  public List<OrderVo> orderList(OrderVo vo){
    return userdao.orderList(vo);
  }
  
  public OrderVo orderInfo(OrderVo vo) {
    return userdao.orderInfo(vo);
  }
}
