package com.cydeo.day03;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P03_SpartanWithResponsePath extends SpartanTestBase {




    @DisplayName("Get Spartan with Response Path")
    @Test
    public void task1(){

/*
     Given accept type is json
     And path param id is 10
     When user sends a get request to "api/spartans/{id}"
     Then status code is 200
     And content-type is "application/json"
     And response payload values match the following:
          id is 10,
          name is "Lorenza",
          gender is "Female",
          phone is 3312820936
   */

        Response response = given().accept(ContentType.JSON).
                pathParam("id",10).when()
                .get("api/spartans/{id}");


        assertEquals(ContentType.JSON.toString(),response.getContentType());

        assertEquals(200,response.getStatusCode());

         int id = response.path("id");
        System.out.println(id);
         String name = response.path("name");
        System.out.println(name);
        String gender = response.path("gender");
        System.out.println(gender);
        long phone = response.path("phone");
        System.out.println(phone);

        assertEquals(10,id);
        assertEquals("Lorenza",name);
        assertEquals("Female",gender);
        assertEquals(3312820936l,phone);


    }

    @DisplayName("Get all Spartan")
    @Test
    public void task2(){

        Response response = given().accept(ContentType.JSON).when().get("api/spartans");

      //  response.prettyPrint();

        //get first spartan ID

         int firstID = response.path("[0].id");
        int ID = response.path("id[0]");//same as line 70
        System.out.println(firstID);
        System.out.println(ID);

        //get first spartan name
        String firstName=response.path("name[0]");
        System.out.println(firstName);

        //get last spartan name
        String lastName = response.path("name[-1]");
        System.out.println(lastName);

        String last2Name= response.path("name[-2]");
        System.out.println(last2Name);

        List<String> names = response.path("name");



        for(String each : names){
            System.out.println(each);
        }

    }




}
