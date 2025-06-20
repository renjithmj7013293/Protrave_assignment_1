
@BDD_Sauce
Feature: Automate sauce demo webpage

  Scenario: Login to webpage with correct credentials
    Given user launches sauce demo url
    When user provides the credentials:
    |   username    |   password   |
    | standard_user | secret_sauce |
    And user clicks on login button
    Then verify that user is successfully logged in to home page

  Scenario: Login to webpage with incorrect username
    Given user launches sauce demo url
    When user provides the credentials:
      |        username       |   password   |
      | standard_user_invalid | secret_sauce |
    And user clicks on login button
    Then verify that user login is failed

  Scenario: Login to webpage with incorrect username
    Given user launches sauce demo url
    When user provides the credentials:
      |   username    |     password    |
      | standard_user | secret_sauce_xx |
    And user clicks on login button
    Then verify that user login is failed

  Scenario: Login to webpage with blank username
    Given user launches sauce demo url
    When user provides the credentials:
      |   username    |     password    |
      |   [empty]     | secret_sauce_xx |
    And user clicks on login button
    Then verify that user login is failed

  Scenario: Login to webpage with blank username
    Given user launches sauce demo url
    When user provides the credentials:
      |   username    |     password    |
      |  standard_user|     [empty]     |
    And user clicks on login button
    Then verify that user login is failed

  Scenario: Verify localStorage after valid login
    Given user launches sauce demo url
    When user provides the credentials:
      |   username    |   password   |
      | standard_user | secret_sauce |
    And user clicks on login button
    Then verify that user is successfully logged in to home page
    And local storage should contain a valid session token
    And the cookie-session-username in local storage should match the logged in user
      |   username    |
      | standard_user |
    And verify the header using overridden get text method is "Swag Labs"