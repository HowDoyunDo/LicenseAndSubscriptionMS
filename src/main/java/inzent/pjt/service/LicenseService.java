package inzent.pjt.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import inzent.pjt.repository.LicenseDao;
import inzent.pjt.vo.AgentVo;
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

	public boolean addOrderList(int policyNo, int userNo, int monthCount, String startDate, String endDate, int orgPrice,
			int dcPrice, int totalPrice, boolean monthPay) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("policyNo", policyNo);
		map.put("userNo", userNo);
		map.put("monthCount", monthCount);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("orgPrice", orgPrice);
		map.put("dcPrice", dcPrice);
		map.put("totalPrice", totalPrice);
		map.put("monthPay", monthPay);
		
		return licenseDao.addOrderList(map);
	}

	public boolean addLicense(int userAdminNo, int policyNo, String licenseKey, int currentCount, int maxCount,
			String startDate, String endDate) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userAdminNo", userAdminNo);
		map.put("policyNo", policyNo);
		map.put("licenseKey", licenseKey);
		map.put("currentCount", currentCount);
		map.put("maxCount", maxCount);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		
		return licenseDao.addLicense(map);
	}

	public boolean addUser(String email, String password, String name, String dept, int adminUserNo, int licenseNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("email", email);
		map.put("password", password);
		map.put("name", name);
		map.put("dept", dept);
		map.put("adminUserNo", adminUserNo);
		map.put("licenseNo", licenseNo);
		
		return licenseDao.addUser(map);
	}

	public boolean addUsers(List<GeneralUserVo> userList, int adminUserNo, int licenseNo) {
		return licenseDao.addusers(userList, adminUserNo, licenseNo);
	}

	public List<AgentVo> getAgentList(int licenseNo) {
		return licenseDao.getAgentList(licenseNo);
	}

	public boolean delAgent(String delUsrs, int licenseNo) {
		return licenseDao.delAgent(delUsrs, licenseNo);
	}

	public boolean addAgent(String email, String password, String name, String dept, String agentname, String macaddr,
			String ipaddr, int adminUserNo, int licenseNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("email", email);
		map.put("password", password);
		map.put("name", name);
		map.put("dept", dept);
		
		map.put("agentname", agentname);
		
		map.put("adminUserNo", adminUserNo);
		map.put("licenseNo", licenseNo);
		
		if(macaddr.equals("")) {
			map.put("ipaddr", ipaddr);
			return licenseDao.addAgentIp(map);
		} else {
			map.put("macaddr", macaddr);
			return licenseDao.addAgentMac(map);
		}
	}

	public List<AgentVo> getSearchAgent(int licenseNo, String email, String name, String dept, String start,
			String end, String agentname, String macaddr, String ipaddr) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("licenseNo", licenseNo);
		
		map.put("email", email);
		map.put("name", name);
		map.put("dept", dept);
		map.put("start", start);
		map.put("end", end);
		
		map.put("agentname", agentname);
		map.put("macaddr", macaddr);
		map.put("ipaddr", ipaddr);
		
		if(start == "" && end == "") 
			if(macaddr.equals("") && ipaddr != "") {
				return licenseDao.getSearchAgentIp2(map);
			} else if (macaddr != "" && ipaddr.equals("")) {
				return licenseDao.getSearchAgentMac2(map);
			} else if (macaddr.equals("") && ipaddr.equals("")) {
				return licenseDao.getSearchAgent2(map);
			} else 
				return null;
		else {
			if(macaddr.equals("") && ipaddr != "") {
				return licenseDao.getSearchAgentIp1(map);
			} else if (macaddr != "" && ipaddr.equals("")) {
				return licenseDao.getSearchAgentMac1(map);
			} else if (macaddr.equals("") && ipaddr.equals("")) {
				return licenseDao.getSearchAgent1(map);
			} else 
				return null;
		}
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
	public  List<UserVo> getLicenseAdminUser(LicenseVO2 vo) {
		return licenseDao.getLicenseAdminUser(vo);
	}

	
}
