package inzent.pjt.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import inzent.pjt.dto.ProductDTO;
import inzent.pjt.vo.ProductVO;
import inzent.pjt.vo.SubscribeVO;

@Repository
public class ProductDAO {
	@Autowired
	private SqlSession sqlSession;

	public List<ProductVO> getProductAllList() {
		return sqlSession.selectList("product.getProductAllList");
	}

	public List<ProductVO> getProductOneList(int productno) {
		return sqlSession.selectList("product.getProductOneList", productno);
	}

	public int setSubscribeProduct(SubscribeVO vo) {
		return sqlSession.insert("product.setSubscribeProduct", vo);
	}

	public List<ProductVO> getProductList(int subscribeNo) {
		return sqlSession.selectList("product.getProductList", subscribeNo);
	}

}
