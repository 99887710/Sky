/**
 * 
 */
package com.bskyb.internettv.parental_control_imp;

import com.bskyb.internettv.parental_control_service.ParentalControlService;
import com.bskyb.internettv.thirdparty.MovieService;
import com.bskyb.internettv.thirdparty.TechnicalFailureException;
import com.bskyb.internettv.thirdparty.TitleNotFoundException;

/**
 * The MovieAccessService class implements the ParentalControlService interface 
 * 
 * @author Milton Chikere Ezeh
 * 
 * Sky Assessment 
 * 
 */
public class MovieAccessService implements ParentalControlService {
	
    // Movie Service interface is implemented elsewhere
    private final MovieService mService;
    
    // constructor
	public MovieAccessService(MovieService mService) {
		//super();
		this.mService = mService;
	}

	/**
	* This method implements the canWatchMovie method contained in ParentalControlService
	* It simply compares the PCL provided by the client against the PCL returned by the 
	* movie service. It then decides whether access can be granted
	* @param: custParentalControlLevel - customer’s parental control level preference
	* @param: movieId - Movie ID
	* @return boolean -prevents or grants access to movies based on parental control level
	*/

	@Override	
	public boolean canWatchMovie(String movieId, String custParentalControlLevel) throws ControlLevelNotFoundException, TechnicalFailureException, TitleNotFoundException  {
		try{
			ControlLevels custPCL = getPCLFromCustomer(custParentalControlLevel);
			ControlLevels movPCL = getPCLFromMovieService(movieId);
			// if pcl of movie is equal or less than customer's pref, caller can watch movie.
			boolean canWe = movPCL.getcLevelValue() <= custPCL.getcLevelValue();
			return canWe;
		}catch (TitleNotFoundException e){
			throw new TitleNotFoundException("The movie Service could not find the movie with the id: " + movieId, e);
		}catch (TechnicalFailureException e){
			throw new TechnicalFailureException("System Error!", e);
		}
    }
	
	/**
	* This method looks up and gets the PCL (from enum), throws an exception if not found
	* @param: controlLevel - Parental Control Level from client
	* @return ControlLevels - Parental Control Level
	*/
	private ControlLevels getPCLFromCustomer(String controlLevel) throws ControlLevelNotFoundException {
		try{
			ControlLevels cPCL = ControlLevels.enumLookUp(controlLevel);
			return cPCL;
		}catch (ControlLevelNotFoundException e){
			throw new ControlLevelNotFoundException("Not defined control level: " + controlLevel + " in ", e);
		}
	}
	
	
	/**
	* This method gets the Parental Control Level returned by the Movie Service 
	* @param: id - MovieID
	* @return ControlLevels - Parental Control Level
	*/
	private ControlLevels getPCLFromMovieService(String movieId) throws TitleNotFoundException, TechnicalFailureException, ControlLevelNotFoundException  {
		String controlLevel = mService.getParentalControlLevel(movieId);
		return getPCLFromCustomer(controlLevel);	
	}
}
