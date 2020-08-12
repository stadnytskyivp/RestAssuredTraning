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

    public static String postAddBookData(String aisle, String isbn) {

        String strBody = "{\r\n\"name\":\"Learn Appium Automation with Java\",\r\n" +
                "\"isbn\":\"" + aisle + "\",\r\n\"aisle\":\"" + isbn + "\",\r\n" +
                "\"author\":\"John foe\"\r\n}";
        return strBody;
    }

    public static String postStartJiraSession() {

        String strBody = "{\r\n    \"username\":\"stadnytskyi.volodymyr\",\r\n    \"password\":\"qwaszxcde\"\r\n}";
        return strBody;
    }

    public static String postCreateJiraIssue() {
        String strBody =     "{"+
                "\"fields\": {"+
                "\"project\":{"+
                "\"key\": \"RES\""+
                "},"+
                "\"summary\": \"Issue adding from IDEA\","+
                "\"description\": \"Creating my second bug using java\","+
                "\"issuetype\": {"+
                "\"name\": \"Bug\""+
                "}}}";
        return strBody;
    }


    public static String сommentJiraIssue() {
        String strBody = "{ \"body\": \"Inserting comment from the automation code\"," +
                "\"visibility\": {" +
                "\"type\": \"role\"," +
                "\"value\": \"Administrators\" }" +
                "}";
        return strBody;
    }


}
