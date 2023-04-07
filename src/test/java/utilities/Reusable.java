package utilities;



import io.restassured.http.ContentType;

import io.restassured.response.Response;
import pages.Login;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class Reusable {



    public static Response getMethod(String url,String token){



        Response response=given().
                accept(ContentType.JSON).
                when().
                auth().preemptive().oauth2(token).
                get(ConfigReader.getProperty(url)).
                then().
                extract().
                response();
        response.prettyPrint();

        return response;

    }

    public static Response putMethod(String url, Object expectedBody,String token){



        Response response=given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                when().
                auth().preemptive().oauth2(token).
                body(expectedBody).
                put(ConfigReader.getProperty(url)).
                then().
                extract().
                response();;
        response.prettyPrint();

        return response;

    }

    public static Response postMethod(String url, Object expectedBody, String token){



        Response response=given().
                accept(ContentType.JSON).
                when().
                auth().preemptive().oauth2(token).
                contentType(ContentType.JSON).
                body(expectedBody).
                post(ConfigReader.getProperty(url)).
                then().
                extract().
                response();

        response.prettyPrint();

        return response;

    }

    public static Response deleteMethod(String url, int id, String token){



        Response response=given().
                accept(ContentType.JSON).
                when().
                auth().preemptive().oauth2(token).
                delete(ConfigReader.getProperty(url)+"/"+id).
                then().
                extract().
                response();


        return response;

    }
    public static Response getIDMethod(String url,String token,String id){



        Response response=given().
                accept(ContentType.JSON).
                when().
                auth().preemptive().oauth2(token).
                get(ConfigReader.getProperty(url)+"/"+id).
                then().
                extract().
                response();
        response.prettyPrint();

        return response;

    }



}
