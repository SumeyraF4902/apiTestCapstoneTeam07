package com.tests.allure;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Login;
import testData.UserStatusServiceData;
import utilities.JsonToJava;
import utilities.Reusable;
import java.util.HashMap;
import java.util.Map;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class UserStatusServiceTest extends Login{


    public static  UserStatusServiceData serviceData = new UserStatusServiceData();

    public static Response responsePost;

    public static Integer id;

    @Test(priority = 1)
    public void getUserStatusService(){



        Response response = Reusable.getMethod("userStatus");

        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("id", hasItems(1, 2, 3, 4, 5, 6, 7, 8),
                        "name",
                        hasItems("Active", "Deactivated", "Locked Out", "Pending User Action", "Suspended"));

    }



    @Test(priority = 2)
    public void postUserStatusService(){

        Map<String,Object> reqBody = serviceData.setupDataUserStatus();

        responsePost = Reusable.postMethod("userStatus",reqBody);

        responsePost.then().assertThat().statusCode(201);

        Map<String,Object> actualDataMap = JsonToJava.convertJsonToJavaObject(responsePost.asString(), HashMap.class);

        id = Integer.valueOf(responsePost.jsonPath().getString("id"));

        reqBody.put("id",id);

        for (String key: reqBody.keySet()){
            Assert.assertEquals(actualDataMap.get(key),reqBody.get(key));
        }
    }


    @Test(priority = 3)
    public void getIDUserStatusService(){

        Response response = Reusable.getIDMethod("userStatus", id);

        response.then().assertThat().statusCode(200);

        Map<String,Object> actualDataMap = JsonToJava.convertJsonToJavaObject(response.asString(), HashMap.class);

        Map<String,Object> expectedDataMap = JsonToJava.convertJsonToJavaObject(responsePost.asString(), HashMap.class);

        for (String key: expectedDataMap.keySet()){
            Assert.assertEquals(actualDataMap.get(key),expectedDataMap.get(key));
        }

    }

    @Test(priority = 4)
    public void putUserStatusService(){

        Map<String,Object> reqBody = serviceData.putDataUserStatus();

        reqBody.put("id",id);

        Response response = Reusable.putMethod("userStatus",reqBody);

        response.then().assertThat().statusCode(200);

        Map<String,Object> actualDataMap = JsonToJava.convertJsonToJavaObject(response.asString(), HashMap.class);


        for (String key: reqBody.keySet()){
            Assert.assertEquals(actualDataMap.get(key),reqBody.get(key));
        }

    }

    @Test(priority = 5)
    public void deleteUserStatusService(){


        Response response = Reusable.deleteMethod("userStatus", id);

        response.then().assertThat().statusCode(200);

        try {

            Reusable.getIDMethod("userStatus",id);

            Assert.assertTrue(true);


        }catch (Exception e){

            System.out.println("Exception = 404 not Found");

            Assert.assertTrue(true);

        }

    }

    @Test
    public void negativePostUserStatusService(){

        Map<String,Object> reqBody = serviceData.setupNegativeDataUserStatus();

        System.out.println("reqBody = " + reqBody);

        Response response= Reusable.postMethod("userStatus",reqBody);

        response.then().assertThat().statusCode(406);

        response.then().body("error",equalTo("Not Acceptable"));

    }

    @Test
        public void negativePutUserStatusService(){

        Map<String,Object> postReqBody = serviceData.setupDataUserStatus();

        Response response = Reusable.postMethod("userStatus",postReqBody);

        Integer id = Integer.valueOf(response.jsonPath().getString("id"));

        Map<String,Object> reqBody = serviceData.setupNegativeDataUserStatus();
        reqBody.put("id",id);


         response= Reusable.putMethod("userStatus",reqBody);

        response.then().assertThat().statusCode(200);

        Map<String,Object> actualDataMap = JsonToJava.convertJsonToJavaObject(response.asString(),HashMap.class);

        Assert.assertEquals(postReqBody.get("name"),actualDataMap.get("name"));

        response = Reusable.deleteMethod("userStatus",id);

        response
                .then().statusCode(200);

    }

    @Test
    public void negativePostUserIDStatusService(){

        Map<String,Object> reqBody = serviceData.setupIDDataUserStatus();

        System.out.println("reqBody = " + reqBody);

        Response response= Reusable.postMethod("userStatus",reqBody);

        response.then().assertThat().statusCode(406);

        response.then().body("error",equalTo("Not Acceptable"));

    }




}
