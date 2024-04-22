package assignment;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

public class hw11 {
    /*
    Given
        https://automationexercise.com/api/productsList
    When
        User sends a GET request
    Then
        Assert that the number of "Women" user type is 12

    Note: Print using JsonPath: response.jsonPath().prettyPrint();

*/

    @Test
    public void hwTest() {

        Response response = RestAssured.get("https://automationexercise.com/api/productsList");
        response.jsonPath().prettyPrint();

        int count = response.jsonPath().getList("products.findAll { it.category.usertype.usertype == 'Women' }")
                .size();
        Assert.assertEquals(count, 12);
    }

}