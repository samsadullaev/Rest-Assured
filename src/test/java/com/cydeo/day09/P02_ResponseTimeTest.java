package com.cydeo.day09;

import com.cydeo.utilities.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class P02_ResponseTimeTest extends SpartanAuthTestBase {

    @DisplayName("GET /api/spartans to get response time")

    @Test
    public void test1(){

         Response response = given().accept(ContentType.JSON)
                .auth().basic("admin", "admin")
                .when().get("/api/spartans").then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .time(both(greaterThan(200L)).and(lessThan(2000L)))
                .extract().response();


    }
}
