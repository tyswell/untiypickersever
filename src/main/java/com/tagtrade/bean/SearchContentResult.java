package com.tagtrade.bean;

import com.tagtrade.dataacess.entity.bean.EContent;


public class SearchContentResult {

	private EContent eContent;
	private Float scoreHit;
	
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
