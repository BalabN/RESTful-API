package org.knedl.example.messenger.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

// Method with subresources
// it is called with MessageResource.getCommentResource()
@Path("/")
public class CommentResource {
	
	
	@GET
	public String test() {
		return "New resource";
	}
	
	//You can get messageId from MessageResource.getCommentResource() -> from the method which calls this
	@GET
	@Path("/{commentId}")
	public String test2(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId) {
		return "Method to return comment ID";
	}
	
	/* TODO 
	 * - new service CommentService -> implement methods  
	 */
	
}
