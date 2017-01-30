package com.tagtrade.jersey.device;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tagtrade.bean.jersey.device.DeviceToken;
import com.tagtrade.bean.user.FirebaseUser;
import com.tagtrade.dataacess.entity.bean.EUserDevice;
import com.tagtrade.exception.EUError;
import com.tagtrade.service.device.DeviceService;
import com.tagtrade.service.user.UserService;

@Component
@Path("/deviceservice")
public class DeviceFrontendService {
	
	@Autowired
	private UserService userService;
	@Autowired
	private DeviceService deviceService;
	
	@POST
	@Path("/updatetoken")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateToken(DeviceToken deviceToken) throws EUError {
		validate(deviceToken);
		
		FirebaseUser user = userService.getFirebaseUser(deviceToken.getTokenId());
		
		if (user != null) {
			EUserDevice eUserDevice = deviceService.getDevice(user.getUserId(), deviceToken.getDeviceModel());
			if (eUserDevice != null) {
				deviceService.updateDeviceToke(eUserDevice, deviceToken.getTokenNotification());
				return Response.status(201).entity(true).build();
			} else {
				throw new EUError("DeviceModel IS WRONG : " + deviceToken.getDeviceModel());
			}
		} else {
			throw new EUError("TOKEN IS WRONG");
		}
	}

	private void validate(DeviceToken deviceToken) throws EUError {
		if (deviceToken == null) {
			throw new EUError("deviceToken IS NULL");
		}
		
		if (deviceToken.getTokenId() == null) {
			throw new EUError("TokenId IS NULL");
		}
		
		if (deviceToken.getTokenNotification() == null) {
			throw new EUError("TokenNotification IS NULL");
		}
		
		if (deviceToken.getDeviceModel() == null) {
			throw new EUError("DeviceModel IS NULL");
		}
	}

}
