package org.knedl.example.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.knedl.example.messenger.datebase.DatabaseClass;
import org.knedl.example.messenger.model.Comment;
import org.knedl.example.messenger.model.ErrorMessage;
import org.knedl.example.messenger.model.Message;

public class CommentService {
	
	private Map<Long, Message> messages = DatabaseClass.getMessages();
	
	// Get all comments for a message
	public List<Comment> getAllComments(long messageId) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		return new ArrayList<Comment>(comments.values());
	}
	
	// Get one comment for a message
	// Not business call here. We do not want exceptions throwing in service
	public Comment getComment(long messageId, long commentId) {
		ErrorMessage errorMessage = new ErrorMessage("Not Found", 404, "http://knedl.com");
		Response response = Response.status(Status.NOT_FOUND)
				.entity(errorMessage)
				.build();
		Message message = messages.get(messageId);
		if (message == null) {
			throw new WebApplicationException(response);
		}
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		
		// Other way of throwing exceptions
		Comment comment = comments.get(commentId);
		if (comment == null) {
			throw new NotFoundException(response);
		}
		return comment;
	}
	
	// Add a new comment to a message
	public Comment addComent(long messageId, Comment comment) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		comment.setId(comments.size() + 1);
		comments.put(comment.getId(), comment);
		return comment;
	}
	
	// Edit/update a comment
	public Comment updateComment(long messageId, Comment comment) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		if (comment.getId() <= 0) {
			return null;
		}
		comments.put(comment.getId(), comment);
		return comment;
	}
	
	// Delete a comment
	public Comment removeComment(long messageId, long commentId) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		return comments.remove(commentId);
	}

}
