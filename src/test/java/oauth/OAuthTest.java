package oauth;

import data.URIs;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;
import pojo.API;
import pojo.GetCourse;
import pojo.WebAutomation;

import java.util.List;

import static io.restassured.RestAssured.given;

public class OAuthTest {

    final static String CLIENT_ID = "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com";
    final static String CLIENT_SECRET = "erZOWM9g3UtwNRj340YYaK_W";
    final static String GRANT_TYPE = "authorization_code";

    @Test
    public void getCoursesTest() {

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
        String strURL = "https://rahulshettyacademy.com/getCourse.php?code=4%2F3AF3nxkCGu5CwDAR2KNbf-Nbg0Kcsicsh3bwZI5-xOxQ-QJVUD4q48OnRkOa92zHSgWQ2MZStwNwXvEnCls-Kho&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none#";

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
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();

        JsonPath jsonPath = new JsonPath(accessTokenResponse);
        String accessToken = jsonPath.getString("access_token");


        GetCourse getCourse = given()
                .queryParam("access_token", accessToken)
                .expect()
                .defaultParser(Parser.JSON)
                .when()
                .get("https://rahulshettyacademy.com/getCourse.php")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .as(GetCourse.class);

        System.out.println(getCourse.getLinkedIn());
        System.out.println(getCourse.getInstructor());
//        System.out.println(getCourse.getCourses().getApi().get(1).getCourseTitle());

        System.out.println("\nAPI courses : ");
        List<API> apiCourses = getCourse.getCourses().getApi();
        for (int i = 0; i < apiCourses.size(); i++) {
            System.out.println(apiCourses.get(i).getCourseTitle());
            System.out.println(apiCourses.get(i).getPrice());
        }

        System.out.println("Web Automation courses : ");
        List<WebAutomation> automationCourses = getCourse.getCourses().getWebAutomation();
        for (int i = 0; i < automationCourses.size(); i++) {
            System.out.println(automationCourses.get(i).getCourseTitle());
        }


    }

}
