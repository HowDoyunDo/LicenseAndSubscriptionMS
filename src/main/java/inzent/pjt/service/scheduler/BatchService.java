package inzent.pjt.service.scheduler;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import inzent.pjt.repository.BatchDao;
import inzent.pjt.vo.BatchVo;

@Service
@EnableScheduling
@Async
public class BatchService {
	@Autowired
	BatchDao batchDao;
	
	public BatchVo getBatchInfo() {
		return batchDao.getBatchInfo();
	}
	
	public void BasicBatch(int months, int days, int hours) {
		Calendar standard = Calendar.getInstance();
		standard.setTime(new Date());	// 현재 시간
		
		DateFormat format = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
		standard.add(Calendar.MONTH, -months);	// n달 전 시간
		standard.add(Calendar.DATE, -days);		// n일 전 시간
		standard.add(Calendar.HOUR, -hours);	// n시간 전 시간
		
		String strStandard = format.format(standard.getTime());	// 문자열로 저장
		
		batchDao.BatchService(strStandard);
	}

	public boolean updateBatch(int period, int months, int days, int hours) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("period", period);
		map.put("months", months);
		map.put("days", days);
		map.put("hours", hours);
		
		return batchDao.updateBatch(map);
	}
}
