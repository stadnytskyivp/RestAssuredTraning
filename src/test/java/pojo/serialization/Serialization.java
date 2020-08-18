package pojo.serialization;

import data.Resources;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pojo.serialization.maps.AddPlace;
import pojo.serialization.maps.Location;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

        List<String> myList = new ArrayList<String>();
        myList.add("something1");
        myList.add("something2");

        Location myLocation = new Location();
        myLocation.setLat(49.815392);
        myLocation.setLng(23.972449);

        AddPlace newPlace = new AddPlace();
        newPlace.setAccuracy(50);
        newPlace.setAddress("29, side layout, cohen 09");
        newPlace.setLanguage("Ukrainian");
        newPlace.setPhone_number("(+91) 983 893 3937");
        newPlace.setWebsite("http://google.com");
        newPlace.setName("Dexter laboratory");
        newPlace.setTypes(myList);
        newPlace.setLocation(myLocation);


        Response response = given()
                .log()
                .all()
                .queryParam("key", properties.getProperty("KEY"))
                .body(newPlace)
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
        System.out.println("\n"+strResponse);

    }


}
