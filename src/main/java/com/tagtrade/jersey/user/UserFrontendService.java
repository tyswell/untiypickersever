package com.tagtrade.jersey.user;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tagtrade.bean.jersey.account.User;
import com.tagtrade.bean.jersey.account.UserLogin;
import com.tagtrade.constant.UserLoginType;
import com.tagtrade.exception.EUError;
import com.tagtrade.mapper.UserMapper;
import com.tagtrade.service.user.UserService;

@Component
@Path("/userx")
public class UserFrontendService {
	
	@Autowired
	private UserService userService;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response account(User user) throws EUError {
		validate(user);
		
		if (userService.isValidToken(user)) {
			User respondUser = null;
			if (userService.isEmailExist(user.getEmail())) {
				respondUser = userService.login(user);
			} else {
				respondUser = userService.registerUser(user);
			}
			
			UserLogin result = UserMapper.toJersey(respondUser);
			
			return Response.status(201).entity(result).build();
		} else {
			throw new EUError("VALIDATE TOKEN IS WRONG");
		}		
	}

	private void validate(User user) throws EUError {
		if (user == null) {
			throw new EUError("User IS NULL");
		}
		
		if (user.getEmail() == null) {
			throw new EUError("Email IS NULL");
		}
		
		if (user.getDisplayName() == null) {
			throw new EUError("DisplayName IS NULL");
		}
		
		if (user.getUserLoginType() == null) {
			throw new EUError("UserLoginType IS NULL");
		}
		
		if (user.getUserLoginType() == UserLoginType.FACEBOOK_LOGIN) {
			if (user.getFacebookUser() == null) {
				throw new EUError("FacebookUser IS NULL");
			}
			
			if (user.getFacebookUser().getFacebookId() == null) {
				throw new EUError("FacebookId IS NULL");
			}
			
			if (user.getFacebookUser().getTokenFacebook() == null) {
				throw new EUError("TokenFacebook IS NULL");
			}
		} else if (user.getUserLoginType() == UserLoginType.GOOGLE_LOGIN) {
			
		} else {
			throw new EUError("LOGINTYPECODE IS WRONG value =" + user.getUserLoginType());
		}
		
		if (user.getDevice() == null) {
			throw new EUError("Device IS NULL");
		}
		
		if (user.getDevice().getOsTypeCode() == null) {
			throw new EUError("OsTypeCode IS NULL");
		}
		
		if (user.getDevice().getTokenNotification() == null) {
			throw new EUError("TokenNotification IS NULL");
		}
		
		if (user.getDevice().getDeviceModel() == null) {
			throw new EUError("DeviceModel IS NULL");
		}
		
	}
	
	
}
