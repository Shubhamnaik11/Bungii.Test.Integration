  @android

  Feature: Partner Portal Cases integration with Android

  @aa
  Scenario: Verify that the Partner name shown on driver app
    When I request Partner Portal "SOLO" Trip for "MRFM" partner
    |Geofence| Bungii Time   | Customer Phone | Customer Name |
    |Kansas| NEXT_POSSIBLE | 9999999208 | Testcustomertywd_appleNewU Customer|
    When I Switch to "driver" application on "same" devices
    And I am logged in as "Testdrivertywd_appleks_a_drva Kansas_a" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I Select "AVAILABLE BUNGIIS" from driver App menu
    And I Select Trip from available trip
    Then Partner Portal name should be displayed in "AVAILABLE BUNGIIS" section
    And I tap on "ACCEPT" on driver Trip details Page
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from driver scheduled trip
    Then Partner Portal name should be displayed in "SCHEDULED BUNGIIS" section
    And I start selected Bungii
    Then Bungii driver should see "Enroute screen"
    Then Partner Portal name should be displayed in "EN ROUTE" section
    And I slide update button on "EN ROUTE" Screen
    Then Bungii driver should see "Arrived screen"
    Then Partner Portal name should be displayed in "ARRIVED" section
    And I slide update button on "ARRIVED" Screen
    Then Bungii driver should see "Loading Item screen"
    Then Partner Portal name should be displayed in "LOADING ITEM" section
    And I slide update button on "LOADING ITEM" Screen
    Then Bungii driver should see "Driving to DropOff screen"
    Then Partner Portal name should be displayed in "DRIVING TO DROP OFF" section
    And I slide update button on "DRIVING TO DROP OFF" Screen
    Then Bungii driver should see "Unloading Item screen"
    Then Partner Portal name should be displayed in "UNLOADING ITEM" section
    And I slide update button on "UNLOADING ITEM" Screen
    Then I should be navigated to "Bungii Completed" screen
