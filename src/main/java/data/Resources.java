package data;

public class Resources {

    public static String placePostData(){
        String res = "/maps/api/place/add/json";
        return res;
    }

    public static String placePostDataXML(){
        String res = "/maps/api/place/add/xml";
        return res;
    }

    public static String placeGetData(){
        String res = "/maps/api/place/nearbysearch/json";
        return res;
    }

    public static String bookPostData(){
        String res = "/Library/Addbook.php";
        return res;
    }

    public static String jiraPostSession(){
        String res = "/rest/auth/1/session";
        return res;
    }

}
