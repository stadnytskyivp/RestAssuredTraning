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

    public static String jiraPostStartSession(){
        String res = "/rest/auth/1/session";
        return res;
    }

    public static String jiraPostCreateIssue(){
        String res = "/rest/auth/2/issue";
        return res;
    }

}
