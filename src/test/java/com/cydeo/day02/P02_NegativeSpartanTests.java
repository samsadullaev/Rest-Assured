package com.cydeo.day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class P02_NegativeSpartanTests {

    @BeforeAll
    public static void init() {

        RestAssured.baseURI = "http://34.235.154.64:8000";

    }

    @DisplayName("GET ALL SPARTANS")
    @Test
    public void getAllSpartans() {

        /*
        Given Accept type application/xml
        When user send GET request to /api/spartans/10 end point
        Then status code must be 406
        And response Content Type must be application/xml;charset=UTF-8;
    */

        Response response = given().
                accept(ContentType.XML).
                when().get("/api/spartans/10");

        // response.prettyPrint();

        //how to get status code





        int status = response.getStatusCode();

        assertEquals(406, status);

        assertEquals("application/xml;charset=UTF-8",response.getContentType());


    }
}
