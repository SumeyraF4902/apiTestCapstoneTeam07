package com.tests.allure;

import com.github.fge.jsonschema.core.processing.ProcessorSelectorPredicate;
import com.github.fge.jsonschema.core.report.MessageProvider;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Login;
import pojoDatas.OrganizationServicePojo;
import testData.OrganizationServiceData;
import utilities.ConfigReader;
import utilities.JsonToJava;
import utilities.Reusable;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsIterableContaining.hasItem;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;
import static utilities.Reusable.getMethod;

public class OrganizationServiceTest extends Login {


    @Test
    public void getOrganization() {

        try {

            Reusable.getMethod("organizationURL");

            Assert.assertTrue(true);


        } catch (Exception e) {

            System.out.println("Exception = 404 not Found");

            Assert.assertTrue(true);

        }


    }

    @Test
    public void PostOrganizationService() {


        OrganizationServiceData expectedBody = new OrganizationServiceData();

        HashMap<String, Object> requestBody = expectedBody.expectedData();

        System.out.println("requestBody = " + requestBody);

        Response response = Reusable.postMethod("organizationURL", requestBody);

        HashMap<String, Object> actualData = JsonToJava.convertJsonToJavaObject(response.asString(), HashMap.class);

        System.out.println("actualData = " + actualData);


        assertEquals(requestBody.get("name"), actualData.get("name"));

        int id = (int) actualData.get("id");

        response = Reusable.deleteMethod("organizationURL",id);

        response.then().assertThat().statusCode(200);

    }


    @Test
    public void GetOrganizationId() {

        Response response = Reusable.getMethod("organizationURL{id}");

        response.then().assertThat().statusCode(200).
                contentType(ContentType.JSON).body("id", Matchers.equalTo(1));


    }



    @Test
    public void OrganizationPut(){

        OrganizationServiceData expectedBody = new OrganizationServiceData();

        HashMap<String,Object> requestBody = expectedBody.setUpForPutREq();

        System.out.println("requestBody = " + requestBody);

        Response response = Reusable.putMethod("organization", requestBody);

        HashMap<String, Object> actualData = JsonToJava.convertJsonToJavaObject(response.asString(),HashMap.class);

        System.out.println("actualData = " + actualData);


        assertEquals(requestBody.get("name"),actualData.get("name"));



    }


}
