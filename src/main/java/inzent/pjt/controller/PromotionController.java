package inzent.pjt.controller;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import inzent.pjt.service.PromotionService;
import inzent.pjt.vo.PromotionVO2;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class PromotionController {

	@Autowired
	PromotionService service;

	@GetMapping("/promotionAllList")
	public List<PromotionVO2> promotionAllList() {
		System.out.println("/promotionAllList");

		List<PromotionVO2> list = service.getPromotionAllList();
		
		return list;
	}
	
	@GetMapping("/promotionPolicyAllList")
	public List<PromotionVO2> promotionPolicyAllList() {
		System.out.println("/promotionPolicyAllList");

		List<PromotionVO2> list = service.getPromotionPolicyAllList();
		
		return list;
	}
	
	@PostMapping("/promotion-add")
	public int promotionAdd(@RequestBody PromotionVO2 PromotionVO2) {
		System.out.println("/promotion-add");
		int result = service.setPromotionAdd(PromotionVO2);
		return result;
	}
	@PostMapping("/promotion-policy-add")
	public int promotionPolicyAdd(@RequestBody PromotionVO2 PromotionVO2) {
		System.out.println("/promotion-policy-add");
		int result = service.setPromotionPolicyAdd(PromotionVO2);
		return result;
	}
	
	@PostMapping("/promotioneOneList")
	public List<PromotionVO2> promotioneOneList(@RequestBody PromotionVO2 PromotionVO2) {
		System.out.println("/promotioneOneList");
		List<PromotionVO2> list = service.getPromotionOneList(PromotionVO2);
		return list;
	}
	@PostMapping("/promotionPolicyOneList")
	public List<PromotionVO2> promotionPolicyOneList(@RequestBody PromotionVO2 PromotionVO2) {
		System.out.println("/promotionPolicyOneList");
		List<PromotionVO2> list = service.getPromotionPolicyOneList(PromotionVO2);
		return list;
	}
	
	@PostMapping("/promotionModify")
	public int promotionModify(@RequestBody PromotionVO2 PromotionVO2) {
		System.out.println("/promotionModify");
		int result = service.setPromotionModify(PromotionVO2);
		return result;
	}
	@PostMapping("/promotionPolicyModify")
	public int promotionPolicyModify(@RequestBody PromotionVO2 PromotionVO2) {
		System.out.println("/promotionPolicyModify");
		int result = service.setPromotionPolicyModify(PromotionVO2);
		return result;
	}
	
	@PostMapping("/promotionDelete")
	public int promotionDelete(@RequestBody PromotionVO2 PromotionVO2) {
		System.out.println("/promotionDelete");
		int result = service.setPromotionDelete(PromotionVO2);
		return result;
	}
	@PostMapping("/promotionPolicyDelete")
	public int promotionPolicyDelete(@RequestBody PromotionVO2 PromotionVO2) {
		System.out.println("/promotionPolicyDelete");
		int result = service.setPromotionPolicyDelete(PromotionVO2);
		return result;
	}
	
	@PostMapping("/promotionCheckPNo")
	public List<PromotionVO2> promotionCheckPNo(@RequestBody HashMap<String,Object> map) {
		System.out.println("/promotionCheckPNo");
		//promotionProduct
		List<PromotionVO2> result = service.getPromotionCheckPNo(map);
		return result;
	}
	@PostMapping("/promotionPolicyCheckPNo")
	public List<PromotionVO2> promotionPolicyCheckPNo(@RequestBody PromotionVO2 PromotionVO2) {
		System.out.println("/promotionPolicyCheckPNo");
		
		List<PromotionVO2> result = service.getPromotionPolicyCheckPNo(PromotionVO2);
		return result;
	}

}
