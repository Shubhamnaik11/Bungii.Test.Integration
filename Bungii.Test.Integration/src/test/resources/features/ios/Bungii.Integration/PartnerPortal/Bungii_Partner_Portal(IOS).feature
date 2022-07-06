@ios
Feature: Partner Portal Cases integration with IOS

  @ready
  Scenario: Verify Partner portal name shown on driver app
    When I request Partner Portal "SOLO" Trip for "MRFM" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |Kansas| NEXT_POSSIBLE | 9999999208 | Testcustomertywd_appleNewU Customer|
    When I Switch to "driver" application on "same" devices
    And I login as "valid partner kansas driver" driver on "same" device and make driver status as "Online"
    And I Select "AVAILABLE BUNGIIS" from driver App menu
    #And I Select Trip from available trip
    And I Select Partner portal Trip from available trip

    #    Core - 2569 Verify ~ sign under earnings is shown on Driver app for Variable pricing Deliveries
    And I check if variable sign is shown under "available bungii details"

    Then I should be navigated to "BUNGII DETAILS" screen
    Then Partner Portal name should be displayed in "AVAILABLE BUNGIIS" section
    When I accept selected Bungii
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from scheduled trip
    And I check if variable sign is shown under "schedule bungii details"
    Then I should be navigated to "BUNGII DETAILS" screen
    Then Partner Portal name should be displayed in "SCHEDULED BUNGIIS" section
    And I start selected Bungii
    Then I should be navigated to "EN ROUTE" trip status screen on driver
    Then Partner Portal name should be displayed in "EN ROUTE" section
    And I slide update button on "EN ROUTE" Screen
    Then I should be navigated to "ARRIVED" trip status screen on driver
    Then Partner Portal name should be displayed in "ARRIVED" section
    And I slide update button on "ARRIVED" Screen
    Then I should be navigated to "LOADING ITEMS" trip status screen on driver
    Then Partner Portal name should be displayed in "LOADING ITEMS" section
    And I slide update button on "LOADING ITEM" Screen
    Then I should be navigated to "DRIVING TO DROP-OFF" trip status screen on driver
    Then Partner Portal name should be displayed in "DRIVING TO DROP-OFF" section
    And I slide update button on "DRIVING TO DROP-OFF" Screen
    Then I should be navigated to "UNLOADING ITEMS" trip status screen on driver
    Then Partner Portal name should be displayed in "UNLOADING ITEMS" section
    And I slide update button on "UNLOADING ITEMS" Screen
    And I click "Skip This Step" button on "Rate customer" screen
    Then I should be navigated to "Bungii Completed" screen
    When I click "On To The Next One" button on "Bungii completed" screen

  #    Core-3098 Verify online/offline pop up is shown for solo Partner portal trip and go-offline functionality
    And I check online or offline pop up is displayed
    And I click on "GO OFFLINE" button
    And I Select "HOME" from driver App menu
    And I check if the status is "OFFLINE"

     #  Core-2569: Verify ~ sign under earnings is not shown on Driver app for Fixed pricing Deliveries
  @ready
  Scenario: Verify ~ sign under earnings is not shown on Driver app for Fixed pricing Deliveries
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "Testdrivertywd_applega_a_steveC Stark_altOnEC" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist

    When I request Partner Portal "SOLO" Trip for "Biglots" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |atlanta  | NEXT_POSSIBLE | 8877661047 | Testcustomertywd_appleMarkAV LutherAV|

    When I switch to "ORIGINAL" instance
    And I Switch to "driver" application on "same" devices
    And I Select "AVAILABLE BUNGIIS" from driver App menu
    And I Select Partner portal Trip from available trip
    And I check if variable sign is not shown under "available bungii details"
    When I accept selected Bungii
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from scheduled trip
    Then I check if variable sign is not shown under "schedule bungii details"
