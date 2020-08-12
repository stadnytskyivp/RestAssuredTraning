package jiraAPI;

import data.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class Issue {

    Properties properties = new Properties();

    @BeforeTest
    public void getData() throws IOException {

        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") +
                "\\src\\main\\resources\\env.properties");
        properties.load(fis);

    }

    @Test
    public void jiraIssues(){

        RestAssured.baseURI = properties.getProperty("HOST_JIRA");

        Response response = given()
                .header("Content-Type", "application/json")
                .body(PayLoad.postStartJiraSession())
                .when()
                .post(Resources.jiraPostSession())
                .then()
                .statusCode(200)
                .extract()
                .response();

        JsonPath jsonPath = ReusableMethods.rawToJSON(response);
        String session = jsonPath.get("session.value");
        System.out.println(session);

    }

}
