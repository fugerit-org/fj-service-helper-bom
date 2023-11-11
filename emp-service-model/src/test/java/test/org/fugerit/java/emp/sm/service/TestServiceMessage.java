package test.org.fugerit.java.emp.sm.service;

import org.fugerit.java.emp.sm.service.ServiceMessage;
import org.junit.Assert;
import org.junit.Test;

public class TestServiceMessage {

	private static final String TEST_CODE = "401001";
	private static final String TEST_MESSAGE = "Test error";
	
	@Test
	public void testMessageAllArgs() {
		ServiceMessage message = new ServiceMessage( TEST_CODE , ServiceMessage.SEVERITY_ERROR, "Test error");
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
