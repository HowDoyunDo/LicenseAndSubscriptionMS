package inzent.pjt.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import inzent.pjt.vo.PolicyVo;

@Repository
public class PolicyDao {
	@Autowired
	SqlSession sqlSession;
	
	public List<PolicyVo> getUserPolicyList(String sol) {
		return sqlSession.selectList("policy.getUserPolicyList", sol);
	}
}
