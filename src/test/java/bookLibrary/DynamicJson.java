package bookLibrary;

import dataProvider.Books;
import data.PayLoad;
import data.Resources;
import data.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class DynamicJson {

    Properties properties = new Properties();

    @BeforeTest
    public void getData() throws IOException {

        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") +
                "\\src\\main\\resources\\env.properties");
        properties.load(fis);

    }

    @Test(priority = 1, dataProvider = "BooksData", dataProviderClass = Books.class)
    public void addBook(String isbn, String aisle) throws IOException {

        String strBody = GenerateStringFromResource(
                "C:\\Eleks\\Work\\Tranings\\RestSideProject\\src\\main\\resources\\postData.xml");

        RestAssured.baseURI = properties.getProperty("HOST");

        Response response = given()
                .header("Content-Type", "application/json")
                .body(PayLoad.postAddBookData(isbn, aisle))
                .when()
                .log()
                .all()
                .post(Resources.bookPostData())
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .and()
                .log()
                .body()
                .extract()
                .response();

        JsonPath jsonPath = ReusableMethods.rawToJSON(response);
        String bookId = jsonPath.get("ID");


    }

    public static String GenerateStringFromResource(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }

}
