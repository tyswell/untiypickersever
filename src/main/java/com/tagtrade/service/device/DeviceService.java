package com.tagtrade.service.device;

import com.tagtrade.dataacess.entity.bean.EUserDevice;

public interface DeviceService {
	
	public EUserDevice getDevice(String userId, String deviceModel);
	
	public void updateDeviceToke(EUserDevice eUserDevice, String tokenNotification);

}
