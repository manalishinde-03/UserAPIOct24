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
      |         3 |

  @TC2
  Scenario Outline: Create user with only mandatory valid details
    Given User creates a POST request with valid details
    When User sends POST request having mandatory details with "<rowNumber>"
    Then The response status code should be 201 Created

    Examples: 
      | rowNumber |
      |         4 |

  @TC3
  Scenario Outline: Create user with invalid details
    Given User creates a POST request with valid details
    When User sends POST request with invalid details and "<rowNumber>"
    Then For invalid details response status code should be 400 Bad Request

    Examples: 
      | rowNumber |
      |         5 |
      |         6 |
      |         7 |
      |         8 |

  @TC4
  Scenario Outline: Create user with invalid endpoint
    Given User creates a POST request with valid details
    When User sends POST request with invalid endpoint and "<rowNumber>"
    Then For invalid endpoint response status code should be 404 Not Found

    Examples: 
      | rowNumber |
      |         9 |

  @TC5
  Scenario Outline: Create user with No Auth
    Given User creates a POST request with valid details
    When User sends POST without Authorization and "<rowNumber>"
    Then For NoAuth response status code should be 401 Unauthorized

    Examples: 
      | rowNumber |
      |         1 |
