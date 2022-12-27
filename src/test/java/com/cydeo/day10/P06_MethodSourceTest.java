package com.cydeo.day10;

import com.cydeo.utilities.ExcelUtil;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class P06_MethodSourceTest {

    public static List<String> getNames() {


        List<String> nameList = Arrays.asList("Kimberly", "King", "TJ", "Bond");


        return nameList;

    }

    @ParameterizedTest
    @MethodSource("getNames")
    public void test1(String name) {

        System.out.println("name = " + name);

    }

    @ParameterizedTest
    @MethodSource("getExcelData")
        public void credentials(Map< String, String> userInfo){

        System.out.println("userInfo.get(\"Email\") = " + userInfo.get("Email"));
        System.out.println("userInfo.get(\"Password\") = " + userInfo.get("Password"));
        System.out.println("userInfo.get(\"role\") = " + userInfo.get("Role"));

    }


        public static List<Map<String, String>> getExcelData () {

            ExcelUtil library = new ExcelUtil("src/test/resources/Library.xlsx", "Library1-short");

            return library.getDataList();

        }


    }

