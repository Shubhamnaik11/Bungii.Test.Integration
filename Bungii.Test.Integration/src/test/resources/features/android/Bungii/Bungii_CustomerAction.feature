@android

Feature: Bungii

  Background:
    Given I am on customer Log in page
    Given I am logged in as "my" customer
    When I enter "current location in pickup and dropoff fields" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I add "1" photos to the Bungii
    And I add loading/unloading time of "30 mins"
    And I tap on "Request Bungii" on Bungii estimate

  @regression
  Scenario: Bungii_CustomerCancelOnHeadsUp
    When I tap on "Cancel on HeadsUp pop up" on Bungii estimate
    Then for a Bungii I should see "Bungii Estimate page with all details"
    Then I should see "customer cancelled" on Bungii estimate

  @regression
  Scenario: Bungii_CustomerCancelOnSearch
    When I tap on "Yes on HeadsUp pop up" on Bungii estimate
    Then for a Bungii I should see "Bungii search screen"
    When I tap on "Cancel during search" on Bungii estimate
    Then for a Bungii I should see "Bungii Home page with locations"
