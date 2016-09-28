package com.tagtrade.service.searching;

import java.util.List;

import com.tagtrade.dataacess.entity.bean.ESearching;

public interface SearchingService {
	
	public List<ESearching> getSearching(boolean isActive);
	
	public int getNextId();

}
