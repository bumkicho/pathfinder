package com.bkc.pathfinder.repository.activity.projection;

import java.time.LocalDateTime;

/**
 * 
 * @author bumki
 *
 */
public interface ActivityProjection {
	String getSubject();
	LocalDateTime getScheduledDt();
	LocalDateTime getStartDt();
	LocalDateTime getEndDt();
	String getNote();	
}
