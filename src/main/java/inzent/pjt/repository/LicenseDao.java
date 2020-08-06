package inzent.pjt.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.modelmapper.internal.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition.Undefined;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import inzent.pjt.vo.AgentVo;
import inzent.pjt.vo.GeneralUserVo;
import inzent.pjt.vo.LicenseVO2;
import inzent.pjt.vo.LicenseVo;
import inzent.pjt.vo.UserVo;

@Repository
public class LicenseDao {
	@Autowired
	SqlSession sqlSession;

	public List<LicenseVo> getLicenseList(int adminNo) {
		return sqlSession.selectList("license.getLicenseList", adminNo);
	}

	public List<AgentVo> getUserList(int licenseNo) {
		return sqlSession.selectList("license.getUserList", licenseNo);
	}

	public List<GeneralUserVo> getSearch1(Map<String, Object> map) {
		return sqlSession.selectList("license.getSearch1", map);
	}
	
	public List<GeneralUserVo> getSearch2(Map<String, Object> map) {
		return sqlSession.selectList("license.getSearch2", map);
	}

	public boolean delUser(String delUsrs, int licenseNo) {
		int cnt = 0;
		for(int i=0; i<delUsrs.length(); i++) {
			if(delUsrs.charAt(i) == ',' ) 
				cnt++;
		}
		
		if(delUsrs.indexOf(")") != -1) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("delUsrs", delUsrs);
			map.put("licenseNo", licenseNo);
			sqlSession.delete("license.delUser", map);
			
			for(int i = 0; i<cnt+1; i++) { 
				sqlSession.update("license.downCurrentCount", licenseNo);
			}
			return true;
		} else {
			return false;
		}
	}

	public boolean addOrderList(Map<String, Object> map) {
		return sqlSession.insert("addOrderList", map) == 1;
	}

	public boolean addLicense(Map<String, Object> map) {
		return sqlSession.insert("addLicense", map) == 1;
	}

	public boolean addUser(Map<String, Object> map) {
		if(sqlSession.selectOne("license.dupUserEmail", map) == null || sqlSession.selectOne("dupUserEmail", map) == "") {	
			if(sqlSession.selectOne("license.dupUser", map) == null) {	// 해당하는 이메일을 가진 사용자가 라이센스 내부, 외부 모두 없는 경우
				sqlSession.insert("license.addUser", map);	// 사용자 인덱스 추가
				sqlSession.insert("license.addAgent", map);	// 에이전트 인덱스 추가
				sqlSession.update("license.upCurrentCount", map.get("licenseNo"));	//사용량 증가
				return true;
			} else {	// 해당하는 이메일을 가진 사용자가 라이센스 내부에는 없지만 외부에는 존재하는 경우
				sqlSession.update("license.updateUserInfo", map); // 사용자 업데이트
				sqlSession.insert("license.addAgent", map);	// 에이전트 인덱스 추가
				sqlSession.update("license.upCurrentCount", map.get("licenseNo"));	//사용량 증가
				return true;
			}
		} else {	// 해당하는 이메일을 가진 사용자가 라이센스 내부에 존재하는 경우 
			sqlSession.update("license.updateUserInfo", map);
			return false;
		}
	}

	public char addUsers(List<GeneralUserVo> userList, int adminUserNo, int licenseNo) {
		
		if(userList.size() == 0) {
			return 'F';
		} else {
			for(GeneralUserVo guv: userList) {
				Map<String, Object> obj = new HashMap<String, Object>();
				obj.put("email", guv.getEmail());
				obj.put("password", guv.getPassword());
				obj.put("name", guv.getName());
				obj.put("dept", guv.getDept_name());
				obj.put("licenseNo", licenseNo);
				obj.put("adminUserNo", adminUserNo);
				
				if(sqlSession.selectOne("license.getCurCnt", licenseNo) == sqlSession.selectOne("license.getMaxCnt", licenseNo))
					return 'M';
				
				if(sqlSession.selectOne("license.dupUserEmail", obj) == null) {	// 해당 라이센스 내 중복된 이메일을 가진 사용자가 존재하지 않으면
					if(sqlSession.selectOne("license.dupUser", obj) == null) {	// 해당 이메일을 가진 사용자가 존재하지 않으면
						sqlSession.insert("license.addUser", obj);
						sqlSession.insert("license.addAgent", obj);
						sqlSession.update("license.upCurrentCount", licenseNo);
					} else {													// 해당 이메일을 가진 사용자가 외부에 존재하면,
						sqlSession.update("license.updateUserInfo", obj); // 사용자 업데이트
						sqlSession.insert("license.addAgent", obj);
						sqlSession.update("license.upCurrentCount", licenseNo);
					}
				} else {	// 해당 라이센스 내에 중복된 이메일을 가진 사용자가 존재하면
					sqlSession.update("license.updateUserInfo", obj);
					System.out.println("중복된 이메일: " + obj.get("email"));
				}
			}
			
			return 'T';
		}
	}

	public List<AgentVo> getAgentList(int licenseNo) {
		return sqlSession.selectList("license.getAgentList", licenseNo);
	}

	public boolean delAgent(String delUsrs, int licenseNo) {
		int cnt = 0;
		for(int i=0; i<delUsrs.length(); i++) {
			if(delUsrs.charAt(i) == ',' ) 
				cnt++;
		}
		
		if(delUsrs.indexOf(")") != -1) {
			sqlSession.delete("license.delAgent", delUsrs);
			
			for(int i = 0; i<cnt+1; i++) { 
				sqlSession.update("license.downCurrentCount", licenseNo);
			}
			return true;
		} else {
			return false;
		}
	}

	public boolean addAgentMac(Map<String, Object> map) {
		if(sqlSession.selectOne("license.dupAgentMac", map) == null) {
			if(sqlSession.selectOne("license.dupUser", map) == null) {
				sqlSession.insert("license.addUser", map);
				sqlSession.insert("license.addAgentMac", map);
				sqlSession.update("license.upCurrentCount", map.get("licenseNo"));
				return true;
			} else {
				sqlSession.update("license.updateUserInfo", map); // 사용자 업데이트
				sqlSession.insert("addAgentMac", map);
				sqlSession.update("license.upCurrentCount", map.get("licenseNo"));
				return true;
			}
		} else {
			return false;
		}
	}

	public boolean addAgentIp(Map<String, Object> map) {
		if(sqlSession.selectOne("license.dupAgentIp", map) == null) {	// 라이센스 내부에 해당 ip를 가진 에이전트가 존재X
			if(sqlSession.selectOne("license.dupUser", map) == null) {	// 해당 사용자 정보X
				sqlSession.insert("license.addUser", map);
				sqlSession.insert("license.addAgentIp", map);
				sqlSession.update("license.upCurrentCount", map.get("licenseNo"));
				return true;
			} else {	// 해당 사용자 정보O
				sqlSession.update("license.updateUserInfo", map); // 사용자 업데이트
				sqlSession.insert("addAgentIp", map);
				sqlSession.update("license.upCurrentCount", map.get("licenseNo"));
				return true;
			}
		} else {	// 라이센스 내부에 해당 ip를 가진 에이전트가 존재O
			return false;
		}
	}

	public List<AgentVo> getSearchAgentIp1(Map<String, Object> map) {
		return sqlSession.selectList("license.getSearchAgentIp1", map);
	}
	
	public List<AgentVo> getSearchAgentIp2(Map<String, Object> map) {
		return sqlSession.selectList("license.getSearchAgentIp2", map);
	}
	
	public List<AgentVo> getSearchAgentMac1(Map<String, Object> map) {
		return sqlSession.selectList("license.getSearchAgentMac1", map);
	}

	public List<AgentVo> getSearchAgentMac2(Map<String, Object> map) {
		return sqlSession.selectList("license.getSearchAgentMac2", map);
	}
	
	// mav, ip 둘 다 ""일 경우
	public List<AgentVo> getSearchAgent1(Map<String, Object> map) {
		return sqlSession.selectList("license.getSearchAgent1", map);
	}

	public List<AgentVo> getSearchAgent2(Map<String, Object> map) {
		return sqlSession.selectList("license.getSearchAgent2", map);
	}
	
	// add
	public List<LicenseVO2> getLicenseAllList() {
		return sqlSession.selectList("license.getLicenseAllList");
	}

	public List<LicenseVO2> getLicenseOneList(LicenseVO2 vo) {
		return sqlSession.selectList("license.getLicenseOneList", vo);
	}

	public UserVo pwCheck(UserVo vo) {
		return sqlSession.selectOne("license.pwCheck", vo);
	}

	public int setLicenseCancel(LicenseVO2 vo) {
		int result = -1;
		int x = sqlSession.insert("license.setLicenseCancel", vo);
		int y = sqlSession.update("license.updateCancel", vo);
	
		if(x==1 & y==1) {
			result = 1;
		}
		
		return result;
	}
	public List<LicenseVO2> getLicenseUserList(UserVo vo) {
		return sqlSession.selectList("license.getLicenseUserList", vo);
	}
	public List<UserVo> getLicenseAdminUser(LicenseVO2 vo) {
		return sqlSession.selectList("license.getLicenseAdminUser", vo);
	}

	public boolean changeActive(int licenseNo, String activeUsrs) {
		int cnt = 0;
		for(int i=0; i<activeUsrs.length(); i++) {
			if(activeUsrs.charAt(i) == ',' ) 
				cnt++;
		}
		
		if(activeUsrs.indexOf(")") != -1) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("activeUsrs", activeUsrs);
			map.put("licenseNo", licenseNo);
			sqlSession.update("license.changeActive", map);
			sqlSession.update("license.updateCnt", licenseNo);
			
			return true;
		} else {
			return false;
		}
	}

	public List<LicenseVo> alertLicenseOver(int userAdminNo) {
		return sqlSession.selectList("alertLicenseOver", userAdminNo);
	}

	public boolean updateLicense(Map<String, Object> map) {
		return sqlSession.update("license.updateLicense", map) == 1;
	}
}
