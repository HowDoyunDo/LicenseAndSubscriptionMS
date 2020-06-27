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
}
