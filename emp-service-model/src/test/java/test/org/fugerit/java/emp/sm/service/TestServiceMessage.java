package test.org.fugerit.java.emp.sm.service;

import org.fugerit.java.emp.sm.service.ServiceMessage;
import org.junit.Assert;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestServiceMessage {

	private static final String TEST_CODE = "500001";
	private static final String TEST_MESSAGE = "Test error";
	
	public static ServiceMessage newDefaultMessage( String severity ) {
		return new ServiceMessage(TEST_CODE, severity, TEST_MESSAGE);
	}
	
	@Test
	public void testMessageAllArgs() {
		ServiceMessage message = new ServiceMessage( TEST_CODE , ServiceMessage.SEVERITY_ERROR, TEST_MESSAGE);
		log.info( "message : {}", message );
		Assert.assertEquals( TEST_CODE , message.getCode() );
		Assert.assertEquals( TEST_MESSAGE , message.getText() );
		Assert.assertEquals( ServiceMessage.SEVERITY_ERROR , message.getSeverity() );
	}
	
	@Test
	public void testMessageNoArgs() {
		ServiceMessage message = new ServiceMessage();
		message.setCode( TEST_CODE );
		message.setSeverity( ServiceMessage.SEVERITY_WARNING );
		message.setText( TEST_MESSAGE );
		Assert.assertEquals( TEST_CODE , message.getCode() );
		Assert.assertEquals( TEST_MESSAGE , message.getText() );
		Assert.assertEquals( ServiceMessage.SEVERITY_WARNING , message.getSeverity() );
	}
	
}
