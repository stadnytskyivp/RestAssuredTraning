import data.PayLoad;
import data.Resources;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PostRequest {

    Properties properties = new Properties();

    @BeforeTest
    public void getData() throws IOException {

        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") +
                "\\src\\test\\resources\\env.properties");
        properties.load(fis);

    }

    @Test
    public void postData() {

        RestAssured.baseURI = properties.getProperty("HOST");

        given()
                .queryParam("key", properties.getProperty("KEY"))
                .body(PayLoad.getPostData())
                .when()
                .post(Resources.placePostData())
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body("status", equalTo("OK"));
    }

}
