package data;

public class Resources {

    public static String placePostData() {
        String res = "/maps/api/place/add/json";
        return res;
    }

    public static String placePostDataXML() {
        String res = "/maps/api/place/add/xml";
        return res;
    }

    public static String placeGetData() {
        String res = "/maps/api/place/nearbysearch/json";
        return res;
    }

    public static String bookPostData() {
        String res = "/Library/Addbook.php";
        return res;
    }

    public static String jiraPostStartSession() {
        String res = "/rest/auth/1/session";
        return res;
    }

    public static String jiraPostCreateIssue() {
        String res = "/rest/api/2/issue";
        return res;
    }

    public static String jiraPostAddComment(String issueId) {
        String res = "/rest/api/2/issue/" + issueId + "/comment";
        return res;
    }

    public static String jiraPostUpdateComment(String issueId, String commentId) {
        String res = "/rest/api/2/issue/" + issueId + "/comment/" + commentId;
        return res;
    }

}
