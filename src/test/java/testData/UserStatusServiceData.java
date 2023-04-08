package testData;

import com.github.javafaker.Faker;

import java.util.HashMap;
import java.util.Map;

public class UserStatusServiceData {


    public Map<String,Object> expectedDataMap;

   /*
   {

  "name": "active",
  "description": "User account is active"
}
    */
       public static Faker faker = new Faker();


    public Map<String,Object> setupDataUserStatus(){

        expectedDataMap = new HashMap<>();

        expectedDataMap.put("name","faker.name()");
        expectedDataMap.put("description","User account is active");


        return expectedDataMap;


    }

    public Map<String,Object> putDataUserStatus(){

        

        expectedDataMap = new HashMap<>();


        expectedDataMap.put("name","faker.name()1");
        expectedDataMap.put("description","User account is active");


        return expectedDataMap;


    }

}
