@android

Feature: EstimateBungii

  Background:
Given I am on customer Log in page
   Given I am logged in as "no promocode" customer

 #Scenario fails when fixed valid code is not default code
  @regression
  Scenario: Bungii_Estimate_ExistingUser_FixedPromoCode
    When I Select "ACCOUNT" from customer app menu list
    Then I get customer account details
    When I Select "HOME" from customer app menu list

    When I enter "valid pickup and dropoff locations" on Bungii estimate
    When I tap on "Get Estimate button" on Bungii estimate
    And I add loading/unloading time of "30 mins"
    When I tap on "Promo Code" on Bungii estimate
    And I add "fixed valid" PromoCode
    And I tap "Add" on Save Money page
    And I tap on "desired Promo Code" on Bungii estimate
    Then I should see "all elements" on Bungii estimate
    When I add "4" photos to the Bungii
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    Then for a Bungii I should see "Bungii search screen"

    And I tap on "Cancel during search" on Bungii estimate
    Then for a Bungii I should see "Bungii Home page with locations"