package org.knedl.example.messenger.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/injectdemo")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class InjectDemoResource {

	
	// matrixParams are sent in url
	// HeaderParams are sent in headers, and are used for meta data  of request or sending security token
	// CookieParam to access cookies
	// FormParam for form submissions, not implemented here
	@GET
	@Path("annotations")
	public String getParamsUsingAnnotations(@MatrixParam("param") String matrixParam,
											@HeaderParam("authSessionId") String header,
											@CookieParam("name") String cookie) {
		return "Matrix param: " + matrixParam + " Header param: " + header + " Cookie: " + cookie;
	}
	
	// Access all cookies or headers
	// Context annotation can be used only for special types:
	// a) UriInfo -> get information about url path
	// b) HttpHeaders -> Cookies, headers, etc.
	@GET
	@Path("context")
	public String getParamsUsingContex(@Context UriInfo uriInfo, @Context HttpHeaders headers) {
		String path = uriInfo.getAbsolutePath().toString();
		return "URI path: " + path;
	}
	
	
}
