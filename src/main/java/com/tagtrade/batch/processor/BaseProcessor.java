package com.tagtrade.batch.processor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tagtrade.bean.BatchOutput;
import com.tagtrade.bean.SearchContentResult;
import com.tagtrade.bean.thaimtb.SearchMapContent;
import com.tagtrade.constant.LuceneConstant;
import com.tagtrade.dataacess.entity.bean.EContent;
import com.tagtrade.dataacess.entity.bean.ESearching;
import com.tagtrade.dataacess.entity.bean.RUrl;
import com.tagtrade.service.content.ContentService;
import com.tagtrade.service.searching.SearchingService;

public abstract class BaseProcessor {
	
	public abstract List<EContent> extractData(String url);
	
	@Autowired
	private ContentService contentService;
	@Autowired
	private SearchingService searchingService;
	
	private Logger logger = LoggerFactory.getLogger(BaseProcessor.class);
	
	public BatchOutput process(RUrl rUrl) {
		logger.debug("START EACTRACT URL" + rUrl.getUrl());
		
		BatchOutput output = new BatchOutput();
		
		lastestID = 0;
		
		EContent lastestRow = getLatestRow(rUrl.getCode());
		List<EContent> unInsertRows = getUnInsertRow(rUrl.getUrl(), lastestRow);
		List<EContent> newInsertRows = setID(unInsertRows);
		setNextID(lastestID + 1);
		
		List<ESearching> listSearch = getSearchWord(rUrl.getWebTypeCode());
		
		List<SearchMapContent> matchDatas = search(newInsertRows, listSearch);
		
		logger.debug("NUM INSERT ROWS = " + newInsertRows.size());
		
		output.seteContent(newInsertRows);
		output.setSearchMapThaimtbs(matchDatas);
		
		return output;
	}
	
	private EContent getLatestRow(int urlCode) {
		EContent data = contentService.getLastestContent(urlCode);
		if (data != null) {
			logger.debug("Lastest ID : " + data.getContentId());
		}
		return data;
	}
	
	private List<EContent> getUnInsertRow(String url, EContent lastestRow) {
		List<EContent> newsRows = extractData(url);
		
		if (lastestRow != null) {
			List<EContent> unInsertRows = new ArrayList<>();
			for (EContent newsRow : newsRows) {
				if (! newsRow.getContentWebId().equals(lastestRow.getContentWebId())) {
					unInsertRows.add(newsRow);
				} else {
					return unInsertRows;
				}
			}
			
			return unInsertRows;
		} else {
			return newsRows;
		}
	}
	
	private List<ESearching> getSearchWord(int webTypeCode) {
		List<ESearching> searchDatas = searchingService.getSearching(webTypeCode, true);
		return searchDatas;
	}
	
	private int lastestID = 0;
	
	private List<EContent> setID(List<EContent> insertRows) {
		int nextId = (contentService.getNextId() - 1) + insertRows.size();
		lastestID = nextId;
		
		for (EContent row : insertRows) {
			row.setContentId(nextId);
			nextId--;
		}
		
		return insertRows;
	}
	
	private void setNextID(int nextId) {
		contentService.setNextId(nextId);
	}
	
	private List<SearchMapContent> search(List<EContent> newInsertRows, List<ESearching> searchDatas) {
		List<SearchMapContent> matchDatas = new ArrayList<>();
		
//		BaseLucene lucene = new BaseLucene(newInsertRows);
		SearchTool searchTool = new SearchTool(newInsertRows);
		
		for (ESearching searchData : searchDatas) {
			List<SearchContentResult> searchResults = searchTool.search(searchData.getDescription());
			logger.debug("URL SEARCH MATCH FOR WORD :" + searchData.getDescription() + " MATCH NUMBER =" + searchResults.size());
			for (SearchContentResult searchResult : searchResults) {
				logger.debug("URL SEARCH MATCH TITLE DETAIL =" +searchResult.geteContent().getTitle());
				SearchMapContent matchData = new SearchMapContent();
				matchData.seteSearching(searchData);
				matchData.seteContent(searchResult.geteContent());
				matchData.setScoreHit(searchResult.getScoreHit());
				matchDatas.add(matchData);
			}
		}
		
		return matchDatas;
	}
	
	protected Document webScapping(String url) {
		Document doc = null;

		try {
			System.setProperty("http.proxyHost", "192.168.5.24");
			System.setProperty("http.proxyPort", "3128");
			
			doc = Jsoup
					.connect(url)
					.userAgent(
							"Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
					.timeout(20 * 1000).referrer("http://www.google.com").get();
		} catch (IOException e) {
			logger.error("HAS ERROR IN CONNECTION :" + e.getMessage());
		}

		return doc;
	}
	
}
