package data;

public class PayLoad {

    public static String getPostDataPlaceAPI() {

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

        return strBody;
    }

    public static String postAddBookData() {

        String strBody = "{\r\n\"name\":\"Learn Appium Automation with Java\",\r\n" +
                "\"isbn\":\"bcgghzdq\",\r\n\"aisle\":\"2217\",\r\n" +
                "\"author\":\"John foe\"\r\n}";
        return strBody;
    }

}
