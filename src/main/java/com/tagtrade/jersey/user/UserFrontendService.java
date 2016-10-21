package com.tagtrade.jersey.user;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tagtrade.bean.jersey.account.User;
import com.tagtrade.service.user.UserService;

@Component
@Path("/userx")
public class UserFrontendService {
	
	@Autowired
	private UserService userService;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response account(User user) {
		if (user != null) {
			boolean isValidToken = userService.isValidTokenUID(user.getUsername(), user.getTokenUid());
			
			System.out.println("isValidToken="+isValidToken);
			System.out.println("getTokenUid="+user.getTokenUid());
			System.out.println("getTokenUid="+user.getUsername());
			
			if (isValidToken) {
				boolean isLogin = userService.isUserExist(user.getUsername());
				if (isLogin) {
					userService.login(user);
				} else {
					userService.registerUser(user);
				}
			}
		}
		String result = "OKOKOK";
		
		return Response.status(201).entity(result).build();
	}
	
	
	
}
