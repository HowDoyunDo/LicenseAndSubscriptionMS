package inzent.pjt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import inzent.pjt.service.ProductService;
import inzent.pjt.service.SubscribeService;
import inzent.pjt.vo.SubscribeVO;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class SubscribeController {

	@Autowired
	private SubscribeService service;
	@Autowired
	private ProductService p_service;

	@GetMapping("/alllist")
	public List<SubscribeVO> subscribeAllList() {
		System.out.println("/alllist");

		List<SubscribeVO> allList = service.getSubAllList();
//		allList.forEach(s -> System.out.println(s.getPolicy_title()));

		return allList;
	}

	@PostMapping("/subscribeAdd")
	public int subscribeAdd(@RequestBody SubscribeVO subscribevo) {
		System.out.println("/subscribeAdd");


		// 구독 정책 추가
		int resultSubAdd = service.setSubAdd(subscribevo);

		// subscribe_policy의 max(no) 추출
		// 같은 정책 번호로 insert하기 위해
		int policy_no = service.getMaxNo();

		// 정책 추가 시 선택한 제품목록 추가
		subscribevo.setPolicy_no(policy_no);
		int resultSubProduct = p_service.setSubscribeProduct(subscribevo);

		int result = -1;
		if (resultSubAdd == 1 && resultSubProduct == 1) {
			result = 1;
		} else {
			result = 0;
		}

		return result;
	}

}