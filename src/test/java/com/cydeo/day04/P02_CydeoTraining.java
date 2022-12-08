package com.cydeo.day04;

import com.cydeo.utilities.CydeoTrainingTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P02_CydeoTraining extends CydeoTrainingTestBase {
@Test
    public void test1(){

/*
    Given accept type is application/json
    And path param is 2
    When user send request /student/{id}
    Then status code should be 200
    And content type is application/json;charset=UTF-8
    And Date header is exist
    And Server header is enjoy
    And verify following
                firstName Mark
                batch 13
                major math
                emailAddress mark@email.com
                companyName Cydeo
                street 777 5th Ave
                zipCode 33222


     */

    Response response = given().accept(ContentType.JSON).pathParam("id",2)
            .when().get("/student/{id}");

    assertEquals(200,response.getStatusCode());

    assertEquals("application/json;charset=UTF-8",response.getContentType());

    assertTrue(response.headers().hasHeaderWithName("Date"));

    assertEquals("envoy",response.header("server"));


    JsonPath jsonPath = response.jsonPath();

    assertEquals("Mark",jsonPath.getString("students[0].firstName"));

    assertEquals(13,jsonPath.getInt("students[0].batch"));

    assertEquals("math",jsonPath.getString("students[0].major"));

    assertEquals("mark@email.com",jsonPath.getString("students[0].contact.emailAddress"));

    assertEquals("Cydeo",jsonPath.getString("students[0].company.companyName"));

    assertEquals("777 5th Ave",jsonPath.getString("students[0].company.address.street"));

    assertEquals(33222,jsonPath.getInt("students[0].company.address.zipCode"));



}

}
