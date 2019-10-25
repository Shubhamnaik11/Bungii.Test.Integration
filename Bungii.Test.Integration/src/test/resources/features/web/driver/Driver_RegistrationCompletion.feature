@web
Feature: Driver_RegistrationCompletion

  Background:
    Given I navigate to "Bungii Driver URL"
    Then I should be directed to "signup tab" on Driver portal
    When I click on "Login" link
    #Driver Hal Drake
    And I enter driver Phone number as "9999990265" and valid password
    And I click "LOG IN button" on driver portal
    And I click Next on "Driver Details" page
    And I click Next on "Pickup Information" page
    And I click Next on "Documentation" page
    And I click Next on "Bank Details" page

  @regression
  Scenario: Driver_Terms_AgreeUnchecked_ExisitingNonFountainApplication
    When I click Next on "Terms & Conditions" page
    Then I should see blank fields validation on "Terms & Conditions" page

  @regression
  Scenario: Driver_VideoTraining_ViewedUnchecked_ExisitingNonFountainApplication
    When I click "I agree to the Terms and Conditions" on driver portal
    And I click Next on "Terms & Conditions" page
    Then I should be directed to "Video Training" on Driver portal
    When I click Next on "Video Training" page
    Then I should see blank fields validation on "Video Training" page

