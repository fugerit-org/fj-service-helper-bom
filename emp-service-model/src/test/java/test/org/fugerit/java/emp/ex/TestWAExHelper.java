package test.org.fugerit.java.emp.ex;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import org.fugerit.java.emp.ex.WAExHelper;
import org.fugerit.java.emp.sm.service.ServiceMessage;
import org.fugerit.java.emp.sm.service.ServiceResponse;
import org.fugerit.java.emp.sm.service.ServiceResponseHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TestWAExHelper {

    private static final String TEST_MESSAGE = "My Message";

    private ServiceResponse getResponse(WebApplicationException ex) {
        return ((ServiceResponse)ex.getResponse().getEntity());
    }

    private String getFirstError(WebApplicationException ex) {
        return this.getResponse( ex ).getErrors().get( 0 ).getText();
    }

    @Test
    void test() {
        Assertions.assertEquals( TEST_MESSAGE, this.getFirstError( WAExHelper.newEx( TEST_MESSAGE ) ) );
        Assertions.assertEquals( TEST_MESSAGE, this.getFirstError( WAExHelper.newEx(Response.Status.INTERNAL_SERVER_ERROR, TEST_MESSAGE ) ) );
        Assertions.assertEquals( TEST_MESSAGE, this.getFirstError( WAExHelper.newEx(Response.Status.INTERNAL_SERVER_ERROR, ServiceResponseHelper.newDefaultErrorMessage( TEST_MESSAGE ) ) ) );
        Assertions.assertEquals( TEST_MESSAGE, this.getFirstError( WAExHelper.newEx(Response.Status.INTERNAL_SERVER_ERROR, new ServiceResponse().addAllBySeverity( ServiceResponseHelper.newDefaultErrorMessage( TEST_MESSAGE ) ) ) ) );
    }

}
