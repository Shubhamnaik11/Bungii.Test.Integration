@android
Feature: Customer Home screen

  Background:
    Given I am on Customer logged in Home page

  @regression
  @ready
  Scenario: Verify Clear Text Button On Pickup And Dropoff Location
    When I tap on "Menu" > "Home" link
    And I select "Pick up" location
    Then "Pick up" address should be displayed in text box
    And I tap "Pick Up Clear Text" on Home page
    And I tap "My location" on Home page
    Then current location should be present as pickup location
    When I select "Pick up" location
    Then "Pick up" address should be displayed in text box
    When I select "Drop" location
    Then "Drop" address should be displayed in text box
    When I tap "Drop Clear Text" on Home page
    Then "Drop" address should be empty
    When I select "Drop" location
    Then "Drop" address should be displayed in text box
    When I tap "Pick Up Clear Text" on Home page
    And I tap "My location" on Home page
    Then current location should be present as pickup location
    And "Drop" address should be empty

  #@regression
  @ready
  Scenario: Verify That Dropoff Field Is Displayed Only When Pickup Address Is Selected
    When I tap on "Menu" > "Home" link
    And I select "Pick up" location
    Then "Drop Off" address text box should be displayed on app screen

  #@regression
  @ready
  Scenario: Verify If ETA Bar Remains On Map When Pickup Address Is Cleared
    When I tap on "Menu" > "Home" link
    And I select "Pick up" location
    Then The ETA bar is seen on screen
    When I clear "Pick up" location
    Then The ETA bar is seen on screen

  #@regression
  @ready
  Scenario: Verify If Driver ETA Is Displayed When Drivers Within 30 min Of Radius From Pickup Location Is Available
    When I tap on "Menu" > "Home" link
    And I select "Pick up" location to check driver within 30mins
    Then The ETA bar is seen on screen with less then 30 mins

  #@regression
  @ready
  Scenario: Verify That Customer Is Allowed To Set Pickup And Dropoff Locations When No Driver ETA Is Found (Within Geofence)
    When I tap on "Menu" > "Home" link
    And I enter "Goa pickup and dropoff locations" on Bungii estimate screen
    And I tap on "Get Estimate button" on Bungii estimate
    And I add "1" photos to the Bungii
    And I add loading/unloading time of "30 mins"
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    Then for a Bungii I should see "Bungii search screen"
    And I tap on "Cancel during search" on Bungii estimate

  #@regression
  @ready
    @demo
  Scenario: Verify Long Haul(>150 miles) Alert Is Shown When Distance Between Pickup And Dropoff Is >150 Miles
      When I tap on "Menu" > "Home" link
      And I enter "Atlanta pickup and Indiana dropoff location" on Bungii estimate screen
      Then I get the error popup message for "More than 150 miles trip"

  #@regression
  @ready
  Scenario: Verify ETA Box When Geofence Is Not Active
    When I tap on "Menu" > "Home" link
    And I enter "Non Geofence pickup location" on Bungii estimate screen
    Then I get the error popup message for "Non Geofence Location"