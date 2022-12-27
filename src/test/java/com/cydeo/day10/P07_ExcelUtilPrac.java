package com.cydeo.day10;

import com.cydeo.utilities.ExcelUtil;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class P07_ExcelUtilPrac {


@Test
    public void test1(){

    ExcelUtil library = new ExcelUtil("src/test/resources/Library.xlsx","Library1-short");


    List<Map<String,String>> allUser = library.getDataList();

    for(Map<String,String> eachUser : allUser){
        System.out.println(eachUser);
    }


}

}
