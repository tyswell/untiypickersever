package com.tagtrade.service.searching;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tagtrade.dataacess.entity.bean.ESearching;
import com.tagtrade.dataacess.entity.bean.ErSearchTypeMapWebType;
import com.tagtrade.dataacess.entity.dao.ESearchingDAO;
import com.tagtrade.dataacess.entity.dao.ErSearchTypeMapWebTypeDAO;
import com.tagtrade.util.FlagConstant;

public class SearchingServiceImpl implements SearchingService {

	@Autowired
	private ESearchingDAO eSearchingDAO;
	@Autowired
	private ErSearchTypeMapWebTypeDAO erSearchTypeMapWebTypeDAO;
	
	@Override
	public List<ESearching> getSearching(int webTypeCode, boolean isActive) {
		List<ErSearchTypeMapWebType> erDatas = erSearchTypeMapWebTypeDAO.selectSearchType(webTypeCode);
		
		List<Integer> seachTypeCodes = new ArrayList<>();
		for (ErSearchTypeMapWebType erData : erDatas) {
			seachTypeCodes.add(erData.getSearchTypeCode());
		}
		
		return eSearchingDAO.getSearch(FlagConstant.convertToBooleanString(isActive), seachTypeCodes);
	}

	@Override
	public int getNextId() {
		return eSearchingDAO.nextSequence();
	}

}
