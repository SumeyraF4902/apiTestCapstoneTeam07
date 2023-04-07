package com.tests.allure;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojoDatas.RoleServicePojo;
import utilities.Reusable;

import static org.hamcrest.Matchers.equalTo;


public class RoleServiceTest {

     /*  "resource": "book",
                "action": "deneme, write etc.",
                "app_id": 2*/

    @Test


    public void getRolesList(){

        Response response= Reusable.getMethod("rolesURL");
        response.then().assertThat().statusCode(200).contentType(ContentType.JSON);



        {



        }
    }
}