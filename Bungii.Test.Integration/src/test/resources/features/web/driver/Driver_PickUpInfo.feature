@web
Feature: Driver_PickupInfo

  Background:
    Given I navigate to "Bungii Driver URL"
    Then I should be directed to "signup tab" on Driver portal
    When I click on "Login" link
    #Driver James JmUK
    And I enter driver Phone number as "9999991717" and valid password
    And I click "LOG IN button" on driver portal
    And I click Next on "Driver Details" page

  @regression
    @fail
  Scenario: Driver_PickupInfo_InvalidData_ExisitingNonFountainApplication
    When I click Next on "Pickup Information" page
     Then I should see blank fields validation on "Pickup Information" page
     When I enter "less truck image - i" data on Pickup Information page
     And I click Next on "Pickup Information" page
     Then I should see individual field validations on "Pickup Information - i" page
     When I enter "less truck image - ii" data on Pickup Information page
     And I click Next on "Pickup Information" page
     Then I should see individual field validations on "Pickup Information - ii" page


