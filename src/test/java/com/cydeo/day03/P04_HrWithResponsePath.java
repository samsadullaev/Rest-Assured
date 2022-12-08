package com.cydeo.day03;

import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P04_HrWithResponsePath extends HrTestBase {

    @DisplayName("Get reguest to countries with using response path")
    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON).
                queryParam("q", "{\"region_id\":2}").when()
                .get("/countries");

        //response.prettyPrint();


        System.out.println("response.path(\"limit\") = " + response.path("limit"));

        //second country id

        System.out.println("response.path(\"country_id[2]\") = " + response.path("items[1].country_id"));

        // print 4th element country name

        System.out.println("response.path(\"items[3].country_name\") = " + response.path("items[3].country_name"));


        //4th element href

        System.out.println("response.path(\"items[3].links[0].href\") = " + response.path("items[3].links[0].href"));


        //get all countries

        List<String> allCountries = response.path("items.country_name");
        System.out.println(allCountries);

        //get all region id and verify its 2

        List<Integer> allID = response.path("items.region_id");

        for (Integer each : allID) {
            assertEquals(2, each);
            System.out.println("region id = " + each);
        }

        // through stream

        assertTrue(allID.stream().allMatch(each -> each == 2));

    }

    @DisplayName("Get reguest to employees with using response path")
    @Test
    public void test2() {



    /*

    send a get request to employees endpoint to see only job id = it_prog
    Query params
    q={}

    then assert


     */                                         //"{\"region_id\":2}"

        Response response = given().accept(ContentType.JSON).
                queryParam("q", "{\"job_id\":\"IT_PROG'\"}").when()
                .get("/employees");

        List<String> jobId = response.path("items.job_id");

        for (String each : jobId) {

            assertEquals("IT_PROG", each);
            System.out.println(each + " job id for employees ");

        }


    }
}
