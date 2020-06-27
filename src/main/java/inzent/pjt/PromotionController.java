package inzent.pjt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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

}
