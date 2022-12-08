package com.cydeo.day04;

import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class P01_hrWithJsonPath extends HrTestBase {

    @DisplayName(" Get All /employees?limit=200 --> JSONPATH ")
    @Test
    public void test1(){

Response response = given().accept(ContentType.JSON).queryParam("limit", 200).when()
        .get("/employees");


assertEquals(200,response.getStatusCode());

assertEquals(ContentType.JSON.toString(),response.getContentType());

JsonPath jp = response.jsonPath();
//all emails
         List<String> allemails = jp.getList("items.email");
        System.out.println(allemails);
        System.out.println(allemails.size());
//all emails whos job id is it prog
       List<String> emails =  jp.getList("items.findAll {it.job_id == 'IT_PROG'}.email");
        System.out.println(emails);
//employee first name making more than 10000
       List<String> names=jp.getList("items.findAll {it.salary >= 10000}.first_name");
        System.out.println(names);
//max salary
        System.out.println("jp.getString(\"items.max{it.salary}\") = " + jp.getString("items.max{it.salary}"));
//first name of person w max salary
        System.out.println("jp.getString(\"items.max{it.salary}.first_name\") = " + jp.getString("items.max{it.salary}.first_name"));

        System.out.println("jp.getString(\"items.min{it.salary}\") = " + jp.getString("items.min{it.salary}"));



        /*

    TASK
    Given
             accept type is application/json
     When
             user sends get request to /locaitons
     Then
             response status code must be 200
             content type equals to application/json
             get the second city with JsonPath
             get the last city with JsonPath
             get all country ids
             get all city where their country id is UK

      */


    }

    }


