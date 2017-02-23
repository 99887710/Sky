package com.bskyb.internettv.parental_control_imp;

import org.junit.Test;
import org.junit.After;
import org.junit.Assert;
//import org.junit.Before;
import org.junit.Before;

import com.bskyb.internettv.thirdparty.TechnicalFailureException;
import com.bskyb.internettv.thirdparty.TitleNotFoundException;
import com.bskyb.internettv.parental_control_service.ParentalControlService;
import com.bskyb.internettv.thirdparty.MovieService;

import static org.mockito.Mockito.*;

public class MovieAccessServiceTest {

	MovieService ms;
	ParentalControlService pcs;
	
	@Before
	// create objects before you use them
	public void create() {
		ms = mock(MovieService.class);
		pcs = new MovieAccessService(ms);
	}

	@Test
	public void canWatchMovieTest()
			throws TechnicalFailureException, TitleNotFoundException, ControlLevelNotFoundException {
		// test: PCL of movie is equal or less than customer's preference
		when(ms.getParentalControlLevel("U")).thenReturn("U");
		Assert.assertTrue("Movie U can  be watched by client PCL PG", pcs.canWatchMovie("U", "PG"));
		// test: PCL of movie is greater than customer's preference
		when(ms.getParentalControlLevel("12")).thenReturn("12");
		Assert.assertFalse("Movie PCL 12 can not be watched by client PCL PG", pcs.canWatchMovie("12", "PG"));	
	}
	
	@Test(expected = TechnicalFailureException.class)
	public void canWatchTechnicalFailureException()
			throws TechnicalFailureException, TitleNotFoundException, ControlLevelNotFoundException {
		when(ms.getParentalControlLevel(anyString())).thenThrow(TechnicalFailureException.class);
		pcs.canWatchMovie("selMovieId", "PG");
	}
	
	@Test(expected = TitleNotFoundException.class)
	public void canWatchTitleNotFoundException()
			throws TechnicalFailureException, TitleNotFoundException, ControlLevelNotFoundException {
		when(ms.getParentalControlLevel(anyString())).thenThrow(TitleNotFoundException.class);
		pcs.canWatchMovie("selMovieId", "PG");
	}
	
	@Test(expected = ControlLevelNotFoundException.class)
	public void canWatchControlLevelNotFoundException()
			throws TechnicalFailureException, TitleNotFoundException, ControlLevelNotFoundException {
		when(ms.getParentalControlLevel("ABCD")).thenThrow(ControlLevelNotFoundException.class);
		pcs.canWatchMovie("selMovieId", "PG");
	}
	
	@After  
    public void tearDown() {  
        ms = null;  
        pcs = null;          
    }  



}
