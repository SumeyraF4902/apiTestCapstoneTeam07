package testData;

import java.util.HashMap;

public class OrganizationServiceData {

    public static HashMap<String,Object> expectedData;

    /*
     {
      "id": 1,
      "name": "Acme01",
      "founder_id": 2,
      "short_name": "team",
      "address": "Ronald Avenue McMillan Drive No. 5, Tysons, Virginia",
      "phone": "15555555555",
      "email": "contactacme.com",
      "website": "www.acme.com",
      "fax": "1555555555",
      "status_id": 1
       "created_at": "2023-04-06T21:24:42.939062Z",
       "updated_at": "2023-04-06T21:24:42.939064Z"


     }
      */
 public HashMap expectedData(){

     expectedData = new HashMap<>();


     expectedData.put("id",null);
     expectedData.put("name","Acme07123");
     expectedData.put("founder_id",2);
     expectedData.put("short_name","team");
     expectedData.put("address","Ronald Avenue McMillan Drive No. 5, Tysons, Virginia");
     expectedData.put("phone","15555555555");
     expectedData.put("email","contactacme.com");
     expectedData.put("website","www.acme.com");
     expectedData.put("fax","1555555555");
     expectedData.put("status_id",1);
     expectedData.put("created_at",null);
     expectedData.put("updated_at",null);


     return expectedData;
 }



    public HashMap<String,Object>setUpForPutREq (){

        expectedData = new HashMap<>();

        expectedData.put("id",1);
        expectedData.put("name","Acme");
        expectedData.put("founder_id",2);
        expectedData.put("short_name","team");
        expectedData.put("address","Ronald Avenue McMillan Drive No. 5, Tysons, Virginia");
        expectedData.put("phone","15555555555");
        expectedData.put("email","contactacme.com");
        expectedData.put("website","www.acme.com");
        expectedData.put("fax","1555555555");
        expectedData.put("status_id",1);




     return expectedData;
    }



}
