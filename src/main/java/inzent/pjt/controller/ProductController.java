package inzent.pjt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import inzent.pjt.service.ProductService;
import inzent.pjt.vo.ProductVO;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class ProductController {
	
	@Autowired
	ProductService service;
	
	@GetMapping("/productAllList")
	public List<ProductVO> productAllList() {
		System.out.println("/productAllList");
		
		List<ProductVO> list = service.getProductAllList();
		
		return list;
	}
	
	@PostMapping("/productList")
	public List<ProductVO> productList(@RequestBody ProductVO productvo  ) {
		System.out.println("/productList");
		List<ProductVO> list = service.getProductOneList(productvo.getNo());
		list.forEach(s -> System.out.println("해당 제품 : " + s.getName()));
		
		return list;
	}
}







