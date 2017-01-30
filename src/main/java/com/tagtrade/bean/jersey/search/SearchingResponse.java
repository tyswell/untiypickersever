package com.tagtrade.bean.jersey.search;

import java.sql.Timestamp;
import java.util.List;

public class SearchingResponse {
	
	private Integer searchingId;
	private Timestamp createDate;
	
	private List<SearchResult> searchResults;
	
	public Integer getSearchingId() {
		return searchingId;
	}
	public void setSearchingId(Integer searchingId) {
		this.searchingId = searchingId;
	}
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	public List<SearchResult> getSearchResults() {
		return searchResults;
	}
	public void setSearchResults(List<SearchResult> searchResults) {
		this.searchResults = searchResults;
	}
	
	

}
