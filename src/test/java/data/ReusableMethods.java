package data;

import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ReusableMethods {

    public static XmlPath rawToXML(Response response) {

        String strResponse = response.asString();
        XmlPath xmlPath = new XmlPath(strResponse);
        return xmlPath;

    }

    public static JsonPath rawToJSON(Response response) {

        String strResponse = response.asString();
        System.out.println(strResponse);
        JsonPath jsonPath = new JsonPath(strResponse);
        return jsonPath;

    }

}
