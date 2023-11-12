package org.fugerit.java.sh.demo.emp;

import java.util.Arrays;

import org.fugerit.java.emp.sm.service.ServiceMessage;
import org.fugerit.java.emp.sm.service.ServiceResponse;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
@Path("/service/response/demo")
public class ServiceResponseDemoRest {

	@GET
	@Path("/ok")
	public Response demoOk() {
		ServiceResponse sr = new ServiceResponse();
		sr.setSuccess( Arrays.asList( new ServiceMessage( "200001", ServiceMessage.SEVERITY_SUCCESS, "Operation OK!" ) ) );
		return Response.ok().entity( sr ).build();
	}
	
}
