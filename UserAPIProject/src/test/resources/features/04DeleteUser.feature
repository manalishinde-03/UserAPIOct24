@Order4
Feature: Delete User

  @TC1
  Scenario Outline: Delete user by ID with valid endpoint
    Given User sets a DELETE request with valid details
    When User sends DELETE request with userID and valid endpoint with "<rowNumber>"
    Then The status code should be 200 OK

    Examples: 
      | rowNumber |
      |         1 |

  @TC2
  Scenario Outline: Delete user by invalid ID with valid endpoint
    Given User sets a DELETE request with valid details
    When User sends DELETE request with invalid ID and "<rowNumber>"
    Then The status code should be 400 Bad Request

    Examples: 
      | rowNumber |
      |         1 |
      |         2 |

  @TC3
  Scenario Outline: Delete user by non existing ID with valid endpoint
    Given User sets a DELETE request with valid details
    When User sends DELETE request for non existing ID and "<rowNumber>"
    Then The status code should be 404 Not Found

    Examples: 
      | rowNumber |
      |         3 |

  @TC4
  Scenario Outline: Delete user by FirstName with valid endpoint
    Given User sets a DELETE request with valid details
    When User sends DELETE request FirstName with "<rowNumber>" and valid endpoint
    Then User should get status code 200 OK

    Examples: 
      | rowNumber |
      |         2 |
