@ios

Feature: FORGOT PASSWORD
  As I Bungii customer
  When I click Forgot password button
  I Should able to Reset my password

  Background:
    Given I am on the "LOG IN" page
  @FAILED2702
  @regression
  Scenario Outline: Verify Customer Is Alerted If Adds Invalid Phone Number During Forget Password Functionality - Scenario:<Scenario>
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
  Scenario Outline: erify Customer Is Alerted If Enters Invalid Password Or Sms Code During Forget Password Functionality - Scenario:<Scenario>
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
      | Invalid SMS code | {VALID USER} | Cci12345     | invalid  | INVALID SMS CODE             |

  @regression
  Scenario Outline: Verify Customer Can Change My Password Using Forgot Password Functionality
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

  @regression
  Scenario Outline: Verify Resend OTP Functionality On The Forgot Password Screen
    When I click "Forgot Password" button on "LOG IN" screen
    When I Enter "8877995510" value in "Phone Number" field in "FORGOT PASSWORD" Page
    And I click "SEND" button on "FORGOT PASSWORD" screen
    And I Get SMS CODE for "8877995510" number
    And I click "RESEND" button on "Forgot Password" screen
    Then I verify that new sms code is different then existing code for "8877995510"
    Then user is alerted for "SMS CODE SENT"
    And I Enter "old" value in "sms code" field in "FORGOT PASSWORD" Page
    And I Enter "<New Password>" value in "new password" field in "FORGOT PASSWORD" Page
    And I click "Continue" button on "Forgot Password" screen
    Then user is alerted for "INVALID SMS CODE"
    And I Get SMS CODE for "8877995510" number
    And I Enter "valid" value in "sms code" field in "FORGOT PASSWORD" Page
    And I click "Continue" button on "Forgot Password" screen
    Then user is alerted for "<Expected Message>"
    And I should be navigated to "Home" screen
    And I Select "LOGOUT" from Customer App menu

    Examples:
      | Scenario    | Value        | New Password | Expected Message        |
      | VALID PHONE | {VALID USER} | Cci12345     | PASSWORD CHANGE SUCCESS |


  @regression
  Scenario: Verify Customer Is Able To Login And Complete Bungii After Updating Password Using Forgot Password On Being Locked Out

    When I enter Username :8877995510 and  Password :cci12345
    And I click "Log In" button on "Log In" screen
    Then Alert message with INVALID_PASSWORD text should be displayed
    When I accept Alert message

    When I enter Username :8877995510 and  Password :cci12345
    And I click "Log In" button on "Log In" screen
    Then Alert message with INVALID_PASSWORD text should be displayed
    When I accept Alert message

    When I enter Username :8877995510 and  Password :cci12345
    And I click "Log In" button on "Log In" screen
    Then user is alerted for "3 OUT OF 5 ATTEMPT"
    When I enter Username :8877995510 and  Password :cci12345
    And I click "Log In" button on "Log In" screen
    Then Alert message with INVALID_PASSWORD text should be displayed
    When I accept Alert message
    When I enter Username :8877995510 and  Password :cci12345
    And I click "Log In" button on "Log In" screen
    Then user is alerted for "USER ACCOUNT LOCKED"
#6th attempts with correct password
    # commented this as , locked user are able to login in QA_AUTO
 #   When I enter Username :8877995510 and  Password :Cci12345
 #   And I click "Log In" button on "Log In" screen
 #   Then user is alerted for "USER ACCOUNT LOCKED"

    When I click "Forgot Password" button on "LOG IN" screen
    When I Enter "8877995510" value in "Phone Number" field in "FORGOT PASSWORD" Page
    And I click "SEND" button on "FORGOT PASSWORD" screen
    And I Get SMS CODE for "8877995510" number
    And I Enter "valid" value in "sms code" field in "FORGOT PASSWORD" Page
    And I Enter "Cci12345" value in "new password" field in "FORGOT PASSWORD" Page

    And I click "Continue" button on "Forgot Password" screen
    Then user is alerted for "PASSWORD CHANGE SUCCESS"
    And I should be navigated to "Home" screen
    And I Select "LOGOUT" from Customer App menu
