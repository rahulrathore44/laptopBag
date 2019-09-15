package com.laptop.laptopbag;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.laptop.bag.exception.ErrorMessage;
import com.laptop.bag.interfaces.Ioperation;
import com.laptop.bag.interfaces.LaptopOperation;
import com.laptop.bag.model.LaptopDetails;

/**
 * @author rahul.rathore
 *	
 *	24-June-2019
 *	
 *	@see  http://localhost:8080/laptop-bag/webapi/delay/
 */
@Path("delay")
public class MyResourceDelay {
	
	private Ioperation operation = new LaptopOperation();

	private void generateDelay(int sec){
		try{
			Thread.sleep((sec * 1000));
		}catch (Exception e) {
			// Ignore
		}
	}
	
    /**
     * @see http://localhost:8080/laptop-bag/webapi/delay/all
     * @return javax.ws.rs.core.Response
     */
    @GET
    @Path(value="all")
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Response getIt() {
    	generateDelay(15);
    	List<LaptopDetails> data = operation.getAllLaptops();
    	if(!data.isEmpty())
    		return Response.status(Status.OK).entity(new GenericEntity<List<LaptopDetails>>(data){}).build();
        return Response.status(Status.NO_CONTENT).entity("Not Found").build();
    }
    
    
    /**
     * @see http://localhost:8080/laptop-bag/webapi/api/find/{id}
     * @param id int
     * @return javax.ws.rs.core.Response
     */
    @GET
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    @Path("find/{id}")
    public Response getUsingId(@PathParam("id") int id) {
    	generateDelay(15);
    	LaptopDetails data = operation.searchLaptop(id);
    	if(data != null)
    		return Response.status(Status.OK).entity(new GenericEntity<LaptopDetails>(data){}).build();
		return Response.status(Status.NOT_FOUND).entity("").build();
	}
    
    /**
     * @see http://localhost:8080/laptop-bag/webapi/delay/add
     * @param detail LaptopDetails
     * @return javax.ws.rs.core.Response
     */
    @POST
    @Path("add")
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Response postIt(LaptopDetails detail){
    	generateDelay(15);
    	LaptopDetails data = operation.addLaptopDetail(detail);
    	if(data.getId() < Integer.MIN_VALUE || data.getId() > Integer.MAX_VALUE)
    		return Response.status(Status.BAD_REQUEST).entity("").build();
    	return Response.status(Status.OK).entity(new GenericEntity<LaptopDetails>(data){}).build();
    }
    
    /**
     * @see http://localhost:8080/laptop-bag/webapi/delay/delete/{id}
     * @param id int
     * @return javax.ws.rs.core.Response
     */
    @DELETE
    @Path("delete/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteIt(@PathParam ("id") int id) {
    	generateDelay(15);
		int data = operation.deleteLaptopBag(id);
		if(data != -1)
    		return Response.status(Status.OK).entity(data).build();
		return Response.status(Status.NOT_FOUND).entity("").build();
	}
    
    /**
     * @see http://localhost:8080/laptop-bag/webapi/delay/update
     * @param detail LaptopDetails
     * @return javax.ws.rs.core.Response
     */
    @PUT
    @Path("update")
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Response putIt(LaptopDetails detail) {
    	generateDelay(15);
		LaptopDetails data = operation.updateLaptopDetail(detail);
		if(data != null)
    		return Response.status(Status.OK).entity(new GenericEntity<LaptopDetails>(data){}).build();
		return Response.status(Status.NOT_FOUND).entity("").build();
	}
    
    /**
     * @see http://localhost:8080/laptop-bag/webapi/delay/ping
     * @param text String
     * @return String
     */
    @GET
    @Path("ping/{message}") 
    @Produces(MediaType.TEXT_PLAIN)
    public String pingAlive(@PathParam("message") String text) {
    	generateDelay(15);
		return String.format("%1s %2s", "Hi!",text);
	}
    
    /**
     * @see http://localhost:8080/laptop-bag/webapi/delay/query?id=1&laptopName=Dell
     * @param id int 
     * @param laptopName String
     * @return javax.ws.rs.core.Response
     */
    
    @GET
    @Path("query") 
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response queryParam(@QueryParam(value="id") int id,@QueryParam(value="laptopName") String name) {
    	generateDelay(15);
    	LaptopDetails data;
    	try {
    		data = operation.searchLaptop(id, name);
    		if(data == null)
    			return Response.status(Status.NO_CONTENT).entity("").build();
    		return Response.status(Status.OK).entity(new GenericEntity<LaptopDetails>(data){}).build();
		} catch (Exception exp) {
			ErrorMessage message = new ErrorMessage(Status.INTERNAL_SERVER_ERROR, exp.getMessage());
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(new GenericEntity<ErrorMessage>(message, ErrorMessage.class)).build();
		}
	}

}
