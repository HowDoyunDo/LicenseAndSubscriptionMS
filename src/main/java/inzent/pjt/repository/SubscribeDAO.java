package inzent.pjt.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import inzent.pjt.vo.SubscribeVO;

@Repository
public class SubscribeDAO {

	@Autowired
	private SqlSession sqlSession;

	public List<SubscribeVO> getSubList() {
		return sqlSession.selectList("subscribe.getSubAllList");
	}
	
	public int setSubAdd(SubscribeVO vo) {
		return sqlSession.insert("subscribe.setSubAdd", vo);
	}
	
	public int getMaxNo() {
		return sqlSession.selectOne("subscribe.getMaxNo");
	}
	
	
	
}
