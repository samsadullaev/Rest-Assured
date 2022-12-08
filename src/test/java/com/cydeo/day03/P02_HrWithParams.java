package com.cydeo.day03;

import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P02_HrWithParams extends HrTestBase {

    @DisplayName("GET request with regionID")
    @Test
    public void test1() {

/*
        Given accept type is Json
        And parameters: q = {"region_id":2}
        When users sends a GET request to "/countries"
        Then status code is 200
        And Content type is application/json
        And Payload should contain "United States of America"
     */


        Response response = given().accept(ContentType.JSON).
                queryParam("q","{\"region_id\":2}").when()
                .get("/countries");

        response.prettyPrint();

        assertEquals(200,response.getStatusCode());

        assertEquals(ContentType.JSON.toString(), response.getContentType());

        assertTrue(response.body().asString().contains("United States of America"));


    }
}
