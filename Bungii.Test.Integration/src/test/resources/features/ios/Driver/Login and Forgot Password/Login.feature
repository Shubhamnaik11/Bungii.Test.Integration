@ios
Feature: Log In
  As a Bungii Driver I should be allowed to login only using valid credential

  Background:
    Given I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp


  @regression
  Scenario Outline: As Bungii driver I should not able login to application using invalid details.  Scenario:<Scenario>
    When I enter phoneNumber :<Username> and  Password :<Password>
    And I click "Log In" button on "Log In" screen on driverApp
    Then Alert message with <Expected Message> text should be displayed on driverApp
    And I accept Alert message on driverApp
    Then I should be navigated to "LOG IN" screen on driverApp

    Examples:
      | Scenario                | Username | Password | Expected Message |
      | INVALID PASSWORD        | {VALID}  | Cci1234  | INVALID_PASSWORD |
      | EMPTY PASSWORD          | {VALID}  | <BLANK>  | EMPTY_FIELD      |
      | EMPTY USERNAME PASSWORD | <BLANK>  | <BLANK>  | EMPTY_FIELD      |
      | EMPTY USERNAME          | <BLANK>  | Cci12345 | EMPTY_FIELD      |
  @sanity
  @regression
  Scenario: As Bungii driver , I should able to login to application using valid password
    When I enter phoneNumber :{VALID} and  Password :{VALID}
    And I click "Log In" button on "Log In" screen on driverApp
    Then I should be successfully logged in to the application

  @test1
  @regression
  Scenario Outline: Driver canNot login on driver app before admin verification
    When I enter phoneNumber :<Username> and  Password :<Password>
    And I click "Log In" button on "Log In" screen on driverApp
    Then Alert message with <Expected Message> text should be displayed on driverApp
    And I accept Alert message on driverApp
    Then I should be navigated to "LOG IN" screen on driverApp
    Examples:
      | Scenario        | Username   | Password | Expected Message                                  | Login Button Status  |
      | PENDING VERIFICATION | 9823901494 | Cci12345 | Your account registration is still under process. | LOGIN BUTTON ENABLED |

  @regression

  Scenario Outline: As I enter wrong password 5 times, driver's account gets locked
    When I enter phoneNumber
    And I enter invalid password and click on "Log In" button for 5 times on Log In screen on driver app
    Then I should see "<Expected Message>" on Log In screen on driver app
    Examples:
      | Expected Message                                                                                                      |
      | Invalid login credentials. Your account has been locked. Please use the Forgot Password option to reset your account. |