package inzent.pjt;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@GetMapping("/category")
	public String category() {
		System.out.println("/category");
		return "hi";
	}
}
