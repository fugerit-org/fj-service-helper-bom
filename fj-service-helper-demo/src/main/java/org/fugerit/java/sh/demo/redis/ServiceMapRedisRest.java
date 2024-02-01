package org.fugerit.java.sh.demo.redis;

import java.util.Arrays;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.fugerit.java.dsb.attributes.SimpleServiceMap;
import org.fugerit.java.emp.sm.service.ServiceMessage;
import org.fugerit.java.emp.sm.service.ServiceResponse;

import io.vertx.core.cli.annotations.Description;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/service/redis")
@Description("Demo service for Service Map Quarkus Redis")
public class ServiceMapRedisRest {

	private SimpleServiceMap serviceMap;
	
	public ServiceMapRedisRest( SimpleServiceMap serviceMap ) {
		this.serviceMap = serviceMap;
	}
	
	/*
	 * http://localhost:8080/service/redis/set/myKey/myValue
	 */
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@APIResponse(responseCode = "200", description = "Operation OK!", 
		content = @Content(mediaType = MediaType.APPLICATION_JSON, 
		schema = @Schema(implementation = ServiceResponse.class)))
	@Path("/set/{key}/{value}")
	@Operation(operationId = "redis set ok", summary = "set the key/value pair from path pararmeters.")
	public Response redisSet( @PathParam("key") String key, @PathParam("value") String value ) {
		ServiceResponse sr = new ServiceResponse();
		serviceMap.set(key, value);
		sr.setSuccess(Arrays.asList(new ServiceMessage("200002", ServiceMessage.SEVERITY_SUCCESS, String.format( "Set OK key : %s, value : %s" , key, value ) )));
		return Response.ok().entity(sr).build();
	}
	
	/*
	 * http://localhost:8080/service/redis/get/myKey
	 */
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@APIResponse(responseCode = "200", description = "Operation OK!", 
		content = @Content(mediaType = MediaType.APPLICATION_JSON, 
		schema = @Schema(implementation = ServiceResponse.class)))
	@Path("/get/{key}")
	@Operation(operationId = "redis get ok", summary = "get the value from key path pararmeter.")
	public Response redisGet( @PathParam("key") String key ) {
		ServiceResponse sr = new ServiceResponse();
		String value = serviceMap.get(key);
		sr.setSuccess(Arrays.asList(new ServiceMessage("200003", ServiceMessage.SEVERITY_SUCCESS, String.format( "Get OK key : %s, value : %s" , key, value ) )));
		return Response.ok().entity(sr).build();
	}
	
}
