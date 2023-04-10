package com.tests.allure;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Login;
import testData.UserServiceData;
import utilities.JsonToJava;
import utilities.Reusable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;

public class UserServiceTest extends Login {
    UserServiceData userService  = new UserServiceData();
    Faker faker = new Faker();
    @Test
    public void postNewUserAdd () {

        Map<String, Object> requestBody = userService.
                userPostexpectedDataSetUp(faker.name().username(),faker.internet().emailAddress());
        System.out.println("requestBody = " + requestBody);

        Response response = Reusable.postMethod("userserviceAddNewUserURL",requestBody);
        response.then().assertThat().statusCode(201);
        Map<String,Object> actualBody= JsonToJava.convertJsonToJavaObject(response.asString(), HashMap.class);
        System.out.println("actualBody = " + actualBody);
        Assert.assertEquals(requestBody.get("username"),actualBody.get("username"));
        Assert.assertEquals(requestBody.get("email"),actualBody.get("email"));

    }


    @Test
    public void gettApiUser () {

        Response response = Reusable.getMethod("userserviceGetAllUsersURL");
        response.then().
                assertThat().
                statusCode(200);
        List<HashMap<String, Object>> allUsers = response.jsonPath().getList("id");

        Assert.assertTrue(allUsers.contains(73));



    }


    @Test
    public void gettUserId () {

        Response response = Reusable.getIDMethod("userserviceGetAllUsersURL","73");
        response.then().
                assertThat().
                statusCode(200).body("email",equalTo("ademalabas@gmail.com"));




        response.prettyPrint();


    }










}
