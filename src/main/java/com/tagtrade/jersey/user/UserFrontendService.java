package com.tagtrade.jersey.user;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.tagtrade.bean.jersey.account.User;
import com.tagtrade.constant.MessageErrorConstant;
import com.tagtrade.exception.EUException;
import com.tagtrade.exception.ErrorResponse;
import com.tagtrade.service.user.UserService;

@Component
@Path("/userx")
public class UserFrontendService {
	
	@Autowired
	private UserService userService;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response account(User user) {
		validate(user);
		
		if (userService.isValidToken(user)) {
			String tokenUid = null;
			if (userService.isUserExist(user.getUsername())) {
				tokenUid = userService.login(user);
			} else {
				tokenUid = userService.registerUser(user);
			}
			
			return Response.status(201).entity(tokenUid).build();
		} else {
			return Response.status(201).entity("FAILLLL").build();
		}		
	}

	private boolean validate(User user) {
		if (user == null) {
			throw new EUException(
					MessageErrorConstant.CRITERIA_WRONG_CODE, 
					"OBJECT USER IS NULL");
		}
		
		if (user.getUsername() == null) {
			throw new EUException(
					MessageErrorConstant.CRITERIA_WRONG_CODE, 
					"Username IS NULL");
		}
		return false;
	}
	
	@ExceptionHandler(EUException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.PRECONDITION_FAILED.value());
		error.setMessage(ex.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);
	}
	
	
	
}
