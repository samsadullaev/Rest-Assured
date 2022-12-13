package com.cydeo.day08;

import com.cydeo.utilities.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
public class P01_SpartanAuthTest extends SpartanAuthTestBase {

    @DisplayName("Get api/spartans as GUEST -- expect 401")//since guest has no access to anything
    @Test
    public void test1(){


        given().log().all().accept(ContentType.JSON).when().get("api/spartans").prettyPeek()
                .then().log().ifValidationFails().statusCode(401).body("error",is("Unauthorized"));

    }
    @DisplayName("Get api/spartans as USER -- expect 200 ")
    @Test
    public void test2(){


        given().log().all().accept(ContentType.JSON).
        auth().basic("user","user").
                when().get("api/spartans").prettyPeek()
                .then().log().ifValidationFails().statusCode(200);

    }
    @DisplayName("DELETE api/spartans as Editor ")
    @Test
    public void test3(){


        given().pathParam("id",2).
                auth().basic("editor","editor").
                when().delete("api/spartans/{id}").prettyPeek()
                .then().log().ifValidationFails().statusCode(403).
                body("error",is("Forbidden"));



    }
    @DisplayName("DELETE api/spartans as Admin ")
    @Test
    public void test4(){


        given().pathParam("id",3).
                auth().basic("admin","admin").
                when().delete("api/spartans/{id}")
                .then().statusCode(204);




    }
}
