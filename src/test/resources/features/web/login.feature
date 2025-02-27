@web
Feature: User Login on DemoBlaze

  Scenario: Successful login
    Given user is on the login page
    When user enters username "testuser" and password "password123"
    And user clicks the login button
    Then user should be redirected to the homepage

  Scenario: Login with incorrect credentials (Negative Test)
    Given user is on the login page
    When user enters username "wronguser" and password "wrongpass"
    And user clicks the login button
    Then an error message should be displayed

  Scenario: End-to-end login and navigate to cart
    Given user is on the login page
    When user enters username "testuser" and password "password123"
    And user clicks the login button
    Then user should be redirected to the homepage
    When user navigates to the cart
    Then the cart page should be displayed

  Scenario: Logout after login
    Given user is logged in
    When user clicks the logout button
    Then user should be redirected to the login page

  Scenario: Attempt login without entering credentials
    Given user is on the login page
    When user clicks the login button
    Then an error message should be displayed
