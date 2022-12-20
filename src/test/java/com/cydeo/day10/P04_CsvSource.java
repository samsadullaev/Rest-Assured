package com.cydeo.day10;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class P04_CsvSource {



    @ParameterizedTest
    @CsvSource({"1,3,4",
            "2,3,5",
            "3,4,7",
            "5,6,11"})

    public void test(int num1,int num2,int num3){

        System.out.println(num1 + "+" + num2 + " = " + num3);

        Assertions.assertEquals(num3,num1+num2);


    }

    //// Write a parameterized test for this request
    //    // GET https://api.zippopotam.us/us/{state}/{city}
    //    /*
    //        "NY, New York",
    //        "CO, Denver",
    //        "VA, Fairfax",
    //        "MA, Boston",
    //        "NY, New York",
    //        "MD, Annapolis"
    //     */
    //    //verify place name contains your city name
    //    //print number of places for each request

    @ParameterizedTest
    @CsvSource({"NY, New York",
            "CO, Denver",
            "VA, Fairfax",
            "MA, Boston",
            "MD, Annapolis"})

    public void test2(String state, String city){

        int placeNumber = given().accept(ContentType.JSON)
                .pathParam("state", state)
                .pathParam("city", city)
                .when().get("https://api.zippopotam.us/us/{state}/{city}")
                .then().body("places.'place name'",everyItem(containsString(city)))
                 .extract().jsonPath().getList("places").size();


        System.out.println(city + placeNumber);

    }
}
