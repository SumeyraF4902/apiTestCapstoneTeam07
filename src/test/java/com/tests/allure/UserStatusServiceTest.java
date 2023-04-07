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

public class UserStatusServiceTest {


    public static Login login = new Login();

    public static String token;


    public static Response responsePost;

    @BeforeClass
    public void getToken(){
        token = login.beToken();
    }
    @Test
    public void apostUserStatusService(){

        UserStatusServiceData serviceData = new UserStatusServiceData();

        Map<String,String> reqBody = serviceData.setupDataUserStatus();

        System.out.println("reqBody = " + reqBody);

        responsePost = Reusable.postMethod("userStatusPost",reqBody,token);

        responsePost.then().assertThat().statusCode(201);

    }


    @Test
    public void getIDUserStatusService(){

        String id = responsePost.jsonPath().getString("id");

       Response response = Reusable.getIDMethod("userStatusPost",token,id);

        response.then().assertThat().statusCode(200);

        Map<String,Object> actualDataMap = JsonToJava.convertJsonToJavaObject(response.asString(), HashMap.class);

        Map<String,Object> expectedDataMap = JsonToJava.convertJsonToJavaObject(responsePost.asString(), HashMap.class);

        for (String key: expectedDataMap.keySet()){
            Assert.assertEquals(actualDataMap.get(key),expectedDataMap.get(key));
        }

    }

}
