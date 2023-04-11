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

public class PermissionServiceTest extends Login {
    @Test(
            groups = {"smoke,regression"}
    )
    public void postPermession() {


        Response response = Reusable.getMethod("permissionURL");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);

        PermissionServiceData permissionServiceData = new PermissionServiceData();
        HashMap<String, Object> permessionExpectReq = permissionServiceData.expecdetData(null, "Güzel",
                "Günler Göreceğiz, Çocuklar ;)", 2);

        response = Reusable.postMethod("permissionURL", permessionExpectReq);


        Map<String, Object> actualData = JsonToJava.convertJsonToJavaObject(response.asString(), HashMap.class);
        assertEquals(permessionExpectReq.get("resource"), actualData.get("resource"));
        assertEquals(permessionExpectReq.get("action"), actualData.get("action"));
        assertEquals(permessionExpectReq.get("app_id"), actualData.get("app_id"));

        int id = (int) actualData.get("id");

        response = Reusable.getIDMethod("permissionURLId", id);
        System.out.println("creat sonrası get" + response);
        response.prettyPrint();

        response = Reusable.deleteMethod("permissionURLId", id);
        response.then().assertThat().statusCode(200);

    }

    @Test(
            groups = {"regression"}
    )
    public void putPermessions() {

        PermissionServiceData permissionServiceData = new PermissionServiceData();
        HashMap<String, Object> permissionsPostReq = permissionServiceData.expecdetData(null, "YILDIZ", "GÜNEŞ,AY", 2);

        Response response = Reusable.postMethod("permissionURL", permissionsPostReq);
        response.then().assertThat().statusCode(201);

        Map<String, Object> actualData = JsonToJava.convertJsonToJavaObject(response.asString(), HashMap.class);
        assertEquals(permissionsPostReq.get("resource"), actualData.get("resource"));
        int id = (int) actualData.get("id");
        HashMap<String, Object> permessionPutReq = permissionServiceData.expecdetData(id, "İZİN",
                "C.F.T.24, write etc.", 2);
        response = Reusable.putMethod("permissionURL", permessionPutReq);
        response.then().assertThat().statusCode(200).contentType(ContentType.JSON);

        response = Reusable.getIDMethod("permissionURLId", id);
        response.prettyPrint();

        response = Reusable.deleteMethod("permissionURLId", id);
        response.then().assertThat().statusCode(200);


    }

    @Test(
            groups = {"smoke,regression"}
    )
    public void deletePermessions() {


        PermissionServiceData permissionServiceData = new PermissionServiceData();
        HashMap<String, Object> permessionExpectReq = permissionServiceData.expecdetData(null, "Güzel",
                "Günler Göreceğiz, Çocuklar ;)", 2);

        Response response = Reusable.postMethod("permissionURL", permessionExpectReq);


        Map<String, Object> actualData = JsonToJava.convertJsonToJavaObject(response.asString(), HashMap.class);
        assertEquals(permessionExpectReq.get("resource"), actualData.get("resource"));

        int id = (int) actualData.get("id");
        response = Reusable.deleteMethod("permissionURLId", id);
        response.then().assertThat().statusCode(200);


    }
}
