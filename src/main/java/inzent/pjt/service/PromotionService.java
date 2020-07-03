package inzent.pjt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import inzent.pjt.repository.PromotionDAO;
import inzent.pjt.vo.PromotionVO;

@Service
public class PromotionService {
	@Autowired
	private PromotionDAO dao;
	
	public List<PromotionVO> getPromotionAllList(){
		return dao.getPromotionAllList();
	}
	public int setPromotionAdd(PromotionVO vo) {
		// start_date + start_date_time 
		vo.setStart_date(vo.getStart_date() + " " + vo.getStart_date_time());
		vo.setEnd_date(vo.getEnd_date() + " " + vo.getEnd_date_time());
		
		return dao.setPromotionAdd(vo);
	}
	public List<PromotionVO> getPromotionOneList(PromotionVO vo){
		return dao.getPromotionOneList(vo);
	}
	
	public int setPromotionModify(PromotionVO vo) {
		vo.setStart_date(vo.getStart_date() + " " + vo.getStart_date_time());
		vo.setEnd_date(vo.getEnd_date() + " " + vo.getEnd_date_time());
		
		return dao.setPromotionModify(vo);
	}
	public int setPromotionDelete(PromotionVO vo) {
		return dao.setPromotionDelete(vo);
	}
}

