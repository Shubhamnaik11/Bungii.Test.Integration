@FORGOT
Feature: FORGOT
  I want to use this template for my feature file

  Background: 
    Given I am on the "LOG IN" page
 
  @FAILED
  @FORGOT_1
  Scenario Outline: As Bungii customer , I should able to alerted  if i  add invalid phone number during forget password functionalitu . Scenario:<Scenario>
    When I click "Forgot Password" button on "LOG IN" screen
    Then "FORGOT PASSWORD INFORMATION" message should be displayed on "FORGOT PASSWORD" page
    And I Enter "<Value>" value in "Phone Number" field in "FORGOT PASSWORD" Page
    When I click "SEND" button on "FORGOT PASSWORD" screen
    Then user is alerted for "<Expected Message>"

    Examples: 
      | Scenario                  | Value        | Expected Message     |
      | Phone Number less than 10 |       940396 | FAILED TO SEND TOKEN |
      | INVALID PHONE             | 212121212121 | FAILED TO SEND TOKEN |
  @FORGOT_2
  Scenario Outline: As Bungii customer , I should able to alerted if I Enter invalid password during forget password functionality
    When I click "Forgot Password" button on "LOG IN" screen
    Then "FORGOT PASSWORD INFORMATION" message should be displayed on "FORGOT PASSWORD" page
    And I Enter "<Value>" value in "Phone Number" field in "FORGOT PASSWORD" Page
    When I click "SEND" button on "FORGOT PASSWORD" screen
    And I Get SMS CODE for "<Value>"
    And I Enter "valid" value in "sms code" field in "FORGOT PASSWORD" Page
    And I Enter "<New Password>" value in "new password" field in "FORGOT PASSWORD" Page
    When I click "Continue" button on "Forgot Password" screen
    Then user is alerted for "<Expected Message>"
    Then I should be navigated to "Forgot Password" screen
    When I click "Back" button on "Forgot Password" screen
    When I click "Back" button on "Forgot Password" screen

    Examples: 
      | Scenario    | Value      | New Password | Expected Message |
      | INVALID_NEWPASSWORD | 9434833770 | Cci1     | INVALID PASSWORD WHILE RESET |

  @FORGOT_4
  Scenario Outline: As Bungii customer , I should able to alerted if I enter invalid sms code while using forget password functonality
    When I click "Forgot Password" button on "FORGOT PASSWORD" screen
    Then "FORGOT PASSWORD INFORMATION" message should be displayed on "FORGOT PASSWORD" page
    And I Enter "<Value>" value in "Phone Number" field in "FORGOT PASSWORD" Page
    When I click "SEND" button on "LOG IN" screen
    And I Get SMS CODE for "<Value>"
    And I Enter "invalid" value in "sms code" field in "FORGOT PASSWORD" Page
    And I Enter "<New Password>" value in "new password" field in "FORGOT PASSWORD" Page
    When I click "Continue" button on "Forgot Password" screen
    Then user is alerted for "<Expected Message>"
    Then I should be navigated to "Forgot Password" screen
    When I click "Back" button on "Forgot Password" screen
    When I click "Back" button on "Forgot Password" screen

    Examples: 
      | Scenario    | Value      | New Password | Expected Message |
      | VALID PHONE | 9434833770 | Cci12345     | INVALID SMS CODE |

  @FORGOT_3
  Scenario Outline: As Bungii customer , I should able to change my password using forgot password functionality
    When I click "Forgot Password" button on "LOG IN" screen
    Then "FORGOT PASSWORD INFORMATION" message should be displayed on "FORGOT PASSWORD" page
    And I Enter "<Value>" value in "Phone Number" field in "FORGOT PASSWORD" Page
    When I click "SEND" button on "FORGOT PASSWORD" screen
    And I Get SMS CODE for "<Value>"
    And I Enter "valid" value in "sms code" field in "FORGOT PASSWORD" Page
    And I Enter "<New Password>" value in "new password" field in "FORGOT PASSWORD" Page
    When I click "Continue" button on "Forgot Password" screen
    Then user is alerted for "<Expected Message>"
    Then I should be navigated to "Home" screen

    Examples: 
      | Scenario    | Value      | New Password | Expected Message        |
      | VALID PHONE | 9999977558 | Cci12345     | PASSWORD CHANGE SUCCESS |
