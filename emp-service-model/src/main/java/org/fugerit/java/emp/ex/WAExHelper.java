package org.fugerit.java.emp.ex;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import org.fugerit.java.emp.sm.service.ServiceMessage;
import org.fugerit.java.emp.sm.service.ServiceResponse;
import org.fugerit.java.emp.sm.service.ServiceResponseHelper;

public class WAExHelper {

    private WAExHelper() {}

    public static WebApplicationException newEx( String message) {
        return newEx( Response.Status.INTERNAL_SERVER_ERROR, message  );
    }

    public static WebApplicationException newEx( Response.StatusType status, String message ) {
        return newEx(  null, status, message );
    }

    public static WebApplicationException newEx( Throwable cause, Response.StatusType status, String message ) {
        return newEx( cause, status, ServiceResponseHelper.newMessageByStatus( status, message ) );
    }

    public static WebApplicationException newEx( Response.StatusType status, ServiceMessage sm ) {
        return newEx( null, status, sm );
    }

    public static WebApplicationException newEx( Throwable cause, Response.StatusType status, ServiceMessage sm ) {
        return newEx( cause, status, new ServiceResponse().addAllBySeverity( sm ) );
    }

    public static WebApplicationException newEx( Response.StatusType status, ServiceResponse sr ) {
        return newEx( null, status, sr );
    }

    public static WebApplicationException newEx( Throwable cause, Response.StatusType status, ServiceResponse sr ) {
        return new WebApplicationException( cause , Response.status( status ).entity( sr ).build()  );
    }

}
