@web
Feature: Driver_RegistrationCompletion

  Background:
    Given I navigate to "Bungii Driver URL"
    Then I should be directed to "signup tab" on Driver portal
    When I enter "valid" details on Signup page
    And I enter "unique" driver phone number on Signup page
    And I click "Signup button" on driver portal
    And I enter "correct" verification code
    And I click "Submit verification code" on driver portal
    And I should see "Logged in user name" on Driver Registration
    And I click "Continue Registration" on driver portal
    And I enter "valid" data on Driver Details page
    And I click Next on "Driver Details" page
    And I enter "valid" data on Pickup Information page
    And I click Next on "Pickup Information" page
    And I enter "valid" data on Documentation page
    And I click Next on "Documentation" page
    And I enter "valid" data on Bank Details page
    And I click Next on "Bank Details" page
  @TEST1
  @regression
  Scenario: Driver_Terms_AgreeUnchecked
    When I click Next on "Terms & Conditions" page
    Then I should see blank fields validation on "Terms & Conditions" page

  @regression
  Scenario: Driver_VideoTraining_ViewedUnchecked
    When I click "I agree to the Terms and Conditions" on driver portal
    And I click Next on "Terms & Conditions" page
    Then I should be directed to "Video Training" on Driver portal
    When I click Next on "Video Training" page
    Then I should see blank fields validation on "Video Training" page

