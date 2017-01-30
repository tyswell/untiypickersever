package com.tagtrade.mapper;

import com.tagtrade.bean.jersey.search.SearchResult;
import com.tagtrade.bean.jersey.search.Searching;
import com.tagtrade.bean.user.FirebaseUser;
import com.tagtrade.constant.FacebookGroupConstant;
import com.tagtrade.constant.UrlConstant;
import com.tagtrade.constant.WebNameConstant;
import com.tagtrade.dataacess.entity.bean.EContent;
import com.tagtrade.dataacess.entity.bean.ESearching;
import com.tagtrade.util.DateUtil;
import com.tagtrade.util.TimestampUtil;
import com.tagtrade.util.dozer.MappingUtil;

public class SearchingMapper {
	
	public static ESearching mapToDAO(String userId, Searching data) {
		if (data == null) {
			return null;
		}
		
		ESearching result = new ESearching();
		MappingUtil.map(data, result);
		result.setUserId(userId);
		
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
		
	public static SearchResult mapToFrontend(EContent content, Integer searchId, String searchDesc, String userId) {
		SearchResult result = new SearchResult();
		
		result.setContentDate(TimestampUtil.timestampToStringThaiFormat(content.getCreateDate()));
		result.setContentId(content.getContentId());
		result.setSearchDesc(searchDesc);
		result.setSearchId(searchId);
		result.setTitleDesc(content.getTitle());
		result.setUrl(content.getUrlContentLink());
		result.setUserId(userId);
		if (content.getUrlCode() == UrlConstant.FACEBOOK_URL_CODE) {
			result.setWebName(FacebookGroupConstant.codeToDesc(content.getFacebookGropCode()));
		} else {
			result.setWebName(WebNameConstant.codeToDesc(content.getUrlCode()));
		}
		
		return result;
	}

}
