@ios
Feature: Driver Home screen

  Background:
    Given I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid" driver
  @FAILED2702
  @regression
  Scenario: To verify that the default status mode is Offline
    Then the status of the driver should be "Offline"

  @regression
  Scenario: To Verify driver information is populated correctly
    Then The "name" for "valid" driver should be correctly displayed
    And The "Vehicle info" for "valid" driver should be correctly displayed
    And The "rating" for "valid" driver should be correctly displayed
    And I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "drivers" from admin sidebar
    And I search driver from drivers
    Then rattings should be correctly displayed on grid
    Then rattings should be correctly displayed on profile


  @regression
  Scenario: Verify the buttons on page
    When I click "Go Online" button on "Home" screen on driverApp
    Then The title of button should change to "Go Offline" on driverApp
    And Info text should be updated
    And The navigation title should change to "Online"

    When I click "Available Trips" button on "Home" screen on driverApp
    Then I should be navigated to "Available Trips" screen on driverApp