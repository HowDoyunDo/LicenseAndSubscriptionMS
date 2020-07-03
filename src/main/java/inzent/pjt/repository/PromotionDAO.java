package inzent.pjt.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import inzent.pjt.vo.PromotionVO2;

@Repository
public class PromotionDAO {
	@Autowired
	private SqlSession sqlSession;

	public List<PromotionVO2> getPromotionAllList() {
		return sqlSession.selectList("promotion.getPromotionAllList");
	}
	public int setPromotionAdd(PromotionVO2 vo) {
		return sqlSession.insert("promotion.setPromotionAdd", vo);
	}
	public List<PromotionVO2> getPromotionOneList(PromotionVO2 vo){
		return sqlSession.selectList("promotion.getPromotionOneList", vo);
	}

	public int setPromotionModify(PromotionVO2 vo) {
		return sqlSession.insert("promotion.setPromotionModify", vo);
	}
	public int setPromotionDelete(PromotionVO2 vo) {
		return sqlSession.insert("promotion.setPromotionDelete", vo);
	}
}
