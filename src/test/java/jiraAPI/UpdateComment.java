package jiraAPI;

import data.PayLoad;
import data.Resources;
import data.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class UpdateComment {

    Properties properties = new Properties();

    @BeforeTest
    public void getData() throws IOException {

        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") +
                "\\src\\main\\resources\\env.properties");
        properties.load(fis);

    }

    @Test
    public void updateComment() throws IOException {

        RestAssured.baseURI = properties.getProperty("HOST_JIRA");

        String sessionKey = NewJiraSession.getSessionKey();

        CreateIssue createIssue = new CreateIssue();
        AddComment addComment = new AddComment();

        Response response = given()
                .header("Content-Type", "application/json")
                .header("Cookie", "JSESSIONID=" + sessionKey)
                .body(PayLoad.reсommentJiraIssue())
                .log()
                .body()
                .when()
                .put(Resources.jiraPostUpdateComment(createIssue.createJiraIssue(sessionKey),
                                                     addComment.addComment(sessionKey)))
                .then()
                .log()
                .all()
                .statusCode(201)
                .extract()
                .response();

        JsonPath jsonPath = ReusableMethods.rawToJSON(response);
        String id = jsonPath.get("id");
        System.out.println("new ID = " + id);

    }

}
