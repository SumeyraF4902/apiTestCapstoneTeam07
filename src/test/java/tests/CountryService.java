package tests;


import io.restassured.response.Response;
import utilities.Reusable;





public class CountryService  {




    @org.testng.annotations.Test
    public void get(){



        Response response = Reusable.getMethod("countryURL");

        response.then().assertThat().statusCode(200);



    }

}
