  @android

  Feature: Partner Portal Cases integration with Android

  @ready
  Scenario: Verify that the Partner name shown on driver app
    And I am logged in as "Testdrivertywd_appleks_a_drva Kansas_a" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    When I request Partner Portal "SOLO" Trip for "MRFM" partner
    |Geofence| Bungii Time   | Customer Phone | Customer Name |
    |Kansas| NEXT_POSSIBLE | 9999999208 | Testcustomertywd_appleNewU Customer|
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

#  Core-2638: Verify the changed Driver cut is reflected in driver app
    @ready
    Scenario: Verify the changed Driver cut is reflected in driver app
      When I request Partner Portal "SOLO" Trip for "Floor and Decor" partner
        |Geofence| Bungii Time   | Customer Phone | Customer Name |
        |washingtondc| NEXT_POSSIBLE | 9999999127 | Testcustomertywd_appleNewRB Customer|
      Then I wait for "2" mins
      When I open new "Chrome" browser for "ADMIN PORTAL"
      And I navigate to admin portal
      And I log in to admin portal
      And I Select "Scheduled Trip" from admin sidebar
      And I open the trip for "Testcustomertywd_appleNewRB Customer" the customer
      And I Select "Edit Trip Details" option
      And I assign driver "Testdrivertywd_appledc_a_drvs Driver" for the trip
      And I click on "VERIFY" button
      And the "Your changes are good to be saved." message is displayed
      Then I click on "SAVE CHANGES" button
      And the "Bungii Saved!" message is displayed
      When I click on "Close" button
      And I wait for "2" mins
      And I open the trip for "Testcustomertywd_appleNewRB Customer" the customer for delivery details
      Then I click on "Price Override" button and change the driver cut
      When I wait for "2" mins
      When I switch to "ORIGINAL" instance
      And I Switch to "driver" application on "same" devices
      And I am on the LOG IN page on driver app
      And I am logged in as "Testdrivertywd_appledc_a_drvs Driver" driver
      And I Select "SCHEDULED BUNGIIS" from driver App menu
      Then I check if driver cut is reflected

