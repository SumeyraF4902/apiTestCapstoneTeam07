package testData;

import java.util.HashMap;
import java.util.Map;

public class UserGroupServiceData {

/*
        {
            "id": 74,
                "name": "Arama Department",
                "group_type_id": 1,
                "organization_id": 1
        }

 */

        public Map<String, Object > expectedPostData(String name,Integer group_type_id,Integer organization_id) {
            Map<String, Object> expectedDataPostMap = new HashMap<String, Object>();
            expectedDataPostMap.put("name",name);
            expectedDataPostMap.put("group_type_id",group_type_id);
            expectedDataPostMap.put("organization_id",organization_id);

            return expectedDataPostMap;
        }

        public Map<String, Object > expectedPutData(Integer id,String name,Integer group_type_id,Integer organization_id) {
            Map<String, Object> expectedDataPutMap = new HashMap<String, Object>();
            expectedDataPutMap.put("id",id);
            expectedDataPutMap.put("name",name);
            expectedDataPutMap.put("group_type_id",group_type_id);
            expectedDataPutMap.put("organization_id",organization_id);

            return expectedDataPutMap;
        }

        public Map<String, String> expectedUserPostData(String username, String email){
            Map<String, String> expectedUserPostMap = new HashMap<String, String>();
            expectedUserPostMap.put("username", username);
            expectedUserPostMap.put("email", email);


            return expectedUserPostMap;
        }


    }

