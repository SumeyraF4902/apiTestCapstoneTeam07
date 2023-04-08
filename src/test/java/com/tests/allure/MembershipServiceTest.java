package com.tests.allure;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Login;
import testData.MembershipServiceData;
import testData.UserGroupTypeServiceData;
import utilities.ConfigReader;
import utilities.Reusable;
import utilities.ReusableGulsum;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class MembershipServiceTest extends Login {
    static Object subscriptionId ;
    @Test
    public void getAllMembership(){
        Reusable.getMethod("getMembershipServiceUrl" );

    }

    @Test(priority = 1)
    public void post() {
        MembershipServiceData data = new MembershipServiceData();
        Map<String, Object> expectedData = data.Data(2,33,6,6,true,6,true,false);

        System.out.println("expectedData = " + expectedData);
        Response response = ReusableGulsum.postMethod("membershipServiceUrl", expectedData);
        JsonPath actualData= response.jsonPath();
      actualData.prettyPrint();
        Assert.assertEquals(expectedData.get("app_id"), actualData.getInt("app_id"));
        Assert.assertEquals(expectedData.get("user_id"), actualData.getInt("user_id"));
        Assert.assertEquals(expectedData.get("membership_type_id"), actualData.getInt("membership_type_id"));
        Assert.assertEquals(expectedData.get("default_role_id"), actualData.getInt("default_role_id"));
        subscriptionId = actualData.get("subscription_id");
        System.out.println("subscriptionId = " + subscriptionId);

    }
    @Test(priority = 2)
    public void delete(){
        ReusableGulsum.deleteMethod("membershipServiceUrl" , subscriptionId);
        System.out.println("subscriptionId = " + subscriptionId);
    }

}
