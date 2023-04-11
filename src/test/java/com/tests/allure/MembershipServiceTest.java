package com.tests.allure;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Login;
import testData.MembershipServiceData;
import utilities.Reusable;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

public class MembershipServiceTest extends Login {
    static Object subscriptionId;
    static JsonPath actualData;
    MembershipServiceData data;

    @Test(
            groups = {"regression"}
    )
    public void TC01GetAllMembership() {
        Reusable.getMethod("getMembershipServiceUrl");

    }


    @Test(
            groups = {"smoke,regression"}
    )

    public void TC02Post() {
        data = new MembershipServiceData();
        Map<String, Object> expectedData = data.Data(2, 332, 27, 27, true,  true, false);

        System.out.println("expectedData = " + expectedData);
        Response response = Reusable.postMethod("membershipServiceUrl", expectedData);
        actualData = response.jsonPath();

        Assert.assertEquals(expectedData.get("app_id"), actualData.getInt("app_id"));
        Assert.assertEquals(expectedData.get("user_id"), actualData.getInt("user_id"));
        Assert.assertEquals(expectedData.get("membership_type_id"), actualData.getInt("membership_type_id"));

        subscriptionId = actualData.get("subscription_id");


    }

    @Test(
            groups = {"regression"}
    )
    public void TC03Put() {
        data = new MembershipServiceData();
        Map<String, Object> expectedData = data.putSubscriptionId(2, 33, subscriptionId, 6,
                6, true, 6, false, false);
        assertEquals(actualData.getString("subscription_id"), expectedData.get("subscription_id"));
    }


    @Test(
            groups = {"smoke,regression"}
    )
    public void TC04Delete() {
        Reusable.deleteMethod("membershipServiceUrl", subscriptionId);
        System.out.println("subscriptionId = " + subscriptionId);
    }

    @Test(
            groups = {"regression"}
    )
    public void TC05GetMembershipSuspend() {
        Response response = Reusable.getMethod("getMembershipServiceSuspendUrl");
        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("app_id", hasItems(2),
                        "user_id", hasItems(332));


    }


    @Test(
            groups = {"regression"}
    )
    public void TC06PutRole() {
        data = new MembershipServiceData();
        Map<String, Object> expectedData = data.expectedDataRole(2, 332, "5dbc8b45-956a-4eaa-bb1f-3411730b7453", 6, false);
        System.out.println("expectedData = " + expectedData);
        Response response = Reusable.putMethod("putMembershipServiceRoleUrl", expectedData);

        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("app_id", equalTo(2),
                        "user_id", equalTo(332), "role_id", equalTo(6), "is_default", equalTo(false));

    }

    @Test(
            groups = {"regression"}
    )
    public void TC07PutRoleNegative() {
        data = new MembershipServiceData();
        Map<String, Object> expectedData = data.expectedDataRole(2, 332, "5dbc8b45-956a-4eaa-bb1f-3411730b7452", 6, false);


            Response response = Reusable.putMethod("putMembershipServiceRoleUrl", expectedData);

           response.then().assertThat().statusCode(404);



    }


}