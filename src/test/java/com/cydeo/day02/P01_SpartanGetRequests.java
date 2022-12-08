package com.cydeo.day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class P01_SpartanGetRequests {

    String url = "http://34.235.154.64:8000";
    /*
     * Given accept is application/json
     * When user sends GET request /api/spartans endpoint
     * Then status code should be 200
     * And Content type should be application/json
     */

@DisplayName("GET ALL SPARTANS")
    @Test
    public void getAllSpartans(){

        Response response =  RestAssured.given().
                accept(ContentType.JSON).
                when().
                get(url+"/api/spartans");

       // response.prettyPrint();

        //how to get status code

       int status = response.getStatusCode();

        Assertions.assertEquals(200,status);

        //get content type --- application/json
        String content = response.getContentType();
        System.out.println(content);

        Assertions.assertEquals("application/json",content);

        //how to get header info

       String connection = response.header("Connection");

        System.out.println(connection);

        //get content type w header

        System.out.println(response.header("Content-Type"));

        //get data header

        System.out.println(response.header("Date"));

        //verify date

         boolean doesDateExist = response.headers().hasHeaderWithName("Date");//boolean

        Assertions.assertTrue(doesDateExist);




    }

    @DisplayName("GET SPARTAN")
    @Test
    public void getSpartan(){

        /**
         * Given accept is application/json
         * Given user sends Get request /api/spartans/3 endpoint
         * Then status code should be 200
         * And Content type should be application/json
         * And response body needs to contains fidole
         */


        Response response =RestAssured.given().accept(ContentType.JSON).when().get(url+"/api/spartans/3");


        //verify status code

        response.getStatusCode();

        Assertions.assertEquals(200,response.getStatusCode());

        //verify content type is application json

        response.getContentType();

        Assertions.assertEquals("application/json", response.getContentType());

        //verify content type

        response.getBody().prettyPrint();

        Assertions.assertTrue(response.getBody().asString().contains("Fidole"));



    }

    @Test
    public void helloSpartan(){

    /*
        Given no headers provided
        When Users send GET request to /api/hello
        Then response status code should be 200
        And Content type header should be "text/plain;charset=UTF-8"
        And header should contain Date
        And Content-Length should be 17
        And body should be "Hello from Sparta"
    */

        Response response = RestAssured.when().get(url+"/api/hello");

        Assertions.assertEquals(200,response.getStatusCode());

        Assertions.assertEquals("text/plain;charset=UTF-8",response.getContentType());

        boolean date = response.headers().hasHeaderWithName("date");


Assertions.assertTrue(date);

        String header = response.getHeader("Content-Length");

        Assertions.assertEquals("17",header);

        Assertions.assertTrue(response.body().asString().contains("Hello from Sparta"));





    }
}
