package inzent.pjt.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import inzent.pjt.repository.PromotionDAO;
import inzent.pjt.vo.PromotionVO2;

@Service
public class PromotionService {
	@Autowired
	private PromotionDAO dao;
	
	public List<PromotionVO2> getPromotionAllList(){
		return dao.getPromotionAllList();
	}
	public int setPromotionAdd(PromotionVO2 vo) {
		// start_date + start_date_time 
		vo.setStart_date(vo.getStart_date() + " " + vo.getStart_date_time());
		vo.setEnd_date(vo.getEnd_date() + " " + vo.getEnd_date_time());
		
		return dao.setPromotionAdd(vo);
	}
	public List<PromotionVO2> getPromotionOneList(PromotionVO2 vo){
		return dao.getPromotionOneList(vo);
	}
	
	public int setPromotionModify(PromotionVO2 vo) {
		vo.setStart_date(vo.getStart_date() + " " + vo.getStart_date_time());
		vo.setEnd_date(vo.getEnd_date() + " " + vo.getEnd_date_time());
		
		return dao.setPromotionModify(vo);
	}
	public int setPromotionDelete(PromotionVO2 vo) {
		return dao.setPromotionDelete(vo);
	}
	public List<PromotionVO2> getPromotionCheckPNo(HashMap<String,Object> map) {
		return dao.getPromotionCheckPNo(map);
	}
}

