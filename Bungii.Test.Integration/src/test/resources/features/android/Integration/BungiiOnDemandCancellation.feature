@android
  Feature: OnDemandBungii_Cancellation
  Scenarios where customer requests and cancels the Bungii.

  #This feature will run in goa geofence.

  @regression
  Scenario: Verify that 'SET PICKUP TIME' page is shown when no driver accepts on demand bungii.
    Given I am on customer Log in page
    When I am logged in as "valid goa customer" customer
#    And I Switch to "driver" application on "same" devices
#    And I am on the LOG IN page on driver app
#    And I am logged in as "goa driver_1" driver
#    And I tap on "Go Online button" on Driver Home page
#    And I Switch to "customer" application on "same" devices
    And I enter "current location in pickup and dropoff fields long distance" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I add "1" photos to the Bungii
    And I add loading/unloading time of "30 mins"
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    And I wait for "2" mins
    Then I verify that "SET PICKUP TIME PAGE" is displayed
    And I verify that "DRIVERS NOT AVAILABLE" is displayed
    When I click on "i info" icon
    Then I verify that "Message Popup" is displayed

    @regression1
    Scenario: Verify that the customer can schedule pickup by clicking on SCHEDULE BUNGII button.
      Given I am on customer Log in page
      When I am logged in as "valid goa customer" customer
      And I enter "current location in pickup and dropoff fields long distance" on Bungii estimate
      And I tap on "Get Estimate button" on Bungii estimate
      And I add "1" photos to the Bungii
      And I add loading/unloading time of "30 mins"
      And I tap on "Request Bungii" on Bungii estimate
      And I tap on "Yes on HeadsUp pop up" on Bungii estimate
      And I wait for "2" mins
      Then I verify that "SET PICKUP TIME PAGE" is displayed
      When I click on "SCHEDULE BUNGII" button
      Then I click "Done" button on "Success" screen