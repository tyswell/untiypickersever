package com.tagtrade.batch.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.tagtrade.batch.processor.thaimtb.ThaimtbProcessor;

/*
 * 0-5   every 60 min
 * 5-6   every 30 min
 * 6-7   every 15 min
 * 7-8   every 15 min
 * 8-10  every 5  min
 * 11-12 every 7  min
 * 12-14 every 5  min
 * 15-17 every 10 min
 * 17-20 every 5  min
 * 20-22 every 7  min
 * 22-24 every 15 min 
 */ 

@Component
public class Scheduler {
	
	@Autowired
	JobLauncher jobLauncher;

	@Autowired
	Job importUserJobx;
	
	private Logger logger = LoggerFactory.getLogger(ThaimtbProcessor.class);

//	@Scheduled(fixedDelayString="5000")
//	public void scheduler1() {
//		executeTask();
//	}
	

	
	@Scheduled(cron = "0 */60 0-4 * * *")
	public void scheduler1() {
		executeTask();
	}
	
	@Scheduled(cron = "0 */30 5 * * *")
	public void scheduler2() {
		executeTask();
	}
	
	@Scheduled(cron = "0 */15 6 * * *")
	public void scheduler3() {
		executeTask();
	}
	
	@Scheduled(cron = "0 */10 7 * * *")
	public void scheduler4() {
		executeTask();
	}
	
	@Scheduled(cron = "0 */5 8-10 * * *")
	public void scheduler5() {
		executeTask();
	}
	
	@Scheduled(cron = "0 */7 11 * * *")
	public void scheduler6() {
		executeTask();
	}
	
	@Scheduled(cron = "0 */5 12-14 * * *")
	public void scheduler7() {
		executeTask();
	}
	
	@Scheduled(cron = "0 */10 15-16 * * *")
	public void scheduler8() {
		executeTask();
	}
	
	@Scheduled(cron = "0 */5 17-19 * * *")
	public void scheduler9() {
		executeTask();
	}
	
	@Scheduled(cron = "0 */7 20-21 * * *")
	public void scheduler10() {
		executeTask();
	}
	
	@Scheduled(cron = "0 */15 22-23 * * *")
	public void scheduler11() {
		executeTask();
	}
	
	private void executeTask() {
		logger.debug("RUN SCHEDULE");
		
		JobParameters jobParameters = new JobParametersBuilder().addLong(
				"time", System.currentTimeMillis()).toJobParameters();
		try {
			JobExecution execution = jobLauncher.run(importUserJobx,
					jobParameters);
			System.out.println("Exit Status : " + execution.getStatus());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	private void executeTask() {
//		logger.debug("RUN SCHEDULE");
//	}
	
	

}
