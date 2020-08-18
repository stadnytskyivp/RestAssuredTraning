package pojo.serialization;

import data.PayLoad;
import data.Resources;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Serialization {

    Properties properties = new Properties();

    @BeforeTest
    public void getData() throws IOException {

        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") +
                "\\src\\main\\resources\\env.properties");
        properties.load(fis);
    }

    @Test
    public void serializationTest() {
        RestAssured.baseURI = properties.getProperty("HOST");

        Response response = given()
                .queryParam("key", properties.getProperty("KEY"))
                .body(PayLoad.getPostDataPlaceAPI())
                .when()
                .post(Resources.placePostData())
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body("status", equalTo("OK"))
                .extract()
                .response();

//        JsonPath jsonPath = ReusableMethods.rawToJSON(response);

        String strResponse = response.asString();
        System.out.println(strResponse);

    }


}
