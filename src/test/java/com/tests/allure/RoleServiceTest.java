package com.tests.allure;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.maven.lifecycle.internal.LifecycleStarter;
import org.openqa.selenium.bidi.log.Log;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Login;
import pojoDatas.RoleServicePojo;
import utilities.Reusable;

import java.awt.*;
import java.util.Map;

import static org.hamcrest.Matchers.*;


public class RoleServiceTest extends Login {



    @Test

    public void getRolesList() {

        Response response = Reusable.getMethod("rolesURL");

        response.then().assertThat().statusCode(200).contentType(ContentType.JSON).
                body("id", hasItems(17, 23, 4), "name", hasItems("A3M_ADMIN", "Accountant", "APP_DOMAIN_MANAGER",
                        "Customer", "Guest", "Logistics Manager", "Logistics Personnel", "Purchase Manager",
                        "Purchase Personnel", "Quality Controller", "Quality Manager", "Sales Manager", "Sales Personnel",
                        "Store Manager", "Warehouse Manager", "Warehouse Personnel", "Business Owner"));


        {


        }

    }

    @Test
    public void getRolesID26() {


        Response response = Reusable.getIDMethod("rolesIdURL",26);
        response.then().assertThat().statusCode(200).contentType(ContentType.JSON).
                body("permissions.id", hasItem(498));


    }


    @Test
    public void getRolesID27() {

        Response response = Reusable.getIDMethod("rolesIdURL",27);
        response.then().assertThat().statusCode(200).contentType(ContentType.JSON).
                body("id", equalTo(27), "name", equalTo("Logistics Personnel"),
                        "permissions.id", hasItem(498), "permissions.resource", hasItems("company", "country", "dashboard", "handmade",
                                "inventory", "invoice", "order", "procurement", "purchasing", "inventory", "invoice"


                        ));


    }

    @Test
    public void getRolesID30() {

        Response response = Reusable.getIDMethod("rolesIdURL",30);
        response.then().assertThat().statusCode(200).contentType(ContentType.JSON).
                body("id", equalTo(30), "name", equalTo("Customer"));


    }

    @Test

    public void getRolesNegatif() {

        Response response = Reusable.getIDMethod("rolesIdURL",11);
        response.then().assertThat().statusCode(404);


}
}