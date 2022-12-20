package com.cydeo.day09;

import com.cydeo.utilities.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class P01_SpartanXMLTest extends SpartanAuthTestBase {

    /**
     * Given accept type is application/xml
     * When send the request /api/spartans
     * Then status code is 200
     * And content type is application/xml
     *   print firstname
     *   .....
     *   ...
     */


    @Test
    public void test1() {

        given().accept(ContentType.XML)
                .auth().basic("admin","admin")
                .when().get("/api/spartans").prettyPeek()
                .then().statusCode(200).contentType(ContentType.XML)
                .body("list.item[0].name", is("Paige"));


    }
    @DisplayName("GET /api/spartans with using XMLPath")
    @Test
    public void test2() {

        Response response = given().accept(ContentType.XML)
                .auth().basic("admin", "admin").
                when().get("/api/spartans");

        XmlPath xmlPath = response.xmlPath();
        // get first gender
        System.out.println("xmlPath.get(\"List.item[0].gender\") = " + xmlPath.get("List.item[0].gender"));
        //get 3rd name
        System.out.println("xmlPath.get(\"List.item[2].name\") = " + xmlPath.get("List.item[2].name"));
        //get last name
        System.out.println("xmlPath.get(\"List[-1].name\") = " + xmlPath.get("List.item[-1].name"));
        //get all names
        List<Map<Integer,String>> names = xmlPath.getList("List.item.name");
        List<String> ListName = xmlPath.getList("List.item.name");
        System.out.println(ListName + " ");

      for(int i = 0; i< names.size();i++){
          System.out.println(i);
      }

        System.out.println("xmlPath.getList(\"List.item\").size() = " + xmlPath.getList("List.item").size());


    }

}
