package com.tagtrade.service.searching;

import java.util.List;

import com.tagtrade.bean.jersey.search.Searching;
import com.tagtrade.bean.user.FirebaseUser;
import com.tagtrade.dataacess.entity.bean.ESearching;

public interface SearchingService {
	
	public void addSearching(FirebaseUser user, Searching search);
	
	public List<ESearching> getSearching(int webTypeCode, boolean isActive);
	
	public int getNextId();
	
	public boolean isWordExist(String userId, String description);

	public List<Searching> getDataSearching(String userId, List<Integer> searchingIds);
}
