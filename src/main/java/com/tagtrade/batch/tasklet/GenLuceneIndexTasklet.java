package com.tagtrade.batch.tasklet;

import java.util.List;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import com.tagtrade.batch.processor.BaseLucene;
import com.tagtrade.dataacess.entity.bean.EContent;
import com.tagtrade.service.content.ContentService;

public class GenLuceneIndexTasklet implements Tasklet {
	
	@Autowired
	private ContentService contentService;

	@Override
	public RepeatStatus execute(StepContribution arg0, ChunkContext arg1)
			throws Exception {
		List<EContent> contents = contentService.getAllContent();
		System.out.println(contents.size());
		BaseLucene lucene = new BaseLucene();
		lucene.initCreateIndex();
		lucene.addDataToIndex(contents);
		return null;
	}

}
