package com.victorkayk.algafood;

import com.victorkayk.algafood.utils.DatabaseCleaner;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
class KitchenTests {
	@LocalServerPort
	private int port;

	@Autowired
	private DatabaseCleaner databaseCleaner;

	@BeforeEach
	public void before() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/kitchens";

		databaseCleaner.clearTables();
	}

	@Test
	public void shouldReturn200WhenGetKitchens() {
		RestAssured
				.given()
					.accept(ContentType.JSON)
				.when()
					.get()
				.then()
					.statusCode(200);
	}
}
