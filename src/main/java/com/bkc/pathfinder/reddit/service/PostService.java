package com.bkc.pathfinder.reddit.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bkc.pathfinder.exception.PFException;
import com.bkc.pathfinder.reddit.model.Post;
import com.bkc.pathfinder.reddit.model.Subreddit;
import com.bkc.pathfinder.reddit.repository.PostRepository;
import com.bkc.pathfinder.reddit.repository.SubredditRepository;

@Service
public class PostService implements PostServiceInterface {
	
	@Autowired
	PostRepository postRepository;
	
	@Autowired
	SubredditRepository subredditRepository;
	
	@Override
	public Post savePost(Post post) {
		post.setCreatedDate(Instant.now());
		return postRepository.save(post);
	}

	@Override
	public List<Post> findPostsBySubredditId(Long id) {
		Optional<Subreddit> subreddit = subredditRepository.findById(id);
		return postRepository.findAllBySubreddit(subreddit.get());
	}

	@Override
	public Post findById(Long id) {
		Optional<Post> post = postRepository.findById(id);
		post.orElseThrow(() -> new PFException("No post found"));
		
		return post.get();
	}

}
