package inzent.pjt.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import inzent.pjt.dto.ProductDTO;
import inzent.pjt.vo.CategoryVo;
import inzent.pjt.vo.ProductVo2;

@Repository
public class ProductDao2 {

	@Autowired
	SqlSession session;

	public List<CategoryVo> categoryList(CategoryVo vo) {

		List<CategoryVo> list;
		list = session.selectList("product2.categoryList", vo);

		return list;
	}

	public void categoryAdd(CategoryVo vo) {
		session.insert("product2.categoryAdd", vo);
	}

	public String categoryChk(CategoryVo vo) {
		int a = session.selectOne("product2.categoryChk", vo);

		if (a == 0) {
			return "S";
		} else {
			return "F";
		}
	}

	public String categoryDel(CategoryVo vo) {
		int a = session.selectOne("product2.categoryproduct", vo);
		
		if (a == 0) {
			session.delete("product2.categoryDel", vo);
			return "S";
		} else {
			return "F";
		}
	}

	public List<ProductVo2> productList(ProductVo2 vo) {

		List<ProductVo2> list;
		list = session.selectList("product2.productList", vo);

		return list;
	}

	public String productChk(ProductVo2 vo) {
		int a = session.selectOne("product2.productChk", vo);

		if (a == 0) {
			return "S";
		} else {
			return "F";
		}
	}

	public void productAdd(ProductVo2 vo) {
		session.insert("product2.productAdd", vo);
	}

	public void productDel(ProductVo2 vo) {
		session.delete("product2.productDel", vo);
	}

	public ProductVo2 productInfo(ProductVo2 vo) {
		return session.selectOne("product2.productinfo", vo);
	}

	public void productChange(ProductVo2 vo) {
		session.update("product2.productChange", vo);
	}

	public List<ProductDTO> getProducts() {
		return session.selectList("product2.getProducts");
	}

	public ProductDTO getFirstProduct(String sol) {
		return session.selectOne("product2.getFirstProduct", sol);
	}

	public List<ProductDTO> productsInCategory(String sol) {
		return session.selectList("product2.productsInCategory", sol);
	}
}
