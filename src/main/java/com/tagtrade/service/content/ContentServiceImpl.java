package com.tagtrade.service.content;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tagtrade.dataacess.entity.bean.EContent;
import com.tagtrade.dataacess.entity.bean.SContent;
import com.tagtrade.dataacess.entity.dao.EContentDAO;
import com.tagtrade.dataacess.entity.dao.SContentDAO;


public class ContentServiceImpl implements ContentService {

	@Autowired
	private EContentDAO eContentDAO;
	
	@Autowired
	private SContentDAO sContentDAO;
	
	@Override
	public EContent getLastestContent(int urlCode) {
		return eContentDAO.selectLastestId(urlCode);
	}

	@Override
	public int getNextId() {
		return sContentDAO.nextSequence();
	}

	@Override
	public EContent getLastestFacebookContent(int facebookGroupCode) {
		return eContentDAO.selectLastestFacebookCode(facebookGroupCode);
	}

	@Override
	public void setNextId(int id) {
		sContentDAO.deleteAll();
		SContent sContent = new SContent();
		sContent.setId(id);
		sContentDAO.insert(sContent);
	}

	@Override
	public List<EContent> getAllContent() {
		return eContentDAO.selectAll();
	}

}
