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

#    Core - 2569 Verify ~ sign under earnings is shown on Driver app for Variable pricing Deliveries
    And I check if variable sign is shown under "available bungii details"

    Then Partner Portal name should be displayed in "AVAILABLE BUNGIIS" section
    And I tap on "ACCEPT" on driver Trip details Page
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from driver scheduled trip
    And I check if variable sign is shown under "schedule bungii details"
    Then Partner Portal name should be displayed in "SCHEDULED BUNGIIS" section
    And I start selected Bungii
    Then Bungii driver should see "General Instructions"
    Then Bungii driver should see "Enroute screen"
    Then Partner Portal name should be displayed in "EN ROUTE" section
    And I slide update button on "EN ROUTE" Screen
    Then Bungii driver should see "Arrived screen"
    Then Partner Portal name should be displayed in "ARRIVED" section
    And I slide update button on "ARRIVED" Screen
    Then Bungii driver should see "Loading Items screen"
    Then Partner Portal name should be displayed in "LOADING ITEMS" section
    And I slide update button on "LOADING ITEM" Screen
    Then Bungii driver should see "Driving to Drop-Off screen"
    Then Partner Portal name should be displayed in "DRIVING TO DROP-OFF" section
    And I slide update button on "DRIVING TO DROP OFF" Screen
    Then Bungii driver should see "Unloading Items screen"
    Then Partner Portal name should be displayed in "UNLOADING ITEMS" section
    And I slide update button on "UNLOADING ITEM" Screen

#  Core-3098 Verify online/offline pop up is shown for solo Partner portal trip and check stay online functionality
    And Bungii Driver "skips to rate customer"
    And I click "Next Bungii" button on the "Bungii Completed" screen
    And I check online or offline pop up is displayed
    And I click on "STAY ONLINE" button
    And I Select "HOME" from driver App menu
    And I check if the status is "ONLINE"

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
      And I assign driver "Testdrivertywd_appledc_a_drvC WashingtonC" for the trip
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
      And I am logged in as "Testdrivertywd_appledc_a_drvC WashingtonC" driver
      And I Select "SCHEDULED BUNGIIS" from driver App menu
      Then I check if driver cut is reflected

  #  Core-2569: Verify ~ sign under earnings is not shown on Driver app for Fixed pricing Deliveries
    @ready
    Scenario: Verify ~ sign under earnings is not shown on Driver app for Fixed pricing Deliveries
      When I am logged in as "Testdrivertywd_applega_a_steveB Stark_altOnEB" driver
      And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist

      When I request Partner Portal "SOLO" Trip for "Biglots" partner
        |Geofence| Bungii Time   | Customer Phone | Customer Name |
        |atlanta  | NEXT_POSSIBLE | 8877661046 | Testcustomertywd_appleMarkAU LutherAU|

      When I switch to "ORIGINAL" instance
      And I Switch to "driver" application on "same" devices
      And I Select "AVAILABLE BUNGIIS" from driver App menu
      And I Select Trip from available trip
      And I check if variable sign is not shown under "available bungii details"
      And I tap on "ACCEPT" on driver Trip details Page
      And I Select "SCHEDULED BUNGIIS" from driver App menu
      And I Select Trip from driver scheduled trip
      Then I check if variable sign is not shown under "schedule bungii details"

      When I open new "Chrome" browser for "ADMIN PORTAL"
      When I navigate to "Partner" portal configured for "service level" URL
      When I enter "valid" password on Partner Portal
      And I click "SIGN IN" button on Partner Portal
      When I click the "Track Deliveries" button on Partner Portal
      And I click on the delivery based on customer name
      And I click "Cancel Delivery link" button on Partner Portal
      And I click "Cancel Delivery" button on Partner Portal
      Then I click "OK" button on Partner Portal

#  Core-2411:Verify that driver's status remains Online when his previous status was Online once he starts the schedule trip
    @ready
    Scenario: Verify that driver's status remains Online when his previous status was Online once he starts the schedule trip
      When I request Partner Portal "SOLO" Trip for "MRFM" partner
        |Geofence| Bungii Time   | Customer Phone | Customer Name |
        |Kansas| NEXT_POSSIBLE | 8877661018 | Testcustomertywd_appleMarkS LutherS|
      And I am logged in as "Testdrivertywd_appleks_a_drval Kansas_al" driver
      Then I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
      And Driver status should be "Offline"
      And I Select "AVAILABLE BUNGIIS" from driver App menu
      And I Select Trip from available trip
      Then Partner Portal name should be displayed in "AVAILABLE BUNGIIS" section
      And I tap on "ACCEPT" on driver Trip details Page
      And I Select "SCHEDULED BUNGIIS" from driver App menu
      When I Select Trip from driver scheduled trip
      Then Partner Portal name should be displayed in "SCHEDULED BUNGIIS" section
      And I start selected Bungii
      And Bungii driver should see "General Instructions"
      And Bungii driver should see "Enroute screen"
      And Partner Portal name should be displayed in "EN ROUTE" section
      And Driver status should be "Online"
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
      Then I click on notification for "STACK TRIP"
      And I should see "New Bungii Request" popup displayed



