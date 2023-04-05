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
        RoleServicePojo roleServicePojo=new RoleServicePojo("book","deneme, write etc.",2);
        Response response= Reusable.getMethod("rolesURL");
        response.then().assertThat().statusCode(200).contentType(ContentType.JSON).
                //body("resource",equalTo("book"),"action",equalTo("deneme, write etc."));
                        body("resource",equalTo(roleServicePojo.getResource()),"action",equalTo(roleServicePojo.getAction()));



        {



        }
    }
}