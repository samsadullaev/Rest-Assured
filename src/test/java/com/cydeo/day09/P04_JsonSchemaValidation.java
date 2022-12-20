package com.cydeo.day09;

import com.cydeo.utilities.SpartanAuthTestBase;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class P04_JsonSchemaValidation extends SpartanTestBase {

    @DisplayName("GET /api/spartans/{id} to validate with JsonSchemaValidator")
    @Test
    public void test1(){


        given().accept(ContentType.JSON).pathParam("id",45)
                .when().get("/api/spartans/{id}").prettyPeek()
                .then().statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("single.json"));

    }
}
