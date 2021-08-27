package com.bkc.pathfinder.projection;

import java.time.LocalDateTime;

/**
 * 
 * @author bumki
 *
 */

/*
 * It is not currently used
 */
public interface ContactActivityProjection {
	
	String getFirstName();
	String getSubject();
	LocalDateTime getScheduledStartDt();
	LocalDateTime getScheduledEndDt();
	String getNotes();

}
