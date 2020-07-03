package inzent.pjt.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import inzent.pjt.vo.GeneralUserVo;
import inzent.pjt.vo.LicenseVo;

@Repository
public class LicenseDao {
	@Autowired
	SqlSession sqlSession;

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
}
