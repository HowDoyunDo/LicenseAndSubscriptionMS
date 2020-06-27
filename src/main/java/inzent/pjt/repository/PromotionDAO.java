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

}
