package com.tagtrade.service.searching;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tagtrade.dataacess.entity.bean.ESearching;
import com.tagtrade.dataacess.entity.dao.ESearchingDAO;
import com.tagtrade.util.FlagConstant;

public class SearchingServiceImpl implements SearchingService {

	@Autowired
	private ESearchingDAO eSearchingDAO;
	
	@Override
	public List<ESearching> getSearching(boolean isActive) {
		return eSearchingDAO.getActive(FlagConstant.convertToBooleanString(isActive));
	}

	@Override
	public int getNextId() {
		return eSearchingDAO.nextSequence();
	}

}
