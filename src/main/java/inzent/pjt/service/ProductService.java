package inzent.pjt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import inzent.pjt.repository.ProductDAO;
import inzent.pjt.vo.ProductVO;
import inzent.pjt.vo.SubscribeVO;

@Service
public class ProductService {

	@Autowired
	private ProductDAO dao;
	
	public List<ProductVO> getProductOneList(int productno) {
		return dao.getProductOneList(productno);
	}
	
	public List<ProductVO> getProductAllList() {
		return dao.getProductAllList();
	}
	
	public int setSubscribeProduct(SubscribeVO vo) {
		return dao.setSubscribeProduct(vo); 
	}

	public List<ProductVO> getProductList(int subscribeNo) {
		return dao.getProductList(subscribeNo);
	}

}
