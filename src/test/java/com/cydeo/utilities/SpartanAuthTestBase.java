package com.cydeo.utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class SpartanAuthTestBase {

    @BeforeAll
    public static void init() {

        RestAssured.baseURI = "http://34.235.154.64:7000";


    }
}

