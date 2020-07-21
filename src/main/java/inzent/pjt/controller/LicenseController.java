package inzent.pjt.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.ckpoint.toexcel.core.ToWorkBook;
import com.github.ckpoint.toexcel.core.ToWorkSheet;

import inzent.pjt.service.LicenseService;
import inzent.pjt.service.scheduler.DynamicSchedule;
import inzent.pjt.vo.AgentVo;
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
	
	@GetMapping("/addOrderList")
	public boolean addOrderList(
			@RequestParam(value = "policyNo") int policyNo,
			@RequestParam(value = "userNo") int userNo,
			@RequestParam(value = "monthCount") int monthCount,
			@RequestParam(value = "startDate") String startDate,
			@RequestParam(value = "endDate") String endDate,
			@RequestParam(value = "orgPrice") int orgPrice,
			@RequestParam(value = "dcPrice") int dcPrice,
			@RequestParam(value = "totalPrice") int totalPrice,
			@RequestParam(value = "monthPay") boolean monthPay
			) {
		System.out.println(
				"policyNo : " + policyNo + "\n" +
				"userNo : " + userNo + "\n" +
				"monthCount : " + monthCount + "\n" +
				"startDate : " + startDate + "\n" +
				"endDate : " + endDate + "\n" +
				"orgPrice : " + orgPrice + "\n" +
				"dcPrice : " + dcPrice + "\n" +
				"totalPrice : " + totalPrice + "\n" +
				"monthPay : " + monthPay + "\n"
			);
		
		return licenseService.addOrderList(policyNo, userNo, monthCount, startDate, endDate, orgPrice, dcPrice, totalPrice, monthPay);
	}
	
	@GetMapping("/addLicense")
	public boolean addLicense(
			@RequestParam(value = "userAdminNo") int userAdminNo,
			@RequestParam(value = "policyNo") int policyNo,
			@RequestParam(value = "licenseKey") String licenseKey,
			@RequestParam(value = "currentCount") int currentCount,
			@RequestParam(value = "maxCount") int maxCount,
			@RequestParam(value = "startDate") String startDate,
			@RequestParam(value = "endDate") String endDate
			) {
		return licenseService.addLicense(userAdminNo, policyNo, licenseKey, currentCount, maxCount, startDate, endDate);
	}
	
	@PostMapping("/addUser")
	public boolean addUser(
			@RequestParam(value = "email") String email,
			@RequestParam(value = "password") String password,
			@RequestParam(value = "name") String name,
			@RequestParam(value = "dept") String dept,
			@RequestParam(value = "adminUserNo") int adminUserNo,
			@RequestParam(value = "licenseNo") int licenseNo
			) {
		return licenseService.addUser(email, password, name, dept, adminUserNo, licenseNo);
	}
	
	@PostMapping("/addUsers")
	public boolean addUser(
			@RequestPart MultipartFile file,
			@RequestParam(value = "adminUserNo") int adminUserNo,
			@RequestParam(value = "licenseNo") int licenseNo
			) {
		File convFile = new File("./uploads/" + file.getOriginalFilename());
		ToWorkBook toWorkBook = null;
		ToWorkSheet sheet = null;
		try {
			convFile.createNewFile();
			FileOutputStream fos = new FileOutputStream(convFile);
			fos.write(file.getBytes());
			fos.close();
			
			toWorkBook = new ToWorkBook(convFile);
			sheet = toWorkBook.getSheetAt(0);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<GeneralUserVo> userList = sheet.map(GeneralUserVo.class);
		if(userList == null) {
			return false;
		}
		
		convFile.delete();
		
//		return true;
		return licenseService.addUsers(userList, adminUserNo, licenseNo);
	}
	
	
	// TODO
	// Agent 
	// 컨트롤러
	
	@GetMapping("/license/agentlist")
	public List<AgentVo> getAgentList(
			@RequestParam(value = "licenseNo") int licenseNo
			) {
		return licenseService.getAgentList(licenseNo);
	}
	
	@GetMapping("/delagent")
	public boolean delAgent(
			@RequestParam(value = "delUsrs") String delUsrs,
			@RequestParam(value = "licenseNo") int licenseNo
			) {
		System.out.println(delUsrs);
		return licenseService.delAgent(delUsrs, licenseNo);
	}
	
	@PostMapping("/addAgent")
	public boolean addAgent(
			@RequestParam(value = "email") String email,
			@RequestParam(value = "password") String password,
			@RequestParam(value = "name") String name,
			@RequestParam(value = "dept") String dept,
			
			@RequestParam(value = "agentname") String agentname,
			@RequestParam(value = "macaddr") String macaddr,
			@RequestParam(value = "ipaddr") String ipaddr,
			
			@RequestParam(value = "adminUserNo") int adminUserNo,
			@RequestParam(value = "licenseNo") int licenseNo
			) {
		return licenseService.addAgent(email, password, name, dept, agentname, macaddr, ipaddr, adminUserNo, licenseNo);
	}
	
	@GetMapping("/license/search/agent")
	public List<AgentVo> getSearchAgent(
			@RequestParam(value = "licenseNo") int licenseNo,
			@RequestParam(value = "email") String email,
			@RequestParam(value = "name") String name,
			@RequestParam(value = "dept") String dept,
			@RequestParam(value = "start") String start,
			@RequestParam(value = "end") String end,
			
			@RequestParam(value = "agentname") String agentname,
			@RequestParam(value = "macaddr") String macaddr,
			@RequestParam(value = "ipaddr") String ipaddr
			) {
		System.out.println("hehehehe");
		System.out.println(licenseNo + " " + email + " " + name + " " + dept + " " + start + " " + end + " " + agentname + " " + macaddr + " " + ipaddr);
		return licenseService.getSearchAgent(licenseNo, email, name, dept, start, end, agentname, macaddr, ipaddr);
	}
}
