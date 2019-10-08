@android

Feature: Log In
  As a Bungii Driver I should be allowed to login only using valid credential

  Background:
 #   Given I Open "driver" application on "same" devices
    And I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app


  @regression
  Scenario Outline: As Bungii driver I should not able login to application using invalid details.  Scenario:<Scenario>
    When I enter phoneNumber :<Username> and  Password :<Password>
    And I click "Log In" button on Log In screen on driver app
    Then I should see "<Expected Message>" on Log In screen on driver app
    Then I should see "<Login Button Status>" on Log In screen on driver app

  #  Then I should be navigated to "LOG IN" screen on driverApp

    Examples:
      | Scenario                | Username | Password | Expected Message                          |Login Button Status|
      | INVALID PASSWORD        | {VALID}  | Cc1234   | SNACK BAR VALIDATION FOR INVALID PASSWORD |LOGIN BUTTON ENABLED|
      | EMPTY PASSWORD          | {VALID}  | <BLANK>  | Empty Password Error                      |LOGIN BUTTON DISABLED|
      | EMPTY USERNAME PASSWORD | <BLANK>  | <BLANK>  | Empty Phone and Password Error            |LOGIN BUTTON DISABLED|
      | EMPTY USERNAME          | <BLANK>  | Cci12345 | Empty Phone Error                         |LOGIN BUTTON DISABLED|

  @regression
  Scenario: As Bungii driver , I should able to login to application using valid password
    When I enter phoneNumber :{VALID} and  Password :{VALID}
    And I click "Log In" button on Log In screen on driver app
    And I should be navigated to Home screen on driver app
