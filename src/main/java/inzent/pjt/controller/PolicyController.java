package inzent.pjt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import inzent.pjt.service.PolicyService;
import inzent.pjt.vo.PolicyVo;
import inzent.pjt.vo.PromotionVo;

@RestController
public class PolicyController {
	@Autowired
	PolicyService policyService;

	@GetMapping("/userpolicylist")
	public List<PolicyVo> getUserPolicyList(
			@RequestParam(value = "sol") String sol
			) {
		return policyService.getUserPolicyList(sol);
	}
	
	@GetMapping("/agentpolicylist")
	public List<PolicyVo> getAgentPolicyList(
			@RequestParam(value = "sol") String sol
			) {
		return policyService.getAgentPolicyList(sol);
	}

	@GetMapping("/promotion")
	public List<PromotionVo> getPromotionList(
			@RequestParam(value = "startdate") String startdate,
			@RequestParam(value = "policyno") int policyno
			) {
		
		return policyService.getPromotionList(startdate, policyno);
	}
	
	// 정기 결제 구현 중 중단
//	@PostMapping("/subscription/issue-billing")
//	public CardVo getBillingInfo(
//			@RequestBody CardVo cardvo
//			) {
//		Map<String, Object> map = new HashMap<String, Object>();
//		
//		System.out.println("hello");
//		
//		return cardvo;
//	}
}