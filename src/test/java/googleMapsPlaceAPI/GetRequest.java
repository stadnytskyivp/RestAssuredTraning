package googleMapsPlaceAPI;

import data.Resources;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequest {

    Properties properties = new Properties();

    @BeforeTest
    public void getData() throws IOException {

        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") +
                "\\src\\main\\resources\\env.properties");
        properties.load(fis);

    }

    @Test
    public void getPlaceAPI() {
        RestAssured.baseURI = properties.getProperty("HOST_MAPS");

        given()
                .param("location", "-33.8670522,151.1957362")
                .param("radius", "500")
                .param("key", "AIzaSyBFE_zzoGme59y3KLZ436wgqX6WB0B-dUg")
                .when()
                .get(Resources.placeGetData())
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body("results[0].name", equalTo("Sydney"))
                .and()
                .body("results[0].place_id", equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM"))
                .and()
                .header("Server", "scaffolding on HTTPServer2");

    }

}

