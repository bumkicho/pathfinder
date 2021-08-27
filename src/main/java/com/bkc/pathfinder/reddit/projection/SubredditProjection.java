package com.bkc.pathfinder.reddit.projection;

import java.time.Instant;

/*
 * 
 */
public interface SubredditProjection {
	String getName();
	String getDescription();
	Instant getCreatedDate();
	Integer getNumberOfPosts();
}
