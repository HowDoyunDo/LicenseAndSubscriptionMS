package inzent.pjt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import inzent.pjt.repository.PolicyDao;
import inzent.pjt.vo.PolicyVo;

@Service
public class PolicyService {
	@Autowired
	PolicyDao policyDao;

	public List<PolicyVo> getUserPolicyList(String sol) {
		return policyDao.getUserPolicyList(sol);
	}
	
	
}
