package assignment;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class hw9 {
    @Test
    public void createNewUser() {
        RestAssured.baseURI = "https://petstore.swagger.io";

        Map<String, Object> requestPayload = new HashMap<>();
        requestPayload.put("id", 0);
        requestPayload.put("username", "username");
        requestPayload.put("firstName", "John");
        requestPayload.put("lastName", "Doe");
        requestPayload.put("email", "johndoe@gmail.com");
        requestPayload.put("password", "password");
        requestPayload.put("phone", "0456789");
        requestPayload.put("userStatus", 0);

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestPayload)
                .post("/v2/user");

        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asString();

        System.out.println("Response status code: " + statusCode);
        System.out.println("Response body: " + responseBody);
    }
}