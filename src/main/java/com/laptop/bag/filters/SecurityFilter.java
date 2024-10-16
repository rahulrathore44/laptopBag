package com.laptop.bag.filters;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.Provider;


import com.laptop.bag.exception.ErrorMessage;

@Provider
public class SecurityFilter implements ContainerRequestFilter,ContainerResponseFilter {
	
	private static final String BASIC_AUTH = "Basic ";
	private static final String AUTHORIZATION_KEY = "Authorization";
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		boolean flag = false;
		if(requestContext.getUriInfo().getPath().contains("secure")){
			List<String> headers = requestContext.getHeaders().get(AUTHORIZATION_KEY);
			String authValue = new String(Base64.getDecoder().decode(headers.get(0).replace(BASIC_AUTH, "")));
			if(null == headers || !isAuthorize(authValue)){
				flag = true;
			}
			if(flag){
				ErrorMessage msg = new ErrorMessage(Status.UNAUTHORIZED, "An error occured");
				requestContext.abortWith(Response.status(Status.UNAUTHORIZED)
						.entity(new GenericEntity<ErrorMessage>(msg, ErrorMessage.class))
						.build());
			}
			
		}
	}

	@Override
	public void filter(ContainerRequestContext arg0,
			ContainerResponseContext arg1) throws IOException {
	}
	
	private boolean isAuthorize(String password){
		System.out.println("Info => " + password);
		String []str = password.split(":");
		if(str == null || str.length == 0) 
			return false;
		if(str[0].equalsIgnoreCase("admin") && str[1].equalsIgnoreCase("welcome"))
			return true;
		return false;
	}

}
