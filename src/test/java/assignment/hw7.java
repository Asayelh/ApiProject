package assignment;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class hw7 {
     /*
       Given
              https://reqres.in/api/unknown/
       When
            I send GET Request to the URL
       Then

            1)Status code is 200
            2)Print all pantone_values
            3)Print all ids greater than 3 on the console
              Assert that there are 3 ids greater than 3
            4)Print all names whose ids are less than 3 on the console
              Assert that the number of names whose ids are less than 3 is 2
    */
    @Test
    public void ass7(){ String url = "https://reqres.in/api/unknown/";

        Response response = given().get(url);
        response.then()
                .statusCode(200);
        response.jsonPath()
                .getList("data.pantone_value").forEach(System.out::println);
        response.jsonPath().
                getList("data.findAll {it.id > 3}.id").forEach(System.out::println);
        response
                .then()
                .assertThat()
                .body("data.findAll {it.id > 3}.size()", equalTo(3));
        response.jsonPath().
                getList("data.findAll {it.id < 3}.name").forEach(System.out::println);

        response
                .then()
                .assertThat()
                .body("data.findAll {it.id < 3}.size()", equalTo(2));
    }
}