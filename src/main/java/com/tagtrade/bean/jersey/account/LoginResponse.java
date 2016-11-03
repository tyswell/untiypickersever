package com.tagtrade.bean.jersey.account;

import java.util.List;

import com.tagtrade.bean.jersey.search.Searching;

public class LoginResponse {
	
	private List<Searching> searching;
	
	public List<Searching> getSearching() {
		return searching;
	}
	public void setSearching(List<Searching> searching) {
		this.searching = searching;
	}

	
}
