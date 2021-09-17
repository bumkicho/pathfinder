package com.bkc.pathfinder.reddit.service;

import java.util.List;

import com.bkc.pathfinder.reddit.model.Comment;

public interface CommentServiceInterface {

	Comment saveComment(Comment comment);

	List<Comment> getCommentsByPostId(Long postId);

	Comment getCommentById(Long commentId);

	void sendNotificationToPostOwner(List<Comment> comments);

	List<Comment> getCommentsByUserName(String username);

}
