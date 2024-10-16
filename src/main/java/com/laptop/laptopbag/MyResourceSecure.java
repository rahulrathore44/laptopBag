package com.laptop.laptopbag;

import java.util.List;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import com.laptop.bag.exception.ErrorMessage;
import com.laptop.bag.interfaces.Ioperation;
import com.laptop.bag.interfaces.LaptopOperation;
import com.laptop.bag.model.LaptopDetails;

/**
 * @author rahul.rathore
 *	
 *	24-June-2019
 *	
 *	@see  http://localhost:8080/laptop-bag/webapi/secure/
 */
@Path("secure")
public class MyResourceSecure {
	
	private Ioperation operation = new LaptopOperation();

    /**
     * @see http://localhost:8080/laptop-bag/webapi/secure/all
     * @return jakarta.ws.rs.core.Response
     */
    @GET
    @Path(value="all")
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Response getIt() {
    	List<LaptopDetails> data = operation.getAllLaptops();
    	if(!data.isEmpty())
    		return Response.status(Status.OK).entity(new GenericEntity<List<LaptopDetails>>(data){}).build();
        return Response.status(Status.NO_CONTENT).entity(new GenericEntity<ErrorMessage>(
        		new ErrorMessage(Status.NO_CONTENT, "No Content"), ErrorMessage.class)).build();
    }
    
    
    /**
     * @see http://localhost:8080/laptop-bag/webapi/secure/find/{id}
     * @param id int
     * @return jakarta.ws.rs.core.Response
     */
    @GET
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON,MediaType.TEXT_PLAIN})
    @Path("find/{id}")
    public Response getUsingId(@PathParam("id") int id) {
    	LaptopDetails data = operation.searchLaptop(id);
    	if(data != null)
    		return Response.status(Status.OK).entity(new GenericEntity<LaptopDetails>(data){}).build();
		return Response.status(Status.NOT_FOUND).entity("").build();
	}
    
    /**
     * @see http://localhost:8080/laptop-bag/webapi/secure/add
     * @param detail LaptopDetails
     * @return jakarta.ws.rs.core.Response
     */
    @POST
    @Path("add")
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Response postIt(LaptopDetails detail){
    	LaptopDetails data = operation.addLaptopDetail(detail);
    	if(data.getId() < Integer.MIN_VALUE || data.getId() > Integer.MAX_VALUE)
    		return Response.status(Status.BAD_REQUEST).entity("").build();
    	return Response.status(Status.OK).entity(new GenericEntity<LaptopDetails>(data){}).build();
    }
    
    /**
     * @see http://localhost:8080/laptop-bag/webapi/secure/delete/{id}
     * @param id int
     * @return jakarta.ws.rs.core.Response
     */
    @DELETE
    @Path("delete/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteIt(@PathParam ("id") int id) {
		int data = operation.deleteLaptopBag(id);
		if(data != -1)
    		return Response.status(Status.OK).entity(data).build();
		return Response.status(Status.NOT_FOUND).entity("").build();
	}
    
    /**
     * @see http://localhost:8080/laptop-bag/webapi/secure/update
     * @param detail LaptopDetails
     * @return jakarta.ws.rs.core.Response
     */
    @PUT
    @Path("update")
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Response putIt(LaptopDetails detail) {
		LaptopDetails data = operation.updateLaptopDetail(detail);
		if(data != null)
    		return Response.status(Status.OK).entity(new GenericEntity<LaptopDetails>(data){}).build();
		return Response.status(Status.NOT_FOUND).entity("").build();
	}
    
    /**
     * @see http://localhost:8080/laptop-bag/webapi/secure/ping
     * @param text String
     * @return String
     */
    @GET
    @Path("ping/{message}") 
    @Produces({MediaType.TEXT_PLAIN,MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public String pingAlive(@PathParam("message") String text) {
		return String.format("%1s %2s", "Hi!",text);
	}
}
