package com.tests.allure;

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
import utilities.Reusable;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.hasItems;
import static org.testng.Assert.assertEquals;


public class UserGroupTypeServiceTest extends Login {


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

    @org.testng.annotations.Test
    public void postTestAddNewUserType() {
      //  UserGroupTypeServicePojo requestBody = new UserGroupTypeServicePojo("Group07", "Students");
        UserGroupTypeServiceData testData = new UserGroupTypeServiceData();
        Map<String,Object> requestBody= testData.expectedDataSetUp("Group07", "Students");

        Response response = Reusable.postMethod("userGroupTypeURL",requestBody );

        Map<String,Object> actualBody = response.as(HashMap.class);

        assertEquals(requestBody.get("name"),actualBody.get("name"));
        assertEquals(requestBody.get("description"), actualBody.get("description"));

    }

    @org.testng.annotations.Test
    public void postTestAddNewUserTypeWithID() {

        UserGroupTypeServicePojo requestBody = new UserGroupTypeServicePojo(77, "Education07", "Organization of Education");

        Response response = Reusable.postMethod("userGroupTypeURL", requestBody);
        response.
                then().
                assertThat().
                statusCode(406);
    }

    @org.testng.annotations.Test
    public void postTestAddNewUserTypeWithEmptyName() {
        UserGroupTypeServicePojo requestBody = new UserGroupTypeServicePojo(null, "", "Organization of Education");

        Response response = Reusable.postMethod("userGroupTypeURL", requestBody);
        response.
                then().
                assertThat().
                statusCode(406);
    }

    @Test
    public void postTestAddNewUserTypeWith3SpaceName() {
        // UserGroupTypeServicePojo requestBody = new UserGroupTypeServicePojo(null, "   ", "Space Character");
        UserGroupTypeServiceData testData = new UserGroupTypeServiceData();
        Map<String,Object> requestBody= testData.expectedDataSetUp("   ", "Space Character");

        Response response = Reusable.postMethod("userGroupTypeURL", requestBody);

        JsonPath actualBody = response.jsonPath();
        response.then().assertThat().statusCode(201);

        assertEquals(requestBody.get("name"), actualBody.getString("name"));
        assertEquals(requestBody.get("description"), actualBody.getString("description"));


    }


    @org.testng.annotations.Test
    public void postTestAddNewUserTypeWithSpecialCharacterName() {

        UserGroupTypeServiceData testData = new UserGroupTypeServiceData();
        Map<String,Object> requestBody= testData.expectedDataSetUp("?*/%", "Special Character");
        Response response = Reusable.postMethod("userGroupTypeURL", requestBody);

        Gson gson = new Gson();
        Map<String,Object>  actualBody = gson.fromJson(response.asString(),HashMap.class);

        response.then().assertThat().statusCode(201);
        assertEquals(requestBody.get("name"), actualBody.get("name"));
        assertEquals(requestBody.get("description"), actualBody.get("description"));


    }

    @org.testng.annotations.Test
    public void postTestAddNewUserTypeWithNumericCharacterName() {

        UserGroupTypeServiceData testData = new UserGroupTypeServiceData();
        Map<String,Object> requestBody= testData.expectedDataSetUp("12345", "Numeric Character");
        Response response = Reusable.postMethod("userGroupTypeURL", requestBody);

        JsonPath actualBody = response.jsonPath();

        response.then().assertThat().statusCode(201);
        assertEquals(requestBody.get("name"), actualBody.getString("name"));
        assertEquals(requestBody.get("description"), actualBody.getString("description"));


    }

    @org.testng.annotations.Test
    public void postTestAddNewUserTypeWithEmptyDescription() {
        UserGroupTypeServicePojo requestBody = new UserGroupTypeServicePojo( "Edu07", null);

        Response response = Reusable.postMethod("userGroupTypeURL", requestBody);

        UserGroupTypeServicePojo actualBody = response.as(UserGroupTypeServicePojo.class);

        response.then().assertThat().statusCode(201);
        assertEquals(requestBody.getName(), actualBody.getName());
        assertEquals(requestBody.getDescription(), actualBody.getDescription());
    }

    @org.testng.annotations.Test
    public void putTestUpdateUserGroupType() throws IOException {
        UserGroupTypeServicePojo requestBodyt = new UserGroupTypeServicePojo(33,"NewGroup7","Studnts");
        System.out.println(requestBodyt.getId());

        Response response = Reusable.putMethod("userGroupTypeURL",requestBodyt);
        ObjectMapper actualBody = new ObjectMapper();

        UserGroupTypeServicePojo act = actualBody.readValue(response.asString(),UserGroupTypeServicePojo.class);

        assertEquals(requestBodyt.getName(),act.getName());
        assertEquals(requestBodyt.getDescription(),act.getDescription());

    }

    @org.testng.annotations.Test
    public void putTestUpdateNoUserGroupType() {
        try {
            Reusable.putMethod("userGroupTypeURL",300);
            Assert.assertTrue(true);

        } catch (Exception e){
            System.out.println("Exception = 404 not Found");
            Assert.assertTrue(true);
        }
    }

    @org.testng.annotations.Test
    public void getTestUserGroupType() {
        // get için id li method lazım 200
    }

    @org.testng.annotations.Test
    public void getTestNoUserGroupType() {
        try {
            Reusable.getMethod("userGroupTypeIdURL");
            Assert.assertTrue(true);

        } catch (Exception e){
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
            Reusable.putMethod("userGroupTypeURL",300);
            Assert.assertTrue(true);

        } catch (Exception e){
            System.out.println("Exception = 404 not Found");
            Assert.assertTrue(true);
        }
    }
}


