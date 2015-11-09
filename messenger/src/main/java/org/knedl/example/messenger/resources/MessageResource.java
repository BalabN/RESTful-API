package org.knedl.example.messenger.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.net.ssl.SSLEngineResult.Status;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.knedl.example.messenger.model.Message;
import org.knedl.example.messenger.service.MessageService;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {

	MessageService messageService = new MessageService();
	
	// Get all messages
	// QuerryParam takes if it is in url (?year exp.)
	// @QueryParam("year") int year,
	// @QueryParam("start") int start,
	// @QueryParam("size") int size
	// Changed here with bean parameter
	@GET
	public List<Message> getMessages(@BeanParam MessageFilterBean filterBean) {
		if (filterBean.getYear() > 0 ) {
			return messageService.getAllMessagesForYear(filterBean.getYear());
		} if (filterBean.getStart() > 0 && filterBean.getSize() > 0) {
			return messageService.getAllMessagesPaginated(filterBean.getStart(), filterBean.getSize());
		}
		return messageService.getAllMessage();
	}
	
	// Create new message
	@POST
	public Response addMessage(Message message, @Context UriInfo uriInfo) {
		System.out.println(uriInfo.getAbsolutePath());
		Message newMessage = messageService.addMessage(message);
		String newId = String.valueOf(newMessage.getId());
		// To create Response we use Response builder
		// This returns the entity (which is Message) and status response code
		// in commented examples the location is sent back via header method in builder
		
/*		return Response.status(javax.ws.rs.core.Response.Status.CREATED)
				.entity(newMessage)
				.header(arg0, arg1)
				.build(); */
		
		// the location and status code is set in one created
		/*return Response.created(new URI("/messenger/webapi/messages/" + newMessage.getId()))
						.entity(newMessage)
						.build();*/
		
		// Concurrent method for URI. 
		URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build(); // Add to URI
		return Response.created(uri)
						.entity(newMessage)
						.build();
		
		//return messageService.addMessage(message);
	}
	
	// Update existing message
	@PUT
	@Path("/{messageId}")
	public Message updateMessgae(@PathParam("messageId") long messageId, Message message) {
		message.setId(messageId);
		return messageService.updateMessage(message);
	}
	
	// get individual message
	// You can change method param type
	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") long messageId) {
		return messageService.getMessage(messageId);
	}
	
	// Delete a message
	@DELETE
	@Path("/{messageId}")
	public void deleteMessage(@PathParam("messageId") long messageId) {
		messageService.removeMessage(messageId);
	}
	
	// Get comments for a message, here is defined subresource
	@Path("/{messageId}/comments")
	public CommentResource getCommentResource() {
		return new CommentResource();
	}
	
	
	
	
	
	
	
	
	
}
