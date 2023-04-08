package com.tests.allure;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.Login;
import testData.UserStatusServiceData;
import utilities.ConfigReader;
import utilities.JsonToJava;
import utilities.Reusable;

import java.util.HashMap;
import java.util.Map;

public class UserStatusServiceTest extends Login{





  public static  UserStatusServiceData serviceData = new UserStatusServiceData();

    public static Response responsePost;

    public static Integer id;


    @Test
    public void apostUserStatusService(){



        Map<String,Object> reqBody = serviceData.setupDataUserStatus();

        System.out.println("reqBody = " + reqBody);

        responsePost = Reusable.postMethod("userStatusPost",reqBody);

        responsePost.then().assertThat().statusCode(201);

    }


    @Test
    public void getIDUserStatusService(){

         id = Integer.valueOf(responsePost.jsonPath().getString("id"));

       Response response = Reusable.getIDMethod("userStatusPost", id);

        response.then().assertThat().statusCode(200);

        Map<String,Object> actualDataMap = JsonToJava.convertJsonToJavaObject(response.asString(), HashMap.class);

        Map<String,Object> expectedDataMap = JsonToJava.convertJsonToJavaObject(responsePost.asString(), HashMap.class);

        for (String key: expectedDataMap.keySet()){
            Assert.assertEquals(actualDataMap.get(key),expectedDataMap.get(key));
        }

    }

    @Test
    public void putUserStatusService(){

        Map<String,Object> reqBody = serviceData.putDataUserStatus();

        reqBody.put("id",id);


        Response response = Reusable.putMethod("userStatusPost",reqBody);

        response.then().assertThat().statusCode(200);

        Map<String,Object> actualDataMap = JsonToJava.convertJsonToJavaObject(response.asString(), HashMap.class);



        for (String key: reqBody.keySet()){
            Assert.assertEquals(actualDataMap.get(key),reqBody.get(key));
        }

    }






}
