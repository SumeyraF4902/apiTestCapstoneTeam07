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
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.IsIterableContaining.hasItem;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;
import static utilities.Reusable.getMethod;

public class OrganizationServiceTest extends Login {

    public static Integer id;



    public static Response responsePost;

    public static OrganizationServiceData expectedBody = new OrganizationServiceData();

    @Test(
            groups = {"regression"}
    )
    public void getOrganization() {


        Response response =  Reusable.getMethod("organizationURL");

        response.then().assertThat().statusCode(404);



    }

    @Test(
            groups = {"smoke,regression"}
    )
    public void PostOrganizationService() {


        HashMap<String, Object> requestBody = expectedBody.expectedData();

        System.out.println("requestBody = " + requestBody);

        responsePost = Reusable.postMethod("organizationURL", requestBody);

        responsePost.then().assertThat().statusCode(201);

        HashMap<String, Object> actualData = JsonToJava.convertJsonToJavaObject(responsePost.asString(), HashMap.class);

        System.out.println("actualData = " + actualData);

        assertEquals(requestBody.get("name"), actualData.get("name"));

        id = (int) actualData.get("id");

        responsePost = Reusable.deleteMethod("organizationURL", id);

        responsePost.then().assertThat().statusCode(200);

        requestBody.put("id", id);

        Response response =  Reusable.putMethod("organizationURL",requestBody);

        response.then().assertThat().statusCode(404);


    }


    @Test(
            groups = {"regression"}
    )
    public void GetOrganizationId() {

        Response response = Reusable.getIDMethod("organizationURL",187);

        response.then().assertThat().statusCode(200).
                contentType(ContentType.JSON).body("id", Matchers.equalTo(187));


    }


    @Test(
            groups = {"regression"}
    )
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



   @Test(
           groups = {"regression"}
   )
    public void negatifPost(){

       HashMap<String, Object> requestBody = expectedBody.expectedData();

       responsePost = Reusable.postMethod("organizationURL", requestBody);

       HashMap<String, Object> actualData = JsonToJava.convertJsonToJavaObject(responsePost.asString(), HashMap.class);

       System.out.println("actualData = " + actualData);

       Integer id = (int) actualData.get("id");

       Response response =  Reusable.getIDMethod("organizationURL",id);

       response.then().assertThat().statusCode(404);

       responsePost = Reusable.deleteMethod("organizationURL", id);

       responsePost.then().assertThat().statusCode(200);


   }


    @Test(
            groups = {"regression"}
    )
    public void negatifnamePost(){

        HashMap<String, Object> reqBody = expectedBody.expectedDataN(null,"",43,"team");

        System.out.println("reqBody = " + reqBody);

        Response response= Reusable.postMethod("organizationURL",reqBody);

        response.then().assertThat().statusCode(406);

        response.then().assertThat().body("error",equalTo("Not Acceptable"));



    }

    @Test(
            groups = {"regression"}
    )
    public void negatiffounderIdPost(){

        HashMap<String, Object> reqBody = expectedBody.expectedDataN(null,"team07",null,"team");

        System.out.println("reqBody = " + reqBody);

        Response response= Reusable.postMethod("organizationURL",reqBody);

        response.then().assertThat().statusCode(406);

        response.then().assertThat().body("error",equalTo("Not Acceptable"));




    }



}
