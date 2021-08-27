package com.bkc.pathfinder.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bkc.pathfinder.config.security.PFUserDetails;
import com.bkc.pathfinder.model.user.User;
import com.bkc.pathfinder.reddit.model.Subreddit;
import com.bkc.pathfinder.reddit.projection.SubredditProjection;
import com.bkc.pathfinder.reddit.service.SubredditServiceInterface;
import com.bkc.pathfinder.repository.user.UserRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/reddit/subreddit")
@AllArgsConstructor
public class SubredditController {
	
	@Autowired
	SubredditServiceInterface subredditService;
	
	@Autowired
	UserRepository userRepository;
	
	@PostMapping
	public ResponseEntity<?> createSubreddit(@RequestBody Subreddit subreddit, @AuthenticationPrincipal PFUserDetails currentUser) {
		User user = userRepository.findByUserId(currentUser.getUserId());
		subreddit.setUser(user);
		return new ResponseEntity<Subreddit> (subredditService.save(subreddit), HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<Subreddit>> getAllSubreddits() {
		
		return new ResponseEntity<List<Subreddit>> (subredditService.getAll(), HttpStatus.OK);
		
	}
	
	@GetMapping("info")
	public ResponseEntity<List<SubredditProjection>> getAllSubredditsWithPostCount() {
		
		return new ResponseEntity<List<SubredditProjection>> (subredditService.getAllWithPostCount(), HttpStatus.OK);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<SubredditProjection> getSubredditsWithPostCountById(@PathVariable Long id) {
		
		return new ResponseEntity<SubredditProjection> (subredditService.getSubredditWithCount(id), HttpStatus.OK);
		
	}

}
