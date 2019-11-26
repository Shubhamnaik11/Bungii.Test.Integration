@android
  #These feature will run in boston geofence

Feature: OnDemandBungii_DriverActions
Scenarios where customer requests a Bungii and driver accepts/rejects and cancels the Bungii.


  @regression
  Scenario: OnDemand_DriverRejectsBungiiRequest
    Given I am on customer Log in page
    When I am logged in as "valid boston" customer
    And I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid boston" driver
    And I tap on "Go Online button" on Driver Home page
    And I Switch to "customer" application on "same" devices
    And I enter "boston pickup and dropoff locations" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I add "2" photos to the Bungii
    And I add loading/unloading time of "30 mins"
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    And I Open "driver" application on "same" devices
    And Bungii Driver "rejects On Demand Bungii" request
    Then Bungii driver should see "Home screen"
    When I Switch to "customer" application on "same" devices
    And I tap on "Cancel during search" on Bungii estimate
    And Quit Bungii Driver app

  @sanity
  @regression
  Scenario: OnDemand_DriverCancelBungii_EnrouteState
    Given that ondemand bungii is in progress
      | geofence | Bungii State |
      | boston   | Enroute      |

    When I am on customer Log in page
    And I am logged in as "valid boston" customer
    Then for a Bungii I should see "Enroute screen"

    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid boston" driver
    Then Bungii driver should see "Enroute screen"
    When Bungii Driver "cancels Bungii"
    And I Switch to "customer" application on "same" devices
    Then Alert message with DRIVER CANCELLED text should be displayed
    When I click "OK" on alert message
    Then "Home" page should be opened
  @failed
  @sanity
  @regression
  Scenario: OnDemand_DriverCancelBungii_ArrivedState

    Given that ondemand bungii is in progress
      | geofence | Bungii State |
      | boston   | ARRIVED      |

    And I am on customer Log in page
    When I am logged in as "valid boston" customer
    And I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid boston" driver
    Then Bungii driver should see "Arrived screen"
    When Bungii Driver "cancels Bungii"
    And I Switch to "customer" application on "same" devices
    Then Alert message with DRIVER CANCELLED text should be displayed
    When I click "OK" on alert message
    Then "Home" page should be opened