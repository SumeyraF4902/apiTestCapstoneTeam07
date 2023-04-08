package com.tests.allure;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Login;
import testData.MembershipServiceData;
import utilities.Reusable;

import java.util.Map;

import static org.testng.Assert.assertEquals;

public class MembershipServiceTest extends Login {
    static String subscriptionId;
    static JsonPath actualData;

    @Test
    public void getAllMembership() {
        Reusable.getMethod("getMembershipServiceUrl");

    }

    @Test(priority = 1)
    public void post() {
        MembershipServiceData data = new MembershipServiceData();
        Map<String, Object> expectedData = data.Data(2, 33, 6, 6, true, 6, true, false);

        System.out.println("expectedData = " + expectedData);
        Response response = Reusable.postMethod("membershipServiceUrl", expectedData);
        actualData = response.jsonPath();

        Assert.assertEquals(expectedData.get("app_id"), actualData.getInt("app_id"));
        Assert.assertEquals(expectedData.get("user_id"), actualData.getInt("user_id"));
        Assert.assertEquals(expectedData.get("membership_type_id"), actualData.getInt("membership_type_id"));
        Assert.assertEquals(expectedData.get("default_role_id"), actualData.getInt("default_role_id"));
        subscriptionId = actualData.get("subscription_id");
        System.out.println("subscriptionId = " + subscriptionId);

    }

    @Test(priority = 2)
    public void put() {
        MembershipServiceData data = new MembershipServiceData();
        Map<String, Object> expectedData = data.putSubscriptionId(2,33,subscriptionId,6,6,true,6,true, true);
        assertEquals(actualData.getString("subscription_id"), expectedData.get("subscription_id"));
    }

    @Test(priority = 3)
    public void delete() {
        Reusable.deleteMethod("membershipServiceUrl", subscriptionId);
        System.out.println("subscriptionId = " + subscriptionId);
    }

}
