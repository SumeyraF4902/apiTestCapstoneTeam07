package com.tests.allure;

import io.restassured.http.ContentType;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojoDatas.OrganizationStatusServicePojo;
import testData.OrganizationStatusServiceData;
import utilities.ConfigReader;
import utilities.Reusable;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.AssertJUnit.assertEquals;

public class OrganizationStatusServiceTest {

    Response response;

    OrganizationStatusServiceData obj = new OrganizationStatusServiceData();


    @org.testng.annotations.Test
    public void get() {



        Response response = Reusable.getMethod("organizationStatusURL");

        response.then().assertThat().statusCode(200);
    }

    @Test
    public void GetID() {

        /*
        {
        "id": 4,
        "name": "team07",
        "description": "training"
    }
         */

        response = Reusable.getMethod("organizationStatusIdURL");


        response.
                then().
                assertThat().
                body("id", Matchers.equalTo(4),
                        "name",Matchers.equalTo("team07"),
                        "description",Matchers.equalTo("trainingg"));

    }

    @Test
    public void post(){

       response = Reusable.postMethod("organizationStatusURL",
                new OrganizationStatusServicePojo(null,"team07Post","create"));

    }

    @Test
    public void put(){
        response = Reusable.putMethod("organizationStatusURL",
                new OrganizationStatusServicePojo(7,"team07FB","createPut"));
        response.then().assertThat().body("id", Matchers.equalTo(6),
                "name",Matchers.equalTo("team07FB"),"description",Matchers.equalTo("createPut"));

    }

}