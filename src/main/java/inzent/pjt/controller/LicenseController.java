package inzent.pjt.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import inzent.pjt.service.LicenseService;
import inzent.pjt.vo.GeneralUserVo;
import inzent.pjt.vo.LicenseVo;

@RestController
public class LicenseController {

	@Autowired
	LicenseService licenseService;
	
	@GetMapping("/license/list")
	public List<LicenseVo> getLicenseList(
			@RequestParam(value = "adminNo") int adminNo
			) {
		return licenseService.getLicenseList(adminNo);
	}
	
	@GetMapping("/license/userlist")
	public List<GeneralUserVo> getUserList(
			@RequestParam(value = "licenseNo") int licenseNo
			) {
		return licenseService.getUserList(licenseNo);
	}

	@GetMapping("/license/search")
	public List<GeneralUserVo> getSearch(
			@RequestParam(value = "licenseNo") int licenseNo,
			@RequestParam(value = "email") String email,
			@RequestParam(value = "name") String name,
			@RequestParam(value = "dept") String dept,
			@RequestParam(value = "start") String start,
			@RequestParam(value = "end") String end
			) {
		return licenseService.getSearch(licenseNo, email, name, dept, start, end);
	}
	
	@GetMapping("/deluser")
	public boolean delUser(
			@RequestParam(value = "delUsrs") String delUsrs,
			@RequestParam(value = "licenseNo") int licenseNo
			) {
		System.out.println(delUsrs);
		return licenseService.delUser(delUsrs, licenseNo);
	}
	
	@PostMapping("/addusers/form")
	public void downloadForm(
			HttpServletResponse response
			) {
	    try {
	        File file = new File("./excel/test.xlsx");
	        Files.copy(file.toPath(), response.getOutputStream());
	        String mimeType = URLConnection.guessContentTypeFromName(file.getName());
	        String contentDisposition = String.format("attachment; filename=%s", file.getName());
	        int fileSize = Long.valueOf(file.length()).intValue();

	        response.setContentType(mimeType);
	        response.setHeader("Content-Disposition", contentDisposition);
	        response.setContentLength(fileSize);
	    }catch (FileNotFoundException e) {
	        System.out.println(e);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
}
