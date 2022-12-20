package com.cydeo.day10;

import org.junit.jupiter.api.*;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Disabled;

public class P01_Junit5AnnotationsLifeCycle {

    // TestNG --> BeforeClass
    @BeforeAll
    public static void init(){

        System.out.println("----> BeforeAll is running");

    }

    @BeforeEach
    public void initEach(){
        System.out.println("--------> BeforeEach is running");

    }

    @Test
    public void test1() {

        System.out.println("----------> Test1 is running");

    }

    // Ignore Test --> TestNG -->@Ignore
    //@Disabled --> It is possibble to use without message as well
    @Disabled("This tc is ignored")
    @Test
    public void test2() {

        System.out.println("----------> Test2 is running");

    }

    @AfterEach
    public void afterEach(){
        System.out.println("--------> AfterEach is running");

    }

    //TestNG --> AfterClass
    @AfterAll
    public static void destroy(){

        System.out.println("----> AfterAll is running");

    }




}


