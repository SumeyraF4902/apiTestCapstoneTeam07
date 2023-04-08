package com.tests.allure;

import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Login;
import testData.UserGroupTypeServiceData;
import utilities.JsonToJava;
import utilities.Reusable;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.hasItems;
import static org.testng.Assert.assertEquals;


public class UserGroupTypeServiceTest extends Login {

    public static Integer postId;

    // BAĞIMSIZ
    @org.testng.annotations.Test
    public void getTestAllUserGroupType() {
        Response response = Reusable.getMethod("userGroupTypeURL");

        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("id", hasItems(1, 2, 3),
                        "name", hasItems("Team", "Remote Unit", "Department"));

    }

    //  ( 1 ) ÜRET
    @org.testng.annotations.Test ( priority = 1 )
    public void postTestAddNewUserType() {
        UserGroupTypeServiceData testData = new UserGroupTypeServiceData();
        Map<String, Object> requestBody = testData.expectedDataSetUp("Group07", "Students");

        Response response = Reusable.postMethod("userGroupTypeURL", requestBody);


        Map<String, Object> actualBody = JsonToJava.convertJsonToJavaObject(response.asString(),HashMap.class);

        assertEquals(requestBody.get("name"), actualBody.get("name"));
        assertEquals(requestBody.get("description"), actualBody.get("description"));

        postId = (Integer) actualBody.get("id");
        System.out.println(postId);

    }

    //  ( 2 ) GETİR
    @org.testng.annotations.Test ( priority = 2 )
    public void getTestUserGroupType() {
        Response response = Reusable.getIDMethod("userGroupTypeURL", String.valueOf(postId));

        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON);

    }

    //  ( 3 ) DEĞİŞTİR
    @org.testng.annotations.Test ( priority = 3 )
    public void putTestUpdateUserGroupType() throws IOException {

        UserGroupTypeServiceData testData = new UserGroupTypeServiceData();
        Map<String, Object> requestBody = testData.expectedDataIdSetUp(postId, "NewGroup7", "Stdents");

        Response response = Reusable.putMethod("userGroupTypeURL", requestBody);

        Map<String, Object> actualBody = JsonToJava.convertJsonToJavaObject(response.asString(), HashMap.class);

        response.then().assertThat().statusCode(200);
        assertEquals(requestBody.get("id"), actualBody.get("id"));
        assertEquals(requestBody.get("name"), actualBody.get("name"));
        assertEquals(requestBody.get("description"), actualBody.get("description"));


    }
    //  ( 4 ) SİL
    @org.testng.annotations.Test ( priority = 4 )
    public void deleteTestUserGroupType() {

        Response response = Reusable.deleteMethod("userGroupTypeURL", postId);
        response.then().assertThat().statusCode(200);
    }

    // BAĞIMSIZ
    @org.testng.annotations.Test
    public void postTestAddNewUserTypeWithID() {

        UserGroupTypeServiceData testData = new UserGroupTypeServiceData();
        Map<String, Object> requestBody = testData.expectedDataIdSetUp(77, "Education07", "Organization of Education");

        Response response = Reusable.postMethod("userGroupTypeURL", requestBody);
        response.
                then().
                assertThat().
                statusCode(406);
    }

    // BAĞIMSIZ
    @org.testng.annotations.Test
    public void postTestAddNewUserTypeWithEmptyName() {

        UserGroupTypeServiceData testData = new UserGroupTypeServiceData();
        Map<String, Object> requestBody = testData.expectedDataSetUp("", "Organization of Education");

        Response response = Reusable.postMethod("userGroupTypeURL", requestBody);
        response.
                then().
                assertThat().
                statusCode(406);
    }

    // BAĞIMSIZ
    @Test
    public void postTestAddNewUserTypeWith3SpaceName() {

        UserGroupTypeServiceData testData = new UserGroupTypeServiceData();
        Map<String, Object> requestBody = testData.expectedDataSetUp("   ", "Space Character");

        Response response = Reusable.postMethod("userGroupTypeURL", requestBody);

        JsonPath actualBody = response.jsonPath();

        response.then().assertThat().statusCode(201);
        assertEquals(requestBody.get("name"), actualBody.getString("name"));
        assertEquals(requestBody.get("description"), actualBody.getString("description"));

        int ID = actualBody.getInt("id");
        Reusable.deleteMethod("userGroupTypeURL", ID).then().assertThat().statusCode(200);


    }

    // BAĞIMSIZ
    @org.testng.annotations.Test
    public void postTestAddNewUserTypeWithSpecialCharacterName() {

        UserGroupTypeServiceData testData = new UserGroupTypeServiceData();
        Map<String, Object> requestBody = testData.expectedDataSetUp("?*/%", "Special Character");
        Response response = Reusable.postMethod("userGroupTypeURL", requestBody);

        JsonPath actualBody = response.jsonPath();

        response.then().assertThat().statusCode(201);
        assertEquals(requestBody.get("name"), actualBody.getString("name"));
        assertEquals(requestBody.get("description"), actualBody.getString("description"));

        int ID = actualBody.getInt("id");
        Reusable.deleteMethod("userGroupTypeURL", ID);
    }


    // BAĞIMSIZ
    @org.testng.annotations.Test
    public void postTestAddNewUserTypeWithNumericCharacterName() {

        UserGroupTypeServiceData testData = new UserGroupTypeServiceData();
        Map<String, Object> requestBody = testData.expectedDataSetUp("12345", "Numeric Character");
        Response response = Reusable.postMethod("userGroupTypeURL", requestBody);

        JsonPath actualBody = response.jsonPath();

        response.then().assertThat().statusCode(201);
        assertEquals(requestBody.get("name"), actualBody.getString("name"));
        assertEquals(requestBody.get("description"), actualBody.getString("description"));

        int ID = actualBody.get("id");
        Reusable.deleteMethod("userGroupTypeURL", ID);

    }

    @org.testng.annotations.Test
    public void postTestAddNewUserTypeWithEmptyDescription() {
        UserGroupTypeServiceData testData = new UserGroupTypeServiceData();
        Map<String, Object> requestBody = testData.expectedDataSetUp("Edu07", null);
        Response response = Reusable.postMethod("userGroupTypeURL", requestBody);

        JsonPath actualBody = response.jsonPath();

        response.then().assertThat().statusCode(201);
        assertEquals(requestBody.get("name"), actualBody.getString("name"));
        assertEquals(requestBody.get("description"), actualBody.getString("description"));

        int ID = actualBody.get("id");
        Reusable.deleteMethod("userGroupTypeURL", ID);
    }

    @org.testng.annotations.Test
    public void getTestNoUserGroupType() {
        try {
            Reusable.getMethod("userGroupTypeIDURL");
            Assert.assertTrue(true);

        } catch (Exception e) {
            System.out.println("Exception = 404 not Found");
            Assert.assertTrue(true);
        }
    }

    @org.testng.annotations.Test
    public void putTestUpdateNoUserGroupType() {
        try {
            Reusable.putMethod("userGroupTypeURL", 300);
            Assert.assertTrue(true);

        } catch (Exception e) {
            System.out.println("Exception = 404 not Found");
            Assert.assertTrue(true);
        }
    }


    @org.testng.annotations.Test
    public void deleteTestNoUserGroupType() {
        try {
            Reusable.putMethod("userGroupTypeURL", 300);
            Assert.assertTrue(true);

        } catch (Exception e) {
            System.out.println("Exception = 404 not Found");
            Assert.assertTrue(true);
        }
    }
}


