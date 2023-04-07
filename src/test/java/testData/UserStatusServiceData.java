package testData;

import java.util.HashMap;
import java.util.Map;

public class UserStatusServiceData {


    public Map<String,String> expectedDataMap;

   /*
   {

  "name": "active",
  "description": "User account is active"
}
    */



    public Map<String,String> setupDataUserStatus(){

        expectedDataMap = new HashMap<>();

        expectedDataMap.put("name","Merv active");
        expectedDataMap.put("description","User account is active");


        return expectedDataMap;


    }

}
