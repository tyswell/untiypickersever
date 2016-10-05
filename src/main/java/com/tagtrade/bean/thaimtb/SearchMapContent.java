package com.tagtrade.bean.thaimtb;

import com.tagtrade.dataacess.entity.bean.ESearching;
import com.tagtrade.dataacess.entity.bean.EThaimtbContent;

public class SearchMapThaimtb {

	private ESearching eSearching;
	private EThaimtbContent eThaimtbContent;
	private Float scoreHit;
	
	public ESearching geteSearching() {
		return eSearching;
	}
	public void seteSearching(ESearching eSearching) {
		this.eSearching = eSearching;
	}
	public EThaimtbContent geteThaimtbContent() {
		return eThaimtbContent;
	}
	public void seteThaimtbContent(EThaimtbContent eThaimtbContent) {
		this.eThaimtbContent = eThaimtbContent;
	}
	public Float getScoreHit() {
		return scoreHit;
	}
	public void setScoreHit(Float scoreHit) {
		this.scoreHit = scoreHit;
	}
	
	
}
