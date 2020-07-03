package inzent.pjt.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import inzent.pjt.vo.OrderListVO;
import inzent.pjt.vo.SubscribeVO;
import inzent.pjt.vo.UserVo;

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

	public List<SubscribeVO> getSubscribeOneList(int subscribeNo) {
		return sqlSession.selectList("subscribe.getSubscribeOneList", subscribeNo);
	}

	public int setSubscribeModify(SubscribeVO vo) {
		return sqlSession.insert("subscribe.setSubModify", vo);
	}
	
	public int setSubscribeAllModify(SubscribeVO vo) {
		return sqlSession.insert("subscribe.setSubAllModify", vo);
	}
	
	public int getLicenseCheck(int subscribeNo) {
		return sqlSession.selectOne("subscribe.getLicenseCheck", subscribeNo);
	}

	public List<OrderListVO> getUserSubscribeList(UserVo vo){
		return sqlSession.selectList("subscribe.getUserSubscribeList",vo);
	}
}