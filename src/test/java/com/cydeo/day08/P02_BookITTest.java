package com.cydeo.day08;
import com.cydeo.utilities.BookitTestBase;
import com.cydeo.utilities.BookitUtils;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class P02_BookITTest extends BookitTestBase {

    String accessToken= BookitUtils.getToken("lfinnisz@yolasite.com","lissiefinnis");

@DisplayName("GET api/campuses")
    @Test
    void test1() {
    System.out.println(accessToken);

    given().accept(ContentType.JSON).header("Authorization",accessToken)
            .when().get("/api/campuses").prettyPeek()
            .then().statusCode(200);

    }
    @DisplayName("GET api/users/me")
    @Test
    void test2() {

        System.out.println(accessToken);

        given().accept(ContentType.JSON).header("Authorization",accessToken)
                .when().get("/api/users/me").prettyPeek()
                .then().statusCode(200);

    }
}
