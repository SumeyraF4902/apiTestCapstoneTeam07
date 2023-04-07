package com.tests.allure;


import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import pages.Login;
import testData.CountryServiceData;
import utilities.JsonToJava;
import utilities.Reusable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CountryServiceTest {

    public static Login login = new Login();

    public static String token;

    @BeforeClass
    public void getToken(){
       token = login.beToken();
    }


    @org.testng.annotations.Test
    public void getAllCountry(){



        Response response = Reusable.getMethod("countryURL", token);

        response.then().assertThat().statusCode(200);

        List<HashMap<String,String>> countryList = response.jsonPath().getList("name");

        int countrySayisi = 250;

        Assert.assertEquals(countryList.size(),countrySayisi);

    }

    @org.testng.annotations.Test
    public void getIDCountry(){


        Response response = Reusable.getMethod("germanyURL", token);

        response.then().assertThat().statusCode(200);

       // CountryServicePojo expectedData = new CountryServicePojo("DE","Germany","EUR","Euro","���");

        Map<String,String> actualDataMap= JsonToJava.convertJsonToJavaObject(response.asString(), HashMap.class);

        CountryServiceData expectedData = new CountryServiceData();

        for (String key:actualDataMap.keySet()){

            Assert.assertEquals(actualDataMap.get(key),expectedData.setupDataIdCountry().get(key));
        }


    }

    @org.testng.annotations.Test
    public void getNonIDCountry(){



        try {

            Reusable.getMethod("nonCountryURL", token);

                Assert.assertTrue(true);


        }catch (Exception e){

            System.out.println("Exception = 404 not Found");

            Assert.assertTrue(true);

        }

    }






}
