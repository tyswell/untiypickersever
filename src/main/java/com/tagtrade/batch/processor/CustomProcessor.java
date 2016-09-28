package com.tagtrade.batch.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import com.tagtrade.batch.processor.thaimtb.ThaimtbProcessor;
import com.tagtrade.bean.BatchOutput;
import com.tagtrade.constant.WebNameConstant;
import com.tagtrade.dataacess.entity.bean.RUrl;


public class CustomProcessor implements ItemProcessor<RUrl, BatchOutput>{

	
	private Logger logger = LoggerFactory.getLogger(CustomProcessor.class);
	
	@Autowired
	private ThaimtbProcessor thaimtbProcessor;
		
	@Override
	public BatchOutput process(RUrl url) throws Exception {
		Integer webNameCode = url.getWebNameCode();
		logger.debug("START CUSTOM PROCESS NAME :" + webNameCode);
		
		if (webNameCode == WebNameConstant.THAIMTB_CODE) {			
			return thaimtbProcessor.process(url.getDescription());
		}
		return null;
	}
	

}
