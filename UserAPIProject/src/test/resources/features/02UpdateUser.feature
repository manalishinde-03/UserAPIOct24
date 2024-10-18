@Order2
Feature: Update User

  @TC1
  Scenario Outline: Update user email by using user ID
    Given User creates PUT request with request body having new valid email ID
    When User sends PUT request with "<rowNumber>" and valid endpoint
    Then User should receive status code 200 OK
    And Validate JSON Schema for the updated user

    Examples: 
      | rowNumber |
      |         1 |
      |         2 |
