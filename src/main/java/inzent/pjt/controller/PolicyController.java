package inzent.pjt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import inzent.pjt.service.PolicyService;
import inzent.pjt.vo.PolicyVo;

@RestController
public class PolicyController {
	@Autowired
	PolicyService policyService;

	@GetMapping("/policylist")
	public List<PolicyVo> getUserPolicyList(
			@RequestParam(value = "sol") String sol
			) {
		return policyService.getUserPolicyList(sol);
	}
}