package assignment;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class hw10 {
    @Test
    public void ass10() {
        RestAssured.baseURI = "https://petstore.swagger.io";

        Response response = RestAssured.get("/v2/pet/findByStatus?status=available");

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200, "Invalid status code");
        int count = response.jsonPath().getList("id").size();
        Assert.assertTrue(count > 100, "Number of available pets is not more than 100");
    }
}

