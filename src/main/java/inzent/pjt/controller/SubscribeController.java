package inzent.pjt.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import inzent.pjt.service.ProductService;
import inzent.pjt.service.SubscribeService;
import inzent.pjt.vo.OrderListVO;
import inzent.pjt.vo.ProductVO;
import inzent.pjt.vo.SubscribeChangeVO;
import inzent.pjt.vo.SubscribeVO;
import inzent.pjt.vo.UserVo;

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

	@PostMapping("/alllist_P")
	public List<SubscribeVO> subscribeAllList_P() {
		System.out.println("/alllist_P");

		List<SubscribeVO> allList = service.getSubAllList_P();
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

	@PostMapping("/subscribeOneList")
	public List<SubscribeVO> subscribeOneList(@RequestBody Map<String, Integer> map) {
		System.out.println("/subscribeOneList");
		int subscribeNo = map.get("subscribeNo");
		List<SubscribeVO> list = null;
	
		try {
			// 해당 구독정책 출력
			list = service.getSubscribeOneList(subscribeNo);
			
			// 해당 구독정책의 제품 출력
			ArrayList<ProductVO> p_list = (ArrayList<ProductVO>) p_service.getProductList(subscribeNo);
			list.get(0).setSelectedList(p_list);
			
		}catch (Exception e) {
			// TODO: handle exception
			SubscribeVO vo = new SubscribeVO();
			vo.setError_message(e.toString());
			list.set(0, vo);
		}
		
		return list;

	}

	@PostMapping("/subscribeModify")
	public int subscribeModify(@RequestBody SubscribeVO subscribevo) {
		System.out.println("/subscribeModify");
		int result = service.setSubscribeModify(subscribevo);
		return result;
	}

	@PostMapping("/licenseCheck")
	public int licenseCheck(@RequestBody SubscribeVO subscribevo) {
		System.out.println("/licenseCheck");

		int result = service.getLicenseCheck(subscribevo.getPolicy_no());
		return result;
	}
	
	@PostMapping("/subscribeDelete")
	public int subscribeDelete(@RequestBody SubscribeVO subscribevo) {
		System.out.println("/subscribeDelete");

		int result = service.getSubscribeDelete(subscribevo);
		return result;
	}
	
	/*
	 * 고객관리자
	 * 
	 */

	@PostMapping("/userSubscribeList")
	public List<OrderListVO> userSubscribeList(@RequestBody UserVo uservo) {
		System.out.println("/userSubscribeList");
		List<OrderListVO> list = service.getUserSubscribeList(uservo);
		return list;
	}

	@PostMapping("/subscribeChange")
	public int subscribeChange(@RequestBody SubscribeChangeVO changevo) {
		System.out.println("/subscribeChange");
		int result = service.setSubscribeChange(changevo);
		return result;
	}

}