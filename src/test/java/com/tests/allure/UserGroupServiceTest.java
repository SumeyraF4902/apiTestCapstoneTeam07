package com.tests.allure;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Login;
import testData.UserGroupServiceData;
import utilities.Reusable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class UserGroupServiceTest extends Login {

    @Test
    public void getTestAllGroup() {
        Response response = Reusable.getMethod("usergroupURL");

        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("id", hasItems(118,120),
                        "name", hasItems("Sekretarya", "Arge"));

    }

    @Test
    public void getTestUserGroupById() {
        Response response = Reusable.getIDMethod("usergroupURL", String.valueOf(118));
        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("id", equalTo(118),
                        "name", equalTo("Sekretarya"));

    }

    @Test
    public void postTestAddNewUser() {

        UserGroupServiceData data = new UserGroupServiceData();
        Map<String, Object> expected = data.expectedPostData("Life", 1, 187);

        //post
        Response response = Reusable.postMethod("usergroupURL", expected);

        Map<String, Object> actual = response.as(HashMap.class);

        Assert.assertEquals(expected.get("name"), actual.get("name"));
        Assert.assertEquals(expected.get("group_type_id"), actual.get("group_type_id"));
        Assert.assertEquals(expected.get("organization_id"), actual.get("organization_id"));

        //delete
        Reusable.deleteMethod("usergroupURL", actual.get("id"));

    }

    @Test
    public void putTestUpdateExistingUserGroup() {
        //önce post 
        UserGroupServiceData data = new UserGroupServiceData();
        Map<String, Object> expectedPost = data.expectedPostData("Picture", 2, 187);
        Response responsePost = Reusable.postMethod("usergroupURL", expectedPost);
        Map<String, Object> actualPost = responsePost.as(HashMap.class);


        //şimdi put
        Map<String, Object> expectedPut = data.expectedPutData((Integer) actualPost.get("id"), "Egitimforlife", 2, 187);

        Response response = Reusable.putMethod("usergroupURL", expectedPut);
        JsonPath actualPut = response.jsonPath();

        Assert.assertEquals(expectedPut.get("id"), actualPut.getInt("id"));
        Assert.assertEquals(expectedPut.get("name"), actualPut.getString("name"));
        Assert.assertEquals(expectedPut.get("group_type_id"), actualPut.getInt("group_type_id"));
        Assert.assertEquals(expectedPut.get("organization_id"), actualPut.getInt("organization_id"));

        //delete
        Reusable.deleteMethod("usergroupURL", actualPut.getInt("id"));
    }

    @Test
    public void DeleteExistingUserGroupbyId() {

        //önce post
        UserGroupServiceData data = new UserGroupServiceData();
        Map<String, Object> expectedPost = data.expectedPostData("Kultur", 3, 187);
        Response responsePost = Reusable.postMethod("usergroupURL", expectedPost);
        Map<String, Object> actualPost = responsePost.as(HashMap.class);

        //delete
        Response responseDel =Reusable.deleteMethod("usergroupURL", actualPost.get("id"));
        responseDel.then().assertThat().statusCode(200);

        Reusable.getIDMethod("usergroupURL",actualPost.get("id")).then().assertThat().statusCode(404);

           }


    @Test
    public void PostTestAddUser() {
        UserGroupServiceData data = new UserGroupServiceData();
        Map<String, String> expectedPost = data.expectedUserPostData("goodness@gmail.com", "goodness@gmail.com");


        Response response = Reusable.postMethod("userGroupAddUserURL", expectedPost);

        Map<String, Object> actualPost = response.as(HashMap.class);
        response.then().
                assertThat().
                statusCode(201).
                body("user_group_id", equalTo(118), "user_id", equalTo(395));

        //delete
        Reusable.deleteMethod("userGroupDeleteUserURL", actualPost.get("user_id"));

    }


    @Test
    public void deleteTestUser() {
        UserGroupServiceData data = new UserGroupServiceData();
        Map<String, String> expectedPost = data.expectedUserPostData("goodness@gmail.com", "goodness@gmail.com");

        Response response = Reusable.postMethod("userGroupAddUserURL", expectedPost);
        Map<String, Object> actualPost = response.as(HashMap.class);

        //delete
        Reusable.deleteMethod("userGroupDeleteUserURL", actualPost.get("user_id"));

        Response responseGet = Reusable.getIDMethod("usergroupURL", actualPost.get("user_group_id"));
        Map<String, Object> actualGet = responseGet.as(HashMap.class);

        System.out.println("actualGet = " + actualGet);

        List<Map<String,Object>> list = (List<Map<String, Object>>) actualGet.get("users");


        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).get("id").equals(395)){
                Assert.assertTrue(false);
            } else{
                Assert.assertTrue(true);
            }
        }


    }
}
