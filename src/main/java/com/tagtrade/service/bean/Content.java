package com.tagtrade.service.bean;

import java.util.Date;

public class Content {

	private Integer contentId;
	private Integer urlCode;
	private Integer facebookGropCode;
	private String title;
	private String description;
	private String contentWebId;
	private String urlContentLink;
	private Date dateContentCreate;

	public Integer getContentId() {
		return contentId;
	}

	public void setContentId(Integer contentId) {
		this.contentId = contentId;
	}

	public Integer getUrlCode() {
		return urlCode;
	}

	public void setUrlCode(Integer urlCode) {
		this.urlCode = urlCode;
	}

	public Integer getFacebookGropCode() {
		return facebookGropCode;
	}

	public void setFacebookGropCode(Integer facebookGropCode) {
		this.facebookGropCode = facebookGropCode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContentWebId() {
		return contentWebId;
	}

	public void setContentWebId(String contentWebId) {
		this.contentWebId = contentWebId;
	}

	public String getUrlContentLink() {
		return urlContentLink;
	}

	public void setUrlContentLink(String urlContentLink) {
		this.urlContentLink = urlContentLink;
	}

	public Date getDateContentCreate() {
		return dateContentCreate;
	}

	public void setDateContentCreate(Date dateContentCreate) {
		this.dateContentCreate = dateContentCreate;
	}


}
