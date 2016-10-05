package com.tagtrade.bean.thaimtb;

import com.tagtrade.dataacess.entity.bean.EContent;
import com.tagtrade.dataacess.entity.bean.ESearching;

public class SearchMapContent {

	private ESearching eSearching;
	private EContent eContent;
	private Float scoreHit;
	
	public ESearching geteSearching() {
		return eSearching;
	}
	public void seteSearching(ESearching eSearching) {
		this.eSearching = eSearching;
	}
	public EContent geteContent() {
		return eContent;
	}
	public void seteContent(EContent eContent) {
		this.eContent = eContent;
	}
	public Float getScoreHit() {
		return scoreHit;
	}
	public void setScoreHit(Float scoreHit) {
		this.scoreHit = scoreHit;
	}
	
	
}
