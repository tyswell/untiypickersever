package com.tagtrade.service.searching;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tagtrade.bean.jersey.search.Searching;
import com.tagtrade.bean.user.FirebaseUser;
import com.tagtrade.constant.StatusConst;
import com.tagtrade.dataacess.entity.bean.ESearching;
import com.tagtrade.dataacess.entity.bean.ErSearchTypeMapWebType;
import com.tagtrade.dataacess.entity.dao.ESearchingDAO;
import com.tagtrade.dataacess.entity.dao.ErSearchTypeMapWebTypeDAO;
import com.tagtrade.mapper.SearchingMapper;
import com.tagtrade.util.DateUtil;
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

	@Override
	public ESearching addSearching(FirebaseUser user, Searching search) {
		int id = getNextId();
		ESearching eSearching = SearchingMapper.mapToDAO(user, search);
		eSearching.setSearchingId(id);
		eSearching.setCreateDate(DateUtil.getTimestampNow());
		eSearching.setActive(StatusConst.ACTIVE);
		
		eSearchingDAO.insert(eSearching);
		
		return eSearching;
	}

	@Override
	public boolean isWordExist(String userId, String description) {
		return eSearchingDAO.isWordExist(userId, description);
	}

	@Override
	public List<Searching> getDataSearching(String userId,
			List<Integer> searchingIds) {
		List<Searching> results = new ArrayList<>();
		
		List<ESearching> eSearchings = eSearchingDAO.getSearchingByUser(userId, StatusConst.ACTIVE);
		
		for (ESearching eSearching : eSearchings) {
			Integer dataSearchingId = eSearching.getSearchingId();
			boolean isFound = false;
			
			if (searchingIds != null) {
				for (Integer searchingId : searchingIds) {
					if (dataSearchingId == searchingId) {
						isFound = true;
					}
				}
			}

			if (!isFound) {
				results.add(SearchingMapper.mapToService(eSearching));
			}
		}
		return results;
	}

	@Override
	public void inactiveSearching(Integer searchingId) {
		eSearchingDAO.inactiveByKey(searchingId);
	}

	@Override
	public boolean isSearchingExist(String userId, Integer searchingId) {
		return eSearchingDAO.isSearchingExist(userId, searchingId);
	}
	
}
