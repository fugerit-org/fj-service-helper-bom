package test.org.fugerit.java.emp.sm.service;

import org.fugerit.java.emp.sm.service.ServiceMessage;

import lombok.extern.slf4j.Slf4j;
import org.fugerit.java.emp.sm.service.ServiceResponseHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
class TestServiceMessage {

	private static final String TEST_CODE = "500001";
	private static final String TEST_MESSAGE = "Test error";
	
	static ServiceMessage newDefaultMessage( String severity ) {
		return new ServiceMessage(TEST_CODE, severity, TEST_MESSAGE);
	}
	
	@Test
	void testMessageAllArgs() {
		ServiceMessage message = new ServiceMessage( TEST_CODE , ServiceMessage.SEVERITY_ERROR, TEST_MESSAGE);
		log.info( "message : {}", message );
		Assertions.assertEquals( TEST_CODE , message.getCode() );
		Assertions.assertEquals( TEST_MESSAGE , message.getText() );
		Assertions.assertEquals( ServiceMessage.SEVERITY_ERROR , message.getSeverity() );
	}

	@Test
	void testNewMessage() {
		Assertions.assertEquals( TEST_MESSAGE, ServiceResponseHelper.newDefaultErrorMessage( TEST_MESSAGE ).getText() );
		Assertions.assertEquals( TEST_MESSAGE, ServiceResponseHelper.newDefaultWarningMessage( TEST_MESSAGE ).getText() );
		Assertions.assertEquals( TEST_MESSAGE, ServiceResponseHelper.newDefaultSuccessMessage( TEST_MESSAGE ).getText() );
		Assertions.assertEquals( TEST_MESSAGE, ServiceResponseHelper.newDefaultInfoMessage( TEST_MESSAGE ).getText() );
	}

	@Test
	void testMessageNoArgs() {
		ServiceMessage message = new ServiceMessage();
		message.setCode( TEST_CODE );
		message.setSeverity( ServiceMessage.SEVERITY_WARNING );
		message.setText( TEST_MESSAGE );
		Assertions.assertEquals( TEST_CODE , message.getCode() );
		Assertions.assertEquals( TEST_MESSAGE , message.getText() );
		Assertions.assertEquals( ServiceMessage.SEVERITY_WARNING , message.getSeverity() );
	}
	
}
