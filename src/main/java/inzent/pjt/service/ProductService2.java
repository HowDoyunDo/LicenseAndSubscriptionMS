package inzent.pjt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import inzent.pjt.dto.ProductDTO;
import inzent.pjt.repository.ProductDao2;
import inzent.pjt.vo.CategoryVo;
import inzent.pjt.vo.ProductVo2;

@Service
public class ProductService2 {
  @Autowired
  ProductDao2 productdao;
  
  public List<CategoryVo> categoryList(CategoryVo vo){
    return productdao.categoryList(vo);
  }
  
  public void categoryAdd(CategoryVo vo) {
    productdao.categoryAdd(vo);
  }
  
  public String categoryChk(CategoryVo vo) {
    return productdao.categoryChk(vo);
  }
  
  public void categoryDel(CategoryVo  vo) {
    productdao.categoryDel(vo);
  }
  
  public List<ProductVo2> productList(ProductVo2 vo) {
    return productdao.productList(vo);
  }
  
  public String productChk(ProductVo2 vo) {
    return productdao.productChk(vo);
  }
  
  public void productAdd(ProductVo2 vo) {
    productdao.productAdd(vo);
  }
  
  public void productDel(ProductVo2 vo) {
    productdao.productDel(vo);
  }
  
  public ProductVo2 productInfo(ProductVo2 vo) {
    return productdao.productInfo(vo);
  }
  
  public void productChange(ProductVo2 vo) {
    productdao.productChange(vo);
  }
  
	public List<ProductDTO> getProducts() {
		return productdao.getProducts();
	}

	public ProductDTO getFirstProduct(String sol) {
		return productdao.getFirstProduct(sol);
	}

	public List<ProductDTO> productsInCategory(String sol) {
		return productdao.productsInCategory(sol);
	}
}
