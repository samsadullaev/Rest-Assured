package com.cydeo.day10;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


    public class P05_CsvFileSourceTest {

    @ParameterizedTest
        @CsvFileSource(resources = "/math.csv",numLinesToSkip = 1)

        public void test1(int num1, int num2, int total){

        Assertions.assertEquals(total,num1+num2);

    }

        @ParameterizedTest
        @CsvFileSource(resources = "/zipcode.csv")

        public void test1(String state, String city, int total){

               given().accept(ContentType.JSON).pathParam("state", state)
                    .pathParam("city", city)
                    .when().get("https://api.zippopotam.us/us/{state}/{city}")
                    .then().statusCode(200)
                    .body("places",hasSize(total));




        }
}
