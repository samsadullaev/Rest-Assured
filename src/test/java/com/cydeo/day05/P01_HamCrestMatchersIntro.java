package com.cydeo.day05;

import org.hamcrest.Matchers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;


public class P01_HamCrestMatchersIntro {

    @Test
    public void test1(){

//junit 5 assertions
        assertEquals(9,3+6);


//Coming from Matchers
        assertThat(9,is(6+3));

        assertThat(9, is(equalTo(6+3)));

        assertThat(9,equalTo(6+3));


        //false assertion

        assertThat(5+5,not(9));

        //
        assertThat(5+6,greaterThan(10));

        assertThat(5+6,lessThan(12));



    }

    @Test
    public void test2(){

        String msg="API is fun!";

        assertThat(msg,is("API is fun!"));

        assertThat(msg,startsWithIgnoringCase("api"));

        assertThat(msg,endsWith("fun!"));

        assertThat(msg,containsStringIgnoringCase("is"));

    }

    @Test
    public void test3(){

        List<Integer> numberLst = Arrays.asList( 3, 5, 1, 77, 44, 76 ) ; // 6 elements here

        assertThat(numberLst,hasItem(44));

        assertThat(numberLst,hasSize(6));

        assertThat(numberLst,everyItem(greaterThan(0)));

        assertThat(numberLst,contains(44,76));


    }

}
