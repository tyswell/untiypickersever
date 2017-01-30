package com.tagtrade.service.searching;

import java.util.List;

import com.tagtrade.bean.SearchContentResult;

public interface SearchLuceneService {
	
	public List<SearchContentResult> search(String word, int indexSearchPage);

}
