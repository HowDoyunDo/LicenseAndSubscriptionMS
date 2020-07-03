package inzent.pjt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import inzent.pjt.service.PromotionService;
import inzent.pjt.vo.PromotionVO;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class PromotionController {

	@Autowired
	PromotionService service;

	@GetMapping("/promotionAllList")
	public List<PromotionVO> promotionAllList() {
		System.out.println("/promotionAllList");

		List<PromotionVO> list = service.getPromotionAllList();
		System.out.println(list.toString());
		return list;
	}
	
	@PostMapping("/promotion-add")
	public int promotionAdd(@RequestBody PromotionVO promotionvo) {
		System.out.println("/promotion-add");
		int result = service.setPromotionAdd(promotionvo);
		return result;
	}
	
	@PostMapping("/promotioneOneList")
	public List<PromotionVO> promotioneOneList(@RequestBody PromotionVO promotionvo) {
		System.out.println("/promotioneOneList");
		List<PromotionVO> list = service.getPromotionOneList(promotionvo);
		return list;
	}
	
	@PostMapping("/promotionModify")
	public int promotionModify(@RequestBody PromotionVO promotionvo) {
		System.out.println("/promotionModify");
		int result = service.setPromotionModify(promotionvo);
		return result;
	}
	
	@PostMapping("/promotionDelete")
	public int promotionDelete(@RequestBody PromotionVO promotionvo) {
		System.out.println("/promotionDelete");
		int result = service.setPromotionDelete(promotionvo);
		return result;
	}

}
