package inzent.pjt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import inzent.pjt.repository.LicenseDao;
import inzent.pjt.vo.LicenseVo;

@Service
public class LicenseService {
	@Autowired
	LicenseDao licenseDao;

	public List<LicenseVo> getLicenseList(int adminNo) {
		return licenseDao.getLicenseList(adminNo);
	}

	public LicenseVo getLicenseInfo(int policyNo) {
		return licenseDao.getLicenseInfo(policyNo);
	}
	
}
