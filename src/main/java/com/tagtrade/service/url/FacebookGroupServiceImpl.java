package com.tagtrade.service.url;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tagtrade.dataacess.entity.bean.RFacebookGroup;
import com.tagtrade.dataacess.entity.dao.RFacebookGroupDAO;

public class FacebookGroupServiceImpl implements FacebookGroupService {

	@Autowired
	private RFacebookGroupDAO rFacebookGroupDAO;
	
	@Override
	public List<RFacebookGroup> getAllGroup() {
		return rFacebookGroupDAO.selectAll();
	}

}
