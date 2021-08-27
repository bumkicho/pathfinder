package com.bkc.pathfinder.reddit.service;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bkc.pathfinder.reddit.model.Post;
import com.bkc.pathfinder.reddit.model.Subreddit;
import com.bkc.pathfinder.reddit.projection.SubredditProjection;
import com.bkc.pathfinder.reddit.repository.PostRepository;
import com.bkc.pathfinder.reddit.repository.SubredditRepository;

@Service
public class SubredditService implements SubredditServiceInterface {
	
	@Autowired
	SubredditRepository subredditRepository;
	
	@Autowired
	PostRepository postRepository;

	@Override
	public Subreddit save(Subreddit subreddit) {
		subreddit.setCreatedDate(Instant.now());
		return subredditRepository.save(subreddit);
	}

	@Override
	public List<Subreddit> getAll() {
		return subredditRepository.findAll();
	}

	@Override
	public List<Post> getPosts(Subreddit subreddit) {
		return postRepository.findAllBySubreddit(subreddit);
	}

	@Override
	public Integer getPostsCount(Subreddit subreddit) {
		return getPosts(subreddit).size();
	}

	@Override
	public List<SubredditProjection> getAllWithPostCount() {
		return subredditRepository.findWithPostCount();
	}

	@Override
	public SubredditProjection getSubredditWithCount(Long id) {
		return subredditRepository.findWithPostCountById(id);
	}

}
