package com.tagtrade.bean.jersey.search;

public class SearchResult {
	
	private String userId;
	private Integer searchId;
	private String searchDesc;
	private Integer contentId;
	private String titleDesc;
	private String webName;
	private String url;
	private String contentDate;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Integer getSearchId() {
		return searchId;
	}
	public void setSearchId(Integer searchId) {
		this.searchId = searchId;
	}
	public String getSearchDesc() {
		return searchDesc;
	}
	public void setSearchDesc(String searchDesc) {
		this.searchDesc = searchDesc;
	}
	public Integer getContentId() {
		return contentId;
	}
	public void setContentId(Integer contentId) {
		this.contentId = contentId;
	}
	public String getTitleDesc() {
		return titleDesc;
	}
	public void setTitleDesc(String titleDesc) {
		this.titleDesc = titleDesc;
	}
	public String getWebName() {
		return webName;
	}
	public void setWebName(String webName) {
		this.webName = webName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getContentDate() {
		return contentDate;
	}
	public void setContentDate(String contentDate) {
		this.contentDate = contentDate;
	}
	
	

}
