package inzent.pjt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import inzent.pjt.dto.AnalysisDayDTO;
import inzent.pjt.dto.AnalysisMonthDTO;
import inzent.pjt.dto.AnalysisProductDTO;
import inzent.pjt.dto.AnalysisWeekDTO;
import inzent.pjt.service.AnalysisService;

@RestController
public class AnalysisController {
	@Autowired
	AnalysisService as;
	
	@GetMapping("/analysis/sales/day")
	public List<AnalysisDayDTO> GetOrderListDay() {
		return as.GetOrderListDay(); 
	}
	
	@GetMapping("/analysis/sales/week")
	public List<AnalysisWeekDTO> GetOrderListWeek() {
		return as.GetOrderListWeek(); 
	}
	
	@GetMapping("/analysis/sales/month")
	public List<AnalysisMonthDTO> GetOrderListMonth() {
		return as.GetOrderListMonth(); 
	}
	
	@GetMapping("/analysis/product")
	public List<AnalysisProductDTO> GetOrderListPrdt(
			@RequestParam(value = "start") String start,
			@RequestParam(value = "end") String end
			) {
		return as.GetOrderListPrdt(start, end); 
	}
}
