package com.bskyb.internettv.parental_control_imp;

import java.util.Arrays;

/**
* Define a fixed set of constants for Parental Control Levels
* An interface could have also been used
* ---
* U
* PG
* 12
* 15
* 18
* ---
* 
* Add prefix (CL) to Control Levels -> U, PG, 12, 15, 18
* because of the numeric constants. Strip them when needed
* 
*/

public enum ControlLevels {
	CLU(0), CLPG(1), CL12(2), CL15(3),CL18(4);
	private static String controlLevelPrefix = "CL";
	private int cLevelValue;	
	// constructor
	ControlLevels(final int newValue) {
		cLevelValue = newValue;
	}
	// Getter
	public int getcLevelValue(){
		return cLevelValue;
	}
	
	/**
	* This method returns an enum constant, throws an exception if not found
	* @param: cl - Parental Control Level to lookup e.g. -> U, PG, 12, 15, 18
	* @return ControlLevels - Parental Control Level constant
	*/
	public static ControlLevels enumLookUp(String pcl) throws ControlLevelNotFoundException{
		try {
			ControlLevels pclFound = ControlLevels.valueOf(controlLevelPrefix + pcl);
			return pclFound;
		} catch (IllegalArgumentException e) {
			throw new ControlLevelNotFoundException(pcl + " is not listed as a Parental Control Level. It should be one of the following: " + getControlLevels(), e);
		}
	}
	
	/**
	* This method returns all Parental Control Levels as a single result
	* Its uses Streams, Method references and Lambda expressions 
	* Used when throwing ControlLevelNotFound Exception
	* @return String - Of all Parental Control Level
	*/
	private static String getControlLevels(){
		String allControls = Arrays.stream(ControlLevels.values())
				  .map(ControlLevels::stripPrefix)
				  .reduce((a, b) -> a + ", " + b)
				  .get();
		
		return allControls;
	}
	
	/**
	* This method removes the prefix from the Control Level constants declared in the enum
	* Its used in the method getControlLevels to build a string of all parental control levels
	*  
	* @param: cl - Parental Control Level to lookup e.g. -> U, PG, 12, 15, 18
	* @return String - Parental Control Level
	*/
	private static String stripPrefix(ControlLevels cl) {
		String strippedOfPrefix = cl.toString().replaceFirst(controlLevelPrefix, "");
        return strippedOfPrefix;
    }
	
	
	

}
