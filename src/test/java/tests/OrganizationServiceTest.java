package tests;

import com.github.fge.jsonschema.core.processing.ProcessorSelectorPredicate;
import com.github.fge.jsonschema.core.report.MessageProvider;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import utilities.ConfigReader;
import utilities.Reusable;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;
import static utilities.Reusable.getMethod;

public class OrganizationServiceTest {

    Response response;

    @Test
    public void getOrganization(){

    response = Reusable.getMethod(ConfigReader.getProperty("organizationURL"));

    response.then().assertThat().statusCode(404);

        String responseBodyStr = response.asString();

        System.out.println("Response Body : " + responseBodyStr);

        assertTrue(responseBodyStr.contains("Not Found"));


    }

    @Test
    public void GetOrganizationId(){

        response=Reusable.getMethod(ConfigReader.getProperty("organizationURL{id}"));

        String responseBodyStr = response.asString();

        System.out.println("Response Body : " + responseBodyStr);

        JsonPath jsonPath = response.jsonPath();

        assertEquals("Testorix",jsonPath.getString("name"));
        assertEquals(153,jsonPath.getInt("founder_id"));



    }






}
