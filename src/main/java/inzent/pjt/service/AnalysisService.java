package inzent.pjt.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import inzent.pjt.dto.AnalysisDayDTO;
import inzent.pjt.dto.AnalysisMonthDTO;
import inzent.pjt.dto.AnalysisProductDTO;
import inzent.pjt.dto.AnalysisWeekDTO;
import inzent.pjt.repository.AnalysisDao;

@Service
public class AnalysisService {
	@Autowired
	AnalysisDao ad;
	
	public List<AnalysisDayDTO> GetOrderListDay() {
		return ad.GetOrderListDay();
	}

	public List<AnalysisWeekDTO> GetOrderListWeek() {
		return ad.GetOrderListWeek();
	}
	
	public List<AnalysisMonthDTO> GetOrderListMonth() {
		return ad.GetOrderListMonth();
	}
	
	public List<AnalysisProductDTO> GetOrderListPrdt(String start, String end) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("end", end);
		
		return ad.GetOrderListPrdt(map);
	}
}
