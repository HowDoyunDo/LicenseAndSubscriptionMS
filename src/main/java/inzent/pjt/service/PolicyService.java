package inzent.pjt.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import inzent.pjt.repository.PolicyDao;
import inzent.pjt.vo.PolicyVo;
import inzent.pjt.vo.PromotionVo;

@Service
public class PolicyService {
	@Autowired
	PolicyDao policyDao;

	public List<PolicyVo> getUserPolicyList(String sol) {
		return policyDao.getUserPolicyList(sol);
	}

	public List<PolicyVo> getAgentPolicyList(String sol) {
		return policyDao.getAgentPolicyList(sol);
	}

	public List<PromotionVo> getPromotionList(String startdate, int policyno) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startdate", startdate);
		map.put("policyno", policyno);
		
		return policyDao.getPromotionList(map);
	}
	
	
}
