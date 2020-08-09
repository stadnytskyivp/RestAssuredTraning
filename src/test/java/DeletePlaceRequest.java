import data.Resources;
import data.PayLoad;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DeletePlaceRequest {

    Properties properties = new Properties();

    @BeforeTest
    public void getData() throws IOException {

        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") +
                "\\src\\test\\resources\\env.properties");
        properties.load(fis);

    }

    @Test
    public void deletePlace() {

        RestAssured.baseURI = properties.getProperty("HOST");
        System.out.println(properties.getProperty("HOST"));

        String res = given()
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
                .body("status", equalTo("OK"))
                .extract()
                .response()
                .asString();

        System.out.println(res);

        JsonPath js = new JsonPath(res);
        String placeId = js.get("place_id");
        System.out.println(placeId);

        String bodyPlaceId = "{" +
                "\"place_id\": \"" + placeId + "\"" +
                "}";

        given()
                .queryParam("key", "qaclick123")
                .body(bodyPlaceId)
                .when()
                .post("/maps/api/place/add/json")
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON);
//                .and()
//                .body("status", equalTo("OK"));       // server don't response to the JSON delete request

    }

}
