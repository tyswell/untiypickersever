package com.tagtrade.service.content;

import com.tagtrade.dataacess.entity.bean.EThaimtbContent;

public interface ThaimtbContentService {
	
	public EThaimtbContent getLastestContent();
	
	public int getNextId();

}
