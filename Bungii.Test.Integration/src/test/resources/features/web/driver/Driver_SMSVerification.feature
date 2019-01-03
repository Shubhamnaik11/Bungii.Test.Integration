@web
Feature: Driver_SMSVerification

  Background:
    Given I navigate to "Bungii Driver URL"
    Then I should be directed to "signup tab" on Driver portal
    When I enter "valid" details on Signup page
    And I enter "unique" driver phone number on Signup page
    And I click "Signup button" on driver portal
    Then I should be directed to "phone verification page" on Driver portal

  @sanity
  @regression1
  Scenario: Verify Driver Signup and Form completion
    When I enter "correct" verification code
    And I click "Submit verification code" on driver portal
    Then I should be directed to "Verification Successful page" on Driver portal
    And I should see "Logged in user name" on Driver Registration
    When I click "Continue Registration" on driver portal
    Then I should be directed to "Driver Details page" on Driver portal
    When I enter "valid" data on Driver Details page
    And I click Next on "Driver Details" page
    Then I should be directed to "Pickup Info page" on Driver portal
    When I enter "valid" data on Pickup Information page
    And I click Next on "Pickup Information" page
    Then I should be directed to "Documentation page" on Driver portal
    When I enter "valid" data on Documentation page
    And I click Next on "Documentation" page
    Then I should be directed to "Bank Details page" on Driver portal
    When I enter "valid" data on Bank Details page
    And I click Next on "Bank Details" page
    Then I should be directed to "Terms & Conditions" on Driver portal
    When I click Next on "Terms & Conditions" page
    And I click "I have viewed the entire video" on driver portal
    And I click Next on "Video Training" page
    Then I should be directed to "Finish" on Driver portal
    When I click "Continue on Finish page" on driver portal
    Then I should be directed to "Dashboard" on Driver portal
    Then the driver logout from dashboard

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
  Scenario: DriverSignUp_Incorret_VerificationCode
    When I enter "incorrect" verification code
    And I click "Submit verification code" on driver portal
    Then I should see "validation for incorrect verification code" on Driver Registration
