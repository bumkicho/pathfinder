package com.bkc.pathfinder.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import com.bkc.pathfinder.exception.PFException;
import com.bkc.pathfinder.model.user.User;
import com.bkc.pathfinder.reddit.model.Post;
import com.bkc.pathfinder.reddit.model.Subreddit;
import com.bkc.pathfinder.reddit.repository.SubredditRepository;
import com.bkc.pathfinder.reddit.service.PostService;
import com.bkc.pathfinder.repository.user.UserRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import lombok.AllArgsConstructor;

/**
 * 
 * @author bumki
 *
 */

@RestController
@RequestMapping("api/reddit/post")
@AllArgsConstructor
public class PostController {

	@Autowired
	PostService postService;

	@Autowired
	UserRepository userRepository;

	@Autowired
	SubredditRepository subredditRepository;

	@PostMapping
	public ResponseEntity<?> createPost(@RequestBody ObjectNode objectNode,
			@AuthenticationPrincipal PFUserDetails currentUser) {
		ObjectMapper mapper = new ObjectMapper();
		
		User user = userRepository.findByUserId(currentUser.getUserId());
		List<Post> posts = new ArrayList<Post>();

		JsonNode rootNode = null;
		try {
			rootNode = mapper.readTree(objectNode.toString());
			rootNode.get("elementcount").asInt();

			rootNode.get("elements").forEach(jsonNode -> {
				Long subredditId = jsonNode.get("subredditid").asLong();
				Optional<Subreddit> subreddit = subredditRepository.findById(subredditId);
				subreddit.orElseThrow(() -> new PFException("Invalid Subreddit Id"));

				jsonNode.get("posts").forEach(postNode -> {
					Post post = mapper.convertValue(postNode, Post.class);
					post.setUser(user);
					post.setSubreddit(subreddit.get());
					posts.add(postService.savePost(post));
				});
			});

			return new ResponseEntity<List<Post>>(posts, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>("post was not added successfully", HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/subreddit/{id}")
	public ResponseEntity<List<Post>> getPostsBySubredditId(@PathVariable Long id) {
		return new ResponseEntity<List<Post>>(postService.findPostsBySubredditId(id), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Post> getPostById(@PathVariable Long id) {
		return new ResponseEntity<Post>(postService.findById(id), HttpStatus.OK);
	}

}
