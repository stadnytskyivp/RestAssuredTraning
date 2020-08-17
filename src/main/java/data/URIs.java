package data;

public class URIs {

    public static String getCodeOAuth() {
        String code = "https://accounts.google.com/o/oauth2/v2/auth/identifier?scope=https%3A%2F%" +
                "2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&auth_url=https%3A%2F%2Faccounts.google.com%" +
                "2Fo%2Foauth2%2Fv2%2Fauth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj" +
                ".apps.googleusercontent.com&response_type=code&redirect_uri=https%3A%2F%2Frahulshettyacademy" +
                ".com%2FgetCourse.php&flowName=GeneralOAuthFlow";
        return code;
    }

    public static String getRedirectPathOAuth() {
        String code = "https://rahulshettyacademy.com/getCourse.php";
        return code;
    }

}
