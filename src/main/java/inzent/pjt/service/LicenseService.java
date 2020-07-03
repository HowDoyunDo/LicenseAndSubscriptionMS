package inzent.pjt.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import inzent.pjt.repository.LicenseDao;
import inzent.pjt.vo.GeneralUserVo;
import inzent.pjt.vo.LicenseVO2;
import inzent.pjt.vo.LicenseVo;
import inzent.pjt.vo.UserVo;

@Service
public class LicenseService {
	@Autowired
	LicenseDao licenseDao;

	public List<LicenseVo> getLicenseList(int adminNo) {
		return licenseDao.getLicenseList(adminNo);
	}
	
	public List<GeneralUserVo> getUserList(int licenseNo) {
		return licenseDao.getUserList(licenseNo);
	}

	public List<GeneralUserVo> getSearch(int licenseNo, String email, String name, String dept, String start, String end) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("licenseNo", licenseNo);
		map.put("email", email);
		map.put("name", name);
		map.put("dept", dept);
		map.put("start", start);
		map.put("end", end);
		
		if(start == "" && end == "") 
			return licenseDao.getSearch2(map);
		else
			return licenseDao.getSearch1(map);
	}

	public boolean delUser(String delUsrs, int licenseNo) {
		return licenseDao.delUser(delUsrs, licenseNo);
	}
	
	//add
	public  List<LicenseVO2> getLicenseAllList() {
		return licenseDao.getLicenseAllList();
	}
	public  List<LicenseVO2> getLicenseOneList(LicenseVO2 vo) {
		return licenseDao.getLicenseOneList(vo);
	}
	public UserVo pwCheck(UserVo vo) {
		return licenseDao.pwCheck(vo);
	}
	public int setLicenseCancel(LicenseVO2 vo) {
		return licenseDao.setLicenseCancel(vo);
	}
	public  List<LicenseVO2> getLicenseUserList(UserVo vo) {
		return licenseDao.getLicenseUserList(vo);
	}
}
