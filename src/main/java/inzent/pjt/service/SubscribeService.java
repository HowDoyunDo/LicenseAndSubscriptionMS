package inzent.pjt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import inzent.pjt.repository.SubscribeDAO;
import inzent.pjt.vo.OrderListVO;
import inzent.pjt.vo.SubscribeChangeVO;
import inzent.pjt.vo.SubscribeVO;
import inzent.pjt.vo.UserVo;

@Service
public class SubscribeService {

	@Autowired
	private SubscribeDAO dao;

	public List<SubscribeVO> getSubAllList() {
		return dao.getSubList();
	}
	public List<SubscribeVO> getSubAllList_P() {
		return dao.getSubList_P();
	}

	public int setSubAdd(SubscribeVO vo) {
		return dao.setSubAdd(vo);
	}

	public int getMaxNo() {
		return dao.getMaxNo();
	}

	public List<SubscribeVO> getSubscribeOneList(int subscribeNo) {
		return dao.getSubscribeOneList(subscribeNo);
	}

	public int setSubscribeModify(SubscribeVO subscribevo) {
		int result = -1;

		if (subscribevo.getStandard() != null) {
			// 전체 수정 가능
			result = dao.setSubscribeAllModify(subscribevo);
		} else {
			// 일부 수정 가능
			result = dao.setSubscribeModify(subscribevo);
		}
		return result;
	}

	public int getLicenseCheck(int subscribeNo) {
		int result = dao.getLicenseCheck(subscribeNo);

		if (result >= 1) {
			// 라이선스 존재
			return 1;
		} else {
			return 0;
		}
	}
	public int getSubscribeDelete(SubscribeVO vo) {
		return dao.getSubscribeDelete(vo);
		
	}
	/*
	 * 고객관리자
	 * 
	 */
	public List<OrderListVO> getUserSubscribeList(UserVo vo) {
		return dao.getUserSubscribeList(vo);
	}

	public int setSubscribeChange(SubscribeChangeVO vo) {
		return dao.setSubscribeChange(vo);
	}
}
