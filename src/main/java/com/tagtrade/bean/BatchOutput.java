package com.tagtrade.bean;

import java.util.List;

import com.tagtrade.bean.thaimtb.SearchMapContent;
import com.tagtrade.dataacess.entity.bean.EContent;

public class BatchOutput {
	
	private boolean hasThaimtb;
	private List<EContent> eContent;
	private List<SearchMapContent> searchMapThaimtbs;
	
	public boolean isHasThaimtb() {
		return hasThaimtb;
	}
	public void setHasThaimtb(boolean hasThaimtb) {
		this.hasThaimtb = hasThaimtb;
	}

	public List<EContent> geteContent() {
		return eContent;
	}
	public void seteContent(List<EContent> eContent) {
		this.eContent = eContent;
	}
	public List<SearchMapContent> getSearchMapThaimtbs() {
		return searchMapThaimtbs;
	}
	public void setSearchMapThaimtbs(List<SearchMapContent> searchMapThaimtbs) {
		this.searchMapThaimtbs = searchMapThaimtbs;
	}
	

	

}
