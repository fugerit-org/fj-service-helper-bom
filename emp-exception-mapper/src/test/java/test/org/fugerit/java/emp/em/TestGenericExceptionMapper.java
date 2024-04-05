package test.org.fugerit.java.emp.em;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.fugerit.java.emp.em.GenericExceptionMapper;
import org.fugerit.java.emp.ex.WAExHelper;
import org.fugerit.java.emp.sm.service.ServiceMessage;
import org.fugerit.java.emp.sm.service.ServiceResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
class TestGenericExceptionMapper {

    private static final String TEST_MESSAGE = "test error";

    @Test
    void test() {
        GenericExceptionMapper gem = new GenericExceptionMapper();
        Response r1 = gem.toResponse( WAExHelper.newEx( TEST_MESSAGE+"1" ) );
        Assertions.assertEquals( TEST_MESSAGE+"1", ((ServiceResponse)r1.getEntity()).getErrors().get( 0 ).getText() );
        Response r2 = gem.toResponse( new WebApplicationException( TEST_MESSAGE+"2", (Response) null ) );
        Assertions.assertEquals( TEST_MESSAGE+"2", ((ServiceResponse)r2.getEntity()).getErrors().get( 0 ).getText() );
    }

}
