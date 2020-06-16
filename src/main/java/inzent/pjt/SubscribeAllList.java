package inzent.pjt;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubscribeAllList {
	
	@GetMapping("/categoryy")
	public String category() {
		System.out.println("alllist");
		return "";
	}
}
