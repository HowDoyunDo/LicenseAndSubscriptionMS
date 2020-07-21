package inzent.pjt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import inzent.pjt.controller.BatchController;
import inzent.pjt.vo.BatchVo;

@SpringBootApplication
public class LicenseSubscriptionMsApplication {
	public static BatchController bc = null;
	
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(LicenseSubscriptionMsApplication.class, args);
		bc = context.getBean(BatchController.class);
		
		// 기본 배치 실행
		BatchVo list = bc.getBatchInfo();
		bc.updateBatch(list.getBatch_period_days(), list.getLogin_limit_months(), list.getLogin_limit_days(), list.getLogin_limit_hours());	// update batch
	    bc.startScheduler(list.getBatch_period_days());
	}
}