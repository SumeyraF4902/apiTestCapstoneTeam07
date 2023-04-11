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

    /*
    "email":"aliveli@gma.com","organization_id":187,"app_id":2,"default_role_id":5
     */

    public Map<String, Object> userPostRegisterexpectedDataSetUp(String email, Integer organization_id, Integer app_id, Integer default_role_id ) {
        Map<String, Object> expectedDataMap = new HashMap<String, Object>();

        expectedDataMap.put("email", email);
        expectedDataMap.put("organization_id", organization_id);
        expectedDataMap.put("app_id", app_id);
        expectedDataMap.put("default_role_id", default_role_id);



        return expectedDataMap;


    }

    public Map<String, Object> userPutexpectedDataSetUp(String username, String email, String country ) {
        Map<String, Object> expectedDataMap = new HashMap<String, Object>();

        expectedDataMap.put("email", username);
        expectedDataMap.put("email", email);
        expectedDataMap.put("country_id", country);




        return expectedDataMap;


    }

    public Map<String, Object> userContryExpectedDataSetUp(String email, Integer organization_id, Integer app_id, Integer default_role_id, String country ) {
        Map<String, Object> expectedDataMap = new HashMap<String, Object>();

        expectedDataMap.put("email", email);
        expectedDataMap.put("organization_id", organization_id);
        expectedDataMap.put("app_id", app_id);
        expectedDataMap.put("default_role_id", default_role_id);
        expectedDataMap.put("country_id", country);

        return expectedDataMap;
    }


}