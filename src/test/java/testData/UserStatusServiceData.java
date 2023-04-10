package testData;

import com.github.javafaker.Faker;

import java.util.HashMap;
import java.util.Map;

public class UserStatusServiceData {


    public Map<String,Object> expectedDataMap;


    public Map<String,Object> setupDataUserStatus(String name, String description){

        expectedDataMap = new HashMap<>();

        expectedDataMap.put("name",name);
        expectedDataMap.put("description",description);

        return expectedDataMap;


    }


    public Map<String,Object> setupIDDataUserStatus(Integer id, String name, String description){

        expectedDataMap = new HashMap<>();


        expectedDataMap.put("id",id);
        expectedDataMap.put("name",name);
        expectedDataMap.put("description",description);


        return expectedDataMap;


    }

}
