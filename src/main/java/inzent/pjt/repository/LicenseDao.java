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
		return sqlSession.selectList("getLicenseList", adminNo);
	}

	public List<GeneralUserVo> getUserList(int licenseNo) {
		return sqlSession.selectList("getUserList", licenseNo);
	}

	public List<GeneralUserVo> getSearch1(Map<String, Object> map) {
		return sqlSession.selectList("getSearch1", map);
	}
	
	public List<GeneralUserVo> getSearch2(Map<String, Object> map) {
		return sqlSession.selectList("getSearch2", map);
	}
}
