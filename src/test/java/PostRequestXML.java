import data.PayLoad;
import data.Resources;
import data.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import static data.ReusableMethods.rawToXML;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PostRequestXML {

    Properties properties = new Properties();

    @BeforeTest
    public void getData() throws IOException {

        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") +
                "\\src\\test\\resources\\env.properties");
        properties.load(fis);

    }

    @Test
    public void postData() throws IOException {

        String strBody = GenerateStringFromResource(
                "C:\\Eleks\\Work\\Tranings\\RestSideProject\\src\\test\\resources\\postData.xml");

        RestAssured.baseURI = properties.getProperty("HOST");

        Response response = given()
                .queryParam("key", properties.getProperty("KEY"))
                .body(strBody)
                .when()
                .post(Resources.placePostDataXML())
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.XML)
                .extract()
                .response();

        XmlPath xmlPath = ReusableMethods.rawToXML(response);
        System.out.println(xmlPath.get("response.place_id").toString());

    }

    public static String GenerateStringFromResource(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }

}
