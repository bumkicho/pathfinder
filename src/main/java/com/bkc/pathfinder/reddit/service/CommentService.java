package com.bkc.pathfinder.reddit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.bkc.pathfinder.exception.PFException;
import com.bkc.pathfinder.model.user.User;
import com.bkc.pathfinder.reddit.model.Comment;
import com.bkc.pathfinder.reddit.model.NotificationEmail;
import com.bkc.pathfinder.reddit.model.Post;
import com.bkc.pathfinder.reddit.repository.CommentRepository;
import com.bkc.pathfinder.reddit.repository.PostRepository;
import com.bkc.pathfinder.repository.user.UserRepository;

@Service
public class CommentService implements CommentServiceInterface {
	
	@Autowired
	CommentRepository commentRepository;
	
	@Autowired
	PostRepository postRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private MailService mailService;

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

	@Override
	public Comment getCommentById(Long commentId) {
//		return commentRepository.getById(commentId);
//		getById is lazy loading. change it to findById
		return commentRepository.findById(commentId).get();
	}

	@Override
	public void sendNotificationToPostOwner(List<Comment> comments) {
		
		comments.stream().forEach(comment -> {
			User postUser = comment.getPost().getUser();
			
			mailService.sendMailViaMailGun(new NotificationEmail("You have a new comment",
					postUser.getUserEmail(),
					comment.getUser().getUserName() + " has posted a comment on your post."));
		});
	}

	@Override
	public List<Comment> getCommentsByUserName(String username) {
		User user = userRepository.findByUserName(username);
		return commentRepository.findAllByUser(user);
	}

}
