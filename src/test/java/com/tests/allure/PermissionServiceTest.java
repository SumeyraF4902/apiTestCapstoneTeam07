package com.tests.allure;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pages.Login;
import testData.PermissionServiceData;
import utilities.JsonToJava;
import utilities.Reusable;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static org.testng.AssertJUnit.assertEquals;

public class PermissionServiceTest  extends Login {
static  int id;
    @Test
    public void postPermession(){
    /*{

         "id": 719,
        "resource": "book",
            "action": "deneme24, write etc.",
            "app_id": 2
    }*/


        PermissionServiceData permissionServiceData=new PermissionServiceData();
        HashMap<String,Object>permessionExpectReq=permissionServiceData.expecdetData(null,"TAKAS",
                "CAN.ALİ, write etc.",2);
        System.out.println(permessionExpectReq);
      Response response= Reusable.postMethod("permissionPostURL",permessionExpectReq);
      response.then().assertThat().statusCode(201).contentType(ContentType.JSON);



/*Map<String,Object> actualData= JsonToJava.convertJsonToJavaObject(response.asString(), HashMap.class);
        assertEquals(permessionExpectReq.get("resource"),actualData.get("book"));
        assertEquals(permessionExpectReq.get("action"),actualData.get("deneme24, write etc."));
        assertEquals(permessionExpectReq.get("app_id"),actualData.get(2));*/


        response.then().assertThat().statusCode(201).contentType(ContentType.JSON).
                body("resource",equalTo("TAKAS"),"action",equalTo("CAN.ALİ, write etc."));
JsonPath jsonPath = response.jsonPath();

// Get the teams object from the response body
        Map<String, ?> teams = jsonPath.getMap("");


// Get the team01Id
        id = (int) teams.get("id");

//response=Reusable.getIDMethod("permissionPostURL");
        System.out.println("creat sonrası get"+response);
        response.prettyPrint();
    }
@Test
        public void putPermessions(){
    PermissionServiceData permissionServiceData=new PermissionServiceData();
    HashMap<String,Object>permessionExpectReq=permissionServiceData.expecdetData(729,"book",
            "C.F.T.24, write etc.",2);
    System.out.println(permessionExpectReq);
    Response response= Reusable.putMethod("permissionPostURL",permessionExpectReq);
    response.then().assertThat().statusCode(200).contentType(ContentType.JSON);


}
    @Test
    public void deletePermessions(){
        Response response= Reusable.deleteMethod("permissionDeleteURL", id);
        response.then().assertThat().statusCode(200);

/*JsonPath jsonPath = response.jsonPath();

// Get the teams object from the response body
        Map<String, ?> teams = jsonPath.getMap("");


// Get the team01Id
        team_id = (int) teams.get("id");*/

}
}
