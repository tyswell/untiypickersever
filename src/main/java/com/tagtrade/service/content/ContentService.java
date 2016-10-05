package com.tagtrade.service.content;


import com.tagtrade.dataacess.entity.bean.EContent;

public interface ContentService {
	
	public EContent getLastestContent(int urlCode);
	
	public int getNextId();
	
	public void setNextId(int id);
	
	public EContent getLastestFacebookContent(int facebookGroupCode);

}
