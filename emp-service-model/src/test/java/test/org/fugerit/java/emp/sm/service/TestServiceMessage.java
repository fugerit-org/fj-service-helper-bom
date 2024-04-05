package test.org.fugerit.java.emp.sm.service;

import jakarta.ws.rs.core.Response;
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
		Assertions.assertEquals( TEST_MESSAGE, ServiceResponseHelper.newMessageByStatus( Response.Status.OK, TEST_MESSAGE ).getText() );
		Assertions.assertEquals( TEST_MESSAGE, ServiceResponseHelper.newMessageByStatus( Response.Status.INTERNAL_SERVER_ERROR, TEST_MESSAGE ).getText() );
		Assertions.assertEquals( TEST_MESSAGE, ServiceResponseHelper.newMessageByStatus( Response.Status.BAD_REQUEST, TEST_MESSAGE ).getText() );
		Assertions.assertEquals( TEST_MESSAGE, ServiceResponseHelper.newMessageByStatus( new TestStatus( 100, "Test Info" ), TEST_MESSAGE ).getText() );
		Assertions.assertEquals( TEST_MESSAGE, ServiceResponseHelper.newMessageByStatus( new TestStatus( 800, "Test Warning" ), TEST_MESSAGE ).getText() );
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

class TestStatus implements Response.StatusType {

	private int statusCode;

	private String reasonPhrase;

	public TestStatus(int statusCode, String reasonPhrase) {
		this.statusCode = statusCode;
		this.reasonPhrase = reasonPhrase;
	}

	@Override
	public Response.Status toEnum() {
		return Response.StatusType.super.toEnum();
	}

	@Override
	public int getStatusCode() {
		return this.statusCode;
	}

	@Override
	public Response.Status.Family getFamily() {
		return Response.Status.Family.familyOf( this.getStatusCode() );
	}

	@Override
	public String getReasonPhrase() {
		return this.reasonPhrase;
	}
}