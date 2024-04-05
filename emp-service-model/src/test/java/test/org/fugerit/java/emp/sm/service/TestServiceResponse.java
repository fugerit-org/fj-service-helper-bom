package test.org.fugerit.java.emp.sm.service;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.fugerit.java.emp.sm.service.ServiceMessage;
import org.fugerit.java.emp.sm.service.ServiceResponse;
import org.fugerit.java.emp.sm.service.ServiceResponseHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
class TestServiceResponse {

	@Test
	void testServiceResponse() {
		ServiceResponse response = new ServiceResponse();
		ServiceResponseHelper.addAllBySeverity( response,
				Arrays.stream(ServiceMessage.Severity.values())
						.map( s -> TestServiceMessage.newDefaultMessage( s.getLevel() ) )
						.collect( Collectors.toList() )
		);
		ServiceResponseHelper.addErrors( response,
				Arrays.asList( new ServiceMessage( "custom", ServiceMessage.SEVERITY_ERROR, "Second error message" ) ) );
		Assertions.assertNotNull( response.getErrors() );
		Assertions.assertNotNull( response.getWarnings() );
		Assertions.assertNotNull( response.getInfos() );
		Assertions.assertNotNull( response.getSuccess() );
	}

	@Test
	void testSampleUsage() throws IOException {
		ServiceResponse response = new ServiceResponse()
				.addAllBySeverity( ServiceMessage.newMessage( "400001", ServiceMessage.Severity.ERROR, "Test bad request message" ),
						ServiceMessage.newMessage( ServiceMessage.Severity.INFO, "Test info message" ),
						ServiceMessage.newMessage( ServiceMessage.Severity.WARNING, "Test warning message" ) );
		try (StringWriter writer = new StringWriter()) {
			new ObjectMapper().writerWithDefaultPrettyPrinter().writeValue( writer, response );
			log.info( "test response : \n{}", writer );
		}
		Assertions.assertEquals( 1, response.getErrors().size() );
	}
	
}
