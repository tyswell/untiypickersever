package com.tagtrade.batch.processor.thaimtb;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tagtrade.bean.BatchOutput;
import com.tagtrade.bean.SearchContentResult;
import com.tagtrade.bean.thaimtb.SearchMapContent;
import com.tagtrade.constant.LuceneConstant;
import com.tagtrade.dataacess.entity.bean.ESearching;
import com.tagtrade.service.content.ContentService;
import com.tagtrade.service.searching.SearchingService;

public class ThaimtbProcessorTemp {
	/*
	@Autowired
	private ContentService thaimtbContentService;
	
	@Autowired
	private SearchingService searchingService;
	
	@Autowired
	private File thaimtbFile;
	
	private Logger logger = LoggerFactory.getLogger(ThaimtbProcessorTemp.class);
	
	public BatchOutput process(String url) {
		BatchOutput output = new BatchOutput();
		List<SearchMapContent> maps = new ArrayList<>();
		
		List<EThaimtbContent> insertRows = setID(ThaimtbUtil.getUnInsertRow(url, thaimtbContentService.getLastestContent(), thaimtbFile));
		logger.debug("THAIMTB NUMBER INSERT ROW =" + insertRows.size());
		
		BaseLucene lucene = new BaseLucene(insertRows);
		
		List<ESearching> words = searchingService.getSearching(true);
		
		for (ESearching word : words) {
			List<SearchContentResult> searchResults = lucene.search(word.getDescription(), LuceneConstant.TITLE_CONTENT);
			logger.debug("THAIMTB SEARCH MATCH FOR WORD :" + word.getDescription() + " MATCH NUMBER =" + searchResults.size());
			for (SearchContentResult searchResult : searchResults) {
				logger.debug("THAIMTB SEARCH MATCH TITLE DETAIL =" +searchResult.geteThaimtbContent().getDescription());
				SearchMapContent map = new SearchMapContent();
				map.seteSearching(word);
				map.seteThaimtbContent(searchResult.geteThaimtbContent());
				map.setScoreHit(searchResult.getScoreHit());
				maps.add(map);
			}
		}
		
		output.seteThaimtbContent(insertRows);
		output.setSearchMapThaimtbs(maps);
		
		return output;
	}
	
	private List<EThaimtbContent> setID(List<EThaimtbContent> insertRows) {
		int nextId = (thaimtbContentService.getNextId() - 1) + insertRows.size();
		
		for (EThaimtbContent row : insertRows) {
			row.setThaimtbId(nextId);
			nextId--;
		}
		
		return insertRows;
	}*/

}
