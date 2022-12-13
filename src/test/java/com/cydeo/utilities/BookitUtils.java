package com.cydeo.utilities;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.not;

public class BookitUtils {

    public static String getToken(String email, String password){

        String accessToken = given().accept(ContentType.JSON).queryParam("email", email)
                .queryParam("password", password).when()
                .get("/sign").then().statusCode(200)
                .extract().jsonPath().getString("accessToken");

        assertThat(accessToken,not(emptyOrNullString()));

        return "Bearer " +accessToken;
    }

}
