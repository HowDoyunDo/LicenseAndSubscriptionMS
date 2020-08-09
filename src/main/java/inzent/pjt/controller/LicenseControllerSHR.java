package inzent.pjt.controller;

import static org.slf4j.LoggerFactory.getLogger;

import java.util.List;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import inzent.pjt.service.LicenseService;
import inzent.pjt.service.UserService;
import inzent.pjt.vo.LicenseVO2;
import inzent.pjt.vo.UserVo;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class LicenseControllerSHR {

  @Autowired
  LicenseService service;

  private Logger logger = getLogger(this.getClass());
  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private UserService userService;

  @Autowired
  private UserController controller;


  @PostMapping("/licenseList")
  public List<LicenseVO2> licenseAllList() {
    System.out.println("/licenseList");

    List<LicenseVO2> allList = service.getLicenseAllList();

    return allList;
  }

  @PostMapping("/licenseOneList")
  public List<LicenseVO2> licenseOneList(@RequestBody LicenseVO2 LicenseVO2) {
    System.out.println("/licenseOneList");
    List<LicenseVO2> list = service.getLicenseOneList(LicenseVO2);

    return list;
  }

  @PostMapping("/userPwCheck")
  public UserVo userPwCheck(@RequestBody UserVo vo) throws Exception {
    System.out.println("/userPwCheck");

    if ("S".equals(controller.chkPw(vo))) {
      return vo;
    } else {
      return null;
    }
  }

  @PostMapping("/licenseCancel")
  public int licenseCancel(@RequestBody LicenseVO2 LicenseVO2) {
    System.out.println("/licenseCancel");
    int result = service.setLicenseCancel(LicenseVO2);

    return result;
  }

  @PostMapping("/licenseUserList")
  public List<LicenseVO2> licenseUserList(@RequestBody UserVo vo) {
    System.out.println("/licenseUserList");
    List<LicenseVO2> list = service.getLicenseUserList(vo);

    return list;
  }

  @PostMapping("/licenseAdminUser")
  public List<UserVo> licenseAdminUser(@RequestBody LicenseVO2 vo) {
    System.out.println("/licenseAdminUser");
    List<UserVo> list = service.getLicenseAdminUser(vo);
    return list;
  }
}
