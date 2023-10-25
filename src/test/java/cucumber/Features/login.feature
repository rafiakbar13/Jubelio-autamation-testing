Feature: Login to the Application

  Background:
    Given the user is on the login page

  @Positive
  Scenario: Successful Login
    When the user enters valid email
    And the user enters valid password
    And the user clicks the Login button
    Then user redirected to dashboard page

    @Negative
  Scenario: Login with Invalid password
    When the user enters valid email
    And the user enters invalid password
    And the user clicks the Login button
    Then the system displays an error message

