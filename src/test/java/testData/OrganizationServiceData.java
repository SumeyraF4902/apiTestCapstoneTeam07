package testData;

import com.tests.allure.OrganizationServiceTest;

import java.util.HashMap;

public class OrganizationServiceData {

    public static HashMap<String,Object> expectedData;

    public static HashMap<String,Object> expectedDataPut;

    /*

     {
  "id": 187,
  "name": "Testorix",
  "founder_id": 43,
  "short_name": "TX",
   "address": "....",
  }




      */
 public HashMap expectedData(){

     expectedData = new HashMap<>();


     expectedData.put("id",null);
     expectedData.put("name","Team007237");
     expectedData.put("founder_id",43);
     expectedData.put("address","Poland");
     expectedData.put("short_name","team");
     expectedData.put("created_at",null);
     expectedData.put("updated_at",null);


     return expectedData;
 }



    public HashMap expectedDataPut(){


        expectedDataPut = new HashMap<>();

        expectedDataPut.put("id",null);
        expectedDataPut.put("name","Team070");
        expectedDataPut.put("founder_id",43);
        expectedData.put("address","Poland");
        expectedDataPut.put("short_name","team");





     return expectedDataPut;
    }



}
