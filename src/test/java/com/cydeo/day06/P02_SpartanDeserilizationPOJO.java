package com.cydeo.day06;


import com.cydeo.pojo.Search;
import com.cydeo.pojo.Spartan;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class P02_SpartanDeserilizationPOJO extends SpartanTestBase {

    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 10).
                when().get("/api/spartans/{id}").prettyPeek().
                then()
                .statusCode(200).extract().response();


        //Response

        System.out.println("-----WITH RESPONSE-----");
        Spartan spartan = response.as(Spartan.class);

        System.out.println(spartan.getId());
        System.out.println(spartan.getName());
        System.out.println(spartan.getGender());
        System.out.println(spartan.getPhone());


        //Jsonpath

        System.out.println("----WITH JSONPATH---");

        JsonPath jsonPath = response.jsonPath();

        Spartan sp = jsonPath.getObject("", Spartan.class);

        System.out.println("sp.getGender() = " + sp.getGender());
        System.out.println("sp.getId() = " + sp.getId());
        System.out.println("sp.getName() = " + sp.getName());
        System.out.println("sp.getPhone() = " + sp.getPhone());


    }

    @Test
    public void test2() {

        Response response = given().accept(ContentType.JSON).

                when().get("/api/spartans/search").
                then()
                .statusCode(200).extract().response();


        //Response

        System.out.println("-----WITH RESPONSE   GET FIRST SPARTAN-----");
//response.as --- since we cannot put path in here to get specific part of response
        //we are not gonna do it


        //Jsonpath

        System.out.println("----WITH JSONPATH GET FIRST SPARTAN---");

        JsonPath jsonPath = response.jsonPath();
        System.out.println("jsonPath.getObject(\"content[0]\",Spartan.class) = " + jsonPath.getObject("content[0]", Spartan.class));


    }

    @Test
    public void test3() {

        Response response = given().accept(ContentType.JSON).

                when().get("/api/spartans/search").
                then()
                .statusCode(200).extract().response();

        System.out.println(" ----- RESPONSE -----");
        Search search1 = response.as(Search.class);

        // since we are not providing path for response still we can use response.as() to make deserialization

        System.out.println(" ----- JSON -----");
        JsonPath jp = response.jsonPath();

        Search search = jp.getObject("", Search.class);

        System.out.println(search.getTotalElement());
        System.out.println("search.getContent().get(0) = " + search.getContent().get(0));
        System.out.println("search.getContent().get(0).getName() = " + search.getContent().get(0).getName());
    }

    @Test
    public void test4() {

        Response response = given().accept(ContentType.JSON).

                when().get("/api/spartans/search").
                then()
                .statusCode(200).extract().response();

        JsonPath jp = response.jsonPath();

        List<Spartan> content = jp.getList("content", Spartan.class);

        for (Spartan each : content) {
            System.out.println(each);
        }

    }
}

