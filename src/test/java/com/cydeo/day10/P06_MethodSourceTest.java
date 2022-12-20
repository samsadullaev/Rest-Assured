package com.cydeo.day10;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class P06_MethodSourceTest {

    public static List<String> getNames(){


        List<String> nameList = Arrays.asList("Kimberly","King","TJ","Bond");


        return nameList;

    }

    @ParameterizedTest
    @MethodSource("getNames")
    public void test1(String name){

        System.out.println("name = " + name);

    }

}
