package com.cydeo.utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public abstract class NewsApiTestBase {
    @BeforeAll
    public static void init() {

        RestAssured.baseURI = "https://newsapi.org/v2";


    }
}
