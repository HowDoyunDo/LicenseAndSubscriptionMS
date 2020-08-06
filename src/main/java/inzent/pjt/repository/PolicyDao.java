package inzent.pjt.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import inzent.pjt.vo.PolicyVo;
import inzent.pjt.vo.PromotionVo;

@Repository
public class PolicyDao {
	@Autowired
	SqlSession sqlSession;
	
	public List<PolicyVo> getUserPolicyList(String sol) {
		return sqlSession.selectList("policy.getUserPolicyList", sol);
	}

	public List<PolicyVo> getAgentPolicyList(String sol) {
		return sqlSession.selectList("policy.getAgentPolicyList", sol);
	}

	public List<PromotionVo> getPromotionList(Map<String, Object> map) {
		return sqlSession.selectList("policy.getPromotionList", map);
	}

	public PolicyVo getUpdatePolicy(int policyNo) {
		return sqlSession.selectOne("policy.getUpdatePolicy", policyNo);
	}
}
