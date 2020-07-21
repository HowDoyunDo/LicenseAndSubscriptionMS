package inzent.pjt.service.scheduler;

import java.util.concurrent.TimeUnit;

import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.support.PeriodicTrigger;
import org.springframework.stereotype.Component;

import inzent.pjt.vo.BatchVo;

@Component
public class DynamicSchedule extends DynamicAbstractScheduler {
	BatchService bs;
	
    // 실행로직 
    @Override
    public void runner() {
        System.out.println("Dynamic Batch Start...");
        
        // 배치 내용
        BatchVo list = bs.getBatchInfo();
        bs.BasicBatch(list.getLogin_limit_months(), list.getLogin_limit_days(), list.getLogin_limit_hours());
        
        
        System.out.println("Dynamic Batch End...");
    }
 
    // 실행주기 
    @Override
    public Trigger getTrigger(int per) {
        return new PeriodicTrigger(per, TimeUnit.DAYS);
    }
}