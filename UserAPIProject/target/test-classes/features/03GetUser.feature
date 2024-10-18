@tag
Feature: Get User

  @TC1
  Scenario Outline: Get user with User ID
    Given User creates GET request with valid user ID
    When User sends GET request with "<rowNumber>" and valid endpoint
    Then The response status code should be 200 OK
    And Validate the JSON Schema for retrived user details

    Examples: 
      | rowNumber |
      |         1 |
      |         2 |

  @TC2
  Scenario Outline: Get user with invalid User ID
    Given User creates GET request with invalid user ID
    When User sends GET request with invalid user ID and "<rowNumber>"
    Then The response status code should be 400 Bad Request

    Examples: 
      | rowNumber |
      |         1 |
      |         2 |

  @TC3
  Scenario: Get all users with valid endpoint
    Given User creates GET request for all users
    When User sends GET request to get all user details with valid endpoint
    Then The response status code should be 200 OK

  @TC4
  Scenario Outline: Get user with First Name
    Given User creates GET request with valid First Name
    When User sends GET request to fetch user with "<rowNumber>"
    Then The response status code should be 200 OK
    And Validate the JSON Schema for retrived user details

    Examples: 
      | rowNumber |
      |         1 |

  @TC5
  Scenario Outline: Get user with invalid First Name
    Given User creates GET request with invalid First Name
    When User sends GET request with "<rowNumber>" and invalid FirstName and
    Then The response status code should be 404 Not Found

    Examples: 
      | rowNumber |
      |         1 |
      |         2 |
