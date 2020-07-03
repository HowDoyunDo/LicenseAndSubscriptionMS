package inzent.pjt.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import inzent.pjt.vo.PromotionVO;

@Repository
public class PromotionDAO {
	@Autowired
	private SqlSession sqlSession;

	public List<PromotionVO> getPromotionAllList() {
		return sqlSession.selectList("promotion.getPromotionAllList");
	}
	public int setPromotionAdd(PromotionVO vo) {
		return sqlSession.insert("promotion.setPromotionAdd", vo);
	}
	public List<PromotionVO> getPromotionOneList(PromotionVO vo){
		return sqlSession.selectList("promotion.getPromotionOneList", vo);
	}

	public int setPromotionModify(PromotionVO vo) {
		return sqlSession.insert("promotion.setPromotionModify", vo);
	}
	public int setPromotionDelete(PromotionVO vo) {
		return sqlSession.insert("promotion.setPromotionDelete", vo);
	}
}
