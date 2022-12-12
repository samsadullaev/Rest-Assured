package com.cydeo.day06;

import com.cydeo.utilities.HrTestBase;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class P01_HRDeserilization extends HrTestBase {

@DisplayName("GET ALL LOCATION TO DESERILIZATION INTO JAVA COLLECTIONS")
    @Test
    public void test1(){

        /**
         * Create a test called getLocation
         * 1. Send request to GET /locations
         * 2. log uri to see
         * 3. Get all Json as a map and print out screen all the things with the help of  map
         * System.out.println("====== GET FIRST LOCATION  ======");
         * System.out.println("====== GET FIRST LOCATION LINKS  ======");
         * System.out.println("====== GET ALL LOCATIONS AS LIST OF MAP======");
         * System.out.println("====== FIRST LOCATION ======");
         * System.out.println("====== FIRST LOCATION ID ======");
         * System.out.println("====== FIRST LOCATION COUNTRY_ID ======");
         * System.out.println("====== GET FIRST LOCATION FIRST LINK  ====== ");
         * System.out.println("====== LAST LOCATION ID ======");
         */

    JsonPath jsonPath = given().log().uri().when().get("/locations").then().statusCode(200)
            .extract().response().jsonPath();


    System.out.println("FIRST LOCATION");

     Map<String, Object> map = jsonPath.getMap("items[0]");
   // System.out.println(map);

    System.out.println("====== GET FIRST LOCATION LINKS  ======");

    Map<String, Object> map1 = jsonPath.getMap("items[0].links[0]");
  //  System.out.println(map1);

    System.out.println("====== GET ALL LOCATIONS AS LIST OF MAP======");

    List<Map<String, Object>> items = jsonPath.getList("items");
  //  System.out.println(items);

    for(Map<String,Object> each : items){
      //  System.out.println(each);
    }


    System.out.println("====== FIRST LOCATION ======");

 //   System.out.println("items.get(0) = " + items.get(0));

    System.out.println("====== FIRST LOCATION ID ======");

 //   System.out.println("items.get(0).get(\"location_id\") = " + items.get(0).get("location_id"));

    System.out.println("====== FIRST LOCATION COUNTRY_ID ======");

 //   System.out.println("items.get(0).get(\"country_id\") = " + items.get(0).get("country_id"));

    System.out.println("====== GET FIRST LOCATION FIRST LINK  ====== ");

    System.out.println("items.get(0).get(\"links\") = " + items.get(0).get("links"));

    List<Map<String,Object>> links = (List<Map<String,Object>>) items.get(0).get("links");
    System.out.println("links.get(0).get(\"href\") = " + links.get(0).get("rel"));

    System.out.println("====== LAST LOCATION ID ======");

   // System.out.println("items.get(-1).get(\"location_id\") = " + items.get(items.size()-1).get("location_id"));


}
}
