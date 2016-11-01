package com.tagtrade.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

	@Override
    public Response toResponse(Throwable ex) {
        Response.StatusType type = getStatusType(ex);

        ErrorResponse error = new ErrorResponse(
                type.getStatusCode(),
                type.getReasonPhrase(),
                ex.getLocalizedMessage());

        return Response.status(error.getStatusCode())
                .entity(error)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    private Response.StatusType getStatusType(Throwable ex) {
    	
        if (ex instanceof WebApplicationException) {
        	ex.printStackTrace();
            return((WebApplicationException)ex).getResponse().getStatusInfo();
        } else if (ex instanceof EUError) {
        	ex.printStackTrace();
        	return Response.Status.NOT_ACCEPTABLE;
        } else {
        	ex.printStackTrace();
            return Response.Status.INTERNAL_SERVER_ERROR;
        }
    }
	
}
