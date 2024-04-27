package practice;


import base_urls.bas2;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class pra2 extends bas2 {

    // Given https://dummy.restapiexample.com/api/v1/employees
// When user send Request via GET Method
// Then Assert that Status Code is "200"
// And Print employee_age greater than 55 to the console. Assert that there are 8.
// And Print the ones with id's greater than 17 to the console and assert that there are 7.
// And Print the ones with salary is less than 100,000 to the console.Assert that
// Doris Wilder is one of them.
    @Test
    public void groovyTest() {

    Response response = RestAssured.get("https://dummy.restapiexample.com/api/v1/employees");
        response.prettyPrint();
        response.then().statusCode(200);
        JsonPath jsonPath =response.jsonPath();
        List<Boolean> employee_age=jsonPath.getList("data.findAll{it.employee_age > 55}.employee_age");
        System.out.println("employee_age ->"+employee_age);
        assertEquals(employee_age.size(),8);


        List<Integer> employeeIds = jsonPath.getList("data.findAll {it.id>17}.id");
        System.out.println("Employees ID: " + employeeIds);
        Assert.assertEquals(employeeIds.size(), 7);

        boolean employee_name = jsonPath.getList("data.findAll { it.employee_salary < 100000 && it.employee_name == 'Doris Wilder' }").size() > 0;
        System.out.println("Doris Wilder: " + employee_name);
        Assert.assertTrue(employee_name);
    }
}