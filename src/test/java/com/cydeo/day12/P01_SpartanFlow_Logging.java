package com.cydeo.day12;



import com.cydeo.pojo.Spartan;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    public class P01_SpartanFlow_Logging extends SpartanTestBase{

        static int spartanID;
        static Spartan spartanPost ;
        static Spartan spartanPut ;


        @Order(1)
        @Test
        public void POST() {


            spartanPost = new Spartan();
            spartanPost.setName("API POST Flow");
            spartanPost.setGender("Male");
            spartanPost.setPhone(8877445596l);


            spartanID = given().accept(ContentType.JSON)
                    .contentType(ContentType.JSON)
                    .body(spartanPost).
                    when().post("/api/spartans").
                    then()
                    .statusCode(201)
                    .body("success", is("A Spartan is Born!")).extract().jsonPath().getInt("data.id");


            log.info(spartanID + " is created");


        }
        @Order(2)
        @Test
        public void GETSpartan_01() {

            given().accept(ContentType.JSON)
                    .pathParam("id", spartanID).
                    when()
                    .get("/api/spartans/{id}").
                    then()
                    .statusCode(200)
                    .body("name",is(spartanPost.getName()));
        }

        @Order(3)
        @Test
        public void PUT() {

            spartanPut = new Spartan();
            spartanPut.setName("API PUT Flow");
            spartanPut.setGender("Male");
            spartanPut.setPhone(8877445596l);

            given()
                    .contentType(ContentType.JSON)
                    .pathParam("id", spartanID)
                    .body(spartanPut).
                    when().put("/api/spartans/{id}").
                    then()
                    .statusCode(204);


            log.info(spartanID + " is updated");


        }


        @Order(4)
        @Test
        public void GETSpartan_02() {

            given().accept(ContentType.JSON)
                    .pathParam("id", spartanID).
                    when()
                    .get("/api/spartans/{id}").
                    then()
                    .statusCode(200)
                    .body("name", is(spartanPut.getName()));


        }
        @Order(5)
        @Test
        public void DELETE() {

            given()
                    .pathParam("id", spartanID)
                    .when().delete("/api/spartans/{id}")
                    .then().statusCode(204);


           log.info(spartanID + " is deleted");


        }
        @Order(6)
        @Test
        public void GETSpartan() {

            given().accept(ContentType.JSON)
                    .pathParam("id", spartanID).
                    when()
                    .get("/api/spartans/{id}").
                    then()
                    .statusCode(404);

           log.info(spartanID + " is not exist");

        }


    }


