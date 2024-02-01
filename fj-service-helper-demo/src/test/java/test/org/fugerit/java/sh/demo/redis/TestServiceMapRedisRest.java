package test.org.fugerit.java.sh.demo.redis;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.Response;

@QuarkusTest
class TestServiceMapRedisRest {

	@Test
	void testRedisSetOK() {
        given()
        .when()
        .get( "/service/redis/set/myKey/myValue" )
        .then()
           .statusCode( Response.Status.OK.getStatusCode() );
	}
	
	@Test
	void testRedisGetOK() {
        given()
        .when()
        .get( "/service/redis/get/myKey" )
        .then()
           .statusCode( Response.Status.OK.getStatusCode() );
	}
	
}
