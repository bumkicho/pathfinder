package com.bkc.pathfinder.reddit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bkc.pathfinder.exception.PFException;
import com.bkc.pathfinder.reddit.model.Comment;
import com.bkc.pathfinder.reddit.model.Post;
import com.bkc.pathfinder.reddit.repository.CommentRepository;
import com.bkc.pathfinder.reddit.repository.PostRepository;

@Service
public class CommentService implements CommentServiceInterface {
	
	@Autowired
	CommentRepository commentRepository;
	
	@Autowired
	PostRepository postRepository;

	@Override
	public Comment saveComment(Comment comment) {
		return commentRepository.save(comment);
	}

	@Override
	public List<Comment> getCommentsByPostId(Long postId) {
		Optional<Post> post = postRepository.findById(postId);
		post.orElseThrow(()->new PFException("post was not found"));
		return commentRepository.findByPost(post.get());
	}

}
