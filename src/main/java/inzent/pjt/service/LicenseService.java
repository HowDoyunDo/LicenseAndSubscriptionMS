package inzent.pjt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import inzent.pjt.repository.LicenseDAO;
import inzent.pjt.vo.LicenseVO;
import inzent.pjt.vo.UserVo;

@Service
public class LicenseService {

	@Autowired
	LicenseDAO dao;
	
	public  List<LicenseVO> getLicenseAllList() {
		return dao.getLicenseAllList();
	}
	public  List<LicenseVO> getLicenseOneList(LicenseVO vo) {
		return dao.getLicenseOneList(vo);
	}
	public UserVo pwCheck(UserVo vo) {
		return dao.pwCheck(vo);
	}
	public int setLicenseCancel(LicenseVO vo) {
		return dao.setLicenseCancel(vo);
	}
}
