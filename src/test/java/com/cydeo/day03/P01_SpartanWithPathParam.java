package com.cydeo.day03;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
public class P01_SpartanWithPathParam extends SpartanTestBase {

   /*   Given accept type is Json
    And Id parameter value is 24
    When user sends GET request to /api/spartans/{id}
    Then response status code should be 200
    And response content-type: application/json
    And "Julio" should be in response payload(body)
       */

    @DisplayName("GET SPARTAN WITH PATHPARAM")
    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON).
                and().pathParam("id", 24).when().get("/api/spartans/{id}");

        response.prettyPrint();

        assertEquals(200, response.getStatusCode());

        assertEquals("application/json", response.getContentType());

        assertTrue(response.body().asString().contains("Julio"));


    }

    /*
        TASK
        Given accept type is Json
        And Id parameter value is 500
        When user sends GET request to /api/spartans/{id}
        Then response status code should be 404
        And response content-type: application/json
        And "Not Found" message should be in response payload
     */

    @DisplayName("GET SPARTAN WITH PATHPARAM")
    @Test
    public void test2() {

        Response response = given().accept(ContentType.JSON).
                and().pathParam("id", 500).when().get("/api/spartans/{id}");

        response.prettyPrint();

        assertEquals(HttpStatus.SC_NOT_FOUND, response.getStatusCode());

        assertEquals(ContentType.JSON.toString(), response.getContentType());
        // 60&61 same thing // assertEquals("application/json", response.getContentType());
        assertTrue(response.body().asString().contains("Not Found"));

    }
    /*
        Given Accept type is Json
        And query parameter values are:
        gender|Female
        nameContains|e
        When user sends GET request to /api/spartans/search
        Then response status code should be 200
        And response content-type: application/json
        And "Female" should be in response payload
        And "Janette" should be in response payload
     */

    @DisplayName("GET SPARTAN WITH PATHPARAM")
    @Test
    public void test3() {

        Response response = given().accept(ContentType.JSON).
                and().queryParam("gender", "Female")
                .queryParam("nameContains", "e").when().
                get("/api/spartans/search");

        assertEquals(HttpStatus.SC_OK,response.getStatusCode());

        assertEquals("application/json",response.getContentType());

        assertTrue(response.body().asString().contains("Female"));

        assertTrue((response.body().asString().contains("Janette")));

    }

    @DisplayName("GET SPARTAN WITH PATHPARAM")
    @Test
    public void test4() {

        Map<String,Object> queryMap = new HashMap<>();


        queryMap.put("gender", "Female");
        queryMap.put("nameContains","e");

        System.out.println(queryMap);


        Response response = given().accept(ContentType.JSON)
                        .queryParams(queryMap).when().
                get("/api/spartans/search");

        assertEquals(HttpStatus.SC_OK,response.getStatusCode());

        assertEquals("application/json",response.getContentType());

        assertTrue(response.body().asString().contains("Female"));

        assertTrue((response.body().asString().contains("Janette")));

    }
}
