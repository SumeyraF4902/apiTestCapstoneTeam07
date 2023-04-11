package com.tests.allure;


import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Login;
import testData.MembershipTypeServiceData;
import utilities.JsonToJava;
import utilities.Reusable;
import java.util.HashMap;
import java.util.Map;


public class MembershipTypeServiceTest extends Login {


    Faker faker = new Faker();
    String url = "getAllMembershipType";

    MembershipTypeServiceData memberTypService = new MembershipTypeServiceData();

    @Test(
            groups = {"regression"}
    )
    public void GET_TC001_MembershipTypeAppID(){

        Response response = Reusable.getMethod("getMembershipType");

        response.prettyPrint();

        response.then().assertThat().statusCode(200);

        System.out.println("response.getStatusCode() = " + response.getStatusCode());

    }


    @Test(
            groups = {"regression"}
    )
    public void GET_TC002_MembershipType(){

        Response response = Reusable.getMethod(url);

        response.prettyPrint();

        response.then().assertThat().statusCode(200);

        System.out.println("response.getStatusCode() = " + response.getStatusCode());


    }


    @Test(
            groups = {"smoke,regression"}
    )
    public void POST_TC003_AddNewUser(){


        Map<String,Object> reqBody =
                memberTypService.setupDataMemberShipTypeService(faker.howIMetYourMother().character(),true,2);

        Response response = Reusable.postMethod(url,reqBody);

        response.then().assertThat().statusCode(201);


        HashMap<String,Object> actualData= JsonToJava.convertJsonToJavaObject(response.asString(), HashMap.class);

        Assert.assertEquals(reqBody.get("name"),actualData.get("name"));

        Integer id = (Integer) actualData.get("id");

        response = Reusable.deleteMethod(url,id);

        response.then().assertThat().statusCode(200);

    }

    @Test(
            groups = {"regression"}
    )
    public void PUT_TC004_UpdateExistingUser() {

        Map<String,Object> reqBody =
                memberTypService.setupDataMemberShipTypeService(faker.howIMetYourMother().character(),true,2);

        Response response = Reusable.postMethod(url,reqBody);

        response.then().assertThat().statusCode(201);


        HashMap<String,Object> actualData= JsonToJava.convertJsonToJavaObject(response.asString(), HashMap.class);


        Integer id = (Integer) actualData.get("id");

        Map<String,Object> reqPutBody =
                memberTypService.setupDataIDMemberShipTypeService(id,faker.howIMetYourMother().character(),true,2);

        response = Reusable.putMethod(url,reqPutBody);

        response.then().assertThat().statusCode(200);

        HashMap<String,Object> actualIDData= JsonToJava.convertJsonToJavaObject(response.asString(), HashMap.class);



        Assert.assertEquals(reqPutBody.get("name"),actualIDData.get("name"));


        response.prettyPrint();

        response = Reusable.deleteMethod(url,id);

        response.then().assertThat().statusCode(200);

    }
    @Test(
            groups = {"regression"}
    )
    public void GET_TC005_MembershipTypeID(){
        Map<String,Object> reqBody =
                memberTypService.setupDataMemberShipTypeService(faker.howIMetYourMother().character(),true,2);

        Response response = Reusable.postMethod(url,reqBody);

        response.then().assertThat().statusCode(201);


        HashMap<String,Object> actualData= JsonToJava.convertJsonToJavaObject(response.asString(), HashMap.class);

        Assert.assertEquals(reqBody.get("name"),actualData.get("name"));

        Integer id = (Integer) actualData.get("id");

        response = Reusable.getIDMethod(url,id);

        response.then().assertThat().statusCode(200);

        HashMap<String,Object> actualIDData= JsonToJava.convertJsonToJavaObject(response.asString(), HashMap.class);

        for (String key : actualData.keySet()){

            Assert.assertEquals(actualData.get(key),actualIDData.get(key));
        }

        response.prettyPrint();

        response = Reusable.deleteMethod(url,id);

        response.then().assertThat().statusCode(200);
    }

    @Test(
            groups = {"regression"}
    )
    public void GET_TC006_MembershipTypeDelete(){
        Map<String,Object> reqBody =
                memberTypService.setupDataMemberShipTypeService(faker.howIMetYourMother().character(),true,2);

        Response response = Reusable.postMethod(url,reqBody);

        response.then().assertThat().statusCode(201);


        HashMap<String,Object> actualData= JsonToJava.convertJsonToJavaObject(response.asString(), HashMap.class);

        Integer id = (Integer) actualData.get("id");

        response = Reusable.deleteMethod(url,id);

        response.then().assertThat().statusCode(200);

        response = Reusable.getIDMethod(url,id);

        response.then().assertThat().statusCode(404);

    }






}
