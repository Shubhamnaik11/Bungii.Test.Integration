@android

Feature: Driver Home screen

  Background:
    Given I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid" driver

  @regression
  Scenario: Verify Driver Default Status Is Set To Offline After Login
    Then the status of the driver should be "Offline"

    #Not able to verify Rating value for android, Just ratting bar is validated

  @regression
  Scenario: Verify Driver Information Is Populated Correctly On Driver Dashboard Screen
    Then The "name" for "valid" driver should be correctly displayed
    And The "Vehicle info" for "valid" driver should be correctly displayed
    And The "rating" for "valid" driver should be correctly displayed

  @regression
  Scenario: Verify Driver Go Online button And Available Trips Link On Driver Dashboard Screen
    When I click "Go Online" button on Home screen on driver app
    Then The title of button should change to "Go Offline" on driver app
    And Info text should be updated
    And The navigation title should change to "Online"

    When I click "Available Bungiis" button on Home screen on driver app
    Then I should be navigated to Available Bungiis screen on driver app