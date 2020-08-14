package oauth;

import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;

public class OAuthTest {

    final static String CLIENT_ID = "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com";
    final static String CLIENT_SECRET = "erZOWM9g3UtwNRj340YYaK_W";
    final static String REDIRECT_URI = "https://rahulshettyacademy.com/getCourse.php";
    final static String GRANT_TYPE = "authorization_code";

    public static void main(String[] args) {

        String accessTokenResponse = given()
                .queryParam("code", "")
                .queryParam("client_id", CLIENT_ID)
                .queryParam("client_secret", CLIENT_SECRET)
                .queryParam("redirect_uri", REDIRECT_URI)
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
