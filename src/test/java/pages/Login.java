package pages;

import io.restassured.path.json.JsonPath;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login {

    private  WebDriver driver;
    private String token;

    private String username = "bo@testorix.com";

    private String password = "6121.Bkr";

    public String getToken() {
        return token;
    }

    public Login(WebDriver driver) {
        this.driver = driver;
    }

    public void beToken(){

        driver.get("https://qa-gm3.quaspareparts.com/oauth2/authorization/a3m-client");
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.tagName("button")).click();
        driver.navigate().to("https://qa-gm3.quaspareparts.com/auth/userinfo");
        JsonPath path = new JsonPath(driver.findElement(By.tagName("body")).getText());
         token = path.getString("access_token");
        System.out.println("Token: " + token);
        driver.quit();


    }





}
