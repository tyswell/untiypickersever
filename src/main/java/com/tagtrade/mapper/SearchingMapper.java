package com.tagtrade.mapper;

import com.tagtrade.bean.jersey.search.Searching;
import com.tagtrade.bean.user.FirebaseUser;
import com.tagtrade.dataacess.entity.bean.ESearching;
import com.tagtrade.util.dozer.MappingUtil;

public class SearchingMapper {
	
	public static ESearching mapToDAO(FirebaseUser user, Searching data) {
		if (data == null) {
			return null;
		}
		
		ESearching result = new ESearching();
		MappingUtil.map(data, result);
		result.setUserId(user.getUserId());
		
		return result;
	}
	
	public static Searching mapToService(ESearching data) {
		if (data == null) {
			return null;
		}
		
		Searching result = new Searching();
		MappingUtil.map(data, result);
		
		return result;
	}

}
