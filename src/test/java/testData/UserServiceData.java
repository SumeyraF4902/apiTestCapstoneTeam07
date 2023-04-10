package testData;

import java.util.HashMap;
import java.util.Map;

public class UserServiceData {

    public Map<String, Object> userPostexpectedDataSetUp(String username, String email) {
        Map<String, Object> expectedDataMap = new HashMap<String, Object>();
        expectedDataMap.put("username", username);
        expectedDataMap.put("email", email);

        return expectedDataMap;


    }
}