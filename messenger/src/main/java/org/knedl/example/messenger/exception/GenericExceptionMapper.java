package org.knedl.example.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.knedl.example.messenger.model.ErrorMessage;


// There is a hierarchy on calling exceptions - if there is something to catch it uses that if not it goes here
// THrowable catches everything and Jax-rs knows to identify exceptions so we do not need this
// Removed annotation @Provider to disable this call

public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

	@Override
	public Response toResponse(Throwable ex) {
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), 500, "www.google.hr");
		return Response.status(Status.INTERNAL_SERVER_ERROR)
				.entity(errorMessage)
				.build();
	}
	
}
