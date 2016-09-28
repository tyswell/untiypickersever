package com.tagtrade.bean;

import com.tagtrade.dataacess.entity.bean.EThaimtbContent;

public class SearchThaimtbResult {

	private EThaimtbContent eThaimtbContent;
	private Float scoreHit;
	
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
