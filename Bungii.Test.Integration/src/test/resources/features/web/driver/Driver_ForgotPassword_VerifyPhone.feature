Feature: Driver_ForgotPassword_VerifyPhone

  Background:
    Given I navigate to "Bungii Driver URL"
    When I click "LOG IN link" on driver portal
    And I click "Forgot Password" on driver portal
    When I enter "valid" Phone Number on Forgot password page
    And I click "Send Verification Code" on driver portal
  @Web
  Scenario: Driver_ForgotPassword_VerifyPhone_CorrectPageDetails
    Then I should be directed to "Verify Your Phone tab" on Driver portal
    And driver should see "correct phone number" during phone verification
  @Web
  Scenario: Driver_ForgotPassword_VerifyPhone_Blank
    When I click "Reset Password" on driver portal
    Then I should see blank fields validation on "Verify your phone" page
  @Web
  Scenario: Driver_ForgotPassword_VerifyPhone_ResendCode
    When I click "Resend Code on Verify your phone page" on driver portal
    Then driver should see "new verification code" during phone verification
  @Web
  Scenario: Driver_ForgotPassword_VerifyPhone_InvalidVerificationCode
    When I enter "invalid" code on Verify your phone page
    And I enter "valid" new password on Verify your phone page
    And I enter "correct" password in Confirm password field
    And I click "Reset Password" on driver portal
    Then driver should see "validation for invalid code" during phone verification
  @Web
  Scenario: Driver_ForgotPassword_VerifyPhone_InvalidPasswords
    When I enter "short" new password on Verify your phone page
    And I enter "correct" password in Confirm password field
    And I click "Reset Password" on driver portal
    Then driver should see "validations for password fields" during phone verification
    When I enter "invalid" new password on Verify your phone page
    Then driver should see "validation for invalid password" during phone verification
  @Web
  Scenario: Driver_ForgotPassword_Success
    When I enter "valid" code on Verify your phone page
    And I enter "valid" new password on Verify your phone page
    And I enter "correct" password in Confirm password field
    And I click "Reset Password" on driver portal
    Then I should be directed to "LOG IN tab" on Driver portal
    Then driver should see "success message driver login page" during phone verification