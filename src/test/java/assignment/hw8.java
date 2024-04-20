package assignment;


import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
public class hw8 extends JsonPlaceHolderBaseUrl {
     /*
        Given
            1) https://reqres.in/api/users
            2) {
                "name": "morpheus",
                "job": "leader"
                }
        When
            I send POST Request to the Url
        Then
            Status code is 201
            And response body should be like {
                                                "name": "morpheus",
                                                "job": "leader",
                                                "id": "496",
                                                "createdAt": "2022-10-04T15:18:56.372Z"
                                              }
     */


    @Test
    public void ass8(){

        String url = "https://reqres.in/api/users";
        String requestBody = "{\"name\": \"morpheus\", \"job\": \"leader\"}";

        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post(url);

        response.then()
                .statusCode(201)
                .body("name", equalTo("morpheus"))
                .body("job", equalTo("leader"))
                .body("id", equalTo("496"))
                .body("createdAt", equalTo("2022-10-04T15:18:56.372Z"));
    }
    }
