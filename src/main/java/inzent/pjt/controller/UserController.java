package inzent.pjt.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import inzent.pjt.service.UserService;
import inzent.pjt.vo.CancelVo;
import inzent.pjt.vo.ChangeVo;
import inzent.pjt.vo.OrderVo;
import inzent.pjt.vo.UserVo;

@RestController
public class UserController {
  @Autowired
  UserService userservice;
  
  @GetMapping("/userlist")
  public List<UserVo> userList(UserVo vo) throws Exception{
    return userservice.userList(vo);
  }
  
  @PostMapping("/userinfo")
  public UserVo userInfo(@RequestBody UserVo vo) throws Exception{
    return userservice.userInfo(vo);
  }
  
  @GetMapping("/cancellist")
  public List<CancelVo> cancelList(CancelVo vo) throws Exception{
    return userservice.cancelList(vo);
  }
  
  @GetMapping("/changelist")
  public List<ChangeVo> changeList(ChangeVo vo) throws Exception{
    return userservice.changeList(vo);
  }
  
  @GetMapping("/orderlist")
  public List<OrderVo> orderList(OrderVo vo) throws Exception{
    return userservice.orderList(vo);
  }
  
  @PostMapping("/orderinfo")
  public OrderVo orderInfo(@RequestBody OrderVo vo) throws Exception{
    System.out.println(vo.getNo());
    return userservice.orderInfo(vo);
  }
}
