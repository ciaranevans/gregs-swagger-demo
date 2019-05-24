package com.gregzealley.apidemo;

import com.atlassian.oai.validator.restassured.OpenApiValidationFilter;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiDemoApplicationTestsSwagger {

    private static final String GET_ALL_ENDPOINT = "http://localhost:8080/getAllThings";
    private static final String GET_SINGLE_ENDPOINT = "http://localhost:8080/getOneThing/1";
    private static final String INVALID_GET_SINGLE_ENDPOINT = "http://localhost:8080/getOneThing/9";
    private static final String SWAGGER_SPEC = "/swagger/swagger.yml";

    private OpenApiValidationFilter swaggerValidationFilter;

    @Autowired
    private ApplicationContext applicationContext;

    @Before
    public void setUp() throws IOException {
        RestAssured.useRelaxedHTTPSValidation();
        createSwaggerFilter();
    }

    private void createSwaggerFilter() throws IOException {
        final String swaggerSpecPath = applicationContext
                                        .getResource("classpath:" + SWAGGER_SPEC)
                                        .getFile()
                                        .getAbsolutePath();
        swaggerValidationFilter = new OpenApiValidationFilter(swaggerSpecPath);
    }

    @Test
    //Fail - Wrong header type
    //Fail - Returned array named incorrectly
    public void whenCallingGetAllThenCorrectResultsReturned() {
        String actualResult = given()
                .filter(swaggerValidationFilter)
                .when()
                .get(GET_ALL_ENDPOINT)
                .then()
                .extract()
                .body().asString();

        assertThat(actualResult)
                .contains("Hello", "Goodbye");
    }

    @Test
    //Fail - Returns a String rather than a JSON array
    public void whenGetSingleElementThenCorrectResultReturned() {
        String actualResult = given()
                                .filter(swaggerValidationFilter)
                                .when()
                                .get(GET_SINGLE_ENDPOINT)
                                .then()
                                .extract()
                                .body().asString();

        assertThat(actualResult)
                .isEqualTo("Goodbye");
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
    //Fails as 404 not in swagger
    public void whenCallInvalidGetSingleThenCorrectErrorStatusReturned() {
        given()
                .filter(swaggerValidationFilter)
                .when()
                .get(INVALID_GET_SINGLE_ENDPOINT)
                .then()
                .statusCode(404);
    }

}
