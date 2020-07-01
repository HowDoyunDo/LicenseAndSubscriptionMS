package inzent.pjt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import inzent.pjt.service.LicenseService;
import inzent.pjt.vo.GeneralUserVo;
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
	
	@GetMapping("/license/userlist")
	public List<GeneralUserVo> getUserList(
			@RequestParam(value = "licenseNo") int licenseNo
			) {
		return licenseService.getUserList(licenseNo);
	}

	@GetMapping("/license/search")
	public List<GeneralUserVo> getSearch(
			@RequestParam(value = "licenseNo") int licenseNo,
			@RequestParam(value = "email") String email,
			@RequestParam(value = "name") String name,
			@RequestParam(value = "dept") String dept,
			@RequestParam(value = "start") String start,
			@RequestParam(value = "end") String end
			) {
		return licenseService.getSearch(licenseNo, email, name, dept, start, end);
	}
}
