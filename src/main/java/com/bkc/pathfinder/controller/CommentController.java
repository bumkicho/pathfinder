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
import com.bkc.pathfinder.reddit.model.Comment;
import com.bkc.pathfinder.reddit.model.Post;
import com.bkc.pathfinder.reddit.model.Subreddit;
import com.bkc.pathfinder.reddit.repository.PostRepository;
import com.bkc.pathfinder.reddit.service.CommentServiceInterface;
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

/*
 * 
 */

@RestController
@RequestMapping("api/reddit/comments/")
@AllArgsConstructor
public class CommentController {
	
	@Autowired
	CommentServiceInterface commentsService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PostRepository postRepository;
	
	@PostMapping
	public ResponseEntity<?> createComment(@RequestBody ObjectNode objectNode,
			@AuthenticationPrincipal PFUserDetails currentUser) {
		ObjectMapper mapper = new ObjectMapper();
		User user = userRepository.findByUserId(currentUser.getUserId());
		List<Comment> comments = new ArrayList<Comment>();
		
		JsonNode rootNode = null;
		try {
			rootNode = mapper.readTree(objectNode.toString());
			rootNode.get("elementcount").asInt();

			rootNode.get("elements").forEach(jsonNode -> {
				Long postid = jsonNode.get("postid").asLong();
				Optional<Post> post = postRepository.findById(postid);
				post.orElseThrow(() -> new PFException("Invalid Post Id"));

				jsonNode.get("comments").forEach(commentNode -> {
					Comment comment = mapper.convertValue(commentNode, Comment.class);
					comment.setUser(user);
					comment.setPost(post.get());
					comments.add(commentsService.saveComment(comment));
				});
			});

			return new ResponseEntity<List<Comment>>(comments, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>("post was not added successfully", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<List<Comment>> getCommentsByPostId(@PathVariable Long postId) {
		List<Comment> comments = commentsService.getCommentsByPostId(postId);
		return new ResponseEntity<List<Comment>> (comments, HttpStatus.OK);
	}

}
