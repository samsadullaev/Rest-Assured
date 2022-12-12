package com.cydeo.day06;

import com.cydeo.pojo.Employee;
import com.cydeo.pojo.Region;
import com.cydeo.utilities.HrTestBase;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;


public class P03_HRDeserializationPOJO extends HrTestBase {

    @DisplayName("GET regions to deserilization to POJO - LOMBOK - JSON PROPERTY")

    @Test
    public void test1() {

        JsonPath jsonPath = get("/regions").then().statusCode(200).extract().jsonPath();

        Region region = jsonPath.getObject("items[0]", Region.class);
        System.out.println(region);
        System.out.println(region.getLinks().get(0));


    }

    @Test
    public void test2() {

        JsonPath jsonPath = get("/employees").then().statusCode(200).extract().jsonPath();

        Employee employee = jsonPath.getObject("items[0]", Employee.class);
        System.out.println(employee);


    }
}
