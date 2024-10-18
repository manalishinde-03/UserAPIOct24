@Order1
Feature: Add User

  @TC1
  Scenario Outline: Create user with all valid details
    Given User creates a POST request with valid details
    When User sends POST request with "<rowNumber>" and valid endpoint
    Then The response status code should be 201 Created
    And Validate JSON Schema for the user created

    Examples: 
      | rowNumber |
      |         1 |
      |         2 |
