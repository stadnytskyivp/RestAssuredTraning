package oauth;

import data.URIs;
import io.restassured.path.json.JsonPath;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

public class OAuthTest {

    final static String CLIENT_ID = "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com";
    final static String CLIENT_SECRET = "erZOWM9g3UtwNRj340YYaK_W";
    final static String GRANT_TYPE = "authorization_code";

    public static void main(String[] args) throws InterruptedException {

//        String driverPath = System.getProperty("user.dir") + "\\src\\main\\resources\\chromedriver.exe";
//        System.setProperty("webdriver.chrome.driver", driverPath);
//        WebDriver driver = new ChromeDriver();
//        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        driver.get(URIs.getCodeOAuth());
//
//        driver.findElement(By.id("identifierId")).sendKeys("surgoot1992@gmail.com");
//        driver.findElement(By.id("identifierId")).sendKeys(Keys.ENTER);
//        Thread.sleep(3000);
//        driver.findElement(By.name("password")).sendKeys(System.getenv("PASSWORD"));
//        driver.findElement(By.name("password")).sendKeys(Keys.ENTER);
//        Thread.sleep(4000);
//
//        String strURL = driver.getCurrentUrl();
        String strURL = "https://rahulshettyacademy.com/getCourse.php?code=4%2F3AFyi0hvnNVBDqO-zGTvPu0JWpsByfQNKZcHMWv5a4tYpRL_Sd-ts7X3gD2oWWk3ZhzwxBRbZ7g12UsCoffEaSg&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none#";

        strURL = strURL.split("code=")[1];
        String strCode = strURL.split("&scope")[0];

        String accessTokenResponse = given()
                .urlEncodingEnabled(false)
                .queryParam("code", strCode)
                .queryParam("client_id", CLIENT_ID)
                .queryParam("client_secret", CLIENT_SECRET)
                .queryParam("redirect_uri", URIs.getRedirectPathOAuth())
                .queryParam("grant_type", GRANT_TYPE)
                .when()
                .log()
                .all()
                .post("https://www.googleapis.com/oauth2/v4/token")
                .asString();

        JsonPath jsonPath = new JsonPath(accessTokenResponse);
        String accessToken = jsonPath.getString("access_token");


        String response = given()
                .queryParam("access_token", accessToken)
                .when()
                .log()
                .all()
                .get("https://rahulshettyacademy.com/getCourse.php")
                .asString();

        System.out.println(response);

    }

}
