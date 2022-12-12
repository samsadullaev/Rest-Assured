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

public class P02_SpartanPOST extends SpartanTestBase {

    @DisplayName("POST Spartan with string body")
    @Test
    public void test1() {

        /*
        /**
     Given accept type and Content type is JSON
     And request json body is:
     {
     "gender":"Male",
     "name":"John Doe",
     "phone":8877445596
     }
     When user sends POST request to '/api/spartans'
     Then status code 201
     And content type should be application/json
     And json payload/response/body should contain:
     verify the success value is A Spartan is Born!
         "name": "John Doe",
         "gender": "Male",
         "phone": 1231231231
     */


        String requestBody = " {\n" +
                "     \"gender\":\"Male\",\n" +
                "     \"name\":\"John Doe\",\n" +
                "     \"phone\":8877445596\n" +
                "     }";

        String message = "A Spartan is Born!";

        JsonPath jsonPath = given().accept(ContentType.JSON).log().body().//retrieving response in json
                contentType(ContentType.JSON)//sending in json
                .body(requestBody).when().post("api/spartans").prettyPeek().then().statusCode(201)
                .contentType("application/json").extract().jsonPath();


        assertEquals(message, jsonPath.getString("success"));

        assertEquals("John Doe", jsonPath.getString("data.name"));
        assertEquals("Male", jsonPath.getString("data.gender"));
        assertEquals(8877445596l, jsonPath.getLong("data.phone"));

        System.out.println("jsonPath.getInt(\"data.id\") = " + jsonPath.getInt("data.id"));


    }

    @DisplayName("POST Spartan with MAP body")
    @Test
    public void test2() {

        /*
        /**
     Given accept type and Content type is JSON
     And request json body is:
     {
     "gender":"Male",
     "name":"John Doe",
     "phone":8877445596
     }
     When user sends POST request to '/api/spartans'
     Then status code 201
     And content type should be application/json
     And json payload/response/body should contain:
     verify the success value is A Spartan is Born!
         "name": "John Doe",
         "gender": "Male",
         "phone": 1231231231
     */

        Map<String, Object> requestBody = new LinkedHashMap<>();
        requestBody.put("name", "James Bond");
        requestBody.put("gender", "Male");
        requestBody.put("phone", 1234567890l);

        String message = "A Spartan is Born!";

//body is doing serilization - sending data in json format
        //to do serilization we need one objectMapper jackson/gson
        JsonPath jsonPath = given().accept(ContentType.JSON).log().body().//retrieving response in json
                contentType(ContentType.JSON)//sending in json
                .body(requestBody).when().post("api/spartans").prettyPeek().then().statusCode(201)
                .contentType("application/json").extract().jsonPath();


        assertEquals(message, jsonPath.getString("success"));

        assertEquals("James Bond", jsonPath.getString("data.name"));
        assertEquals("Male", jsonPath.getString("data.gender"));
        assertEquals(1234567890l, jsonPath.getLong("data.phone"));

        System.out.println("jsonPath.getInt(\"data.id\") = " + jsonPath.getInt("data.id"));
    }

        @DisplayName("POST spartan with Spartan POJO body")
        @Test
        public void test3() {

            Spartan requestBody=new Spartan();
            requestBody.setName("John Wick");
            requestBody.setGender("Male");
            requestBody.setPhone(1234567890l);

            System.out.println("requestBody = " + requestBody);


            String expectedMessage="A Spartan is Born!";


            // body(requestBody) --> is doing serilization behind the scene to send data in JSON format
            // to do serilization we need to one the ObjectMapper ( Jackson / Gson )

            JsonPath jsonPath = given().accept(ContentType.JSON).log().body()// API send me response in JSON format
                    .contentType(ContentType.JSON) // API I am sending body in JSON format
                    .body(requestBody).
                    when().post("/api/spartans").prettyPeek().
                    then().statusCode(201)
                    .contentType("application/json").extract().jsonPath();


            // What if I want to get id
            int id = jsonPath.getInt("data.id");
            System.out.println("id = " + id);
//get me spartan with same id we post response
             Spartan spartan = given().accept(ContentType.JSON)
                    .pathParam("id", id).when()
                    .get("/api/spartans/{id}").then().
                    statusCode(200).extract().jsonPath().getObject("", Spartan.class);

             assertEquals(requestBody.getName(), spartan.getName());



//            assertEquals(expectedMessage,jsonPath.getString("success"));
//            assertEquals(requestBody.getName(),jsonPath.getString("data.name"));
//            assertEquals(requestBody.getGender(),jsonPath.getString("data.gender"));
//            assertEquals(requestBody.getPhone(),jsonPath.getLong("data.phone"));


            // Can we create SpartanUtil to create dynamic Spartan as Map to use in request


        }
    }




