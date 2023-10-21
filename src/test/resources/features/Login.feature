Feature: Login
  Scenario: User login using valid credential
    Given user at login page
    When user enter "standard_user" in username input field
    And user enter "secret_sauce" in password input field
    And user click login button
    Then user see product list

  Scenario: User login using invalid credential
    Given user at login page
    When user enter "standard_user" in username input field
    And user enter "wrong_password" in password input field
    And user click login button
    Then user see error message