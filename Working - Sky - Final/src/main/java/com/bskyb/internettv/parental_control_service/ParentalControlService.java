package com.bskyb.internettv.parental_control_service;

import com.bskyb.internettv.parental_control_imp.ControlLevelNotFoundException;
import com.bskyb.internettv.thirdparty.TechnicalFailureException;
import com.bskyb.internettv.thirdparty.TitleNotFoundException;

public interface ParentalControlService {
    boolean canWatchMovie(String customerParentalControlLevel, String movieId) throws ControlLevelNotFoundException,
    TitleNotFoundException, TechnicalFailureException;
    
}
