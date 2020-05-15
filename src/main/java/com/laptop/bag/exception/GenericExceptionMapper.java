package com.laptop.bag.exception;

import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@Provider
public class GenericExceptionMapper implements ExceptionMapper<Exception>  {

	private static final String HTTP_NOT_FOUND = "HTTP 404 Not Found";
	private static final String HTTP_BAD_REQUEST = "HTTP 400 Bad Request";
	private static final Logger oLog = LoggerFactory.getLogger(GenericExceptionMapper.class);
	@Override
	public Response toResponse(Exception exp) {
		ErrorMessage message = null;
		oLog.error(exp.getMessage(), exp);
		
		switch (exp.getMessage()) {
		case HTTP_NOT_FOUND:
			message = new ErrorMessage(Status.NOT_FOUND, exp.getMessage());
			return Response.status(Status.NOT_FOUND).entity(new GenericEntity<ErrorMessage>(message, ErrorMessage.class)).build();
		
		case HTTP_BAD_REQUEST:
			message = new ErrorMessage(Status.BAD_REQUEST, exp.getMessage());
			return Response.status(Status.BAD_REQUEST).entity(new GenericEntity<ErrorMessage>(message, ErrorMessage.class)).build();
	
		default:
			message = new ErrorMessage(Status.INTERNAL_SERVER_ERROR, exp.getMessage());
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(new GenericEntity<ErrorMessage>(message, ErrorMessage.class)).build();
		}
		
	}

}
