package assignment;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class hw14 {
    public class EmployeeApiTest {

        private static final String BASE_URL = "https://dummy.restapiexample.com/api/v1/employees";

        @Test
        public void testEmployeeApi() {
            // Send GET request
            Response response = RestAssured.get(BASE_URL);
            assertEquals(200, response.getStatusCode());

            // Get the number of employees
            int numberOfEmployees = response.jsonPath().getList("data").size();
            assertEquals(24, numberOfEmployees);

            assertTrue(response.jsonPath().getList("data.employee_name").contains("Tiger Nixon"));
            assertTrue(response.jsonPath().getList("data.employee_name").contains("Garrett Winters"));

            // Find the greatest age
            int maxAge = response
                    .jsonPath()
                    .getList("data.employee_age")
                    .stream().mapToInt(Integer::parseInt)
                    .max().orElse(0);
            assertEquals(66, maxAge);

            // Find the employee with the lowest age
            String employeeWithLowestAge = response
                    .jsonPath()
                    .getList("data.employee_age").stream()
                    .filter(age -> Integer.parseInt((String) age) == 19)
                    .map(age -> response.jsonPath()
                            .getString("data.find { it.employee_age == '" + age + "' }.employee_name"))
                    .findFirst()
                    .orElse(null);
            assertEquals("Tatyana Fitzpatrick", employeeWithLowestAge);

            // Calculate the total salary of all employees
            int totalSalary = response.jsonPath()
                    .getList("data.employee_salary")
                    .stream().mapToInt(Integer::parseInt).sum();
            assertEquals(6644770, totalSalary);

        }
    }

}