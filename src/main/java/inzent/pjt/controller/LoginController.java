package inzent.pjt.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import inzent.pjt.service.LoginService;
import inzent.pjt.vo.AdminVo;
import inzent.pjt.vo.UserVo;
import inzent.pjt.web.util.JwtUtil;

@RestController
public class LoginController {
  @Autowired
  LoginService loginservice;
  JwtUtil jwtutil = new JwtUtil();
  @Autowired
  private PasswordEncoder passwordEncoder;
  
  // 회원가입
  @PostMapping("/signup")
  public String signUp(@RequestBody UserVo vo) throws Exception {
    String pw = passwordEncoder.encode(vo.getPassword());
    vo.setPassword(pw);
    loginservice.signUp(vo);

    return "/successSignUp";
  }
  
  // 고객관리자 로그인
  @PostMapping("/login")
  public String login(@RequestBody UserVo vo) throws Exception {
    
    List<UserVo> list = loginservice.loginChk(vo);
    
    if (list.size() != 0) {
      if(passwordEncoder.matches(vo.getPassword(), list.get(0).getPassword())) {
        return jwtutil.createKey(list.get(0));
      }
    }
    return null;
  }
  
  // 시스템관리자 로그인
  @PostMapping("/adminlogin")
  public String adminlogin(@RequestBody AdminVo vo) throws Exception{
    
    List<AdminVo> list = loginservice.adminloginChk(vo);
    
    if (list.size() != 0) {
      if(passwordEncoder.matches(vo.getPassword(), list.get(0).getPassword())) {
        return jwtutil.createKey(list.get(0));
      }
    }
    return null;
  }
  
  // 이메일 중복 체크
  @PostMapping("/emailchk")
  public String emailChk(@RequestBody UserVo vo) throws Exception {
    return loginservice.emailChk(vo);
  }
  
  // 이메일 인증
  @PostMapping("/emailauth")
  public SimpleMailMessage emailauth(@RequestBody UserVo vo) throws Exception{
    SimpleMailMessage msg = loginservice.emailAuth(vo.getEmail(), "이메일 인증번호", "내용");
    
    return msg;
  }
}