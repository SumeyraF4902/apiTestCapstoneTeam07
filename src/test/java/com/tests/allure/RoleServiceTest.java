package com.tests.allure;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.maven.lifecycle.internal.LifecycleStarter;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojoDatas.RoleServicePojo;
import utilities.Reusable;

import java.awt.*;
import java.util.Map;

import static org.hamcrest.Matchers.*;


public class RoleServiceTest {

   /*[
    {
        "id": 17,
        "name": "A3M_ADMIN",
        "app_id": 2
    },
    {
        "id": 23,
        "name": "Accountant",
        "app_id": 2
    },
    {
        "id": 4,
        "name": "APP_DOMAIN_MANAGER",
        "app_id": 2
    },
    {
        "id": 30,
        "name": "Customer",
        "app_id": 2
    },
    {
        "id": 6,
        "name": "Guest",
        "app_id": 2
    },
    {
        "id": 26,
        "name": "Logistics Manager",
        "app_id": 2
    },
    {
        "id": 27,
        "name": "Logistics Personnel",
        "app_id": 2
    },
    {
        "id": 21,
        "name": "Purchase Manager",
        "app_id": 2
    },
    {
        "id": 22,
        "name": "Purchase Personnel",
        "app_id": 2
    },
    {
        "id": 29,
        "name": "Quality Controller",
        "app_id": 2
    },
    {
        "id": 28,
        "name": "Quality Manager",
        "app_id": 2
    },
    {
        "id": 19,
        "name": "Sales Manager",
        "app_id": 2
    },
    {
        "id": 20,
        "name": "Sales Personnel",
        "app_id": 2
    },
    {
        "id": 18,
        "name": "Store Manager",
        "app_id": 2
    },
    {
        "id": 24,
        "name": "Warehouse Manager",
        "app_id": 2
    },
    {
        "id": 25,
        "name": "Warehouse Personnel",
        "app_id": 2
    },
    {
        "id": 5,
        "name": "Business Owner",
        "app_id": 2
    }
]*/

    @Test


    public void getRolesList(){

        Response response= Reusable.getMethod("rolesURL");
        response.then().assertThat().statusCode(200).contentType(ContentType.JSON).
                body("id",hasItem(17),"name",hasItems("A3M_ADMIN","Accountant","APP_DOMAIN_MANAGER",
                        "Customer","Guest","Logistics Manager","Logistics Personnel", "Purchase Manager",
                        "Purchase Personnel","Quality Controller","Quality Manager", "Sales Manager","Sales Personnel",
                        "Store Manager","Warehouse Manager","Warehouse Personnel", "Business Owner"));



        {


        }

    }
    @Test
    public void getRolesID26(){



        Response response=Reusable.getMethod("rolesURL26");
        response.then().assertThat().statusCode(200).contentType(ContentType.JSON);

    }
}