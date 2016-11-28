package com.tagtrade.batch.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.tagtrade.dataacess.entity.dao.EContentDAO;
import com.tagtrade.dataacess.entity.dao.ErSeachingMapContentDAO;

public class ClearDBTasklet implements Tasklet {
	
	@Autowired
	private ErSeachingMapContentDAO erSeachingMapContentDAO;
	
	@Autowired
	private EContentDAO eContentDAO;
	
	private @Value("${oldDate}") int oldDate;

	@Override
	public RepeatStatus execute(StepContribution arg0, ChunkContext arg1)
			throws Exception {		
		erSeachingMapContentDAO.deleteOldContent(oldDate);
		
		eContentDAO.deleteOldContent(oldDate);
		
		return null;
	}

}
