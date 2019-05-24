package com.gregzealley.apidemo;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiDemoApplicationTests {

    private static final String GET_ALL_ENDPOINT = "http://localhost:8080/getAll";
    private static final String GET_SINGLE_ENDPOINT = "http://localhost:8080/getOneThing/1";
    private static final String INVALID_GET_SINGLE_ENDPOINT = "http://localhost:8080/getOneThing/9";

    @Before
    public void setUp() {
        RestAssured.useRelaxedHTTPSValidation();
    }

    @Test
    public void whenCallingGetAllThenCorrectResultsReturned() {
        String actualResult = given()
                .when()
                .get(GET_ALL_ENDPOINT)
                .then()
                .extract()
                .body().asString();

        assertThat(actualResult)
                .as("Incorrect results returned")
                .contains("Hello", "Goodbye");
    }

    @Test
    public void whenGetSingleElementThenCorrectResultReturned() {
        String actualResult = given()
                                .when()
                                .get(GET_SINGLE_ENDPOINT)
                                .then()
                                .extract()
                                .body().asString();

        assertThat(actualResult)
                .as("Incorrect result returned")
                .isEqualTo("{\"message\":\"Goodbye\"}");
    }

    @Test
    public void whenCallGetAllThenCorrectStatusReturned() {
        given()
                .when()
                .get(GET_ALL_ENDPOINT)
                .then()
                .statusCode(200);
    }

    @Test
    public void whenCallGetSingleThenCorrectStatusReturned() {
        given()
                .when()
                .get(GET_SINGLE_ENDPOINT)
                .then()
                .statusCode(200);
    }

    @Test
    public void whenCallInvalidGetSingleThenCorrectErrorStatusReturned() {
        given()
                .when()
                .get(INVALID_GET_SINGLE_ENDPOINT)
                .then()
                .statusCode(404);
    }

}
