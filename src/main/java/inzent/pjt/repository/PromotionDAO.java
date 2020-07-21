package inzent.pjt.repository;

import java.util.HashMap;
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
	public List<PromotionVO2> getPromotionPolicyAllList() {
		return sqlSession.selectList("promotion.getPromotionPolicyAllList");
	}
	public int setPromotionAdd(PromotionVO2 vo) {
		return sqlSession.insert("promotion.setPromotionAdd", vo);
	}
	public int setPromotionPolicyAdd(PromotionVO2 vo) {
		return sqlSession.insert("promotion.setPromotionPolicyAdd", vo);
	}
	public List<PromotionVO2> getPromotionOneList(PromotionVO2 vo){
		return sqlSession.selectList("promotion.getPromotionOneList", vo);
	}
	public List<PromotionVO2> getPromotionPolicyOneList(PromotionVO2 vo){
		return sqlSession.selectList("promotion.getPromotionPolicyOneList", vo);
	}
	public int setPromotionModify(PromotionVO2 vo) {
		return sqlSession.update("promotion.setPromotionModify", vo);
	}
	public int setPromotionPolicyModify(PromotionVO2 vo) {
		return sqlSession.update("promotion.setPromotionPolicyModify", vo);
	}
	public int setPromotionDelete(PromotionVO2 vo) {
		return sqlSession.delete("promotion.setPromotionDelete", vo);
	}
	public int setPromotionPolicyDelete(PromotionVO2 vo) {
		return sqlSession.delete("promotion.setPromotionPolicyDelete", vo);
	}
	public List<PromotionVO2> getPromotionCheckPNo(HashMap<String,Object> map) {
		return sqlSession.selectList("promotion.getPromotionCheckPNo",map);
	}
	public List<PromotionVO2> getPromotionPolicyCheckPNo(PromotionVO2 vo) {
		return sqlSession.selectList("promotion.getPromotionPolicyCheckPNo",vo);
	}

}
