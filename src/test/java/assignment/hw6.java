package assignment;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class hw6 {
    /*
        Given
          https://reqres.in/api/unknown/3
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is "application/json; charset=utf-8"
        And
            Response body should be like;(Soft Assertion)
        {
        "data": {
            "id": 3,
            "name": "true red",
            "year": 2002,
            "color": "#BF1932",
            "pantone_value": "19-1664"
        },
        "support": {
            "url": "https://reqres.in/#support-heading",
            "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
        }
}

      */
    @Test
    public static void main(String[] args) {
        String url = "https://reqres.in/api/unknown/3";
        Response response = given().get(url);
        response.prettyPrint();
        response
                .then()
                .statusCode(200)
                .contentType("application/json; charset=utf-8");
        SoftAssert softAssert = new SoftAssert();
        int id = response.jsonPath().getInt("data.id");
        String name = response.jsonPath().getString("data.name");
        int year = response.jsonPath().getInt("data.year");
        String color = response.jsonPath().getString("data.color");
        String pantone_value = response.jsonPath().getString("data.pantone_value");
        String Urll = response.jsonPath().getString("url");
        String text = response.jsonPath().getString("text");
        softAssert.assertEquals(id, 3);
        softAssert.assertEquals(name, "true red");
        softAssert.assertEquals(year, 2002);
        softAssert.assertEquals(color, "#BF1932");
        softAssert.assertEquals(pantone_value, "19-1664");
        softAssert.assertEquals(Urll, "https://reqres.in/#support-heading");
        softAssert.assertEquals(text, "To keep ReqRes free, contributions towards server costs are appreciated!");


    }
}

