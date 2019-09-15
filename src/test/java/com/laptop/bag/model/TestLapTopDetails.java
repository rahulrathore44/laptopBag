package com.laptop.bag.model;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestLapTopDetails {
	
	private Features features;
	private LaptopDetails laptopDetailsOne;
	private LaptopDetails laptopDetailsTwo;

	@Before
	public void setUp() throws Exception {
		features = new Features();
		features.setFeatures(Arrays.asList(new String[]{"a","b","c"}));
		laptopDetailsOne = new LaptopDetails();
		laptopDetailsTwo = new LaptopDetails();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreationOfLaptopDetails() {
		laptopDetailsOne.setId(1);
		laptopDetailsOne.setLaptopName("name");
		laptopDetailsOne.setBrandName("brand name");
		laptopDetailsOne.setFeatures(features);
		
		Assert.assertEquals(1, laptopDetailsOne.getId());
		Assert.assertEquals("name", laptopDetailsOne.getLaptopName());
		Assert.assertEquals("brand name", laptopDetailsOne.getBrandName());
		Assert.assertTrue("List is Empty", laptopDetailsOne.getFeatures().getFeatures().size() > 2);
		
	}
	
	@Test
	public void testEqualityOfTwoLaptopDetailsObject() {
		laptopDetailsOne.setId(1);
		laptopDetailsOne.setLaptopName("name");
		laptopDetailsOne.setBrandName("brand name");
		laptopDetailsOne.setFeatures(features);
		
		laptopDetailsTwo.setId(1);
		laptopDetailsTwo.setLaptopName("name");
		laptopDetailsTwo.setBrandName("brand name");
		laptopDetailsTwo.setFeatures(features);
		
		Assert.assertTrue("Both Object Should be equal",laptopDetailsOne.equals(laptopDetailsTwo));
		
	}
	
	@Test
	public void testInEqualityOfTwoLaptopDetailsObject() {
		laptopDetailsOne.setId(2);
		laptopDetailsOne.setLaptopName("name");
		laptopDetailsOne.setBrandName("brand name");
		laptopDetailsOne.setFeatures(features);
		
		laptopDetailsTwo.setId(1);
		laptopDetailsTwo.setLaptopName("name");
		laptopDetailsTwo.setBrandName("brand name");
		laptopDetailsTwo.setFeatures(features);
		
		Assert.assertFalse("Both Object Should not be equal",laptopDetailsOne.equals(laptopDetailsTwo));
		
	}
	
	@Test
	public void testInEqualityOfTwoLaptopDetailsObjectDifferentObjType() {
		laptopDetailsOne.setId(2);
		laptopDetailsOne.setLaptopName("name");
		laptopDetailsOne.setBrandName("brand name");
		laptopDetailsOne.setFeatures(features);
	
		
		Assert.assertFalse("Both Object Should not be equal",laptopDetailsOne.equals(new ArrayList<>()));
		
	}

}
