package com.tagtrade.service.content;

import org.springframework.beans.factory.annotation.Autowired;

import com.tagtrade.dataacess.entity.bean.EThaimtbContent;
import com.tagtrade.dataacess.entity.dao.EThaimtbContentDAO;

public class ThaimtbContentServiceImpl implements ThaimtbContentService {

	@Autowired
	private EThaimtbContentDAO eThaimtbContentDAO;
	
	@Override
	public EThaimtbContent getLastestContent() {
		return eThaimtbContentDAO.selectLastestId();
	}

	@Override
	public int getNextId() {
		return eThaimtbContentDAO.nextSequence();
	}

}
