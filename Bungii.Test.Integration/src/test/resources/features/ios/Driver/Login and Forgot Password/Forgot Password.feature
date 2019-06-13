@ios
@NONBUNGII

  @DriverForgotPassword
Feature: FORGOT PASSWORD
  As a Bungii driver
  When I click Forgot password button
  I Should be able to Reset my password

  Background:
    Given I am on the "LOG IN" page on driverApp

  @regression
  Scenario Outline: As Bungii driver, I should be alerted if I add invalid phone number during Forgot Password functionality . Scenario:<Scenario>
    When I click "Forgot Password" button on "LOG IN" screen driverApp
    Then "FORGOT PASSWORD INFORMATION" message should be displayed on "FORGOT PASSWORD" page driverApp
    And I Enter "<Value>" value in "Phone Number" field in "FORGOT PASSWORD" Page driverApp
    When I click "SEND" button on "FORGOT PASSWORD" screen driverApp
    Then user is alerted for "<Expected Message>" driverApp

    Examples:
      | Scenario                  | Value        | Expected Message     |
      | Phone Number less than 10 | 940396       | FAILED TO SEND TOKEN |
      | Invalid Phone Number      | 212121212121 | FAILED TO SEND TOKEN |

  @regression
  Scenario Outline: As Bungii driver , I should be alerted if I Enter invalid password or sms code during Forgot Password functionality. Scenario:<Scenario>
    When I click "Forgot Password" button on "LOG IN" screen driverApp
    Then "FORGOT PASSWORD INFORMATION" message should be displayed on "FORGOT PASSWORD" page driverApp
    And I Enter "<Value>" value in "Phone Number" field in "FORGOT PASSWORD" Page driverApp
    When I click "SEND" button on "FORGOT PASSWORD" screen driverApp
    And I Get SMS CODE for "<Value>" number driver
    And I Enter "<SMS CODE>" value in "sms code" field in "FORGOT PASSWORD" Page driverApp
    And I Enter "<New Password>" value in "new password" field in "FORGOT PASSWORD" Page driverApp
    When I click "Continue" button on "Forgot Password" screen driverApp
    Then user is alerted for "<Expected Message>" driverApp
    Then I should be navigated to "Forgot Password" screen driverApp
    When I click "Back" button on "Forgot Password" screen driverApp

    Examples:
      | Scenario         | Value        | New Password | SMS CODE | Expected Message             |
      | Invalid password | {VALID USER} | Cci1         | valid    | INVALID PASSWORD WHILE RESET |
      | Invalid SMS code | {VALID USER} | Cci12345     | invalid  | INVALID SMS CODE |

  @regression
  Scenario Outline: As Bungii driver , I should be able to change my password using Forgot Password functionality
    When I click "Forgot Password" button on "LOG IN" screen driverApp
    Then "FORGOT PASSWORD INFORMATION" message should be displayed on "FORGOT PASSWORD" page driverApp
    And I Enter "<Value>" value in "Phone Number" field in "FORGOT PASSWORD" Page driverApp
    When I click "SEND" button on "FORGOT PASSWORD" screen driverApp
    And I Get SMS CODE for "<Value>" number driver
    And I Enter "valid" value in "sms code" field in "FORGOT PASSWORD" Page driverApp
    And I Enter "<New Password>" value in "new password" field in "FORGOT PASSWORD" Page driverApp
    When I click "Continue" button on "Forgot Password" screen driverApp
    Then user is alerted for "<Expected Message>" driverApp
    Then I should be navigated to "Home" screen driverApp

    Examples:
      | Scenario    | Value        | New Password | Expected Message        |
      | VALID PHONE | {VALID USER} | Cci12345     | PASSWORD CHANGE SUCCESS |
