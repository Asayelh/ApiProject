package assignment;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class task1 {
    public static void main(String[] args) {

    Response response = RestAssured.get("https://reqres.in/api/users/3");

    //        HTTP Status Code should be 200
    int statusCode = response.statusCode();
        System.out.println("statusCode = "+statusCode);//200

    //        Content Type should be JSON
    String contentType = response.contentType();
        System.out.println("contentType = "+contentType);

    //        Status Line should be HTTP/1.1 200 OK
    String statusLine = response.statusLine();
        System.out.println("statusLine = "+statusLine);
}}