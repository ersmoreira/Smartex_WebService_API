Feature: Employee API Testing

  Scenario: Create an employee
    Given I have the employee details
    When I send a POST request to "api/v1/create"
    Then the response status code should be 200


  Scenario: Validate if the employee was created correctly
    Given I have the employee ID
    When I send a GET request to "api/v1/employee/{id}"
    Then the response status code should be 200
    And the employee details should match the created employee

  Scenario: Delete the employee
    Given I have the employee ID
    When I send a DELETE request to "api/v1/delete/{id}"
    Then the response status code should be 200
    And the employee should be deleted successfully
