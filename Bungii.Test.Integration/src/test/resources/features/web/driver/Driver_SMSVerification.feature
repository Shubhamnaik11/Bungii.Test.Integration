@web
Feature: Driver_SMSVerification

  Background:
    Given I navigate to "Bungii Driver URL"
    Then I should be directed to "signup tab" on Driver portal
    When I enter "valid" details on Signup page
    And I enter "unique" driver phone number on Signup page
    And I click "Signup button" on driver portal
    Then I should be directed to "phone verification page" on Driver portal

  #@ready
   #@failed
  @knownissue
  Scenario: Verify Resend Verification Code on Driver Signup
    When I click "Resend verification code" on driver portal
    Then I should see "new verification code" on Driver Registration

  #@ready
  @knownissue
  Scenario: Verify Blank Verification Code Validation on Driver Signup
    When I enter "empty" verification code
    And I click "Submit verification code" on driver portal
    Then I should see "validation for blank verification code" on Driver Registration

  #@ready
  @knownissue
  Scenario: Verify Incorrect Verification Code Validation on Driver Signup
    When I enter "incorrect" verification code
    And I click "Submit verification code" on driver portal
    Then I should see "validation for incorrect verification code" on Driver Registration
