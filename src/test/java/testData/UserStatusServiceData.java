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



        expectedDataMap.put("name",faker.name().title());
        expectedDataMap.put("description",faker.howIMetYourMother().character());


        return expectedDataMap;


    }

    public Map<String,Object> putDataUserStatus(){

        

        expectedDataMap = new HashMap<>();


        expectedDataMap.put("name",faker.name().title());
        expectedDataMap.put("description",faker.howIMetYourMother().highFive());


        return expectedDataMap;


    }
    public Map<String,Object> setupNegativeDataUserStatus(){

        expectedDataMap = new HashMap<>();



        expectedDataMap.put("name","");
        expectedDataMap.put("description","User account is active");


        return expectedDataMap;


    }

    public Map<String,Object> setupIDDataUserStatus(){

        expectedDataMap = new HashMap<>();


        expectedDataMap.put("id",faker.number().randomDigit());
        expectedDataMap.put("name",faker.name().title());
        expectedDataMap.put("description",faker.howIMetYourMother().character());


        return expectedDataMap;


    }

}
