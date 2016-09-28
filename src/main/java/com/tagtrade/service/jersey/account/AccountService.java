package com.tagtrade.service.jersey.account;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.tagtrade.bean.jersey.account.Account;

@Path("/account")
public class AccountService {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response account(Account account) {
		System.out.println("username="+account.getUsername());
		System.out.println("token"+account.getToken());
		
		String result = "OKOKOK";
		
		return Response.status(201).entity(result).build();
	}
	
}
