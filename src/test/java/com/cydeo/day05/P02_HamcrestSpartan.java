package com.cydeo.day05;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;


import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;


public class P02_HamcrestSpartan extends SpartanTestBase {

    @DisplayName("Get Single Spartan with Hamcrest")
    @Test
    public void test1(){

/*
/*
    Given accept type is Json
    And path param id is 15
    When user sends a get request to spartans/{id}
    Then status code is 200
    And content type is Json
    And json data has following
        "id": 15,
        "name": "Meta",
        "gender": "Female",
        "phone": 1938695106
     */

         Response response = given().log().all().accept(ContentType.JSON).pathParam("id", 15)
                .when().get("api/spartans/{id}").prettyPeek().
                then().log().all().statusCode(200)
                .contentType("application/json").
                body("id", is(15),
                        "name", is("Meta"),
                        "gender", is("Female"),
                        "phone", is(1938695106)).extract().response();





    }

    /*
    log().all()-info of my request and body


    how to print response body?

    -response.prettyprint()

     */

}
