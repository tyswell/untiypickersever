package com.tagtrade.service.device;

import org.springframework.beans.factory.annotation.Autowired;

import com.tagtrade.dataacess.entity.bean.EUserDevice;
import com.tagtrade.dataacess.entity.dao.EUserDeviceDAO;

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

		eUserDeviceDAO.updateByKey(eUserDevice);
	}

}
