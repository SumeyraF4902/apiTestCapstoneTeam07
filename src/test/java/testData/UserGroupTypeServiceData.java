package testData;

import java.util.HashMap;
import java.util.Map;

public class UserGroupTypeServiceData {


    public Map<String, Object> expectedDataSetUp(String name, String description) {
        Map<String, Object> expectedDataMap = new HashMap<String, Object>();
        expectedDataMap.put("name", name);
        expectedDataMap.put("description", description);

        return expectedDataMap;
    }

    public Map<String, Object> expectedDataIdSetUp(Integer id, String name, String description) {
        Map<String, Object> expectedDataMap = new HashMap<String, Object>();
        expectedDataMap.put("id",id);
        expectedDataMap.put("name", name);
        expectedDataMap.put("description", description);

        return expectedDataMap;
    }
}
