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

public class CreateIssue {

    Properties properties = new Properties();

    @BeforeTest
    public void getData() throws IOException {

        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") +
                "\\src\\main\\resources\\env.properties");
        properties.load(fis);

    }

    @Test
    public void createJiraIssue() throws IOException {

        RestAssured.baseURI = properties.getProperty("HOST_JIRA");

        Response response = given()
                .header("Content-Type", "application/json")
                .header("Cookie", "JSESSIONID=" + NewJiraSession.getSessionKey())
                .body(PayLoad.postCreateJiraIssue())
                .log().all()
                .when()
                .post(Resources.jiraPostCreateIssue())
                .then()
                .log().all()
                .statusCode(201)
                .extract()
                .response();

        JsonPath jsonPath = ReusableMethods.rawToJSON(response);
        String issueId = jsonPath.get("id");
        System.out.println("new ID = "+issueId);

    }

    public String createJiraIssue(String sessionKey) throws IOException {

        getData();
        RestAssured.baseURI = properties.getProperty("HOST_JIRA");

        System.out.println("base uri = "+RestAssured.baseURI);

        Response response = given()
                .header("Content-Type", "application/json")
                .header("Cookie", "JSESSIONID=" + sessionKey)
                .body(PayLoad.postCreateJiraIssue())
                .log().all()
                .when()
                .post(Resources.jiraPostCreateIssue())
                .then()
                .log().all()
                .statusCode(201)
                .extract()
                .response();

        JsonPath jsonPath = ReusableMethods.rawToJSON(response);
        String issueId = jsonPath.get("id");
        System.out.println("new ID = "+issueId);

        return issueId;

    }

}
