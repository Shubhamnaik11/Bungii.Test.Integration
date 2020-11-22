@android

Feature: EstimateBungii

  Background:
    Given I am on customer Log in page
    And I am logged in as "no promocode" customer
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist

  @regression
  Scenario: Verify if the information icons display correct information On Bungii Confirmation Screen
    #When I enter "current location in pickup and dropoff fields" on Bungii estimate
    And I enter "atlanta pickup and dropoff locations" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I add loading/unloading time of "30 mins"
    Then "Load/Upload Time" information icon should display correct information
    #removed as part of sprint 32
    #And "Total estimate" information icon should display correct information
    And "Time" information icon should display correct information

  @regression
  Scenario: Verify Customer Home Screen Navigation Upon Cancellation On Estimate Screen
    And I enter "atlanta pickup and dropoff locations" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I add loading/unloading time of "30 mins"
    And I tap on "back" on Bungii estimate
    Then Trip Information should be correctly displayed on CUSTOMER HOME screen

  @regression
  Scenario: Verify All Fields Of Estimate Screen
    When I Select "ACCOUNT" from customer app menu list
    Then I get customer account details
    When I Select "HOME" from customer app menu list
    And I enter "atlanta pickup and dropoff locations" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I add loading/unloading time of "30 mins"
    Then I should see "all elements" on Bungii estimate

  @regression
  Scenario: Verify Load/Unload Time functionality And Estimate Cost Recalculation
    When I Select "HOME" from customer app menu list
    And I enter "valid pickup and dropoff locations" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    Then check if I have ability to select different load time and Estimate cost is re calculated

  #@regression
    @ready
  Scenario: Verify When Customer Switches From Ondemand To Scheduled Bungii On Account Of Unavailibility Of Driver Then Customer Is Taken To Estimate Screen With Prepoulated Existing Fields
    And I enter "atlanta pickup and dropoff locations" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I add loading/unloading time of "30 mins"
    And I get Bungii details on Bungii Estimate
    And I add "1" photos to the Bungii
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    Then for a Bungii I should see "Bungii search screen"
    When I wait for SEARCHING screen to disappear
    Then "SET PICKUP TIME" page should be opened
    #When I tap "Ok" button on DRIVER NOT AVAILABLE screen
    And I should see "Schedule Bungii option" on DRIVER NOT AVAILABLE screen
    When I tap "Schedule Bungii" button on DRIVER NOT AVAILABLE screen
    #Then "Estimate" page should be opened
    #When I add loading/unloading time of "30 mins"
    #Then I should see "previous values" on Bungii estimate
    Then I should be navigated to "Success!" screen
    And I click "Done" button on "Success" screen

  @regression
  Scenario: Verify Add Promo Code On Estimate Screen
    And I enter "atlanta pickup and dropoff locations" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I add loading/unloading time of "30 mins"
    And I tap on "Promo Code" on Bungii estimate
    And I add "valid" PromoCode
    And I tap "Add" on Promos page
    Then I should see "promocode added" on Bungii estimate page

  @regression
  Scenario: Verify Customer Can Add Minimum of One And Maximum Of Four Images Of Items
    And I enter "kansas pickup and dropoff locations" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    Then "Estimate" page should be opened
    And I add "1" photos to the Bungii
    And I add "4" photos to the Bungii

  #@regression
  @regression
  Scenario: Verify Next Available Scheduled Time In Correct Timezone Is Selected On Estimate Screen For Duo Scheduled Bungii
    Given I am on customer Log in page
    When I enter customers "8805368840" Phone Number
    And I enter customers "valid" Password
    And I tap on the "Log in" Button on Login screen
    And I enter "kansas pickup and dropoff locations" on Bungii estimate
    And I tap on "two drivers selector" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    Then "Estimate" page should be opened
    Then correct details next available scheduled time should be displayed

    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | 8805368840      |                 |