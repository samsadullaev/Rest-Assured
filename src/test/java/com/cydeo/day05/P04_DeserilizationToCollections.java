package com.cydeo.day05;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class P04_DeserilizationToCollections extends SpartanTestBase {


    @Test
    public void test1(){


        /*
     Given accept type is application/json
     And Path param id = 10
     When i send GET request to /api/spartans
     Then status code is 200
     And content type is json
     And spartan data matching:
     id > 10
     name>Lorenza
     gender >Female
     phone >3312820936
     */

        Response response = given().accept(ContentType.JSON).pathParam("id", 10).when()
                .get("/api/spartans/{id}").prettyPeek()
                .then().statusCode(200).contentType("application/json")
                .extract().response();



        Map<String,Object> spartanMap=response.as(Map.class);

        System.out.println(spartanMap);

        //approach one --- with Response

         int id =(int) spartanMap.get("id");

        String name = (String) spartanMap.get("name");

        String gender = (String) spartanMap.get("gender");

        //approach second -- with Jsonpath

        JsonPath jp = response.jsonPath();

         Map<String, Object> jpMap = jp.getMap("");
        System.out.println(jpMap);

         int idJson =(int) jpMap.get("id");
         String nameJson  = (String) jpMap.get("name");

        System.out.println(idJson +" " + nameJson);


    }

}
