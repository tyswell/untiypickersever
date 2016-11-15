package com.tagtrade.service.device;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tagtrade.dataacess.entity.bean.EUserDevice;
import com.tagtrade.dataacess.entity.dao.EUserDeviceDAO;
import com.tagtrade.util.DateUtil;

public class DeviceServiceImpl implements DeviceService {

	@Autowired
	private EUserDeviceDAO eUserDeviceDAO;


	@Override
	public EUserDevice getDevice(String userId, String deviceModel) {
		return eUserDeviceDAO.selectByKey(userId, deviceModel);
	}

	@Override
	public void updateDeviceToke(EUserDevice eUserDevice, String tokenNotification) {
		eUserDevice.setTokenNotification(tokenNotification);
		eUserDevice.setModifyDate(DateUtil.getTimestampNow());
		eUserDeviceDAO.updateByKey(eUserDevice);
	}

	@Override
	public List<EUserDevice> getAllDevice(String userId) {
		return eUserDeviceDAO.selectAllDevice(userId);
	}

}
