package com.cydeo.day03;

import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
public class P06_HrWithJsonPath extends HrTestBase {


    @DisplayName("get all countries")
    @Test
    public void test1() {

        Response response = get("/countries");

        assertEquals(200, response.getStatusCode());

        //create json

        JsonPath jsonPath = response.jsonPath();
//get 3rd country name
        System.out.println("jsonPath.getString(\"items[2].country_name\") = " +
                jsonPath.getString("items[2].country_name"));
//get 3rd object info
        System.out.println("jsonPath.getString(\"items[2].country_name\") = " +
                jsonPath.getString("items[2]"));
//get 3rd and 4th country name
        System.out.println("jsonPath.getString(\"items[2,3].country_name\") = " + jsonPath.getString("items[2,3].country_name"));

        //get country name where region id = 2
        System.out.println("jsonPath.get(\"items.country_id==2\") = " +
                jsonPath.getList("items.findAll {it.region_id==2}.country_name"));

    }

    //homework

    @DisplayName("get all countries")
    @Test
    public void test4() {

        /*
        - Given accept type is Json
- Path param value- US
- When users sends request to /countries
- Then status code is 200
- And Content - Type is Json
- And country_id is US
- And Country_name is United States of America
- And Region_id is 2
         */

        Response response = given().accept(ContentType.JSON).pathParam("country_id", "US").
                        when().get("/countries/{country_id}");

        assertEquals(200,response.getStatusCode());

        assertEquals(ContentType.JSON.toString(),response.getContentType());

        JsonPath jsonPath = response.jsonPath();

        assertEquals("US", jsonPath.getString("country_id"));
        assertEquals("United States of America",jsonPath.getString("country_name"));
        assertEquals(2,jsonPath.getInt("region_id"));


    }
}
