package inzent.pjt.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import inzent.pjt.dto.AnalysisDayDTO;
import inzent.pjt.dto.AnalysisMonthDTO;
import inzent.pjt.dto.AnalysisProductDTO;
import inzent.pjt.dto.AnalysisWeekDTO;

@Repository
public class AnalysisDao {
	@Autowired
	SqlSession sqlSession;
	
	public List<AnalysisDayDTO> GetOrderListDay() {
		return sqlSession.selectList("analysis.getOrderListDay");
	}

	public List<AnalysisWeekDTO> GetOrderListWeek() {
		return sqlSession.selectList("analysis.getOrderListWeek");
	}
	
	public List<AnalysisMonthDTO> GetOrderListMonth() {
		return sqlSession.selectList("analysis.getOrderListMonth");
	}
	
	public List<AnalysisProductDTO> GetOrderListPrdt(Map<String, Object> map) {
		return sqlSession.selectList("analysis.getOrderListPrdt", map);
	}
}
