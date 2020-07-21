package inzent.pjt.controller;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.support.PeriodicTrigger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import inzent.pjt.LicenseSubscriptionMsApplication;
import inzent.pjt.service.scheduler.BatchService;
import inzent.pjt.service.scheduler.DynamicAbstractScheduler;
import inzent.pjt.vo.BatchVo;

@RestController
public class BatchController extends DynamicAbstractScheduler {
	@Autowired
	BatchService bs;
	
	// 기본 배치 정보 받기
	@GetMapping("/getBatchInfo")
	public BatchVo getBatchInfo() {
		return bs.getBatchInfo();
	}
	
	// 배치 서비스 업데이트
	@GetMapping("/updateBatch")
	public boolean updateBatch(
			@RequestParam(value = "period") int period,
			@RequestParam(value = "months") int months,
			@RequestParam(value = "days") int days,
			@RequestParam(value = "hours") int hours
			) {
		if(bs.updateBatch(period, months, days, hours) == true) {	// 배치 변경 완료
			if(LicenseSubscriptionMsApplication.bc.stopScheduler() == true) {
				BatchVo list = LicenseSubscriptionMsApplication.bc.getBatchInfo();
				LicenseSubscriptionMsApplication.bc.startScheduler(list.getBatch_period_days());
			}
			return true;
		} else {	// 배치 변경 실패
			return false;
		}
	}
	
    // 실행로직 
    @Override
    public void runner() {
        System.out.println("Dynamic Batch Start...");
        
        // 배치 내용
        BatchVo list = bs.getBatchInfo();
	    System.out.println(list.getBatch_period_days() + "일 간격으로 " + list.getLogin_limit_months() +"달, "
		+ list.getLogin_limit_days() + "일, " + list.getLogin_limit_hours() + "시간 이상 로그인 하지 않은 사용자를 삭제");
	    
        bs.BasicBatch(list.getLogin_limit_months(), list.getLogin_limit_days(), list.getLogin_limit_hours());
        
        System.out.println("Dynamic Batch End...");
    }
 
    // 실행주기 
    @Override
    public Trigger getTrigger(int per) {
        return new PeriodicTrigger(per, TimeUnit.DAYS);
    }
}
