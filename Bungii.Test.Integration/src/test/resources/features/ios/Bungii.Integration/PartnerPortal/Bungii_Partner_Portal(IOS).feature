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

    #  Core-2411:Verify that driver's status remains Online when his pervious status was Online once he starts the schedule trip
  @reaqdy
  Scenario: Verify that driver's status remains Online when his pervious status was Online once he starts the schedule trip
    When I request Partner Portal "SOLO" Trip for "MRFM" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |Kansas| NEXT_POSSIBLE | 9999999208 | Testcustomertywd_appleNewU Customer|
    And I am logged in as "Testdrivertywd_appleks_andro_a Kansas_a" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    When I switch to "ORIGINAL" instance
    And I Switch to "driver" application on "same" devices
    And I Select "AVAILABLE BUNGIIS" from driver App menu
    And I Select Trip from available trip
    Then Partner Portal name should be displayed in "AVAILABLE BUNGIIS" section
    And I tap on "ACCEPT" on driver Trip details Page
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from driver scheduled trip
    Then Partner Portal name should be displayed in "SCHEDULED BUNGIIS" section
    And I start selected Bungii
    Then Bungii driver should see "General Instructions"
    Then Bungii driver should see "Enroute screen"
    Then Partner Portal name should be displayed in "EN ROUTE" section
    And Driver status should be changed in db to "1"
    And I Switch to "customer" application on "same" devices
    When I am on customer Log in page
    And I am logged in as "valid kansas" customer
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    And I enter "new Kansas pickup less than 10 miles" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I add "2" photos to the Bungii
    And I add loading/unloading time of "30 mins"
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    And I Switch to "driver" application on "ORIGINAL" devices
    And I view and accept virtual notification for "Driver" for "on demand trip"

