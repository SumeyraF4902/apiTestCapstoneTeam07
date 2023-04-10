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

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.testng.Assert.assertEquals;


public class UserGroupTypeServiceTest extends Login {

    UserGroupTypeServiceData testData = new UserGroupTypeServiceData();
    Faker faker = new Faker();


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



    @Test
    public void getTestUserGroupTypeById() {
        Map<String, Object> requestBody = testData.expectedDataSetUp(faker.company().industry(), faker.company().catchPhrase());

        Response response = Reusable.postMethod("userGroupTypeURL", requestBody);
        Map<String, Object> actualBody = response.as(HashMap.class);
        Integer id = (Integer) actualBody.get("id");

        response = Reusable.getIDMethod("userGroupTypeURL", id);
        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("id", equalTo(id));

        Reusable.deleteMethod("userGroupTypeURL",id);
    }


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


    @Test
    public void postTestAddNewUserType() {
        Map<String, Object> requestBody = testData.expectedDataSetUp(faker.company().industry(), faker.company().catchPhrase());

        Response response = Reusable.postMethod("userGroupTypeURL", requestBody);

        Map<String, Object> actualBody = response.as(HashMap.class);

        assertEquals(requestBody.get("name"), actualBody.get("name"));
        assertEquals(requestBody.get("description"), actualBody.get("description"));

        Reusable.deleteMethod("userGroupTypeURL", actualBody.get("id"));

    }


    @Test
    public void postTestAddNewUserTypeByID() {
        Map<String, Object> requestBody = testData.expectedDataIdSetUp(faker.number().numberBetween(4, 100), faker.company().industry(), faker.company().catchPhrase());

        Response response = Reusable.postMethod("userGroupTypeURL", requestBody);
        response.
                then().
                assertThat().
                statusCode(406);
    }


    @Test
    public void postTestAddNewUserTypeWithNullName() {
        Map<String, Object> requestBody = testData.expectedDataSetUp(null, faker.company().catchPhrase());

        Response response = Reusable.postMethod("userGroupTypeURL", requestBody);
        response.
                then().
                assertThat().
                statusCode(400);
    }


    @Test
    public void postTestAddNewUserTypeWithEmptyName() {
        Map<String, Object> requestBody = testData.expectedDataSetUp("", faker.company().catchPhrase());

        Response response = Reusable.postMethod("userGroupTypeURL", requestBody);
        response.
                then().
                assertThat().
                statusCode(406);
    }


    @Test
    public void postTestAddNewUserTypeWith3SpaceName() {
        Map<String, Object> requestBody = testData.expectedDataSetUp("   ", "Space Character");

        Response response = Reusable.postMethod("userGroupTypeURL", requestBody);

        JsonPath actualBody = response.jsonPath();
        response.then().assertThat().statusCode(201);

        assertEquals(requestBody.get("name"), actualBody.getString("name"));
        assertEquals(requestBody.get("description"), actualBody.getString("description"));

        Reusable.deleteMethod("userGroupTypeURL", actualBody.get("id"));
    }


    @Test
    public void postTestAddNewUserTypeWithSpecialCharacterName() {
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
        Map<String, Object> requestBody = testData.expectedDataSetUp(faker.company().industry(), null);

        Response response = Reusable.postMethod("userGroupTypeURL", requestBody);

        Map<String, Object> actualBody = response.as(HashMap.class);

        response.then().assertThat().statusCode(201);
        assertEquals(requestBody.get("name"), actualBody.get("name"));
        assertEquals(requestBody.get("decsription"), actualBody.get("description"));

        Reusable.deleteMethod("userGroupTypeURL", actualBody.get("id"));

    }


    @Test
    public void putTestUpdateUserGroupType() {
        Map<String, Object> requestPostBody = testData.expectedDataSetUp(faker.company().industry(), faker.company().catchPhrase());

        Response responsePost = Reusable.postMethod("userGroupTypeURL", requestPostBody);
        JsonPath actualPostBody = responsePost.jsonPath();
        Integer id = actualPostBody.getInt("id");

        Map<String, Object> requestBody = testData.expectedDataIdSetUp(id, faker.company().buzzword(), faker.company().catchPhrase());

        Response response = Reusable.putMethod("userGroupTypeURL", requestBody);

        Map<String, Object> actualBody = JsonToJava.convertJsonToJavaObject(response.asString(), HashMap.class);

        assertEquals(requestBody.get("id"), actualBody.get("id"));
        assertEquals(requestBody.get("name"), actualBody.get("name"));
        assertEquals(requestBody.get("description"), actualBody.get("description"));

        Reusable.deleteMethod("userGroupTypeURL", actualBody.get("id"));

    }


    @Test
    public void putTestUpdateUserGroupTypeById() {
        Map<String, Object> expectedBody = testData.expectedDataIdSetUp(0, faker.company().buzzword(), faker.company().catchPhrase());

        Response response = Reusable.putMethod("userGroupTypeURL", expectedBody);
        response.then().assertThat().statusCode(404);
    }


    @Test
    public void putTestUpdateJustNameUserGroupType() {
        Map<String, Object> requestPostBody = testData.expectedDataSetUp(faker.company().industry(), "same description");

        Response responsePost = Reusable.postMethod("userGroupTypeURL", requestPostBody);
        JsonPath actualPostBody = responsePost.jsonPath();
        Integer id = actualPostBody.getInt("id");

        Map<String, Object> requestBody = testData.expectedDataIdSetUp(id, faker.company().buzzword(), "same description");

        Response response = Reusable.putMethod("userGroupTypeURL", requestBody);

        Map<String, Object> actualBody = JsonToJava.convertJsonToJavaObject(response.asString(), HashMap.class);

        assertEquals(requestBody.get("id"), actualBody.get("id"));
        assertEquals(requestBody.get("name"), actualBody.get("name"));
        assertEquals(requestBody.get("description"), actualBody.get("description"));

        Reusable.deleteMethod("userGroupTypeURL", actualBody.get("id"));

    }


    @Test
    public void putTestUpdateJustDescriptionUserGroupType() {
        Map<String, Object> requestPostBody = testData.expectedDataSetUp("Group07", faker.company().catchPhrase());

        Response responsePost = Reusable.postMethod("userGroupTypeURL", requestPostBody);
        JsonPath actualPostBody = responsePost.jsonPath();
        Integer id = actualPostBody.getInt("id");

        Map<String, Object> requestBody = testData.expectedDataIdSetUp(id, "Group07", faker.company().buzzword());

        Response response = Reusable.putMethod("userGroupTypeURL", requestBody);

        Map<String, Object> actualBody = JsonToJava.convertJsonToJavaObject(response.asString(), HashMap.class);

        assertEquals(requestBody.get("id"), actualBody.get("id"));
        assertEquals(requestBody.get("name"), actualBody.get("name"));
        assertEquals(requestBody.get("description"), actualBody.get("description"));

        Reusable.deleteMethod("userGroupTypeURL", actualBody.get("id"));
    }



    @Test
    public void deleteTestUserGroupTypeById() {
        Map<String, Object> requestPostBody = testData.expectedDataSetUp(faker.company().industry(), faker.company().catchPhrase());

        Response responsePost = Reusable.postMethod("userGroupTypeURL", requestPostBody);
        JsonPath actualPostBody = responsePost.jsonPath();
        Integer id = actualPostBody.getInt("id");

        Reusable.deleteMethod("userGroupTypeURL", id);
        Response response = Reusable.getIDMethod("userGroupTypeURL", id);
        response.then().assertThat().statusCode(404);
    }


    @Test
    public void deleteTestUserGroupTypeByNoId() {
        Map<String, Object> requestPostBody = testData.expectedDataSetUp(faker.company().industry(), faker.company().catchPhrase());

        Response responsePost = Reusable.postMethod("userGroupTypeURL", requestPostBody);
        JsonPath actualPostBody = responsePost.jsonPath();
        Integer id = actualPostBody.getInt("id");

        Response response = Reusable.deleteMethod("userGroupTypeURL", id);
        response.then().assertThat().statusCode(200);

    }


    @Test
    public void generalTest() {
        Map<String, Object> requestPostBody = testData.expectedDataSetUp("General", "not detailed");

        Response responsePost = Reusable.postMethod("userGroupTypeURL", requestPostBody);
        JsonPath actualPostBody = responsePost.jsonPath();
        Integer id = actualPostBody.getInt("id");

        Response response = Reusable.getIDMethod("userGroupTypeURL", id);
        response.
                then().
                assertThat().
                statusCode(200).
                body("name", equalTo("General"), "description", equalTo("not detailed"));


        Map<String, Object> expectedPutBody = testData.expectedDataIdSetUp(id, "Name Changed", "Description Changed");
        response = Reusable.putMethod("userGroupTypeURL", expectedPutBody);

        response = Reusable.getIDMethod("userGroupTypeURL", id);

        response.
                then().
                assertThat().
                statusCode(200).
                body("name", equalTo("Name Changed"), "description", equalTo("Description Changed"));

        response = Reusable.deleteMethod("userGroupTypeURL", id);

        response.
                then().
                assertThat().
                statusCode(200);

        response = Reusable.getIDMethod("userGroupTypeURL", id);

        response.
                then().
                assertThat().
                statusCode(404);
    }

    @Test
    public void testMaxBounds() {
       Map<String,Object> expectedBody = testData.expectedDataSetUp(faker.lorem().fixedString(50),faker.lorem().fixedString(104));
       Response response = Reusable.postMethod("userGroupTypeURL",expectedBody);
       JsonPath actual = response.jsonPath();
       Integer id = actual.getInt("id");
       response.
               then().
               assertThat().
               statusCode(201);

       Reusable.deleteMethod("userGroupTypeURL",id);
    }

    @Test
    public void testNameBoundOut() {
        Map<String,Object> expectedBody = testData.expectedDataSetUp(faker.lorem().fixedString(55),faker.lorem().fixedString(255));
        Response response = Reusable.postMethod("userGroupTypeURL",expectedBody);
        JsonPath actual = response.jsonPath();
        response.
                then().
                assertThat().
                statusCode(500);
    }

    @Test
    public void testDescriptionBoundOut() {
        Map<String,Object> expectedBody = testData.expectedDataSetUp(faker.lorem().fixedString(50),faker.lorem().fixedString(260));
        Response response = Reusable.postMethod("userGroupTypeURL",expectedBody);
        JsonPath actual = response.jsonPath();
        response.
                then().
                assertThat().
                statusCode(500);
    }
}



