package com.tagtrade.service.searching;

import java.util.List;

import com.tagtrade.bean.jersey.search.Searching;
import com.tagtrade.bean.user.FirebaseUser;
import com.tagtrade.dataacess.entity.bean.ESearching;

public interface SearchingService {
	
	public ESearching addSearching(String userId, Searching search);
	
	public void inactiveSearching(Integer searchingId);
	
	public List<ESearching> getSearching(int webTypeCode, boolean isActive);
	
	public int getNextId();
	
	public boolean isWordExist(String userId, String description);
	
	public boolean isSearchingExist(String userId, Integer searchingId);

	public List<Searching> getDataSearching(String userId, List<Integer> searchingIds);
}
