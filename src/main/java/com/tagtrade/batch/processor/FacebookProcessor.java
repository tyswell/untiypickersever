package com.tagtrade.batch.processor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.restfb.Connection;
import com.restfb.FacebookClient;
import com.restfb.FacebookClient.AccessToken;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.batch.BatchRequest;
import com.restfb.batch.BatchResponse;
import com.restfb.types.Post;
import com.tagtrade.bean.BatchOutput;
import com.tagtrade.bean.SearchContentResult;
import com.tagtrade.bean.thaimtb.SearchMapContent;
import com.tagtrade.comparator.ContentComparator;
import com.tagtrade.constant.LuceneConstant;
import com.tagtrade.dataacess.entity.bean.EContent;
import com.tagtrade.dataacess.entity.bean.ESearching;
import com.tagtrade.dataacess.entity.bean.RFacebookGroup;
import com.tagtrade.search.SearchTool;
import com.tagtrade.service.bean.Content;
import com.tagtrade.service.content.ContentService;
import com.tagtrade.service.searching.SearchingService;
import com.tagtrade.service.url.FacebookGroupService;
import com.tagtrade.facebook.DefaultFacebookGraphClient;
import com.vdurmont.emoji.EmojiParser;

public class FacebookProcessor {
	
	private Logger logger = LoggerFactory.getLogger(FacebookProcessor.class);
	
	@Autowired
	private ContentService contentService;
	@Autowired
	private SearchingService searchingService;
	@Autowired
	private FacebookGroupService facebookGroupService;
	
	private static final String FEED_DESC = "feed";
	private static final String FIELDS_REQUEST = "permalink_url,message,link,id,created_time,updated_time";

	static String host = "192.168.5.24";
	static int port = 3128;
	
	static String groupID = "471950956208232";
	
	private @Value("${facebook.app.id}") String appID;
	private @Value("${facebook.app.secret}") String appSecret;
	
	private static final String SOLD_DESC = "SOLD";
	private static final String BATH_SIGN_DESC = "฿";
	
	private FacebookClient facebookClient;

	public BatchOutput process() {
		BatchOutput output = new BatchOutput();
		
		facebookClient = new DefaultFacebookGraphClient(getAccessToken(), 50000, host, port, Version.VERSION_2_7);
		
		List<EContent> insertRows = new ArrayList<>();
		List<SearchMapContent> matchDatas = new ArrayList<>();
		
		List<RFacebookGroup> gropDatas = facebookGroupService.getAllGroup();
		
		List<String> groupIds = getGroupIds(gropDatas);
		
		List<BatchResponse> facebookResponses = requestBuilderes(groupIds);
		
		int faceBookResponseIndex = 0;
		lastestId = 0;
		for (BatchResponse facebookResponse : facebookResponses) {
			RFacebookGroup groupData = gropDatas.get(faceBookResponseIndex);
			List<Content> contents = convert(groupData, facebookResponse);
			Collections.sort(contents, new ContentComparator());// sort latest date first
			EContent lastestRow = getLatestRow(groupData.getCode());
			List<EContent> newInsertRows =  setID(fillterRow(contents, lastestRow));
			List<ESearching> listSearch = getSearchWord(groupData.getWebTypeCode());
			List<SearchMapContent> listMatchData = search(newInsertRows, listSearch);
			
			matchDatas.addAll(listMatchData);
			insertRows.addAll(newInsertRows);
			faceBookResponseIndex++;
		}
		
		setNextID(lastestId + 1);
		
		logger.debug("NUM INSERT ROWS = " + insertRows.size());
		
		output.seteContent(insertRows);
		output.setSearchMapThaimtbs(matchDatas);
		
		return output;
	}
	
	private String getAccessToken() {
		AccessToken accessToken =
  			  new DefaultFacebookGraphClient(null, 50000, host, port, Version.VERSION_2_7).obtainAppAccessToken(appID, appSecret);
		
		logger.debug("FACEBOOK ACCESS TOKEN :"+accessToken.getAccessToken());
		
		return accessToken.getAccessToken();
	}
	
	private List<String> getGroupIds(List<RFacebookGroup> gropDatas) {
		List<String> gropIdWithFeedDesces = new ArrayList<>();
		
		for (RFacebookGroup gropData : gropDatas) {
			gropIdWithFeedDesces.add(gropData.getGroupid());
		}
		
		return gropIdWithFeedDesces;
	}
	
	private List<BatchResponse> requestBuilderes(List<String> groupIds) {
		List<BatchRequest> batchRequests = new ArrayList<>();
		for (String groupId : groupIds) {
			batchRequests.add(getGroupBatchRequest(groupId));
		}
		
		return facebookClient.executeBatch(batchRequests);
	}
	
	private BatchRequest getGroupBatchRequest(String groupId) {
		return new BatchRequest.BatchRequestBuilder(
				groupId + "/" + FEED_DESC)
    	  		.parameters(Parameter.with("fields", FIELDS_REQUEST),
					Parameter.with("limit", 10))
					.build();
	}
	
	private EContent getLatestRow(int facebookGroupCode) {
		EContent data = contentService.getLastestFacebookContent(facebookGroupCode);
		if (data != null) {
			logger.debug("Lastest ID : " + data.getContentId());
		}
		return data;
	}
	
	private List<Content> convert(RFacebookGroup facebookGroupData, BatchResponse response) {
		List<Content> contents = new ArrayList<>();
		Connection<Post> posts = new Connection<Post>(facebookClient, response.getBody(), Post.class);
	
    	for (Post p : posts.getData()) {
    		String message = removeNonUTF8(p.getMessage());
    		
    		Content content = new Content();
    		content.setContentWebId(p.getId());
    		content.setTitle(getTitle(message));
    		content.setDescription(message);
    		content.setUrlContentLink(p.getPermalinkUrl());
    		content.setDateContentCreate(p.getCreatedTime());
    		
    		content.setFacebookGropCode(facebookGroupData.getCode());
    		content.setUrlCode(2);
    		
    		contents.add(content);
    	}
    	
    	return contents;
	}
		
	private List<EContent> fillterRow(List<Content> contents, EContent lastestRow) {
		List<EContent> unInsertRows = new ArrayList<>();
		
		for (Content newsRow : contents) {
			boolean isCorrect = true;
			if (newsRow.getDescription() == null || 
					newsRow.getDescription().length() >= 1000 ||
					newsRow.getDescription().contains(SOLD_DESC) ||
					!newsRow.getDescription().contains(BATH_SIGN_DESC)){
				isCorrect = false;
			}
			if (newsRow.getTitle() == null || 
					newsRow.getDescription().length() >= 500){
				isCorrect = false;
			}
			if (lastestRow != null) {
				if (lastestRow.getDateContentCreate().after(newsRow.getDateContentCreate())) { // now is slow than post date
					isCorrect = false;
				}
				if (newsRow.getContentWebId().equals(lastestRow.getContentWebId())) {
					isCorrect = false;
				} 
			}
			
			if (isCorrect) {
				unInsertRows.add(convert(newsRow));
			}
		}
		
		return unInsertRows;
	}
	
	private String removeNonUTF8(String word) {
		if (word != null) {
			return EmojiParser.removeAllEmojis(word);
		}
		return word;
	}
		
	private EContent convert(Content content) {
		if (content != null) {
			EContent eContent = new EContent();
			eContent.setContentWebId(content.getContentWebId());
			eContent.setDateContentCreate(new Timestamp( content.getDateContentCreate().getTime() ));
			eContent.setDescription(content.getDescription());
			eContent.setFacebookGropCode(content.getFacebookGropCode());
			eContent.setTitle(content.getTitle());
			eContent.setUrlCode(content.getUrlCode());
			eContent.setUrlContentLink(content.getUrlContentLink());
			return eContent;
		}
		return null;
	}
	
	private List<SearchMapContent> search(List<EContent> newInsertRows, List<ESearching> searchDatas) {
		List<SearchMapContent> matchDatas = new ArrayList<>();
		
//		BaseLucene lucene = new BaseLucene(newInsertRows);
		SearchTool searchTool = new SearchTool(newInsertRows);
		
		for (ESearching searchData : searchDatas) {
			List<SearchContentResult> searchResults = searchTool.search(searchData.getDescription());
			logger.debug("THAIMTB SEARCH MATCH FOR WORD :" + searchData.getDescription() + " MATCH NUMBER =" + searchResults.size());
			for (SearchContentResult searchResult : searchResults) {
				logger.debug("THAIMTB SEARCH MATCH TITLE DETAIL =" +searchResult.geteContent().getDescription());
				SearchMapContent matchData = new SearchMapContent();
				matchData.seteSearching(searchData);
				matchData.seteContent(searchResult.geteContent());
				matchData.setScoreHit(searchResult.getScoreHit());
				matchDatas.add(matchData);
			}
		}
		
		return matchDatas;
	}
	
	private List<ESearching> getSearchWord(int webTypeCode) {
		List<ESearching> searchDatas = searchingService.getSearching(webTypeCode, true);
		return searchDatas;
	}
	
	private int lastestId = 0;
	
	private List<EContent> setID(List<EContent> insertRows) {
		int nextId = 0;
		if (lastestId == 0) {
			nextId = (contentService.getNextId() - 1) + insertRows.size();
		} else {
			nextId = lastestId + insertRows.size();
		}
		lastestId = nextId;
		
		for (EContent row : insertRows) {
			row.setContentId(nextId);
			nextId--;
		}
		
		return insertRows;
	}
	
	private void setNextID(int nextId) {
		contentService.setNextId(nextId);
	}
	
	public static String getTitle(String description) {
		if (description != null) {
			if (description.contains(BATH_SIGN_DESC)) {
				return description.substring(0, description.indexOf(BATH_SIGN_DESC));
			}
		}
		
		return description;
	}
	
}
