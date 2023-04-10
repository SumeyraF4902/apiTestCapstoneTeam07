package testData;

import java.util.HashMap;
import java.util.Map;

public class MembershipServiceData {


    public Map<String, Object> expectedDataRole(Integer app_id, Integer user_id, Object subscription_id, Integer role_id, /*Integer organization_id,*/ Boolean is_default){
        Map<String, Object> expectedDataMapRole = new HashMap<>();
        expectedDataMapRole.put("app_id", app_id);
        expectedDataMapRole.put("user_id", user_id);
        expectedDataMapRole.put("subscription_id", subscription_id);
        expectedDataMapRole.put("role_id", role_id);
       // expectedDataMapRole.put("organization_id", organization_id);
        expectedDataMapRole.put("is_default", is_default);

        return expectedDataMapRole;
    }


    public Map<String, Object> Data(Integer app_id, Integer user_id, Integer subscription_type_id,Integer membership_type_id, Boolean is_individual_membership,
    Boolean is_active, Boolean is_default) {


        Map<String, Object> expectedDataMap = new HashMap<>();

        expectedDataMap.put("app_id", app_id);
        expectedDataMap.put("user_id", user_id);
        expectedDataMap.put("subscription_type_id", subscription_type_id);
        expectedDataMap.put("membership_type_id", membership_type_id);
        expectedDataMap.put("is_individual_membership", is_individual_membership);
      //  expectedDataMap.put("default_role_id", default_role_id);
        expectedDataMap.put("is_active", is_active);
        expectedDataMap.put("is_default", is_default);


        return expectedDataMap;


    }
    public Map<String, Object> putSubscriptionId(Integer app_id, Integer user_id,Object subscription_id, Integer subscription_type_id,Integer membership_type_id, Boolean is_individual_membership, Integer default_role_id,
                                    Boolean is_active, Boolean is_default) {


        Map<String, Object> expectedDataMap = new HashMap<>();

        expectedDataMap.put("app_id", app_id);
        expectedDataMap.put("user_id", user_id);
        expectedDataMap.put("subscription_type_id", subscription_type_id);
        expectedDataMap.put("membership_type_id", membership_type_id);
        expectedDataMap.put("is_individual_membership", is_individual_membership);
        expectedDataMap.put("default_role_id", default_role_id);
        expectedDataMap.put("is_active", is_active);
        expectedDataMap.put("is_default", is_default);
        expectedDataMap.put("subscription_id", subscription_id);


        return expectedDataMap;


    }


}
