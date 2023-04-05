package utilities;



import io.restassured.http.ContentType;

import io.restassured.response.Response;
import pages.Login;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class Reusable {

   public static Login login = new Login(Driver.getDriver());


    public static Response getMethod(String url){


        login.beToken();
        Response response=given().
                accept(ContentType.JSON).
                when().
                auth().preemptive().oauth2(login.getToken()).
                get(ConfigReader.getProperty(url)).
                then().
                extract().
                response();
        response.prettyPrint();

        return response;

    }

    public static Response putMethod(String url, Object expectedBody){


        login.beToken();
        Response response=given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                when().
                auth().preemptive().oauth2(login.getToken()).
                body(expectedBody).
                put(ConfigReader.getProperty(url)).
                then().
                extract().
                response();;
        response.prettyPrint();

        return response;

    }

    public static Response postMethod(String url, Object expectedBody){


        login.beToken();
        Response response=given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                when().
                auth().preemptive().oauth2(login.getToken()).
                body(expectedBody).
                post(ConfigReader.getProperty(url)).
                then().
                extract().
                response();

        response.prettyPrint();

        return response;

    }

    public static Response deleteMethod(String url, int id){


        login.beToken();
        Response response=given().
                accept(ContentType.JSON).
                when().
                auth().preemptive().oauth2(login.getToken()).
                delete(ConfigReader.getProperty(url)+"/"+id).
                then().
                extract().
                response();


        return response;

    }



}
