package inzent.pjt.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import inzent.pjt.repository.BatchDao;

@Service
@EnableScheduling
@Async
public class BatchService {
	@Autowired
	BatchDao batchDao;
	
	@Scheduled(fixedRate = 3000)
	public void Batch() {
		System.out.println("Batch Service Start...");
		
		Calendar standard = Calendar.getInstance();
		standard.setTime(new Date());	// 현재 시간
		
		DateFormat format = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
		standard.add(Calendar.DATE, -90);	// 90일 전 시간(날짜)
		
		String strStandard = format.format(standard.getTime());	// 문자열로 저장
		
		batchDao.BatchService(strStandard);
	}
}
