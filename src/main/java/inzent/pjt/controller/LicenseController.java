package inzent.pjt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import inzent.pjt.service.LicenseService;
import inzent.pjt.vo.LicenseVo;

@RestController
public class LicenseController {

	@Autowired
	LicenseService licenseService;
	
	@GetMapping("/license/list")
	public List<LicenseVo> getLicenseList(
			@RequestParam(value = "adminNo") int adminNo
			) {
		return licenseService.getLicenseList(adminNo);
	}
	
	@GetMapping("/license/info")
	public LicenseVo getLicenseInfo(
			@RequestParam(value = "policyNo") int policyNo
			) {
		return licenseService.getLicenseInfo(policyNo);
	}
}
