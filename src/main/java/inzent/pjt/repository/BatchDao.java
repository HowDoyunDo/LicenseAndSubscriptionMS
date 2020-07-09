package inzent.pjt.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BatchDao {
	@Autowired
	SqlSession sqlSession;

	public void BatchService(String strStandard) {
		sqlSession.delete("batchService", strStandard);
		System.out.println("Batch Service Ended...");
	}
}
