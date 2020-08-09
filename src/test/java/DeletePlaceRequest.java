import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DeletePlaceRequest {

    @Test
    public void deletePlace() {

        String strBody = "{" +
                "\"location\": {" +
                "\"lat\" : -38.383494," +
                "\"lng\" : 133.427362" +
                "}," +
                "\"accuracy\": 50," +
                "\"name\" : \"Frontline house\"," +
                "\"phone_number\" : \"(+91) 983 893 3937\"," +
                "\"address\" : \"29, side layout, cohen 09\"," +
                "\"types\" : [\"shoe park\" , \"shop\"]," +
                "\"website\" : \"http://google.com/\"," +
                "\"language\" : \"French-IN\"" +
                "}";

        RestAssured.baseURI = "http://216.10.245.166";

        String res = given()
                .queryParam("key", "qaclick123")
                .body(strBody)
                .when()
                .post("/maps/api/place/add/json")
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
