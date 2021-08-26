@android

Feature: Driver Home screen

  Background:
    Given I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist

  @regression
  Scenario: Verify Driver Default Status Is Set To Offline After Login
    Then the status of the driver should be "Offline"
    
  @regression
  Scenario: Verify Driver Information Is Populated Correctly On Driver Dashboard Screen
    Then The "name" for "valid" driver should be correctly displayed
    #And The "Vehicle info" for "valid" driver should be correctly displayed - Removed in Sprint 48 - Core-2844
    And The "rating" for "valid" driver should be correctly displayed

  @regression
    #stable
  Scenario: Verify Driver Go Online button And Available Trips Link On Driver Dashboard Screen
    And I wait for "1" mins
    When I click "Go Online" button on Home screen on driver app
    Then The title of button should change to "Go Offline" on driver app
    And Info text should be updated
    And The navigation title should change to "Online"

    When I click "Available Bungiis" button on Home screen on driver app
    Then the "AVAILABLE BUNGIIS" page is opened
    