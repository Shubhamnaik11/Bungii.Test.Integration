@ios
Feature: Log In
  As a Bungii Customer I should be allowed to login only using valid credential

  Background:
    Given I am on the "LOG IN" page

  @regression
  Scenario Outline: Verify Customer Cannot Login To Application Using Invalid Details - Scenario:<Scenario>
    When I enter Username :<Username> and  Password :<Password>
    And I click "Log In" button on "Log In" screen
    Then Alert message with <Expected Message> text should be displayed
    When I accept Alert message
    Then I should be navigated to "LOG IN" screen

    Examples:
      | Scenario                | Username | Password | Expected Message |
      | INVALID PASSWORD        | {VALID}  | Cci1234  | INVALID_PASSWORD |
      | EMPTY PASSWORD          | {VALID}  | <BLANK>  | EMPTY_FIELD      |
      | EMPTY USERNAME PASSWORD | <BLANK>  | <BLANK>  | EMPTY_FIELD      |
      | EMPTY USERNAME          | <BLANK>  | Cci12345 | EMPTY_FIELD      |
  @sanity
  @regression
  Scenario: Verify Customer Can Login Using Valid Credentials
    When I enter Username :{VALID} and  Password :{VALID}
    And I click "Log In" button on "Log In" screen
    Then User should be successfully logged in to the application

  @regression
  Scenario: Verify Customer Is Shown Terms And Condition Screen On First Time Login
    Given I install Bungii App again
    When I am on the "LOG IN" page
    And I enter Username :{VALID} and  Password :{VALID}
    And I click "Log In" button on "Log In" screen
    Then I should be navigated to "TERMS AND CONDITION" screen
    And I should see "all details" on Term and Condition agreement
    When I accept Term and Condition agreement
    Then I should be navigated to "ALLOW NOTIFICATIONS" screen
    And I should see "all details" on allow notifications screen
    When I verify and allow access of Notification from Bungii application
    Then I should be navigated to "ALLOW LOCATION" screen
    And I should see "all details" on allow location screen
    When I verify and allow access of Location from Bungii application
    And I close tutorial Page using close button
    Then User should be successfully logged in to the application
    And I Select "LOGOUT" from Customer App menu
    When I am on the "LOG IN" page
    And I enter Username :{VALID} and  Password :{VALID}
    And I click "Log In" button on "Log In" screen
    Then I should be navigated to "Home" screen

  @capability
  @regression
  @permission
    Scenario: Verify Dismissal Of Tutorials By Tapping On Start
    Given I install Bungii App again
    When I am on the "LOG IN" page
    And I enter Username :{VALID} and  Password :{VALID}
    And I click "Log In" button on "Log In" screen
    Then I should be navigated to "TERMS AND CONDITION" screen
    When I accept Term and Condition agreement
    Then I should be navigated to "ALLOW NOTIFICATIONS" screen
    When I verify and allow access of Notification from Bungii application
    Then I should be navigated to "ALLOW LOCATION" screen
    When I verify and allow access of Location from Bungii application
    Then I should able to see "tutorials page 1" on Tutorials screen
    Then I "left" swipe on tutorials page
    Then I should able to see "tutorials page 2" on Tutorials screen
    Then I "left" swipe on tutorials page
    Then I should able to see "tutorials page 3" on Tutorials screen
    Then I "left" swipe on tutorials page
    Then I should able to see "tutorials page 4" on Tutorials screen
    Then I "left" swipe on tutorials page
    Then I should able to see "tutorials page 5" on Tutorials screen
    And I close tutorial Page by using Start button
    Then User should be successfully logged in to the application
    When I Select "LOGOUT" from Customer App menu
    When I am on the "LOG IN" page
    And I enter Username :{VALID} and  Password :{VALID}
    And I click "Log In" button on "Log In" screen
    Then User should be successfully logged in to the application

  @regression
    @permission
  Scenario: Verify Swiping Back And Forth Between Tutorials Screen To View Tutorials
    Given I install Bungii App again
    When I am on the "LOG IN" page
    And I enter Username :{VALID} and  Password :{VALID}
    And I click "Log In" button on "Log In" screen
    Then I should be navigated to "TERMS AND CONDITION" screen
    When I accept Term and Condition agreement
    Then I should be navigated to "ALLOW NOTIFICATIONS" screen
    When I verify and allow access of Notification from Bungii application
    Then I should be navigated to "ALLOW LOCATION" screen
    When I verify and allow access of Location from Bungii application
    Then I should able to see "tutorials page 1" on Tutorials screen
    Then I "left" swipe on tutorials page
    Then I should able to see "tutorials page 2" on Tutorials screen
    Then I "right" swipe on tutorials page
    Then I should able to see "tutorials page 1" on Tutorials screen
    Then I "left" swipe on tutorials page
    Then I "left" swipe on tutorials page
    Then I "left" swipe on tutorials page
    Then I "left" swipe on tutorials page
    Then I should able to see "tutorials page 5" on Tutorials screen
    Then I "right" swipe on tutorials page
    Then I should able to see "tutorials page 4" on Tutorials screen
    Then I "right" swipe on tutorials page
    Then I should able to see "tutorials page 3" on Tutorials screen
    And I close tutorial Page using close button
    Then User should be successfully logged in to the application