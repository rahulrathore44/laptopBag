package com.laptop.bag.model;

import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.test.DeploymentContext;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.ServletDeploymentContext;
import org.glassfish.jersey.test.grizzly.GrizzlyWebTestContainerFactory;
import org.glassfish.jersey.test.spi.TestContainerException;
import org.glassfish.jersey.test.spi.TestContainerFactory;
import org.junit.Assert;
import org.junit.Test;

import com.laptop.laptopbag.MyResource;

public class TestMyResource extends JerseyTest {
	
	@Override
	protected ResourceConfig configure() {
		return new ResourceConfig(MyResource.class);
	}
	
	@Override
	protected TestContainerFactory getTestContainerFactory() throws TestContainerException {
		return new GrizzlyWebTestContainerFactory();
	}
	
	@Override
    public DeploymentContext configureDeployment() {
        return ServletDeploymentContext.forServlet(new ServletContainer(configure())).build();
    }
	
	@Test
	public void testGetAllEndPoint(){
		Response response = target("/api/all").request().get();
		Assert.assertEquals(200, response.getStatus());
		Assert.assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><laptopDetailss><Laptop><BrandName>Alienware</BrandName><Features><Feature>8th Generation Intel® Core™ i5-8300H</Feature><Feature>Windows 10 Home 64-bit English</Feature><Feature>NVIDIA® GeForce® GTX 1660 Ti 6GB GDDR6</Feature><Feature>8GB, 2x4GB, DDR4, 2666MHz</Feature></Features><Id>1</Id><LaptopName>Alienware M17</LaptopName></Laptop></laptopDetailss>", response.readEntity(String.class));
	}
	
	@Test
	public void testGetEndPointWithId(){
		Response response = target("/api/find/1").request().get();
		Assert.assertEquals(200, response.getStatus());
		Assert.assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><Laptop><BrandName>Alienware</BrandName><Features><Feature>8th Generation Intel® Core™ i5-8300H</Feature><Feature>Windows 10 Home 64-bit English</Feature><Feature>NVIDIA® GeForce® GTX 1660 Ti 6GB GDDR6</Feature><Feature>8GB, 2x4GB, DDR4, 2666MHz</Feature></Features><Id>1</Id><LaptopName>Alienware M17</LaptopName></Laptop>", response.readEntity(String.class));
	}
	
	@Test
	public void testGetEndPointWithIdNonExiting(){
		Response response = target("/api/find/2").request().get();
		Assert.assertEquals(404, response.getStatus());
	}
	
	@Test
	public void testPingEndPoint(){
		Response response = target("/api/ping/Hello").request().get();
		Assert.assertEquals(200, response.getStatus());
		Assert.assertEquals("Hi! Hello", response.readEntity(String.class));
	}

}
