package com.bkc.pathfinder.projection;

import java.time.LocalDateTime;

/**
 * 
 * @author bumki
 *
 */

/*
 * This is used in ActiivityRepository
 */
public interface ActivityProjection {
	//TODO: Create a projection that include contact activity fields
	String getSubject();
	LocalDateTime getScheduledStartDt();
	LocalDateTime getScheduledEndDt();
	String getNotes();	
}
