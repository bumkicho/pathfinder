package com.bkc.pathfinder.reddit.service;

import java.util.List;

import com.bkc.pathfinder.reddit.model.Post;
import com.bkc.pathfinder.reddit.model.Subreddit;
import com.bkc.pathfinder.reddit.projection.SubredditProjection;

public interface SubredditServiceInterface {

	Subreddit save(Subreddit subreddit);

	List<Subreddit> getAll();
	
	List<Post> getPosts(Subreddit subreddit);
	
	Integer getPostsCount(Subreddit subreddit);

	List<SubredditProjection> getAllWithPostCount();

	SubredditProjection getSubredditWithCount(Long id);

}
