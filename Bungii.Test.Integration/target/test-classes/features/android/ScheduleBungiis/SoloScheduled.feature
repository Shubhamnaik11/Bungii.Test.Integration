Feature: SoloScheduled
  @Android
  Scenario: SoloScheduled_RequestSuccessfully
    Given I am logged in as "my" customer
    When I enter "current location in pickup and dropoff fields" on Bungii estimate
    When I tap on "Get Estimate button" on Bungii estimate
    When I tap on "Add photo" on Bungii estimate
    And I tap on "Request Bungii" on Bungii estimate


