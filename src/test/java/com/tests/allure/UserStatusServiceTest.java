package com.tests.allure;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Login;
import pages.LoginCustomer;
import testData.UserStatusServiceData;
import utilities.JsonToJava;
import utilities.Reusable;
import java.util.HashMap;
import java.util.Map;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class UserStatusServiceTest extends Login{


    UserStatusServiceData serviceData = new UserStatusServiceData();



    public static String url = "userStatus";

    Faker faker = new Faker();

    @Test
    public void getUserStatusService(){



        Response response = Reusable.getMethod(url);

        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("id", hasItems(1, 2, 3, 4, 5, 6, 7, 8),
                        "name",
                        hasItems("Active", "Deactivated", "Locked Out", "Pending User Action", "Suspended"));

    }



    @Test
    public void postUserStatusService(){

        Map<String,Object> reqBody = serviceData.setupDataUserStatus(faker.name().fullName(),faker.howIMetYourMother().character());

       Response responsePost = Reusable.postMethod(url,reqBody);

        responsePost.then().assertThat().statusCode(201);

        Map<String,Object> actualDataMap = JsonToJava.convertJsonToJavaObject(responsePost.asString(), HashMap.class);

       Integer id = Integer.valueOf(responsePost.jsonPath().getString("id"));

        reqBody.put("id",id);

        for (String key: reqBody.keySet()){
            Assert.assertEquals(actualDataMap.get(key),reqBody.get(key));
        }

        responsePost = Reusable.getIDMethod(url,id);
        responsePost.then().assertThat().statusCode(200);

        responsePost = Reusable.deleteMethod(url,id);
        responsePost.then().assertThat().statusCode(200);


    }


    @Test
    public void getIDUserStatusService(){

        Map<String,Object> reqBody =
                serviceData.setupDataUserStatus(faker.name().fullName(),faker.howIMetYourMother().highFive());

        Response response = Reusable.postMethod(url,reqBody);

        Integer id = Integer.valueOf(response.jsonPath().getString("id"));

        reqBody.put("id",id);

        response = Reusable.getIDMethod(url, id);

        response.then().assertThat().statusCode(200);

        Map<String,Object> actualDataMap = JsonToJava.convertJsonToJavaObject(response.asString(), HashMap.class);


        for (String key: reqBody.keySet()){
            Assert.assertEquals(actualDataMap.get(key),reqBody.get(key));
        }

    }

    @Test
    public void putUserStatusService(){

        Map<String,Object> reqBody =
                serviceData.setupDataUserStatus(faker.name().fullName(),faker.howIMetYourMother().highFive());

        Response response = Reusable.postMethod(url,reqBody);

        Integer id = Integer.valueOf(response.jsonPath().getString("id"));

        Map<String,Object> putReqBody = serviceData.setupIDDataUserStatus(id,faker.name().fullName(),faker.howIMetYourMother().character());

        response = Reusable.putMethod(url,putReqBody);

        response.then().assertThat().statusCode(200);

        Map<String,Object> actualDataMap = JsonToJava.convertJsonToJavaObject(response.asString(), HashMap.class);


        for (String key: putReqBody.keySet()){
            Assert.assertEquals(actualDataMap.get(key),putReqBody.get(key));
        }
        response = Reusable.deleteMethod(url,id);
        response.then().assertThat().statusCode(200);

    }

    @Test
    public void deleteUserStatusService(){
        Map<String,Object> reqBody =
                serviceData.setupDataUserStatus(faker.name().fullName(),faker.howIMetYourMother().highFive());

        Response response = Reusable.postMethod(url,reqBody);

        Integer id = Integer.valueOf(response.jsonPath().getString("id"));

         response = Reusable.deleteMethod(url, id);

        response.then().assertThat().statusCode(200);

        response = Reusable.getIDMethod(url,id);

        response.then().assertThat().statusCode(404);


    }

    @Test
    public void negativePostUserStatusService(){

        Map<String,Object> reqBody = serviceData.setupDataUserStatus("",faker.howIMetYourMother().catchPhrase());

        System.out.println("reqBody = " + reqBody);

        Response response= Reusable.postMethod(url,reqBody);

        response.then().assertThat().statusCode(406);
        response.prettyPrint();

        response.then().assertThat().body("error",equalTo("Not Acceptable"));

    }

    @Test
        public void negativePutUserStatusService(){

        Map<String,Object> postReqBody = serviceData.setupDataUserStatus(faker.name().fullName(),faker.howIMetYourMother().highFive());

        Response response = Reusable.postMethod(url,postReqBody);

        Integer id = Integer.valueOf(response.jsonPath().getString("id"));

        Map<String,Object> reqBody = serviceData.setupIDDataUserStatus(id,"",faker.gameOfThrones().character());

        response= Reusable.putMethod(url,reqBody);

        response.then().assertThat().statusCode(200);

        Map<String,Object> actualDataMap = JsonToJava.convertJsonToJavaObject(response.asString(),HashMap.class);

        Assert.assertEquals(postReqBody.get("name"),actualDataMap.get("name"));

        response = Reusable.deleteMethod(url,id);

        response
                .then().statusCode(200);

    }

    @Test
    public void negativePostUserIDStatusService(){

        Map<String,Object> reqBody =
                serviceData.
                setupIDDataUserStatus(faker.number().randomDigit(),
                        faker.name().title(),
                        faker.howIMetYourMother().character());

        System.out.println("reqBody = " + reqBody);

        Response response= Reusable.postMethod(url,reqBody);

        response.then().assertThat().statusCode(406);

        response.then().body("error",equalTo("Not Acceptable"));

    }
    @Test
    public void negativePostNotDescriptionUserStatusService(){

        Map<String,Object> reqBody =
                serviceData.
                        setupDataUserStatus(
                                faker.howIMetYourMother().character(),
                                faker.howIMetYourMother().character());
        reqBody.remove("description");

        System.out.println("reqBody = " + reqBody);

        Response response= Reusable.postMethod(url,reqBody);

        response.then().assertThat().statusCode(201);

       Integer id = Integer.valueOf(response.jsonPath().getString("id"));

       response = Reusable.deleteMethod(url,id);

       response.then().assertThat().statusCode(200);

    }

    @Test
    public void negativePostNameIsSpaceUserStatusService(){

        Map<String,Object> reqBody =
                serviceData.
                        setupDataUserStatus(
                                "           ",
                                faker.howIMetYourMother().character());
        reqBody.remove("description");

        System.out.println("reqBody = " + reqBody);

        Response response= Reusable.postMethod(url,reqBody);

        response.then().assertThat().statusCode(201);

        Integer id = Integer.valueOf(response.jsonPath().getString("id"));

        response = Reusable.deleteMethod(url,id);

        response.then().assertThat().statusCode(200);

    }

}
