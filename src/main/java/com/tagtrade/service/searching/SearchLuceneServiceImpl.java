package com.tagtrade.service.searching;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import com.tagtrade.batch.processor.BaseLucene;
import com.tagtrade.bean.SearchContentResult;

public class SearchLuceneServiceImpl implements SearchLuceneService {
	
	private @Value("${lucene.take.record.perpage}") int takeRecords;

	@Override
	public List<SearchContentResult> search(String word, int indexSearchPage) {
		BaseLucene lucene = new BaseLucene();
		lucene.initSearch();
		List<SearchContentResult> result = lucene.search(word, indexSearchPage, takeRecords);
		return result;
	}

}
