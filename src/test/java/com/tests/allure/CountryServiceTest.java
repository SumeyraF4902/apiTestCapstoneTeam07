package com.tests.allure;


import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.Login;
import testData.CountryServiceData;
import utilities.JsonToJava;
import utilities.Reusable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CountryServiceTest extends Login{



    @Test(
            groups = {"regression"}
    )
    public void getAllCountry(){



        Response response = Reusable.getMethod("countryURL");

        response.then().assertThat().statusCode(200);

        List<HashMap<String,String>> countryList = response.jsonPath().getList("name");

        int countrySayisi = 250;

        Assert.assertEquals(countryList.size(),countrySayisi);

    }

    @Test(
            groups = {"regression"}
    )
    public void getIDCountry(){


        Response response = Reusable.getMethod("germanyURL");

        response.then().assertThat().statusCode(200);

        Map<String,String> actualDataMap= JsonToJava.convertJsonToJavaObject(response.asString(), HashMap.class);

        CountryServiceData expectedData = new CountryServiceData();

        for (String key:actualDataMap.keySet()){

            Assert.assertEquals(actualDataMap.get(key),expectedData.setupDataIdCountry().get(key));
        }


    }

    @Test(
            groups = {"regression"}
    )
    public void getNonIDCountry(){

       Response response =  Reusable.getMethod("nonCountryURL");

       response.then().assertThat().statusCode(404);


    }

}
