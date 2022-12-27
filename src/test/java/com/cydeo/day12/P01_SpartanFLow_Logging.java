package com.cydeo.day12;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class P01_SpartanFLow_Logging extends SpartanTestBase {

/*
- POST     /api/spartans
            Create a spartan Map
                name = "API Flow POST"
                gender="Male"
                phone=1231231231l
            - verify status code 201
            - message is "A Spartan is Born!"
            - take spartanID from response and save as a global  variable
    - GET  Spartan with spartanID     /api/spartans/{id}
             - verify status code 200
             - verfiy name is API Flow POST
    - PUT  Spartan with spartanID    /api/spartans/{id}
             Create a spartan Map
                name = "API PUT Flow"
                gender="Female"
                phone=3213213213
             - verify status code 204
    - GET  Spartan with spartanID     /api/spartans/{id}
            - verify status code 200
             - verify name is API PUT Flow
    - DELETE  Spartan with spartanID   /api/spartans/{id}
            - verify status code 204
     - GET  Spartan with spartanID   /api/spartans/{id}
             - verify status code 404
 */

    int id = 118;

    @DisplayName("Post")

    @Test
    @Order(value = 1)
    void test1() {

        Map<String,Object> postSpartan = new LinkedHashMap<>();

        postSpartan.put("name","API Flow POST");
        postSpartan.put("gender","Male");
        postSpartan.put("phone",1231231231l);

        String message = "A Spartan is Born!";


         JsonPath jsonPath = given().accept(ContentType.JSON).contentType(ContentType.JSON)
                .body(postSpartan).when()
                .post("/api/spartans").prettyPeek()
                .then().statusCode(201).extract().jsonPath();

         assertEquals(message,jsonPath.getString("success"));

        int id = jsonPath.getInt("data.id");

         JsonPath jsonPath1 = given().accept(ContentType.JSON).pathParam("id", id)
                .when().get("/api/spartans/{id}").then().statusCode(200)
                .extract().jsonPath();

         assertEquals(postSpartan.get("data.name"),jsonPath1.getString("data.name") );






    }
    @DisplayName("PUT")
    @Test
    @Order(value=2)
    void test2() {

        Map<String,Object> putSpartan = new LinkedHashMap<>();
        putSpartan.put("name","API PUT Flow");
        putSpartan.put("gender","Female");
        putSpartan.put("phone",3213213213l);



         given().log().body().contentType(ContentType.JSON).pathParam("id", id)
                .body(putSpartan)
                .when().put("/api/spartans/{id}").then()
                .statusCode(204);

         JsonPath jsonPath1 = given().contentType(ContentType.JSON).pathParam("id", id)
                .when().get("/api/spartans/{id}").then()
                .statusCode(200).extract().jsonPath();

         assertEquals(putSpartan.get("name"),"API PUT Flow");


    }
    @DisplayName("PATCH")
    @Test
    @Order(value=3)
    void test3() {

        Map<String,Object> patchRequest = new LinkedHashMap<>();
        patchRequest.put("name","Samandar");


        given().log().body().contentType(ContentType.JSON)
                .pathParam("id",id)
                .body(patchRequest)
                .when()
                .patch("api/spartans/{id}")
                .then()
                .statusCode(204);


    }

    @DisplayName("Delete")
    @Test
    @Order(value=4)
    void test4() {

        given().pathParam("id",id)
                .when().delete("api/spartans/{id}")
                .then().statusCode(204);


        given().accept(ContentType.JSON).pathParam("id",id)
                .when().get("api/spartans/{id}").then()
                .statusCode(404);

    }

}
