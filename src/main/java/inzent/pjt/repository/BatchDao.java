package inzent.pjt.repository;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import inzent.pjt.vo.BatchVo;

@Repository
public class BatchDao {
	@Autowired
	SqlSession sqlSession;
	
	public BatchVo getBatchInfo() {
		return sqlSession.selectOne("getBatchInfo");
	}

	public void BatchService(String strStandard) {
		sqlSession.delete("batchService", strStandard);
	}

	public boolean updateBatch(Map<String, Object> map) {
		return sqlSession.update("updateBatch", map) == 1;
	}
}
