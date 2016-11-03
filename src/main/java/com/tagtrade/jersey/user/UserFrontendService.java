package com.tagtrade.jersey.user;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tagtrade.bean.jersey.account.LoginReceive;
import com.tagtrade.bean.jersey.account.LoginResponse;
import com.tagtrade.bean.user.FirebaseUser;
import com.tagtrade.constant.UserLoginType;
import com.tagtrade.exception.EUError;
import com.tagtrade.service.searching.SearchingService;
import com.tagtrade.service.user.UserService;

@Component
@Path("/userservice")
public class UserFrontendService {
	
	@Autowired
	private UserService userService;
	@Autowired
	private SearchingService searchingService;

	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response login(LoginReceive loginReceive) throws EUError {
		validate(loginReceive);
		
		FirebaseUser user = userService.getFirebaseUser(loginReceive.getTokenId());
		if (user != null) {
			LoginResponse result = new LoginResponse();
			
			if (userService.isUserIdExist(user.getUserId())) {
				if (userService.isUserValid(user.getUserId())) {
					userService.login(user, loginReceive.getDevice());
					result.setSearching(searchingService.getDataSearching(user.getUserId(), loginReceive.getSearchingIds()));
				} else {
					throw new EUError("USER IS INVALID");
				}				
			} else {
				userService.registerUser(user, loginReceive.getDevice());
			}
						
			return Response.status(201).entity(result).build();
		} else {
			throw new EUError("TOKEN IS WRONG");
		}		
	}

	private void validate(LoginReceive loginReceive) throws EUError {
		if (loginReceive == null) {
			throw new EUError("loginReceive IS NULL");
		}
		
		if (loginReceive.getTokenId() == null) {
			throw new EUError("TokenId IS NULL");
		}
				
		if (loginReceive.getDevice() == null) {
			throw new EUError("Device IS NULL");
		}
		
		if (loginReceive.getDevice().getOsTypeCode() == null) {
			throw new EUError("OsTypeCode IS NULL");
		}
		
		if (loginReceive.getDevice().getTokenNotification() == null) {
			throw new EUError("TokenNotification IS NULL");
		}
		
		if (loginReceive.getDevice().getDeviceModel() == null) {
			throw new EUError("DeviceModel IS NULL");
		}
		
	}
	
	
}
