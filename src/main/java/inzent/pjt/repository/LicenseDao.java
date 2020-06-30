package inzent.pjt.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import inzent.pjt.vo.LicenseVo;

@Repository
public class LicenseDao {
	@Autowired
	SqlSession sqlSession;

	public List<LicenseVo> getLicenseList(int adminNo) {
		return sqlSession.selectList("getLicenseList", adminNo);
	}

	public LicenseVo getLicenseInfo(int policyNo) {
		return sqlSession.selectOne("getLicenseInfo", policyNo);
	}
	
}
