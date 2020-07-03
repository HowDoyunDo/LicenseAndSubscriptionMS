package inzent.pjt.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import inzent.pjt.repository.LoginDao;
import inzent.pjt.vo.AdminVo;
import inzent.pjt.vo.UserVo;

@Service
public class LoginService {
  @Autowired
  LoginDao logindao;
  @Autowired
  private JavaMailSender emailSender;
  
  public void signUp(UserVo vo) {
    logindao.signUp(vo);
  }
  public List<UserVo> loginChk(UserVo vo) {
    return logindao.loginChk(vo);
  }
  public List<AdminVo> adminloginChk(AdminVo vo){
    return logindao.adminloginChk(vo);
  }
  public String emailChk(UserVo vo) {
    return logindao.emailChk(vo);
  }
  
  // 메일 전송
  public SimpleMailMessage emailAuth(String to, String subject, String text) {
    System.out.println(to + "/" + subject + "/" + text);
    SimpleMailMessage msg = createMessage(to, subject, text);
        try {
          emailSender.send(msg);
          return msg;
        }catch(MailException es) {
          es.printStackTrace();
          throw new IllegalArgumentException();
        }
  }
  
  // 전송할 메시지 생성
  private SimpleMailMessage createMessage(String to, String subject, String text) {
    
    SimpleMailMessage message = new SimpleMailMessage();
    
    message.setTo(to);
    message.setSubject(subject);
    message.setText("회원가입 이메일 인증 번호 : "+authNum());
    
    
    System.out.println(message);
    
    return message;
  }
  
  // 인증번호 : 난수발생 function
  private static String authNum(){
     StringBuffer buffer=new StringBuffer();
     for(int i=0;i<=4;i++){
        int num=(int)(Math.random()*9+1);
        buffer.append(num);
     }
     return buffer.toString();
  }

}