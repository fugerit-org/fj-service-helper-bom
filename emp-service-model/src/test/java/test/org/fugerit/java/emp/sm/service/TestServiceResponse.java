package test.org.fugerit.java.emp.sm.service;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.fugerit.java.emp.sm.service.ServiceMessage;
import org.fugerit.java.emp.sm.service.ServiceResponse;
import org.fugerit.java.emp.sm.service.ServiceResponseHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
	
}
