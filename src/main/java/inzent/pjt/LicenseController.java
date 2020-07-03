package inzent.pjt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import inzent.pjt.service.LicenseService;
import inzent.pjt.vo.LicenseVO;
import inzent.pjt.vo.UserVo;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class LicenseController {

	@Autowired
	LicenseService service;

	@PostMapping("/licenseList")
	public List<LicenseVO> licenseAllList() {
		System.out.println("/licenseList");

		List<LicenseVO> allList = service.getLicenseAllList();

		return allList;
	}

	@PostMapping("/licenseOneList")
	public List<LicenseVO> licenseOneList(@RequestBody LicenseVO licensevo) {
		System.out.println("/licenseOneList");
		List<LicenseVO> list = service.getLicenseOneList(licensevo);

		return list;
	}

	@PostMapping("/userPwCheck")
	public UserVo userPwCheck(@RequestBody UserVo vo) {
		System.out.println("/userPwCheck");
		UserVo uservo = service.pwCheck(vo);

		return uservo;
	}

	@PostMapping("/licenseCancel")
	public int licenseCancel(@RequestBody LicenseVO licensevo) {
		System.out.println("/licenseCancel");
		int result = service.setLicenseCancel(licensevo);
		
		return result;
	}
}
