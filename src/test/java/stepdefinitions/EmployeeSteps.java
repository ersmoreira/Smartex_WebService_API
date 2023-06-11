package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

public class EmployeeSteps {
    private String globalEndpoint="http://dummy.restapiexample.com/";
    private Response response;
    private int employeeId;
    private Map<String, String> employeeDetails;

    @Given("I have the employee details")
    public void iHaveTheEmployeeDetails() {
        employeeDetails = new HashMap<>();
        employeeDetails.put("name", "Edgar Moreira");
        employeeDetails.put("salary", "2500");
        employeeDetails.put("age", "42");
    }

    @When("I send a POST request to {string}")

    public void iSendPOSTRequest(String endpoint) {
        RequestSpecification request = RestAssured.given()
                                                  .contentType(ContentType.JSON)
                                                  .body(employeeDetails);

        response = request.post(globalEndpoint.concat(endpoint));
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int expectedStatusCode) {
        System.out.println(response.toString());
        Assert.assertEquals(expectedStatusCode, response.getStatusCode());
    }

    @Then("the employee should be created successfully")
    public void theEmployeeShouldBeCreatedSuccessfully() {
        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains("\"status\":\"success\""));
        employeeId = response.jsonPath().getInt("data.id");
    }

    @Given("I have the employee ID")
    public void iHaveTheEmployeeID() {
        Assert.assertNotNull(employeeId);
    }

    @When("I send a GET request to {string}")
    public void iSendGETRequest(String endpoint) {
        response = RestAssured.get(globalEndpoint.concat(endpoint).replace("{id}", String.valueOf(employeeId)));
    }

    @Then("the employee details should match the created employee")
    public void theEmployeeDetailsShouldMatchTheCreatedEmployee() {
        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains("\"id\":" + employeeId));
        Assert.assertTrue(responseBody.contains("\"employee_name\":\"Edgar Moreira\""));
        Assert.assertTrue(responseBody.contains("\"employee_salary\":\"2500\""));
        Assert.assertTrue(responseBody.contains("\"employee_age\":\"42\""));
    }

    @When("I send a DELETE request to {string}")
    public void iSendDELETERequest(String endpoint) {
        response = RestAssured.delete(globalEndpoint.concat(endpoint).replace("{id}", String.valueOf(employeeId)));
    }

    @Then("the employee should be deleted successfully")
    public void theEmployeeShouldBeDeletedSuccessfully() {
        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains("\"status\":\"success\""));
    }
}
