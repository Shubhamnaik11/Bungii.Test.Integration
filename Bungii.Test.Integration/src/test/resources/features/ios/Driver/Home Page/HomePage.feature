@ios
Feature: Driver Home screen

  Background:
    Given I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist

  @regression
  Scenario: Verify Driver Default Status Is Set To Offline After Login
    Then the status of the driver should be "Offline"

  @regression
  Scenario: Verify Driver Information Is Populated Correctly On Driver Dashboard Screen
    Then The "name" for "valid" driver should be correctly displayed
    #And The "Vehicle info" for "valid" driver should be correctly displayed--removed in Sprint 48
    And The "rating" for "valid" driver should be correctly displayed
    And I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "drivers" from admin sidebar
    And I search driver from drivers
    Then rattings should be correctly displayed on grid
    Then rattings should be correctly displayed on profile


  @regression
  Scenario: Verify Driver Go Online button And Available Trips Link On Driver Dashboard Screen
    When I click "Offline" button on "Home" screen on driverApp
    Then The title of button should change to "Online" on driverApp
    And Info text should be updated
    #And The navigation title should change to "Online"---Sprint 48 Online/Offline title is removed now BUNGII will be shown

    When I click "AVAILABLE BUNGIIS" button on "Home" screen on driverApp
    Then I should be navigated to "AVAILABLE BUNGIIS" screen on driverApp