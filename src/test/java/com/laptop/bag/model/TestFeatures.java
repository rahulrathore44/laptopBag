package com.laptop.bag.model;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestFeatures {

	private Features feature;
	
	@Before
	public void setUp() throws Exception {
		feature = new Features();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInsertIntoFeatureList() {
		feature.setFeatures(Arrays.asList(new String[]{"a","b","c"}));
		assertTrue("List should not be empty", feature.getFeatures().size() >= 1);
	}

}
