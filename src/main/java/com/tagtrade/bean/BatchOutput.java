package com.tagtrade.bean;

import java.util.List;

import com.tagtrade.bean.thaimtb.SearchMapThaimtb;
import com.tagtrade.dataacess.entity.bean.EThaimtbContent;

public class BatchOutput {
	
	private boolean hasThaimtb;
	private List<EThaimtbContent> eThaimtbContent;
	private List<SearchMapThaimtb> searchMapThaimtbs;
	
	public boolean isHasThaimtb() {
		return hasThaimtb;
	}
	public void setHasThaimtb(boolean hasThaimtb) {
		this.hasThaimtb = hasThaimtb;
	}
	public List<EThaimtbContent> geteThaimtbContent() {
		return eThaimtbContent;
	}
	public void seteThaimtbContent(List<EThaimtbContent> eThaimtbContent) {
		this.eThaimtbContent = eThaimtbContent;
	}
	public List<SearchMapThaimtb> getSearchMapThaimtbs() {
		return searchMapThaimtbs;
	}
	public void setSearchMapThaimtbs(List<SearchMapThaimtb> searchMapThaimtbs) {
		this.searchMapThaimtbs = searchMapThaimtbs;
	}
	

	

}
