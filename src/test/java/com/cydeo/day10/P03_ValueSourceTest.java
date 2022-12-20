package com.cydeo.day10;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class P03_ValueSourceTest {

    @ParameterizedTest
    @ValueSource(ints = {10,20,30,40,50})


    public void test1(int number){

        System.out.println(number);

        Assertions.assertTrue(number>30);

    }
    @ParameterizedTest(name = "{index}st name is {0}")
    @ValueSource(strings= {"sam","alex","john","lala","baba"})


    public void test2(String name){

        System.out.println(name);

        Assertions.assertTrue(name.length()>2);



    }

    @ParameterizedTest(name = "{index}st name is {0}")
    @ValueSource(ints= {22030,22031, 22032, 22033 , 22034, 22035, 22036})


    public void test3(int zip){


         ValidatableResponse validation = given().accept(ContentType.JSON)
                .pathParam("zipcode", zip)
                .when().get("https://api.zippopotam.us/us/{zipcode}")
                 .prettyPeek()
                .then().statusCode(200);


    }



}
