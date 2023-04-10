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

    public static Integer id;



    public static Response responsePost;

    public static OrganizationServiceData expectedBody = new OrganizationServiceData();

    @Test
    public void getOrganization() {

        try {

            Reusable.getMethod("organizationURL");

            Assert.assertTrue(false);


        } catch (Exception e) {

            System.out.println("Exception = 404 not Found");

            Assert.assertTrue(true);

        }


    }

    @Test
    public void PostOrganizationService() {


        HashMap<String, Object> requestBody = expectedBody.expectedData();

        System.out.println("requestBody = " + requestBody);

        responsePost = Reusable.postMethod("organizationURL", requestBody);

        HashMap<String, Object> actualData = JsonToJava.convertJsonToJavaObject(responsePost.asString(), HashMap.class);

        System.out.println("actualData = " + actualData);


        assertEquals(requestBody.get("name"), actualData.get("name"));

        id = (int) actualData.get("id");

        responsePost = Reusable.deleteMethod("organizationURL", id);

        responsePost.then().assertThat().statusCode(200);

        requestBody.put("id", id);

        try {

            Reusable.putMethod("organizationURL", requestBody);

            Assert.assertTrue(true);


        } catch (Exception e) {

            System.out.println("Exception = 404 not Found");

            Assert.assertTrue(true);

        }


    }


    @Test
    public void GetOrganizationId() {

        Response response = Reusable.getMethod("organizationURL{id}");

        response.then().assertThat().statusCode(200).
                contentType(ContentType.JSON).body("id", Matchers.equalTo(187));


    }


    @Test
    public void OrganizationPut() {


        HashMap<String, Object> requestBody = expectedBody.expectedData();

        responsePost = Reusable.postMethod("organizationURL", requestBody);

        HashMap<String, Object> actualDataPost = JsonToJava.convertJsonToJavaObject(responsePost.asString(), HashMap.class);

        Integer id2 = (int) actualDataPost.get("id");

        HashMap<String, Object> requestBodyPut = expectedBody.expectedDataPut();

        requestBodyPut.put("id", id2);

        Response responsePut = Reusable.putMethod("organizationURL", requestBodyPut);

        HashMap<String, Object> actualDataPut = JsonToJava.convertJsonToJavaObject(responsePut.asString(), HashMap.class);

        System.out.println("actualData = " + actualDataPut);

        assertEquals(requestBodyPut.get("name"), actualDataPut.get("name"));

        responsePut = Reusable.deleteMethod("organizationURL", id2);

        responsePut.then().assertThat().statusCode(200);





    }



   @Test
    public void negatifPost(){

       HashMap<String, Object> requestBody = expectedBody.expectedData();

       responsePost = Reusable.postMethod("organizationURL", requestBody);

       HashMap<String, Object> actualData = JsonToJava.convertJsonToJavaObject(responsePost.asString(), HashMap.class);

       System.out.println("actualData = " + actualData);

       Integer id = (int) actualData.get("id");

       try {

           Reusable.getIDMethod("organizationURL",id);

           Assert.assertTrue(false);


       } catch (Exception e) {

           System.out.println("Exception = 406 Not Acceptable");

           Assert.assertTrue(true);

       }

       responsePost = Reusable.deleteMethod("organizationURL", id);

       responsePost.then().assertThat().statusCode(200);


   }





}
