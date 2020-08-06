package inzent.pjt.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import inzent.pjt.vo.PriceQuestionVo;

@Repository
public class PriceQuestionDao {
  @Autowired
  SqlSession session;
  
  public void priceQuestion(PriceQuestionVo vo) {
    session.insert("priceQuestion", vo);
    int n = session.selectOne("pricequestionOne", vo);
    for(int a = 0; a < vo.getProduct().length; a++) {
      Map<String, Object> map = new HashMap<String, Object>();
      map.put("no", n);
      map.put("product", vo.getProduct()[a]);
      session.insert("pricequestionproduct",map);
    }
  }
  
  public List<PriceQuestionVo> priceQuestionList(PriceQuestionVo vo){
    return session.selectList("priceQuestionList", vo);
  }
  
  public List<PriceQuestionVo> productNameList(PriceQuestionVo vo){
    return session.selectList("productNameList", vo);
  }
  
  public PriceQuestionVo priceQuestionInfo(PriceQuestionVo vo) {
    return session.selectOne("priceQuestionInfo", vo);
  }
  
  public PriceQuestionVo priceQuestionMailForm(PriceQuestionVo vo) {
    return session.selectOne("priceQuestionMailForm", vo);
  }
  
  public void stateChange(PriceQuestionVo vo) {
    session.update("stateChange", vo);
  }
}
