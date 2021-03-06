package pojo.deserialization;

import data.URIs;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.deserialization.courses.API;
import pojo.deserialization.courses.Course;
import pojo.deserialization.courses.GetCourse;
import pojo.deserialization.courses.WebAutomation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static io.restassured.RestAssured.given;

public class Deserialization {

    final static String CLIENT_ID = "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com";
    final static String CLIENT_SECRET = "erZOWM9g3UtwNRj340YYaK_W";
    final static String GRANT_TYPE = "authorization_code";

    @Test
    public void getCoursesTest() {

        String[] courseTitles = {"Selenium Webdriver Java","Cypress","Protractor"};

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
        String strURL = "https://rahulshettyacademy.com/getCourse.php?code=4%2F3QGj9iDwAEVVH_igY9RZ75syUAmqmQtumRA8YlzL6ONrkWG0eUCa9maF0iJjoPwdpx8Bay1kpdeFXB1VBng5dTU&scope=email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&authuser=0&prompt=none#";

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
        List<Course> apiCourses = getCourse.getCourses().getApi();
        IntStream.range(0, apiCourses.size()).forEach(i -> {
            System.out.println(apiCourses.get(i).getCourseTitle());
            System.out.println(apiCourses.get(i).getPrice());
        });

        ArrayList<String> getList = new ArrayList<String>();

        List<Course> automationCourses = getCourse.getCourses().getWebAutomation();
        for (Course automationCourse : automationCourses) {
            getList.add(automationCourse.getCourseTitle());

        }
        List<String> expectedList = Arrays.asList(courseTitles);

        Assert.assertEquals(expectedList,getList);


    }

}
