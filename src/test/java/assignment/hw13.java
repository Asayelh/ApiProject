package assignment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojos.JsonPlaceHolderPojo;
import pojos.userpojo;

import java.util.Objects;

import static io.restassured.RestAssured.given;

public class hw13 extends userpojo {
    public class PostRequest {
        private String baseUrl;
        private ObjectMapper objectMapper;

        public PostRequest(String baseUrl) {
            this.baseUrl = baseUrl;
            this.objectMapper = new ObjectMapper();
        }

        public Response sendPostRequest(String endpoint, JsonPlaceHolderPojo requestBody) throws Exception {
            String requestJson = objectMapper.writeValueAsString(requestBody);

            return given().contentType(ContentType.JSON)
                    .body(requestJson)
                    .post(baseUrl + endpoint);
        }


        public class PostTest {
            public void main(String[] args) throws Exception {
                //Set the URL
                String baseUrl = "https://example.com";
                PostRequest postRequest = new PostRequest(baseUrl);

                //Set the expected data
                String strJson = """
                        {
                            "userId": 55,
                            "title": "Tidy your room",
                            "completed": false
                        }
                        """;

                JsonPlaceHolderPojo expectedData = new ObjectMapper().readValue(strJson, JsonPlaceHolderPojo.class);
                System.out.println("expectedData = " + expectedData);

                //Send the request and get the response
                Response response = postRequest.sendPostRequest("/todos", expectedData);
                response.prettyPrint();

                //Do assertion
                JsonPlaceHolderPojo actualData = new ObjectMapper().readValue(response.asString(), JsonPlaceHolderPojo.class);
                System.out.println("actualData = " + actualData);

                assert response.statusCode() == 201;
                assert actualData.getUserId() == expectedData.getUserId();
                assert Objects.equals(actualData.getTitle(), expectedData.getTitle());
                assert actualData.getCompleted() == expectedData.getCompleted();
            }
        }
    }
}