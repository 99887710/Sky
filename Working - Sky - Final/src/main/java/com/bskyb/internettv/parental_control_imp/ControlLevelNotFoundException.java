package com.bskyb.internettv.parental_control_imp;

/**
* If an undefined PCL is passed then a
* <tt>ControlLevelNotFoundException</tt> will be thrown.
*/

public class ControlLevelNotFoundException extends Exception{
	public ControlLevelNotFoundException(String message, Throwable reason) {
        super(message, reason);
    }
}
