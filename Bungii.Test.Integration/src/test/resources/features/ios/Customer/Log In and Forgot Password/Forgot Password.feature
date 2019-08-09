@ios
@NONBUNGII
@S29READY
Feature: FORGOT PASSWORD
  As I Bungii customer
  When I click Forgot password button
  I Should able to Reset my password

  Background:
  #  When I Switch to "driver" application on "same" devices
  #  When I Select "LOGOUT" from driver App menu
    Given I am on the "LOG IN" page
  @update1
  @regression
  Scenario Outline: As Bungii customer , I should able to alerted  if i  add invalid phone number during forget password functionality . Scenario:<Scenario>
    When I click "Forgot Password" button on "LOG IN" screen
    Then "FORGOT PASSWORD INFORMATION" message should be displayed on "FORGOT PASSWORD" page
    When I Enter "<Value>" value in "Phone Number" field in "FORGOT PASSWORD" Page
    And I click "SEND" button on "FORGOT PASSWORD" screen
    Then user is alerted for "<Expected Message>"

    Examples:
      | Scenario                  | Value        | Expected Message     |
      | Phone Number less than 10 | 940396       | FAILED TO SEND TOKEN |
      | Invalid Phone Number      | 212121212121 | FAILED TO SEND TOKEN |

  @regression
  Scenario Outline: As Bungii customer , I should able to alerted if I Enter invalid password or sms code during forget password functionality. Scenario:<Scenario>
    When I click "Forgot Password" button on "LOG IN" screen
    Then "FORGOT PASSWORD INFORMATION" message should be displayed on "FORGOT PASSWORD" page
    When I Enter "<Value>" value in "Phone Number" field in "FORGOT PASSWORD" Page
    And I click "SEND" button on "FORGOT PASSWORD" screen
    And I Get SMS CODE for "<Value>" number
    And I Enter "<SMS CODE>" value in "sms code" field in "FORGOT PASSWORD" Page
    And I Enter "<New Password>" value in "new password" field in "FORGOT PASSWORD" Page
    And I click "Continue" button on "Forgot Password" screen
    Then user is alerted for "<Expected Message>"
    And I should be navigated to "Forgot Password" screen
    And I click "Back" button on "Forgot Password" screen
    And I click "Back" button on "Forgot Password" screen

    Examples:
      | Scenario         | Value        | New Password | SMS CODE | Expected Message             |
      | Invalid password | {VALID USER} | Cci1         | valid    | INVALID PASSWORD WHILE RESET |
      | Invalid SMS code | {VALID USER} | Cci12345     | invalid  | INVALID SMS CODE |

  @regression
  Scenario Outline: As Bungii customer , I should able to change my password using forgot password functionality
    When I click "Forgot Password" button on "LOG IN" screen
    Then "FORGOT PASSWORD INFORMATION" message should be displayed on "FORGOT PASSWORD" page
    When I Enter "<Value>" value in "Phone Number" field in "FORGOT PASSWORD" Page
    And I click "SEND" button on "FORGOT PASSWORD" screen
    And I Get SMS CODE for "<Value>" number
    And I Enter "valid" value in "sms code" field in "FORGOT PASSWORD" Page
    And I Enter "<New Password>" value in "new password" field in "FORGOT PASSWORD" Page
    And I click "Continue" button on "Forgot Password" screen
    Then user is alerted for "<Expected Message>"
    And I should be navigated to "Home" screen

    Examples:
      | Scenario    | Value        | New Password | Expected Message        |
      | VALID PHONE | {VALID USER} | Cci12345     | PASSWORD CHANGE SUCCESS |
