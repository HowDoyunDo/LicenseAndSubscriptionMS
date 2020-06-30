package inzent.pjt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import inzent.pjt.repository.LoginDao;
import inzent.pjt.vo.LoginVo;

@RestController
public class HelloController {
	@Autowired
//	private LoginDao logindao;
	
	@GetMapping("/category")
	public String category() {
//		System.out.println(logindao.loginList().get(0).getTitle());
//		for (LoginVo s : logindao.loginList()) {
//          System.out.println(s.getTitle());
//        }
		return "476459486";
	}
}