package inzent.pjt.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import inzent.pjt.repository.PriceQuestionDao;
import inzent.pjt.vo.PriceQuestionVo;

@Service
public class PriceQuestionService {
  @Autowired
  PriceQuestionDao pricequestiondao;
  @Autowired
  private JavaMailSender emailSender;
  
  public void priceQuestion(PriceQuestionVo vo) {
    pricequestiondao.priceQuestion(vo);
  }
  
  public List<PriceQuestionVo> priceQuestionList(PriceQuestionVo vo) {
    return pricequestiondao.priceQuestionList(vo);
  }
  
  public List<PriceQuestionVo> productNameList(PriceQuestionVo vo){
    return pricequestiondao.productNameList(vo);
  }
  
  public PriceQuestionVo priceQuestionInfo(PriceQuestionVo vo) {
    return pricequestiondao.priceQuestionInfo(vo);
  }
  
  public PriceQuestionVo priceQuestionMailForm(PriceQuestionVo vo) {
    return pricequestiondao.priceQuestionMailForm(vo);
  }
  
  public SimpleMailMessage emailSend(String to, String subject, String text) {
    SimpleMailMessage msg = createMessage(to, subject, text);
    
    try {
      emailSender.send(msg);
      return msg;
    }catch(MailException es) {
      es.printStackTrace();
      throw new IllegalArgumentException();
    }
  }
  
  private SimpleMailMessage createMessage(String to, String subject, String text) {
    SimpleMailMessage message = new SimpleMailMessage();
    
    message.setTo(to);
    message.setSubject(subject);
    message.setText(text);
    
    return message;
  }
  
  public void stateChange(PriceQuestionVo vo) {
    pricequestiondao.stateChange(vo);
  }
}
