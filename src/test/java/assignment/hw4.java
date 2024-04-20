package assignment;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class hw4 {
    /*
      Given
          https://reqres.in/api/users/2
      When
          User send GET Request to the URL
      Then
          HTTP Status Code should be 200
      And
          Response format should be "application/json"
      And
          "email" is "janet.weaver@reqres.in",
      And
          "first_name" is "Janet"
      And
          "last_name" is "Weaver"
      And
          "text" is "To keep ReqRes free, contributions towards server costs are appreciated!"
   */
    @Test
    public void t2() {
        String url = "https://reqres.in/api/users/2";
        Response response = given().get(url);
        response.prettyPrint();
        response
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("email", equalTo("\"janet.weaver@reqres.in\"")
                        , "\"first_name\"", equalTo("Janet"),
                        "\"last_name\"", equalTo("Weaver"),
                        "text", equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"));


    }
}
