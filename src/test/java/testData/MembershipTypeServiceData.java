package testData;

import java.util.HashMap;
import java.util.Map;

public class MembershipTypeServiceData {


    public Map<String,Object> expectedDataMap;


    public Map<String,Object> setupDataMemberShipTypeService(String name, Boolean isEnabled, Integer appId){

        expectedDataMap = new HashMap<>();

        expectedDataMap.put("name",name);
        expectedDataMap.put("is_enabled",isEnabled);
        expectedDataMap.put("app_id",appId);

        return expectedDataMap;


    }

    public Map<String,Object> setupDataIDMemberShipTypeService(Integer id, String name, Boolean isEnabled, Integer appId){

        expectedDataMap = new HashMap<>();

        expectedDataMap.put("id",id);
        expectedDataMap.put("name",name);
        expectedDataMap.put("is_enabled",isEnabled);
        expectedDataMap.put("app_id",appId);

        return expectedDataMap;


    }





}
