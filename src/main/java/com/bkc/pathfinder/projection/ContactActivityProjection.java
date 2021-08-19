package com.bkc.pathfinder.projection;

import java.time.LocalDateTime;

public interface ContactActivityProjection {
	
	String getFirstName();
	String getSubject();
	LocalDateTime getScheduledStartDt();
	LocalDateTime getScheduledEndDt();
	String getNotes();

}
