@webundertest
Feature: Driver_BankDetails

  Background:
    Given I am logged in as driver
    When I click Next on "Driver Details" page
    Then I should be directed to "Pickup Info page" on Driver portal
    When I click Next on "Pickup Information" page
    Then I should be directed to "Documentation page" on Driver portal
    When I click Next on "Documentation" page
    Then I should be directed to "Bank Details page" on Driver portal


  @reworkneeded
  Scenario: Driver_BankDetails_Blank
    When I click Next on "Bank Details" page
    Then I should see blank fields validation on "Bank Details" page

  @reworkneeded
  Scenario: Driver_BankDetails_Invalid
    When I enter "short bank account" data on Bank Details page
    And I click Next on "Bank Details" page
    Then I should see individual field validations on "bank account on Bank Details" page
    When I enter "invalid" data on Bank Details page
    And I click Next on "Bank Details" page
    Then I should see individual field validations on "Bank Details" page