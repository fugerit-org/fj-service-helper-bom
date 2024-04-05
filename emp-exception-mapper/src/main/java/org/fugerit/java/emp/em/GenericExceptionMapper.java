package org.fugerit.java.emp.em;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;
import org.fugerit.java.emp.sm.service.ServiceResponse;
import org.fugerit.java.emp.sm.service.ServiceResponseHelper;

@Slf4j
@Provider
public class GenericExceptionMapper implements ExceptionMapper<WebApplicationException> {
    @Override
    public Response toResponse(WebApplicationException ex) {
        if ( ex.getResponse().getEntity() != null ) {
            return ex.getResponse();
        } else {
            Response.Status status = Response.Status.fromStatusCode( ex.getResponse().getStatus() );
            return Response.status( status ).entity(
                    new ServiceResponse().addAllBySeverity( ServiceResponseHelper.newMessageByStatus( status, ex.getMessage() ) )
            ).build();
        }
    }

}
