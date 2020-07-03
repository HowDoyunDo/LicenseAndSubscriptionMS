package inzent.pjt.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import inzent.pjt.vo.GeneralUserVo;
import inzent.pjt.vo.LicenseVO2;
import inzent.pjt.vo.LicenseVo;
import inzent.pjt.vo.UserVo;

@Repository
public class LicenseDao {
	@Autowired
	private SqlSession sqlSession;

	public List<LicenseVo> getLicenseList(int adminNo) {
		return sqlSession.selectList("license.getLicenseList", adminNo);
	}

	public List<GeneralUserVo> getUserList(int licenseNo) {
		return sqlSession.selectList("license.getUserList", licenseNo);
	}

	public List<GeneralUserVo> getSearch1(Map<String, Object> map) {
		return sqlSession.selectList("license.getSearch1", map);
	}
	
	public List<GeneralUserVo> getSearch2(Map<String, Object> map) {
		return sqlSession.selectList("license.getSearch2", map);
	}

	public boolean delUser(String delUsrs, int licenseNo) {
		if(delUsrs != "(") {
			int del1 = sqlSession.delete("license.delUser1", delUsrs);
			int del2 = sqlSession.delete("license.delUser2", delUsrs);
			sqlSession.update("license.downCurrentCount", licenseNo);
			return true;
		} else {
			return false;
		}
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
}
