package inzent.pjt.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import inzent.pjt.vo.LicenseVO;
import inzent.pjt.vo.UserVo;

@Repository
public class LicenseDAO {

	@Autowired
	private SqlSession sqlSession;

	public List<LicenseVO> getLicenseAllList() {
		return sqlSession.selectList("license.getLicenseAllList");
	}

	public List<LicenseVO> getLicenseOneList(LicenseVO vo) {
		return sqlSession.selectList("license.getLicenseOneList", vo);
	}

	public UserVo pwCheck(UserVo vo) {
		return sqlSession.selectOne("license.pwCheck", vo);
	}

	public int setLicenseCancel(LicenseVO vo) {
		int result = -1;
		int x = sqlSession.insert("license.setLicenseCancel", vo);
		int y = sqlSession.update("license.updateCancel", vo);
	
		if(x==1 & y==1) {
			result = 1;
		}
		
	
		return result;
	}
}
