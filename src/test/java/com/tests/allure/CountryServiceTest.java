package com.tests.allure;


import io.restassured.response.Response;
import org.testng.Assert;
import utilities.Reusable;





public class CountryServiceTest {




    @org.testng.annotations.Test
    public void get(){


        Assert.assertTrue(false);


        Response response = Reusable.getMethod("countryURL");

        response.then().assertThat().statusCode(200);





    }

}
