package com.cydeo.day08;

import com.cydeo.utilities.NewsApiTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class P03_NewsApi extends NewsApiTestBase {


    @Test
    void test1() {

        given().log().uri().queryParam("q","bitcoin")
                .queryParam("apiKey","d76cdd6edd704269a89f9742c91c75c4")
                .when().get("/everything").prettyPeek().then().statusCode(200);



    }
    @Test
    void test2() {

        given().log().uri().queryParam("q","bitcoin")
                .header("X-Api-Key","d76cdd6edd704269a89f9742c91c75c4")
                .when().get("/everything").prettyPeek().then().statusCode(200);



    }

    @Test
    void test3() {

        given().log().uri().queryParam("q","bitcoin")
                .header("Authorization","d76cdd6edd704269a89f9742c91c75c4")
                .when().get("/everything").prettyPeek().then().statusCode(200);



    }
}
