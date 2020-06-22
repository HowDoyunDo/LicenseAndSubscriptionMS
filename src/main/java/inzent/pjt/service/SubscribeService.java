package inzent.pjt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import inzent.pjt.repository.ProductDAO;
import inzent.pjt.repository.SubscribeDAO;
import inzent.pjt.vo.SubscribeVO;

@Service
public class SubscribeService {
	
	@Autowired
	private SubscribeDAO dao;

	public List<SubscribeVO> getSubAllList() {
		return dao.getSubList();
	}
	
	public int setSubAdd(SubscribeVO vo) {
		return dao.setSubAdd(vo); 
	}
	
	public int getMaxNo() {
		return dao.getMaxNo();
	}
}
