package com.bkc.pathfinder.reddit.service;

import java.util.List;

import com.bkc.pathfinder.reddit.model.Post;

public interface PostServiceInterface {

	public Post savePost(Post post);
	
	public List<Post> findPostsBySubredditId(Long id);

	Post findById(Long id);
	
}
