@android

Feature: EstimateBungii

  Background:
 # Given I am on customer Log in page
    Given I am logged in as "no promocode" customer

 #Moved to Bungii.feature

#  Scenario: Bungii_Estimate_ExistingUser_FixedPromoCode
#    When I Select "ACCOUNT" from customer app menu list
#    Then I get customer account details
#    When I Select "HOME" from customer app menu list

#    When I enter "valid pickup and dropoff locations" on Bungii estimate
#    When I tap on "Get Estimate button" on Bungii estimate
#    And I add loading/unloading time of "30 mins"
#    When I tap on "Promo Code" on Bungii estimate
#    And I add "fixed valid" PromoCode
#    And I tap "Add" on Save Money page
#    And I tap on "desired Promo Code" on Bungii estimate
#    Then I should see "all elements" on Bungii estimate
#    When I add "4" photos to the Bungii
#    And I tap on "Request Bungii" on Bungii estimate
#    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
#    Then for a Bungii I should see "Bungii search screen"

#    And I tap on "Cancel during search" on Bungii estimate
#    Then for a Bungii I should see "Bungii Home page with locations"
  @regression
  Scenario: To check if the information icons display correct information
    When I enter "current location in pickup and dropoff fields" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I add loading/unloading time of "30 mins"
    Then "Load/Upload Time" information icon should display correct information
    Then "Total estimate" information icon should display correct information
    Then "Time" information icon should display correct information

  @regression
  Scenario: When I cancel on Estimate Page , I should be navigated to Home screen
    When I enter "current location in pickup and dropoff fields" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I add loading/unloading time of "30 mins"
    When I tap on "back" on Bungii estimate
    Then Trip Information should be correctly displayed on CUSTOMER HOME screen

  @regression
  Scenario: I should able to see all elements of Estimate page
    When I Select "ACCOUNT" from customer app menu list
    Then I get customer account details
    When I Select "HOME" from customer app menu list
    When I enter "valid pickup and dropoff locations" on Bungii estimate
    When I tap on "Get Estimate button" on Bungii estimate
    And I add loading/unloading time of "30 mins"
    Then I should see "all elements" on Bungii estimate

  @regression
  Scenario: Verify Load/unload time functionality . Check if Estimate cost is re calculated
    When I Select "HOME" from customer app menu list
    When I enter "valid pickup and dropoff locations" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    Then check if I have ability to select different load time and Estimate cost is re calculated

  @regression
  Scenario: When there are no driver available for on demand Bungii , and Customer choose for Scheduled Bungii instead then he should be navigated to Estimate screen with fields having previous details
    When I enter "valid pickup and dropoff locations" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I add loading/unloading time of "30 mins"
    And I get Bungii details on Bungii Estimate
    When I add "1" photos to the Bungii
    And I tap on "Request Bungii" on Bungii estimate
    When I tap on "Yes on HeadsUp pop up" on Bungii estimate
    Then for a Bungii I should see "Bungii search screen"

    And I wait for SEARCHING screen to disappear
    Then "DRIVER NOT AVAILABLE" page should be opened

    And I tap "Ok" button on DRIVER NOT AVAILABLE screen
    Then I should see "Schedule Bungii option" on DRIVER NOT AVAILABLE screen
    And I tap "Schedule Bungii" button on DRIVER NOT AVAILABLE screen
    Then "Estimate" page should be opened
    And I add loading/unloading time of "30 mins"
    Then I should see "previous values" on Bungii estimate
    When I tap on "back" on Bungii estimate
