Feature: Driver_ForgotPassword

  Background:
    Given I navigate to "Bungii Driver URL"
    When I click "LOG IN link" on driver portal
    And I click "Forgot Password" on driver portal
  @Web
  Scenario: Driver_ForgotPassword_CorrectPage
    Then I should be directed to "Forgot Password tab" on Driver portal
    When I click "Back to Login" on driver portal
    Then I should be directed to "LOG IN tab" on Driver portal
  @Web
  Scenario: Driver_ForgotPassword_Blank
    When I click "Send Verification Code" on driver portal
    Then I should see blank fields validation on "Forgot Password" page
  @Web
  Scenario: Driver_ForgotPassword_InvalidPhone
    When I enter "invalid" Phone Number on Forgot password page
    And I click "Send Verification Code" on driver portal
    Then driver should see "validation for invalid phone" during phone verification