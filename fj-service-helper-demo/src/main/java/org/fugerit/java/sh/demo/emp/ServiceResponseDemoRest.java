package org.fugerit.java.sh.demo.emp;

import java.util.Arrays;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.fugerit.java.emp.sm.service.ServiceMessage;
import org.fugerit.java.emp.sm.service.ServiceResponse;

import io.vertx.core.cli.annotations.Description;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
@Path("/service/response/demo")
@Description("Demo service for ServiceResponse/ServiceMessage")
public class ServiceResponseDemoRest {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@APIResponse(responseCode = "200", description = "Operation OK!", 
		content = @Content(mediaType = MediaType.APPLICATION_JSON, 
		schema = @Schema(implementation = ServiceResponse.class)))
	@Path("/ok")
	@Operation(operationId = "demo ok", summary = "simple operation returing 200 and a single success message.")
	public Response demoOk() {
		ServiceResponse sr = new ServiceResponse();
		sr.setSuccess(Arrays.asList(new ServiceMessage("200001", ServiceMessage.SEVERITY_SUCCESS, "Operation OK!")));
		return Response.ok().entity(sr).build();
	}
	
}
