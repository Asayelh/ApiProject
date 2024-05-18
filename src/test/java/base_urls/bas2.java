package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;

public class bas2 {
    protected RequestSpecification spec;//This is null in this line, setUp() method should run before using this.

    @BeforeMethod //Runs before each @Test
    public void setUp(){

        spec = new RequestSpecBuilder()
                .setBaseUri("https://dummy.restapiexample.com")
                .setContentType(ContentType.JSON)
                .build();

    }

}