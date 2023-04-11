package com.tests.allure;

import com.github.javafaker.Faker;
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

    String url = "userserviceGetAllUsersURL";

    @Test
    public void postRegisterNewUserAdd () {

        Map<String, Object> requestBody = userService.
                userPostRegisterexpectedDataSetUp(faker.internet().emailAddress(),187,2,5);
        System.out.println("requestBody = " + requestBody);

        Response response = Reusable.postMethod("userserviceRegisterUser",requestBody);
        response.then().assertThat().statusCode(201);
        Map<String,Object> actualBody= JsonToJava.convertJsonToJavaObject(response.asString(), HashMap.class);
        System.out.println("actualBody = " + actualBody);

        Assert.assertEquals(requestBody.get("email"),actualBody.get("email"));

        Integer id = (Integer) actualBody.get("id");
        response = Reusable.getIDMethod(url,id);

        response.then().assertThat().statusCode(200);

        response = Reusable.deleteMethod(url,id);

        response.then().assertThat().statusCode(200);

        response = Reusable.getIDMethod(url,id);

        response.then().assertThat().statusCode(404);

    }
    @Test
    public void postRegisterNewUserAddNegativeOne () {

        Map<String, Object> requestBody = userService.
                userPostRegisterexpectedDataSetUp(faker.funnyName().name(),187,2,5);
        System.out.println("requestBody = " + requestBody);

        Response response = Reusable.postMethod("userserviceRegisterUser",requestBody);
        response.then().assertThat().statusCode(201);
        Map<String,Object> actualBody= JsonToJava.convertJsonToJavaObject(response.asString(), HashMap.class);
        System.out.println("actualBody = " + actualBody);

        Assert.assertEquals(requestBody.get("email"),actualBody.get("email"));

        Integer id = (Integer) actualBody.get("id");
        response = Reusable.getIDMethod(url,id);

        response.then().assertThat().statusCode(200);

        response = Reusable.deleteMethod(url,id);

        response.then().assertThat().statusCode(200);

        response = Reusable.getIDMethod(url,id);

        response.then().assertThat().statusCode(404);

    }


    @Test
    public void gettApiUser () {

        Response response = Reusable.getMethod("userserviceGetAllUsersURL");
        response.then().
                assertThat().
                statusCode(200);
        List<HashMap<String, Object>> allUsers = response.jsonPath().getList("id");

        Assert.assertTrue(allUsers.contains(332));



    }


    @Test
    public void gettUserId () {

        Response response = Reusable.getIDMethod("userserviceGetAllUsersURL","434");
        response.then().
                assertThat().
                statusCode(200).body("email",equalTo("bostanebubekir@gmail.com"));




        response.prettyPrint();


    }



    @Test
    public void putUserEmailNotChanged () {

        Map<String, Object> requestBody = userService.
                userPostRegisterexpectedDataSetUp(faker.howIMetYourMother().character(), 187, 2, 5);
        System.out.println("requestBody = " + requestBody);

        Response response = Reusable.postMethod("userserviceRegisterUser", requestBody);
        response.then().assertThat().statusCode(201);

        Integer id = Integer.valueOf(response.jsonPath().getString("id"));

        Map<String, Object> requestPutBody = userService.
                userPostexpectedDataSetUp(faker.howIMetYourMother().character(), faker.internet().emailAddress());

        response = Reusable.putMethod(url, requestPutBody);

        response.then().assertThat().statusCode(406);

        response.then().body("error",equalTo("Not Acceptable"));

        response = Reusable.deleteMethod(url,id);

        response.then().assertThat().statusCode(200);

    }

    @Test
    public void putUserNamenotChanged () {

        String email = faker.internet().emailAddress();

        Map<String, Object> requestBody = userService.
                userPostRegisterexpectedDataSetUp(email, 187, 2, 5);
        System.out.println("requestBody = " + requestBody);

        Response response = Reusable.postMethod("userserviceRegisterUser", requestBody);
        response.then().assertThat().statusCode(201);

        Integer id = Integer.valueOf(response.jsonPath().getString("id"));

        Map<String, Object> requestPutBody = userService.
                userPostexpectedDataSetUp(faker.name().username(), email);

        response = Reusable.putMethod(url, requestPutBody);

        response.then().assertThat().statusCode(406);

        response.then().body("error",equalTo("Not Acceptable"));

        response = Reusable.deleteMethod(url,id);

        response.then().assertThat().statusCode(200);
    }

    @Test
    public void deleteUser () {

        Map<String, Object> requestBody = userService.
                userPostRegisterexpectedDataSetUp(faker.internet().emailAddress(),187,2,5);
        System.out.println("requestBody = " + requestBody);

        Response response = Reusable.postMethod("userserviceRegisterUser",requestBody);
        response.then().assertThat().statusCode(201);

        Integer id = Integer.valueOf(response.jsonPath().getString("id"));

        response = Reusable.getIDMethod(url,id);

        response.then().assertThat().statusCode(200);

        response = Reusable.deleteMethod(url,id);

        response.then().assertThat().statusCode(200);

        response = Reusable.getIDMethod(url,id);

        response.then().assertThat().statusCode(404);

    }

    @Test
    public void postRegisterNewUserAddNegativeTwo () {

        Map<String, Object> requestBody = userService.
                userPostRegisterexpectedDataSetUp("          ",187,2,5);
        System.out.println("requestBody = " + requestBody);

        Response response = Reusable.postMethod("userserviceRegisterUser",requestBody);
        response.then().assertThat().statusCode(201);
        Map<String,Object> actualBody= JsonToJava.convertJsonToJavaObject(response.asString(), HashMap.class);
        System.out.println("actualBody = " + actualBody);

        Assert.assertEquals(requestBody.get("email"),actualBody.get("email"));

        Integer id = (Integer) actualBody.get("id");
        response = Reusable.getIDMethod(url,id);

        response.then().assertThat().statusCode(200);

        response = Reusable.deleteMethod(url,id);

        response.then().assertThat().statusCode(200);

        response = Reusable.getIDMethod(url,id);

        response.then().assertThat().statusCode(404);

    }

}
