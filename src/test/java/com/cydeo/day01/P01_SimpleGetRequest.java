package com.cydeo.day01;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class P01_SimpleGetRequest {

    String url="http://34.235.154.64:8000/api/spartans";

    /*
    when user send request to api/spartans endpoint
    user should see status code 200
    and print out response body
     */

    @Test
    public void  simpleGetRequest(){

         Response response = RestAssured.get(url);

        System.out.println("response.getStatusCode() = " + response.getStatusCode());
        int actual = response.getStatusCode();

        Assertions.assertEquals(200,actual);
        System.out.println("response.getHeader) = " + response.getHeader("Date"));

        response.prettyPrint();



    }
}
