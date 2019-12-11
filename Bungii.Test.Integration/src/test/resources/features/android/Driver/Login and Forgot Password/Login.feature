@android

Feature: Log In
  As a Bungii Driver I should be allowed to login only using valid credential

  Background:
    Given I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app


  @regression
  Scenario Outline: As Bungii driver I should not able login to application using invalid details.  Scenario:<Scenario>
    When I enter phoneNumber :<Username> and  Password :<Password>
    And I click "Log In" button on Log In screen on driver app
    Then I should see "<Expected Message>" on Log In screen on driver app
    And I should see "<Login Button Status>" on Log In screen on driver app

  #  Then I should be navigated to "LOG IN" screen on driverApp

    Examples:
      | Scenario                | Username | Password | Expected Message                          |Login Button Status|
      | INVALID PASSWORD        | {VALID}  | Cc1234   | SNACK BAR VALIDATION FOR INVALID PASSWORD |LOGIN BUTTON ENABLED|
      | EMPTY PASSWORD          | {VALID}  | <BLANK>  | Empty Password Error                      |LOGIN BUTTON DISABLED|
      | EMPTY USERNAME PASSWORD | <BLANK>  | <BLANK>  | Empty Phone and Password Error            |LOGIN BUTTON DISABLED|
      | EMPTY USERNAME          | <BLANK>  | Cci12345 | Empty Phone Error                         |LOGIN BUTTON DISABLED|

  @FAILED
  @sanity
  @regression
  Scenario: As Bungii driver , I should able to login to application using valid password
    When I enter phoneNumber :{VALID} and  Password :{VALID}
    And I click "Log In" button on Log In screen on driver app
    Then I should be navigated to Home screen on driver app

  @regression
  Scenario Outline: As a Bungii driver with Pending payment status, I should not be able to login to application using valid credentials
    When I enter phoneNumber :<Username> and  Password :<Password>
    And I click "Log In" button on Log In screen on driver app
    Then I should see "<Expected Message>" on Log In screen on driver app
    And I should see "<Login Button Status>" on Log In screen on driver app
    Examples:
      | Scenario        | Username   | Password | Expected Message                                  | Login Button Status  |
      | PENDING PAYMENT | 9999991009 | Cci12345 | Your account registration is still under process. | LOGIN BUTTON ENABLED |

  @regression
    @test1
  Scenario Outline: As I enter wrong password 5 times, driver's account gets locked
    When I enter phoneNumber
    And I enter invalid password and click on "Log In" button for 5 times on Log In screen on driver app
    Then I should see "<Expected Message>" on Log In screen on driver app
    Examples:
      | Expected Message                                                                                                      |
      | Invalid login credentials. Your account has been locked. Please use the Forgot Password option to reset your account. |