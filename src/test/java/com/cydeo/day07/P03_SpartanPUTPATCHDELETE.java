package com.cydeo.day07;

import com.cydeo.pojo.Spartan;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P03_SpartanPUTPATCHDELETE extends SpartanTestBase {

    @DisplayName("PUT Spartan Single Spartan with Map")
    @Test
    void test1() {

        Map<String, Object> requestBody = new LinkedHashMap<>();
        requestBody.put("name", "James Bond PUT");
        requestBody.put("gender", "Male");
        requestBody.put("phone", 1234567890l);

        //put will update existing record so we chose by id
        int id = 114;

        JsonPath jp = given().log().body().contentType(ContentType.JSON)
                .pathParam("id", id)
                .body(requestBody)
                .when().put("api/spartans/{id}")
                .then().statusCode(204).extract().jsonPath();//no content
        int id1 = jp.getInt("data.id");

        //crete get to verify
        Spartan spartan = given().accept(ContentType.JSON)
                .pathParam("id", id).when()
                .get("/api/spartans/{id}").then().
                statusCode(200).extract().jsonPath().getObject("", Spartan.class);

        System.out.println(spartan);


    }

    @DisplayName("PATCH Spartan Single Spartan with Map")
    @Test
    void test2() {

        Map<String, Object> requestBody = new LinkedHashMap<>();
        requestBody.put("name", "James PATCH");

        //put will update existing record so we chose by id
        int id = 113;

        JsonPath jp = given().log().body().contentType(ContentType.JSON)
                .pathParam("id", id)
                .body(requestBody)
                .when().patch("api/spartans/{id}")
                .then().statusCode(204).extract().jsonPath();//no content


    }

    @DisplayName("DELETE Spartan Single Spartan with Map")
    @Test
    void test3() {

        Map<String, Object> requestBody = new LinkedHashMap<>();
        requestBody.put("name", "James PATCH");

        //put will update existing record so we chose by id
        int id = 112;

        JsonPath jp = given().log().body().contentType(ContentType.JSON)
                .pathParam("id", id)
                .body(requestBody)
                .when().delete("api/spartans/{id}")
                .then().statusCode(204).extract().jsonPath();//no content

        given().accept(ContentType.JSON).pathParam("id",113)
                .when().get("/api/spartans/{id}").then()
                .statusCode(404);


    }
}
