package inzent.pjt.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import inzent.pjt.service.PriceQuestionService;
import inzent.pjt.vo.PriceQuestionVo;

@RestController
public class PriceQuestionController {
  @Autowired
  PriceQuestionService pricequestionservice;
  
  @PostMapping("/pricequestion")
  public void priceQuestion(@RequestBody PriceQuestionVo vo) throws Exception{
    pricequestionservice.priceQuestion(vo);
  }
  
  @GetMapping("/pricequestionlist")
  public List<PriceQuestionVo> priceQuestionList(PriceQuestionVo vo) throws Exception{
    return pricequestionservice.priceQuestionList(vo);
  }
  
  @GetMapping("/productnamelist")
  public List<PriceQuestionVo> productNameList(PriceQuestionVo vo) throws Exception{
    return pricequestionservice.productNameList(vo);
  }
  
  @PostMapping("/pricequestioninfo")
  public PriceQuestionVo priceQuestionInfo(@RequestBody PriceQuestionVo vo) throws Exception{
    return pricequestionservice.priceQuestionInfo(vo);
  }
  
  @PostMapping("/pricequestionmailform")
  public PriceQuestionVo priceQuestionMailForm(@RequestBody PriceQuestionVo vo) throws Exception{
    return pricequestionservice.priceQuestionMailForm(vo);
  }
  
  @PostMapping("/statechange")
  public void stateChange(@RequestBody PriceQuestionVo vo) throws Exception{
    pricequestionservice.stateChange(vo);
  }
  
  @PostMapping("/emailsend")
  public SimpleMailMessage emailSend(@RequestBody PriceQuestionVo vo) throws Exception{
    SimpleMailMessage msg = pricequestionservice.emailSend(vo.getEmail(), "견적 문의 답장", vo.getContents());
    
    return msg;
  }
}
