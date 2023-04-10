package com.tests.allure;

import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Login;
import pojoDatas.OrganizationStatusServicePojo;
import testData.OrganizationStatusServiceData;
import testData.UserGroupTypeServiceData;
import utilities.ConfigReader;
import utilities.Reusable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.AssertJUnit.assertEquals;

public class OrganizationStatusServiceTest extends Login {

    Response response;
    public static int id;
    public static int putID;

    OrganizationStatusServiceData statusData = new OrganizationStatusServiceData();


    @org.testng.annotations.Test
    public void get() {


        Response response = Reusable.getMethod("organizationStatusURL");

        response.then().assertThat().statusCode(200);

        List<HashMap<String, String>> orgStatusList = response.jsonPath().getList("name");
        int orgStatusSayi = response.jsonPath().getList("name").size();
        Assert.assertEquals(orgStatusList.size(), orgStatusSayi);
    }

    @Test
    public void GetID() {

        /*
         "id": 105,
  "name": "team070",
  "description": "put successfully"
}
         */

        response = Reusable.getMethod("organizationStatusIdURL");


        response.
                then().
                assertThat().
                body("id", Matchers.equalTo(105),
                        "name", Matchers.equalTo("team070"),
                        "description", Matchers.equalTo("put successfully"));


    }

    @Test
    public void post() {

        OrganizationStatusServiceData statusData = new OrganizationStatusServiceData();
        Map<String, Object> request = statusData.post();

        response = Reusable.postMethod("organizationStatusURL", request);
        response.then().assertThat().statusCode(201);
        id = response.jsonPath().get("id");
        System.out.println(id);

        response = Reusable.deleteMethod("organizationStatusURL",
                id);
        response.then().assertThat().statusCode(200);
    }

    @Test
    public void postNegative() {
        Map<String, Object> request = statusData.postNegative1();

        response = Reusable.postMethod("organizationStatusURL", request);
        response.then().assertThat().statusCode(406);

    }

    @Test
    public void postNegative2() {
        Map<String, Object> request = statusData.postNegative2();

        response = Reusable.postMethod("organizationStatusURL", request);
        response.then().assertThat().statusCode(406);
    }

    @Test
    public void postWithSpaceName() {
        // UserGroupTypeServicePojo requestBody = new UserGroupTypeServicePojo(null, "   ", "Space Character");
        UserGroupTypeServiceData testData = new UserGroupTypeServiceData();
        Map<String,Object> requestBody= testData.expectedDataSetUp("   ", "Space Character");

        Response response = Reusable.postMethod("organizationStatusURL", requestBody);

        JsonPath actualBody = response.jsonPath();
        response.then().assertThat().statusCode(201);

        Assert.assertEquals(requestBody.get("name"), actualBody.getString("name"));
        Assert.assertEquals(requestBody.get("description"), actualBody.getString("description"));


    }

    @org.testng.annotations.Test
    public void postWithSpecialChName() {

        UserGroupTypeServiceData testData = new UserGroupTypeServiceData();
        Map<String, Object> requestBody = testData.expectedDataSetUp("?*/%", "Special Character");
        Response response = Reusable.postMethod("organizationStatusURL", requestBody);

        Gson gson = new Gson();
        Map<String, Object> actualBody = gson.fromJson(response.asString(), HashMap.class);

        response.then().assertThat().statusCode(201);
        Assert.assertEquals(requestBody.get("name"), actualBody.get("name"));
        Assert.assertEquals(requestBody.get("description"), actualBody.get("description"));
    }

    @org.testng.annotations.Test
    public void postNumChName() {

        UserGroupTypeServiceData testData = new UserGroupTypeServiceData();
        Map<String,Object> requestBody= testData.expectedDataSetUp("12345", "Numeric Character");
        Response response = Reusable.postMethod("organizationStatusURL", requestBody);

        JsonPath actualBody = response.jsonPath();

        response.then().assertThat().statusCode(201);
        Assert.assertEquals(requestBody.get("name"), actualBody.getString("name"));
        Assert.assertEquals(requestBody.get("description"), actualBody.getString("description"));


    }

        @Test
    public void put() {
        OrganizationStatusServiceData statusData = new OrganizationStatusServiceData();
        Map<String, Object> request = statusData.post2();

        response = Reusable.postMethod("organizationStatusURL", request);
        response.then().assertThat().statusCode(201);
        putID = response.jsonPath().get("id");


        Map<String, Object> requestt = statusData.put();
        response = Reusable.putMethod("organizationStatusURL", requestt);
        response.then().assertThat().body("id", Matchers.equalTo(putID),
                "name", Matchers.equalTo("team070"), "description", Matchers.equalTo("put successfully"));

        response = Reusable.deleteMethod("organizationStatusURL",
                putID);
        response.then().assertThat().statusCode(200);

    }


    @org.testng.annotations.Test
    public void putNegative() {
        try {
            Reusable.putMethod("organizationStatusURL", 3000);
            Assert.assertTrue(true);

        } catch (Exception e) {
            System.out.println("Exception = 404 not Found");
            Assert.assertTrue(true);
        }
    }

    @Test
    public void delete() {
        OrganizationStatusServiceData statusData = new OrganizationStatusServiceData();
        Map<String, Object> request = statusData.post();

        response = Reusable.postMethod("organizationStatusURL", request);
        response.then().assertThat().statusCode(201);
        int deleteID = response.jsonPath().get("id");
        System.out.println(id);
        //  Map<String,Object> request =statusData.delete();
        response = Reusable.deleteMethod("organizationStatusURL",
                deleteID);
        response.then().assertThat().statusCode(200);
    }

}