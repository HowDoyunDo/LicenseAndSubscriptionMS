package inzent.pjt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import inzent.pjt.repository.LoginDao;
import inzent.pjt.vo.LoginVo;

@RestController
public class HelloController {
	@Autowired
	private LoginDao logindao;
	


	@GetMapping("/category")
	public String category() {
		System.out.println("/category");
			
		for (LoginVo s : logindao.loginList()) {
          System.out.println(s.getTitle());
        }

		return "연결완료+++++!!!!!!!!!!!!!!!!!!";
	}

	@GetMapping("/category2")
	public String category2() {
		System.out.println("카테ㅗ리2");
		return "연결?";
	}
	
	
	
}