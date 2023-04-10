package testData;

import com.tests.allure.OrganizationStatusServiceTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrganizationStatusServiceData {


    public Map<String,Object> expectedData;


    public Map<String,Object> post(){

        expectedData = new HashMap<>();

        expectedData.put("name","Turkey");
        expectedData.put("description","post successfully");

        return expectedData;
    }

    public Map<String,Object> postNegative1() {
        expectedData = new HashMap<>();
        expectedData.put("description","post successfully");

        return expectedData;
    }

    public Map<String,Object> postNegative2() {
        expectedData = new HashMap<>();
        expectedData.put("description","post successfully");

        return expectedData;
    }



    public Map<String,Object> post2(){

        expectedData = new HashMap<>();
        expectedData.put("name","Team07");
        return expectedData;


    }

    public Map<String,Object>  put(){

        expectedData = new HashMap<>();
        expectedData.put("id", OrganizationStatusServiceTest.putID);
        expectedData.put("name","team070");
        expectedData.put("description","put successfully");


        return expectedData;
    }



    public Map<String,Object> delete() {

        expectedData = new HashMap<>();
        expectedData.put("id", 64);
        expectedData.put("name", "team070");
        expectedData.put("description", "put successfully");


        return expectedData;
    }
}
