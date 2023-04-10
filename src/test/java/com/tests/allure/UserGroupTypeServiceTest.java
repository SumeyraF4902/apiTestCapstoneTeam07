package com.tests.allure;


import com.github.javafaker.Faker;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Login;
import pojoDatas.UserGroupTypeServicePojo;
import testData.UserGroupTypeServiceData;
import utilities.JsonToJava;
import utilities.Reusable;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.hasItems;
import static org.testng.Assert.assertEquals;


public class UserGroupTypeServiceTest extends Login {

    UserGroupTypeServiceData testData = new UserGroupTypeServiceData();
    Faker faker = new Faker();

    /* TÜM GET */
    @Test
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


    /* ID Lİ GET */
    @Test
    public void getTestUserGroupTypeById() {

        Map<String, Object> requestBody = testData.expectedDataSetUp(faker.company().industry(), faker.company().catchPhrase());

        Response response = Reusable.postMethod("userGroupTypeURL", requestBody);
        Map<String, Object> actualBody = response.as(HashMap.class);
        Integer id = (Integer) actualBody.get("id");
        Reusable.deleteMethod("userGroupTypeURL", id);

        response = Reusable.getIDMethod("userGroupTypeURL", id);
        response.then().assertThat().statusCode(404);
    }

    /* ID SİZ GET */
    @Test
    public void getTestUserGroupTypeByNoId() {

        Map<String, Object> requestBody = testData.expectedDataSetUp(faker.company().industry(), faker.company().catchPhrase());

        Response response = Reusable.postMethod("userGroupTypeURL", requestBody);
        Map<String, Object> actualBody = response.as(HashMap.class);
        Integer id = (Integer) actualBody.get("id");
        Reusable.deleteMethod("userGroupTypeURL", id);

        response = Reusable.getIDMethod("userGroupTypeURL", id);
        response.then().assertThat().statusCode(404);
    }

    @org.testng.annotations.Test
    public void postTestAddNewUserType() {

        UserGroupTypeServiceData testData = new UserGroupTypeServiceData();
        Map<String, Object> requestBody = testData.expectedDataSetUp("Group07", "Students");

        Response response = Reusable.postMethod("userGroupTypeURL", requestBody);

        Map<String, Object> actualBody = response.as(HashMap.class);

        assertEquals(requestBody.get("name"), actualBody.get("name"));
        assertEquals(requestBody.get("description"), actualBody.get("description"));

        Reusable.deleteMethod("userGroupTypeURL", actualBody.get("id"));
        try {
            Reusable.getIDMethod("userGroupTypeURL", actualBody.get("id"));
            Assert.assertTrue(false);

        } catch (Exception e) {
            System.out.println("Exception = 404 not Found");
            Assert.assertTrue(true);
        }


    }

    @Test
    public void postTestAddNewUserTypeWithID() {

        UserGroupTypeServiceData testData = new UserGroupTypeServiceData();
        Map<String, Object> requestBody = testData.expectedDataIdSetUp(77, "Education07", "Organization of Education");

        Response response = Reusable.postMethod("userGroupTypeURL", requestBody);
        response.
                then().
                assertThat().
                statusCode(406);
    }

    @Test
    public void postTestAddNewUserTypeWithEmptyName() {

        UserGroupTypeServiceData testData = new UserGroupTypeServiceData();
        Map<String, Object> requestBody = testData.expectedDataSetUp("", "Organization of Education");

        Response response = Reusable.postMethod("userGroupTypeURL", requestBody);
        response.
                then().
                assertThat().
                statusCode(406);
    }

    @Test
    public void postTestAddNewUserTypeWith3SpaceName() {
        UserGroupTypeServiceData testData = new UserGroupTypeServiceData();
        Map<String, Object> requestBody = testData.expectedDataSetUp("   ", "Space Character");

        Response response = Reusable.postMethod("userGroupTypeURL", requestBody);

        JsonPath actualBody = response.jsonPath();
        response.then().assertThat().statusCode(201);

        assertEquals(requestBody.get("name"), actualBody.getString("name"));
        assertEquals(requestBody.get("description"), actualBody.getString("description"));

        Reusable.deleteMethod("userGroupTypeURL", actualBody.get("id"));
    }


    @org.testng.annotations.Test
    public void postTestAddNewUserTypeWithSpecialCharacterName() {

        UserGroupTypeServiceData testData = new UserGroupTypeServiceData();
        Map<String, Object> requestBody = testData.expectedDataSetUp("?*/%", "Special Character");
        Response response = Reusable.postMethod("userGroupTypeURL", requestBody);

        Map<String, Object> actualBody = response.as(HashMap.class);

        response.then().assertThat().statusCode(201);
        assertEquals(requestBody.get("name"), actualBody.get("name"));
        assertEquals(requestBody.get("description"), actualBody.get("description"));

        Reusable.deleteMethod("userGroupTypeURL", actualBody.get("id"));
    }

    @Test
    public void postTestAddNewUserTypeWithNumericCharacterName() {

        UserGroupTypeServiceData testData = new UserGroupTypeServiceData();
        Map<String, Object> requestBody = testData.expectedDataSetUp("12345", "Numeric Character");
        Response response = Reusable.postMethod("userGroupTypeURL", requestBody);

        JsonPath actualBody = response.jsonPath();

        response.then().assertThat().statusCode(201);
        assertEquals(requestBody.get("name"), actualBody.getString("name"));
        assertEquals(requestBody.get("description"), actualBody.getString("description"));

        Reusable.deleteMethod("userGroupTypeURL", actualBody.get("id"));
    }

    @Test
    public void postTestAddNewUserTypeWithEmptyDescription() {
        UserGroupTypeServiceData testData = new UserGroupTypeServiceData();
        Map<String, Object> requestBody = testData.expectedDataSetUp("Edu07", null);

        Response response = Reusable.postMethod("userGroupTypeURL", requestBody);

        Map<String, Object> actualBody = response.as(HashMap.class);

        response.then().assertThat().statusCode(201);
        assertEquals(requestBody.get("name"), actualBody.get("name"));
        assertEquals(requestBody.get("decsription"), actualBody.get("description"));

        Reusable.deleteMethod("userGroupTypeURL", actualBody.get("id"));

    }

    @org.testng.annotations.Test
    public void putTestUpdateUserGroupType() {

        UserGroupTypeServiceData testData = new UserGroupTypeServiceData();
        Map<String, Object> requestPostBody = testData.expectedDataSetUp("NewGroup07", "Students");

        Response responsePost = Reusable.postMethod("userGroupTypeURL", requestPostBody);
        JsonPath actualPostBody = responsePost.jsonPath();
        Integer id = actualPostBody.getInt("id");

        Map<String, Object> requestBody = testData.expectedDataIdSetUp(id, "NewGroup07", "Students of Software");

        Response response = Reusable.putMethod("userGroupTypeURL", requestBody);

        Map<String, Object> actualBody = JsonToJava.convertJsonToJavaObject(response.asString(), HashMap.class);

        assertEquals(requestBody.get("id"), actualBody.get("id"));
        assertEquals(requestBody.get("name"), actualBody.get("name"));
        assertEquals(requestBody.get("description"), actualBody.get("description"));

        Reusable.deleteMethod("userGroupTypeURL", actualBody.get("id"));

    }

    @org.testng.annotations.Test
    public void putTestUpdateNoUserGroupType() {
        UserGroupTypeServiceData testData = new UserGroupTypeServiceData();
        Map<String, Object> expectedBody = testData.expectedDataIdSetUp(0, "Nothing", "Description");

        try {                                                              // karakter sınırı var mı ?
            Reusable.putMethod("userGroupTypeURL", expectedBody);      // ıd 0 ve negatifide ekle ?
            Assert.assertTrue(false);

        } catch (Exception e) {
            System.out.println("Exception = 404 not Found");
            Assert.assertTrue(true);
        }
    }


    @org.testng.annotations.Test
    public void getTestNoUserGroupType() {
        try {
            Reusable.getMethod("userGroupTypeIdURL");
            Assert.assertTrue(true);

        } catch (Exception e) {
            System.out.println("Exception = 404 not Found");
            Assert.assertTrue(true);
        }
    }


    @org.testng.annotations.Test
    public void deleteTestUserGroupType() {

        Response response = Reusable.deleteMethod("userGroupTypeURL", 31);
        response.then().assertThat().statusCode(200);
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



