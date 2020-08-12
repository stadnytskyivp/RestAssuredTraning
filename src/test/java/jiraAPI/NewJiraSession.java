package jiraAPI;

import data.PayLoad;
import data.Resources;
import data.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class NewJiraSession {


    public static String getSessionKey() throws IOException {

        Properties properties = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") +
                "\\src\\main\\resources\\env.properties");
        properties.load(fis);
        RestAssured.baseURI = properties.getProperty("HOST_JIRA");

        Response response = given()
                .header("Content-Type", "application/json")
                .body(PayLoad.postStartJiraSession())
                .when()
                .post(Resources.jiraPostStartSession())
                .then()
                .statusCode(200)
                .extract()
                .response();

        JsonPath jsonPath = ReusableMethods.rawToJSON(response);
        String session = jsonPath.get("session.value");

        return session;
    }

}
