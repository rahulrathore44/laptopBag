package com.laptop.bag.model;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestFeatures {

	private Features feature;
	
	@Before
	public void setUp() throws Exception {
		feature = new Features();
	}

	@Test
	public void testInsertIntoFeatureList() {
		feature.setFeatures(Arrays.asList(new String[]{"a","b","c"}));
		assertTrue("List should not be empty", feature.getFeatures().size() >= 1);
	}

}
