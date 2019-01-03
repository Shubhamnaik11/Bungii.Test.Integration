Feature: Driver_PickupInfo

  Background:
    Given I am logged in as driver
    When I click Next on "Driver Details" page
    Then I should be directed to "Pickup Info page" on Driver portal

  @Web
  Scenario: Driver_PickupInfo_Valid
    When I enter "valid" data on Pickup Information page
    And I click Next on "Pickup Information" page
    Then I should be directed to "Documentation page" on Driver portal

  @Web
  Scenario: Driver_PickupInfo_Blank
    When I click Next on "Pickup Information" page
    Then I should see blank fields validation on "Pickup Information" page

  @Web
  Scenario: Driver_PickupInfo_LessThan3Images
    When I enter "less truck image - i" data on Pickup Information page
    And I click Next on "Pickup Information" page
    Then I should see individual field validations on "Pickup Information - i" page
    When I enter "less truck image - ii" data on Pickup Information page
    And I click Next on "Pickup Information" page
    Then I should see individual field validations on "Pickup Information - ii" page