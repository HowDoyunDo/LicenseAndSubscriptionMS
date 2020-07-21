package inzent.pjt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import inzent.pjt.service.ProductService2;
import inzent.pjt.vo.CategoryVo;
import inzent.pjt.vo.ProductVo2;

@RestController
public class ProductController2 {
  @Autowired
  ProductService2 productservice;
  
  @GetMapping("/categorylist")
  public List<CategoryVo> categoryList(CategoryVo vo) throws Exception{
    
    return productservice.categoryList(vo);
  }
  
  @PostMapping("/categoryadd")
  public String categoryAdd(@RequestBody CategoryVo vo) throws Exception{
    String chk = productservice.categoryChk(vo);
    if(chk == "S") {
    	System.out.println("카테고리"+vo.getCategory_image());
      productservice.categoryAdd(vo);
      return "S";
    }
    else {
      return "F";
    }
  }
  
  @PostMapping("/categorydel")
  public void categoryDel(@RequestBody CategoryVo vo) throws Exception{
    productservice.categoryDel(vo);
  }
  
  @GetMapping("/productlist")
  public List<ProductVo2> productlist(ProductVo2 vo) throws Exception{
    return productservice.productList(vo);
  }
  
  @PostMapping("/productadd")
  public String productAdd(@RequestBody ProductVo2 vo) throws Exception{
    String chk = productservice.productChk(vo);
    if(chk == "S") {
      productservice.productAdd(vo);
      return "S";
    }
    else {
      return "F";
    }
  }
  
  @PostMapping("/productdel")
  public void productDel(@RequestBody ProductVo2 vo) throws Exception{
    productservice.productDel(vo);
  }
  
  @PostMapping("/productinfo")
  public ProductVo2 productInfo(@RequestBody ProductVo2 vo) throws Exception{
    return productservice.productInfo(vo);
  }
  
  @PostMapping("/productchange")
  public void productChange(@RequestBody ProductVo2 vo) throws Exception{
    productservice.productChange(vo);
  }
}