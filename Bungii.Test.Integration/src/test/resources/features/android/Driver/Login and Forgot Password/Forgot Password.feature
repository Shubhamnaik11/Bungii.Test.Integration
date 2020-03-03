@android
Feature: FORGOT PASSWORD
  As a Bungii driver
  When I click Forgot password button
  I Should be able to Reset my password

  Background:
    Given I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app

  @regression

  Scenario Outline: Verify Driver Is Alerted If Invalid Phone Number Is Added During Forgot Password Functionality . Scenario:<Scenario>
    When I click "Forgot Password" button on Log In screen on driver app
    Then "FORGOT PASSWORD INFORMATION" message should be displayed on FORGOT PASSWORD page on driver app
    And I Enter "<Value>" value in "Phone Number" field in FORGOT PASSWORD Page on driver app
    When I click "SEND" button on FORGOT PASSWORD screen on driver app
    Then I should see "<Expected Message>"  on FORGOT PASSWORD on driver app
    And I should see "<Send Button Status>"  on FORGOT PASSWORD on driver app

    Examples:
      | Scenario                  | Value      | Expected Message                | Send Button Status   |
      | Phone Number less than 10 | 940396     | Please enter Phone number error | SEND BUTTON DISABLED |
      | Invalid Phone Number      | 2121212121 | FAILED TO SEND TOKEN            | SEND BUTTON ENABLED  |

  @regression

  Scenario Outline:  Verify Driver Is Alerted If Enters Invalid Password Or Sms Code During Forgot Password Functionality. Scenario:<Scenario>
    When I click "Forgot Password" button on Log In screen on driver app
    Then "FORGOT PASSWORD INFORMATION" message should be displayed on FORGOT PASSWORD page on driver app
    When I Enter "<Value>" value in "Phone Number" field in FORGOT PASSWORD Page on driver app
    And I click "SEND" button on FORGOT PASSWORD screen on driver app
    And I Get SMS CODE for "<Value>" number on driver app
    And I Enter "<SMS CODE>" value in "sms code" field in FORGOT PASSWORD Page on driver app
    And I Enter "<New Password>" value in "new password" field in FORGOT PASSWORD Page on driver app
    And I Enter "<New Password>" value in "confirm password" field in FORGOT PASSWORD Page on driver app
    And I click "CONTINUE" button on FORGOT PASSWORD screen on driver app
    Then I should see "<Expected Message>"  on FORGOT PASSWORD on driver app


    Examples:
      | Scenario         | Value        | New Password | SMS CODE | Expected Message             |
      | Invalid password | {VALID USER} | Cci1         | valid    | INVALID PASSWORD WHILE RESET |
      | Invalid SMS code | {VALID USER} | Cci12345     | invalid  | INVALID SMS CODE             |

  @regression

  Scenario Outline: Verify Driver Is Able To Change Password Using Forgot Password Functionality
    When I click "Forgot Password" button on Log In screen on driver app
    Then "FORGOT PASSWORD INFORMATION" message should be displayed on FORGOT PASSWORD page on driver app
    When I Enter "<Value>" value in "Phone Number" field in FORGOT PASSWORD Page on driver app
    And I click "SEND" button on FORGOT PASSWORD screen on driver app
    And I Get SMS CODE for "<Value>" number on driver app
    And I Enter "valid" value in "sms code" field in FORGOT PASSWORD Page on driver app
    And I Enter "<New Password>" value in "new password" field in FORGOT PASSWORD Page on driver app
    And I Enter "<Confirm Password>" value in "confirm password" field in FORGOT PASSWORD Page on driver app
    And I click "CONTINUE" button on FORGOT PASSWORD screen on driver app
    Then I should see "PASSWORD CHANGE SUCCESS"  on FORGOT PASSWORD on driver app
    And I should be navigated to Home screen on driver app

    Examples:
      | Scenario    | Value        | New Password | Confirm Password | Expected Message        |
      | VALID PHONE | {VALID USER} | Cci12345     | Cci12345         | PASSWORD CHANGE SUCCESS |
