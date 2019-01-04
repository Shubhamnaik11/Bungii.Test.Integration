@web
Feature: Driver_BankDetails

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

  @regression
  Scenario: Driver_BankDetails_InvalidData
    When I click Next on "Bank Details" page
    Then I should see blank fields validation on "Bank Details" page
    When I enter "short bank account" data on Bank Details page
    And I click Next on "Bank Details" page
    Then I should see individual field validations on "bank account on Bank Details" page
    When I enter "invalid" data on Bank Details page
    And I click Next on "Bank Details" page
    Then I should see individual field validations on "Bank Details" page