@android
Feature: Customer Home screen

  Background:
    Given I am on Customer logged in Home page

  @regression
  Scenario: To Verify clear text button on Pick up and Drop location
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

  @regression
  Scenario: To check that drop off field is displayed only when pickup address is set.
    When I tap on "Menu" > "Home" link
    And I select "Pick up" location
    Then "Drop Off" address text box should be displayed on app screen

  @regression
  Scenario: To check if ETA bar/picker remains on map when pickup address has been cleared
    When I tap on "Menu" > "Home" link
    And I select "Pick up" location
    Then The ETA bar is seen on screen
    When I clear "Pick up" location
    Then The ETA bar is seen on screen

  @regression
  Scenario: To check if driver ETA is displayed when there are drivers present in 30 min radius of pickup location
    When I tap on "Menu" > "Home" link
    And I select "Pick up" location to check driver within 30mins
    Then The ETA bar is seen on screen with less then 30 mins

  @regression
  Scenario: To check that customer is allowed to set pickup and drop off locations when  No driver ETA is found (within geofence)
    When I tap on "Menu" > "Home" link
    And I enter "Goa pickup and dropoff locations" on Bungii estimate screen
    And I tap on "Get Estimate button" on Bungii estimate
    And I add "1" photos to the Bungii
    And I add loading/unloading time of "30 mins"
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    Then for a Bungii I should see "Bungii search screen"
    And I tap on "Cancel during search" on Bungii estimate

  @regression
  Scenario: Long Haul(>150 miles) alert shown (dist bet. pickup and drop off should be >150 miles)
      When I tap on "Menu" > "Home" link
      And I enter "Atlanta pickup and Indiana dropoff location" on Bungii estimate screen
      Then I get the error popup message for "More than 150 miles trip"

  @regression
  Scenario: To check ETA box when geofence Not active
    When I tap on "Menu" > "Home" link
    And I enter "Non Geofence pickup location" on Bungii estimate screen
    Then I get the error popup message for "Non Geofence Location"