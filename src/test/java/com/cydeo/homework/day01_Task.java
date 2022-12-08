package com.cydeo.homework;

import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class day01_Task extends HrTestBase{




        @DisplayName("GET request to countries with using Response Path and JSON")
        @Test
        public void test1() {

             /*
        - Given accept type is Json
- Path param value- US
- When users sends request to /countries
- Then status code is 200
- And Content - Type is Json
- And country_id is US
- And Country_name is United States of America
- And Region_id is 2
         */

            Response response = given().accept(ContentType.JSON).pathParam("country_id", "US").
                    when().get("/countries/{country_id}");

            assertEquals(200,response.getStatusCode());

            assertEquals(ContentType.JSON.toString(),response.getContentType());

            JsonPath jsonPath = response.jsonPath();

            assertEquals("US", jsonPath.getString("country_id"));
            assertEquals("United States of America",jsonPath.getString("country_name"));
            assertEquals(2,jsonPath.getInt("region_id"));




        }

        @Test
    public void test2(){

           /*
           - Given accept type is Json
- Query param value - q={"department_id":80}
- When users sends request to /employees
- Then status code is 200
- And Content - Type is Json
- And all job_ids start with 'SA'
- And all department_ids are 80
- Count is 25
            */

         Response response =   given().accept(ContentType.JSON).queryParam("q","{\"department_id\":80}")
                    .when().get("/employees");

            assertEquals(200,response.getStatusCode());

            assertEquals(ContentType.JSON.toString(),response.getContentType());


            JsonPath jsonPath = response.jsonPath();

            List<String> jobID= jsonPath.getList("items.job_id");

            List<Integer> departmentId = jsonPath.getList("items.department_id");

            for(String each : jobID){
                assertTrue(each.startsWith("SA"));
            }
            for(Integer each1 : departmentId){
                assertEquals(80,each1);
            }

            assertEquals(25,jsonPath.getInt("count"));



        }
    @Test
    public void test3(){
            /*
            - Given accept type is Json
-Query param value q= region_id 3
- When users sends request to /countries
- Then status code is 200
- And all regions_id is 3
- And count is 6
- And hasMore is false
- And Country_name are;
Australia,China,India,Japan,Malaysia,Singapore
             */

            Response response = given().accept(ContentType.JSON).queryParam("q","{\"region_id\" :3}")
                    .when().get("/countries");

        assertEquals(200,response.getStatusCode());

        assertEquals(ContentType.JSON.toString(),response.getContentType());

        JsonPath jsonPath = response.jsonPath();

            List<Integer> regionIDs = jsonPath.getList("items.region_id");

            for(Integer each : regionIDs){
                assertEquals(6,each);
            }

            assertEquals(6,jsonPath.getInt("count"));
            assertEquals(false,jsonPath.getBoolean("hasMore"));







    }

}
