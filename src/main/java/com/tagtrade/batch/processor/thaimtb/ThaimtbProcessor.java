package com.tagtrade.batch.processor.thaimtb;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tagtrade.bean.BatchOutput;
import com.tagtrade.bean.SearchThaimtbResult;
import com.tagtrade.bean.thaimtb.SearchMapThaimtb;
import com.tagtrade.constant.LuceneConstant;
import com.tagtrade.dataacess.entity.bean.ESearching;
import com.tagtrade.dataacess.entity.bean.EThaimtbContent;
import com.tagtrade.service.content.ThaimtbContentService;
import com.tagtrade.service.searching.SearchingService;

public class ThaimtbProcessor {
	
	@Autowired
	private ThaimtbContentService thaimtbContentService;
	
	@Autowired
	private SearchingService searchingService;
	
	@Autowired
	private File thaimtbFile;
	
	private Logger logger = LoggerFactory.getLogger(ThaimtbProcessor.class);
	
	public BatchOutput process(String url) {
		BatchOutput output = new BatchOutput();
		List<SearchMapThaimtb> maps = new ArrayList<>();
		
		List<EThaimtbContent> insertRows = setID(ThaimtbUtil.getUnInsertRow(url, thaimtbContentService.getLastestContent(), thaimtbFile));
		logger.debug("THAIMTB NUMBER INSERT ROW =" + insertRows.size());
		
		ThaimtbLucene lucene = new ThaimtbLucene(insertRows);
		
		List<ESearching> words = searchingService.getSearching(true);
		
		for (ESearching word : words) {
			List<SearchThaimtbResult> searchResults = lucene.search(word.getDescription(), LuceneConstant.TITLE_CONTENT);
			logger.debug("THAIMTB SEARCH MATCH FOR WORD :" + word.getDescription() + " MATCH NUMBER =" + searchResults.size());
			for (SearchThaimtbResult searchResult : searchResults) {
				logger.debug("THAIMTB SEARCH MATCH TITLE DETAIL =" +searchResult.geteThaimtbContent().getDescription());
				SearchMapThaimtb map = new SearchMapThaimtb();
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
	}

}
