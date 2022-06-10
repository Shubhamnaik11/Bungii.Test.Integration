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
    Then I should be navigated to "BUNGII DETAILS" screen
    Then Partner Portal name should be displayed in "AVAILABLE BUNGIIS" section
    When I accept selected Bungii
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from scheduled trip
    Then I should be navigated to "BUNGII DETAILS" screen
    Then Partner Portal name should be displayed in "SCHEDULED BUNGIIS" section
    And I start selected Bungii
    Then I should be navigated to "EN ROUTE" trip status screen
    Then Partner Portal name should be displayed in "EN ROUTE" section
    And I slide update button on "EN ROUTE" Screen
    Then I should be navigated to "ARRIVED" trip status screen
    Then Partner Portal name should be displayed in "ARRIVED" section
    And I slide update button on "ARRIVED" Screen
    Then I should be navigated to "LOADING ITEM" trip status screen
    Then Partner Portal name should be displayed in "LOADING ITEM" section
    And I slide update button on "LOADING ITEM" Screen
    Then I should be navigated to "DRIVING TO DROP OFF" trip status screen
    Then Partner Portal name should be displayed in "DRIVING TO DROP OFF" section
    And I slide update button on "DRIVING TO DROP OFF" Screen
    Then I should be navigated to "UNLOADING ITEM" trip status screen
    Then Partner Portal name should be displayed in "UNLOADING ITEM" section
    And I slide update button on "UNLOADING ITEM" Screen
    Then I should be navigated to "Bungii Completed" screen

 #  Core-2411:Verify that driver's status remains Online when his previous status was Online once he starts the schedule trip
  @ready
  Scenario: Verify that driver's status remains Online when his previous status was Online once he starts the schedule tripfor ios
    When I request Partner Portal "SOLO" Trip for "MRFM" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |Kansas| NEXT_POSSIBLE | 8877661017 | Testcustomertywd_appleMarkR LutherR|
    And I Switch to "driver" application on "same" devices
    And I login to driver app
    And I Select "AVAILABLE BUNGIIS" from driver App menu
    And I Select Partner portal Trip from available trip
    Then I should be navigated to "BUNGII DETAILS" screen
    And Partner Portal name should be displayed in "AVAILABLE BUNGIIS" section
    When I accept selected Bungii
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from scheduled trip
    Then I should be navigated to "BUNGII DETAILS" screen
    And Partner Portal name should be displayed in "SCHEDULED BUNGIIS" section
    When I start selected Bungii
    Then I should be navigated to "EN ROUTE" trip status screen on driver
    And Driver status should be "Online"
    And I Switch to "customer" application on "same" devices
    When I am on Customer logged in Home page
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location                 | Drop Location                      | geofence  |
      | Solo   | 775 Brasilia Avenue, Kansas City |  648 Madrid Avenue, Kansas City | kansas |
    And I click "Get Estimate" button on "Home" screen
    Then I should be navigated to "Estimate" screen
    When I confirm trip with following details
      | LoadTime | PromoCode | Payment Card | Time | PickUpImage |
      | 15       |           |              | Now  | Default     |
    Then I should be navigated to "SEARCHING" screen
    And I Switch to "driver" application on "same" devices
    And I view and check virtual notification for "Driver" for "on demand trip"

