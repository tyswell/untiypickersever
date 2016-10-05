package com.tagtrade.service.searching;

import java.util.List;

import com.tagtrade.dataacess.entity.bean.ESearching;

public interface SearchingService {
	
	public List<ESearching> getSearching(int webTypeCode, boolean isActive);
	
	public int getNextId();

}
