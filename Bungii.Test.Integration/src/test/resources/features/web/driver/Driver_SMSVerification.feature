@web
Feature: Driver_SMSVerification

  Background:
    Given I navigate to "Bungii Driver URL"
    Then I should be directed to "signup tab" on Driver portal
    When I enter "valid" details on Signup page
    And I enter "unique" driver phone number on Signup page
    And I click "Signup button" on driver portal
    Then I should be directed to "phone verification page" on Driver portal

  @regression
  Scenario: DriverSignUp_Resend_VerificationCode
    When I click "Resend verification code" on driver portal
    Then I should see "new verification code" on Driver Registration

  @regression
  Scenario: DriverSignUp_Blank_VerificationCode
    When I enter "empty" verification code
    And I click "Submit verification code" on driver portal
    Then I should see "validation for blank verification code" on Driver Registration

  @regression
  Scenario: DriverSignUp_Incorrect_VerificationCode
    When I enter "incorrect" verification code
    And I click "Submit verification code" on driver portal
    Then I should see "validation for incorrect verification code" on Driver Registration
