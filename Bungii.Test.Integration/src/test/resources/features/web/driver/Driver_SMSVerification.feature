Feature: Driver_SMSVerification

  Background:
    Given I navigate to "Bungii Driver URL"
    Then I should be directed to "signup tab" on Driver portal
    When I enter "valid" details on Signup page
    And I enter "unique" driver phone number on Signup page
    And I click "Signup button" on driver portal
    Then I should be directed to "phone verification page" on Driver portal
  @Web
  Scenario: DriverSignup_Valid_Verification Code
    When I enter "correct" verification code
    And I click "Submit verification code" on driver portal
    Then I should be directed to "Verification Successful page" on Driver portal
    And I should see "Logged in user name" on Driver Registration
    When I click "Continue Registration" on driver portal
    Then I should be directed to "Driver Details page" on Driver portal
  @Web
  Scenario: DriverSignUp_Resend_VerificationCode
    When I click "Resend verification code" on driver portal
    Then I should see "new verification code" on Driver Registration
  @Web
  Scenario: DriverSignUp_Blank_VerificationCode
    When I enter "empty" verification code
    And I click "Submit verification code" on driver portal
    Then I should see "validation for blank verification code" on Driver Registration
  @Web
  Scenario: DriverSignUp_Incorret_VerificationCode
    When I enter "incorrect" verification code
    And I click "Submit verification code" on driver portal
    Then I should see "validation for incorrect verification code" on Driver Registration
