package tests;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.Reusable;

public class OrganizationStatusService {

    @org.testng.annotations.Test
    public void get(){



        Response response = Reusable.getMethod("oganizationStatusURL");

        response.then().assertThat().statusCode(200);

    }



}
