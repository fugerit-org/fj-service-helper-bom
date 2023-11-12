package test.org.fugerit.java.emp.sm.service;

import java.util.ArrayList;
import java.util.List;

import org.fugerit.java.emp.sm.service.ServiceMessage;
import org.fugerit.java.emp.sm.service.ServiceResponse;
import org.junit.Assert;
import org.junit.Test;

public class TestServiceResponse {

	public static List<ServiceMessage> newList( String severity ) {
		List<ServiceMessage> list = new ArrayList<>();
		list.add( TestServiceMessage.newDefaultMessage(severity) );
		return list;
	}
	
	@Test
	public void testServiceResponse() {
		ServiceResponse response = new ServiceResponse();
		response.setErrors( newList( ServiceMessage.SEVERITY_ERROR ) );
		response.setWarnings( newList( ServiceMessage.SEVERITY_WARNING ) );
		response.setInfos( newList( ServiceMessage.SEVERITY_INFO ) );
		response.setSuccess( newList( ServiceMessage.SEVERITY_SUCCESS ) );
		Assert.assertNotNull( response.getErrors() );
		Assert.assertNotNull( response.getWarnings() );
		Assert.assertNotNull( response.getInfos() );
		Assert.assertNotNull( response.getSuccess() );
	}
	
}
