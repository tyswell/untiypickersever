package com.tagtrade.jersey.search;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tagtrade.bean.jersey.search.DeleteSearching;
import com.tagtrade.bean.jersey.search.Searching;
import com.tagtrade.bean.user.FirebaseUser;
import com.tagtrade.exception.EUError;
import com.tagtrade.service.searching.SearchingService;
import com.tagtrade.service.user.UserService;

@Component
@Path("/searchingservice")
public class SearchingFrontendService {
	
	@Autowired
	private SearchingService searchingService;
	
	@Autowired
	private UserService userService;
	
	@POST
	@Path("/addsearch")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addSearch(Searching search) throws EUError {
		validate(search);
		
		FirebaseUser user = userService.getFirebaseUser(search.getTokenId());
		if (user != null) {
			String userId = user.getUserId();
			
			if (!searchingService.isWordExist(userId, search.getDescription())) {
				int id = searchingService.addSearching(user, search);
				
				return Response.status(201).entity(id).build();
			} else {
				throw new EUError("SEARCHING WORD ALREADY EXIST");
			}
		} else {
			throw new EUError("TOKEN IS WRONG");
		}
	}
	
	@POST
	@Path("/deletesearch")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteSearch(DeleteSearching deleteSearching) throws EUError {
		validate(deleteSearching);
		
		FirebaseUser user = userService.getFirebaseUser(deleteSearching.getTokenId());
		if (user != null) {
			String userId = user.getUserId();
			
			if (!searchingService.isSearchingExist(userId, deleteSearching.getSearchingId())) {
				searchingService.deleteSearching(deleteSearching.getSearchingId());
				
				return Response.status(201).entity(true).build();
			} else {
				throw new EUError("SEARCHING ID IS WRONG : " + deleteSearching.getSearchingId());
			}
		} else {
			throw new EUError("TOKEN IS WRONG");
		}
	}

	
	private void validate(DeleteSearching deleteSearching) throws EUError {
		if (deleteSearching == null) {
			throw new EUError("DeleteSearching is Null");
		}
		
		if (deleteSearching.getTokenId() == null) {
			throw new EUError("TokenId is Null");
		}
		
		if (deleteSearching.getSearchingId() == null) {
			throw new EUError("SearchingId is Null");
		}
	}

	private void validate(Searching search) throws EUError {
		if (search == null) {
			throw new EUError("Search is Null");
		}
		
		if (search.getTokenId() == null) {
			throw new EUError("TokenId is Null");
		}
		
		if (search.getDescription() == null) {
			throw new EUError("Description is Null");
		}
		
		if (search.getDescription().length() < 3 ||
				search.getDescription().length() > 199) {
			throw new EUError("Description length is wrong : " + search.getDescription().length() );
		}
		
		if (search.getSearchTypeCode() == null) {
			throw new EUError("SearchTypeCode is Null");
		}
		
	}

}
