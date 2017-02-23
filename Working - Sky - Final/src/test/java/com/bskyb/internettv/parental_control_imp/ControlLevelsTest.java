package com.bskyb.internettv.parental_control_imp;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ControlLevelsTest {

	@Test
	public void enumLookUpTest() throws ControlLevelNotFoundException {
		Assert.assertNotSame("12 should not be mapped to " +  ControlLevels.CL18, ControlLevels.CL18, ControlLevels.enumLookUp("12"));
		Assert.assertSame("PG should be mapped to " + ControlLevels.CLPG, ControlLevels.CLPG, ControlLevels.enumLookUp("PG"));
	      
	}
	
	@Test
	public void getControlLevelsTest() {
		Assert.assertNotEquals("Value for: " + ControlLevels.CLU + "id should be 0", ControlLevels.CLU.getcLevelValue(), 1);
		Assert.assertEquals("Incorrect id for: " + ControlLevels.CL12, ControlLevels.CL12.getcLevelValue(), 2);
	}
	
	//verify that the code throws a ControlLevelNotFound exception.
	@Rule
    public ExpectedException thrown= ExpectedException.none();
	
	@Test(expected = ControlLevelNotFoundException.class)
    public void enumLookUpExceptionTest() throws ControlLevelNotFoundException {
        ControlLevels.enumLookUp("PCL does not exist");
    }

}
