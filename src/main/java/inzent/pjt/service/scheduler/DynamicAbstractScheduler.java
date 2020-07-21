package inzent.pjt.service.scheduler;

import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

public abstract class DynamicAbstractScheduler {
    private ThreadPoolTaskScheduler scheduler;
 
    public boolean stopScheduler() {
    	if(scheduler == null)
    		return false;
    	System.out.println("Dynamic Batch Stopped...");
        scheduler.shutdown();
        return true;
    }
 
    public void startScheduler(int period) {
        scheduler = new ThreadPoolTaskScheduler();
        scheduler.initialize();
        scheduler.schedule(getRunnable(), getTrigger(period));
    }
 
    private Runnable getRunnable(){
        return new Runnable(){
            @Override
            public void run() {
                runner();
            }
        };
    }
 
    public abstract void runner();
    
    public abstract Trigger getTrigger(int period);
}
 
 
