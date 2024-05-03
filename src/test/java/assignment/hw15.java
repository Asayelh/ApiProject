package assignment;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class hw15 {

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "https://your-api-base-url";
    }

    @Test(priority = 1)
    public void createUserTest() {
        String requestBody = "{ \"name\": \"John Doe\", \"email\": \"johndoe@example.com\", \"age\": 30 }";

        String createUserEndpoint = "/users";
        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(createUserEndpoint)
                .then()
                .statusCode(201)
                .extract()
                .response();

        int userId = response.jsonPath().getInt("id");

        
        // Read 
        String getUserEndpoint = "/users/{id}";
        given()
                .pathParam("id", userId)
                .when()
                .get(getUserEndpoint)
                .then()
                .statusCode(200)
                .body("name", equalTo("John Doe"))
                .body("email", equalTo("johndoe@example.com"))
                .body("age", equalTo(30));

        // Update
        String updateRequestBody = "{ \"name\": \"John Smith\", \"email\": \"johnsmith@example.com\", \"age\": 35 }";
        String updateUserEndpoint = "/users/{id}";
        given()
                .contentType(ContentType.JSON)
                .pathParam("id", userId)
                .body(updateRequestBody)
                .when()
                .put(updateUserEndpoint)
                .then()
                .statusCode(200)
                .body("name", equalTo("John Smith"))
                .body("email", equalTo("johnsmith@example.com"))
                .body("age", equalTo(35));

        // Delete
        String deleteUserEndpoint = "/users/{id}";
        given()
                .pathParam("id", userId)
                .when()
                .delete(deleteUserEndpoint)
                .then()
                .statusCode(204);
    }
}

