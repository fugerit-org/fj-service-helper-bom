package test.org.fugerit.java.sh.demo.emp;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.Response;

@QuarkusTest
public class TestServiceResponseDemoRest {

	@Test
	void testDemoOK() {
        given()
        .when()
        .get( "/service/response/demo/ok" )
        .then()
           .statusCode( Response.Status.OK.getStatusCode() );
	}
	
}
