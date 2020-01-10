@android

Feature: EstimateBungii

  Background:
    Given I am on customer Log in page
    And I am logged in as "no promocode" customer

  @regression
  Scenario: To check if the information icons display correct information
  #  When I enter "current location in pickup and dropoff fields" on Bungii estimate
    And I enter "atlanta pickup and dropoff locations" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I add loading/unloading time of "30 mins"
    Then "Load/Upload Time" information icon should display correct information
     #removed as part of sprint 32
  #  And "Total estimate" information icon should display correct information
    And "Time" information icon should display correct information

  @regression
  Scenario: When I cancel on Estimate Page , I should be navigated to Home screen
    And I enter "atlanta pickup and dropoff locations" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I add loading/unloading time of "30 mins"
    And I tap on "back" on Bungii estimate
    Then Trip Information should be correctly displayed on CUSTOMER HOME screen

  @regression
  Scenario: I should able to see all elements of Estimate page
    When I Select "ACCOUNT" from customer app menu list
    Then I get customer account details
    When I Select "HOME" from customer app menu list
    And I enter "atlanta pickup and dropoff locations" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I add loading/unloading time of "30 mins"
    Then I should see "all elements" on Bungii estimate

  @regression
  Scenario: Verify Load/unload time functionality . Check if Estimate cost is re calculated
    When I Select "HOME" from customer app menu list
    And I enter "valid pickup and dropoff locations" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    Then check if I have ability to select different load time and Estimate cost is re calculated

  @regression
  Scenario: When there are no driver available for on demand Bungii , and Customer choose for Scheduled Bungii instead then he should be navigated to Estimate screen with fields having previous details
    And I enter "atlanta pickup and dropoff locations" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I add loading/unloading time of "30 mins"
    And I get Bungii details on Bungii Estimate
    And I add "1" photos to the Bungii
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    Then for a Bungii I should see "Bungii search screen"
    When I wait for SEARCHING screen to disappear
    Then "DRIVER NOT AVAILABLE" page should be opened
    When I tap "Ok" button on DRIVER NOT AVAILABLE screen
    Then I should see "Schedule Bungii option" on DRIVER NOT AVAILABLE screen
    When I tap "Schedule Bungii" button on DRIVER NOT AVAILABLE screen
    Then "Estimate" page should be opened
    When I add loading/unloading time of "30 mins"
    Then I should see "previous values" on Bungii estimate
    And I tap on "back" on Bungii estimate

  @regression
  Scenario: Estimate_AddPromoCode
    And I enter "atlanta pickup and dropoff locations" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I add loading/unloading time of "30 mins"
    And I tap on "Promo Code" on Bungii estimate
    And I add "valid" PromoCode
    And I tap "Add" on Promos page
    Then I should see "promocode added" on Bungii estimate page

  @regression
  Scenario: To check that Customer is able to add at least one and maximum 4 images of Items
    And I enter "kansas pickup and dropoff locations" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    Then "Estimate" page should be opened
    And I add "1" photos to the Bungii
    And I add "4" photos to the Bungii

  @regression1
  Scenario: To check that when duo is selected, Time is selected to next available  scheduled time (correct Timezone)
    When I request "duo" Bungii as a customer in "Kansas" geofence
      | Bungii Time   | Customer Phone | Customer Name                      | Customer Password |
      | NEXT_POSSIBLE | 8805368840     | Testcustomertywd_appleRicha Test   | Cci12345          |
    Given I am on customer Log in page
    When I enter customers "8805368840" Phone Number
    And I enter customers "valid" Password
    And I tap on the "Log in" Button on Login screen
    And I tap on "Menu" > "MY BUNGIIS" link
    Then I verify that selected time is next available time
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | 8805368840 |    |



