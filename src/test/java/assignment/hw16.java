package assignment;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class hw16 {


    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "https://your-api-base-url";

    }

    @Test
    public void addContactTest() {
        String requestBody = "{ \"name\": \"John Doe\", \"email\": \"johndoe@example.com\", \"phone\": \"1234567890\" }";
        String addContactEndpoint = "/contacts";
        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(addContactEndpoint)
                .then()
                .statusCode(201)
                .extract()
                .response();

        int contactId = response.jsonPath().getInt("id");


        String getContactEndpoint = "/contacts/{id}";
        given()
                .pathParam("id", contactId)
                .when()
                .get(getContactEndpoint)
                .then()
                .statusCode(200)
                .body("name", equalTo("John Doe"))
                .body("email", equalTo("johndoe@example.com"))
                .body("phone", equalTo("1234567890"));

        requestBody = "{ \"name\": \"John Smith\", \"email\": \"johnsmith@example.com\", \"phone\": \"9876543210\" }";
        String updateContactEndpoint = "/contacts/{id}";
        given()
                .contentType(ContentType.JSON)
                .pathParam("id", contactId)
                .body(requestBody)
                .when()
                .put(updateContactEndpoint)
                .then()
                .statusCode(200)
                .body("name", equalTo("John Smith"))
                .body("email", equalTo("johnsmith@example.com"))
                .body("phone", equalTo("9876543210"));
        String deleteContactEndpoint = "/contacts/{id}";
        given()
                .pathParam("id", contactId)
                .when()
                .delete(deleteContactEndpoint)
                .then()
                .statusCode(204);

        given()
                .pathParam("id", contactId)
                .when()
                .get(getContactEndpoint)
                .then()
                .statusCode(404)
                .body("message", equalTo("Contact not found"));
    }
}