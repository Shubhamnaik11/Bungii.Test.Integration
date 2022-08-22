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
#   Core-2618 Verify that referral icon is not shown during in process trip on driver app
    And I check if referral icon is not shown
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

      When I tap on "Back" button of android mobile
      And I Select "HOME" from driver App menu
      And I tap on "Back" button of android mobile
#     Core-3371 Verify Push Notification is sent with schedule date/time to driver when partner(Fixed Pricing) cancels trip
      When I open new "Chrome" browser for "ADMIN PORTAL"
      When I navigate to "Partner" portal configured for "service level" URL
      When I enter "valid" password on Partner Portal
      And I click "SIGN IN" button on Partner Portal
      When I click the "Track Deliveries" button on Partner Portal
      And I click on the delivery based on customer name
      And I click "Cancel Delivery link" button on Partner Portal
      And I click "Cancel Delivery" button on Partner Portal
      Then I click "OK" button on Partner Portal
      And I wait for "1" mins

      When I switch to "ORIGINAL" instance
      And I Switch to "driver" application on "same" devices
      Then I check the notification for "partner cancel trip"


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



#    Core-2418: Verify Driver Pricing by weight for Solo delivery for Floor n Decor Partner
    @ready
    Scenario: Verify Driver Pricing by weight for Solo delivery for Floor n Decor Partner
      When I request Partner Portal "SOLO" Trip for "Floor and Decor" partner
        |Geofence| Bungii Time   | Customer Phone | Customer Name |
        |washingtondc| NEXT_POSSIBLE | 8877661083 | Testcustomertywd_BppleMarkCF LutherCF|
      And I wait for "2" mins
      When I open new "Chrome" browser for "ADMIN PORTAL"
      And I navigate to admin portal
      And I log in to admin portal
      And I Select "Scheduled Trip" from admin sidebar
      And I open the trip for "Testcustomertywd_BppleMarkCF LutherCF" the customer for delivery details
      And I get the estimated charge "for customer"
      And I get the driver earnings displayed for "solo"
      And I calculate the driver share and check for "solo"

      When I switch to "ORIGINAL" instance
      And I Switch to "driver" application on "same" devices
      And I am on the LOG IN page on driver app
      And I am logged in as "Testdrivertywd_appledc_a_web TestdriverA" driver
      And I Select "AVAILABLE BUNGIIS" from driver App menu
      And I Select Trip from available trip
      And I verify the driver earnings displayed on driver app for "solo"
#    Core-2537 Verify whether driver can accept deliveries which have suitable payload for his vehicle
      And I tap on "ACCEPT" on driver Trip details Page
      And I Select "SCHEDULED BUNGIIS" from driver App menu
      And I Select Trip from driver scheduled trip

      # Core-2418 Verify Driver Pricing is recalculated for Floor n Decor delivery when admin edits the address and service level of Schedule Trip
      And I wait for "2" mins
      When I open new "Chrome" browser for "ADMIN PORTAL"
      And I navigate to admin portal
      And I log in to admin portal
      And I Select "Scheduled Trip" from admin sidebar
      And I open the trip for "Testcustomertywd_BppleMarkCF LutherCF" the customer
      And I Select "Edit Trip Details" option
      And I edit the drop off address
      Then I change the drop off address to "14800 Carrs Mill Road, Woodbine"
      And I change the service level to "Customer Return - First Threshold" in "Admin" portal
      And I click on "VERIFY" button
      And the "Your changes are good to be saved." message is displayed
      Then I click on "SAVE CHANGES" button
      Then the "Bungii Saved!" message is displayed
      When I click on "Close" button
      And I get the new pickup reference generated
      And I wait for "2" mins
      And I Select "Scheduled Trip" from admin sidebar
      And I open the trip for "Testcustomertywd_BppleMarkCF LutherCF" the customer for delivery details
      And I get the estimated charge "for customer"
      And I calculate the driver share and check for "changed address and service level"

      When I switch to "ORIGINAL" instance
      When I Switch to "driver" application on "same" devices
      When I tap on "Back" button of android mobile
      And I Select Trip from driver scheduled trip
      And I verify the driver earnings displayed on driver app for "changed address and service level"
      And I start selected Bungii for "floor and decor"
      Then Bungii driver should see "General Instructions"
      And I slide update button on "EN ROUTE" Screen
      Then Bungii driver should see "Pickup Instructions"
      And I slide update button on "ARRIVED" Screen

#    Core-2418 Verify Driver Pricing is recalculated for Floor n Decor delivery when admin edits the address and service level of Live Trip
      When I open new "Chrome" browser for "ADMIN PORTAL"
      And I navigate to admin portal
      And I log in to admin portal
      And I Select "live trips" from admin sidebar
      And I select the live trip for "Testcustomertywd_BppleMarkCF LutherCF" customer
      And I Select "Edit Trip Details" option
      And I edit the drop off address
      Then I change the drop off address to "3315 Shepherd Street, Chevy Chase, Maryland"
      And I change the service level to "First Threshold" in "Admin" portal
      And I click on "VERIFY" button
      And the "Your changes are good to be saved." message is displayed
      Then I click on "SAVE CHANGES" button
      Then the "Bungii Saved!" message is displayed
      When I click on "Close" button
      And I get the new pickup reference generated
      And I wait for "2" mins
      And I Select "live trips" from admin sidebar
      And I select the live trip for "Testcustomertywd_BppleMarkCF LutherCF" customer for delivery details
      And I get the estimated charge "for customer"
      And I get the driver earnings displayed for "solo"
      Then I calculate the driver share and check for "solo"


#  Driver : 9049840256 Payload capacity : 1011 lbs
#  Core-2418: Verify Driver Pricing by weight for Duo delivery with both Pallet weight lies same tier for Floor and Decor Partner
    @ready
#      @testsweta
    Scenario:Verify Driver Pricing by weight for Duo delivery with both Pallet weight lies same tier for Floor n Decor Partner
      When I switch to "ORIGINAL" instance
      When I Switch to "driver" application on "same" devices
      And I enter phoneNumber :9049840256 and  Password :Cci12345
      And I click "Log In" button on Log In screen on driver app
      And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist

      When I request Partner Portal "DUO" Trip for "Floor and Decor" partner
        |Geofence| Bungii Time   | Customer Phone | Customer Name |
        |washingtondc| NEXT_POSSIBLE | 8877661042 | Testcustomertywd_appleMarkAQ LutherAQ|
      And I wait for "2" mins
      When I open new "Chrome" browser for "ADMIN PORTAL"
      And I navigate to admin portal
      And I log in to admin portal
      And I Select "Scheduled Trip" from admin sidebar
      And I open the trip for "Testcustomertywd_appleMarkAQ LutherAQ" the customer for delivery details
      And I get the estimated charge "for customer"
      And I get the driver earnings displayed for "duo"
      And I calculate the driver share and check for "duo"

      When I switch to "ORIGINAL" instance
      When I Switch to "driver" application on "same" devices
      And I Select "AVAILABLE BUNGIIS" from driver App menu
      And I Select Trip from available trip
      Then I verify the driver earnings displayed on driver app for "duo"

#  Core-2418: Verify Driver Pricing for Floor n Decor delivery when admin edits the  date/time or research Schedule Trip
      When I open new "Chrome" browser for "ADMIN PORTAL"
      And I navigate to admin portal
      And I log in to admin portal
      And I Select "Scheduled Trip" from admin sidebar
      And I open the trip for "Testcustomertywd_appleMarkAQ LutherAQ" the customer
      And I Select "Edit Trip Details" option
      And I click on the "Time" and select future time
      And I click on "Reason" for change time
      And I click on "Customer initiated" in the dropdown
      And I click on "VERIFY" button
      And the "Your changes are good to be saved." message is displayed
      Then I click on "SAVE CHANGES" button
      Then the "Bungii Saved!" message is displayed
      When I click on "Close" button
      And I wait for "2" mins
      And I Select "Scheduled Trip" from admin sidebar
      And I open the trip for "Testcustomertywd_appleMarkAQ LutherAQ" the customer for delivery details
      And I get the estimated charge "for customer"
      And I get the driver earnings displayed for "duo"
      And I calculate the driver share and check for "duo"

      When I switch to "ORIGINAL" instance
      When I Switch to "driver" application on "same" devices
      And I click on the back button and verify that rejection popup is absent
      And I tap on "Back" button of android mobile
      And I Select "AVAILABLE BUNGIIS" from driver App menu
      And I Select Trip from available trip
      Then I verify the driver earnings displayed on driver app for "duo"
#   Core-2537: Verify whether driver can accept delivery that are upto 100 lb more then the payload
      And I select "Pallet-1" from items
      And I tap on "ACCEPT" on driver Trip details Page
      Then I should be navigated to "AVAILABLE BUNGIIS" screen

#  Driver : 9049840253 Payload capacity : 500 lbs
#  Core-2418: Verify Driver Pricing by weight for Duo delivery with Pallet weight in different tier for Floor n Decor Partner
    @ready
    Scenario:Verify Driver Pricing by weight for Duo delivery with Pallet weight in different tier for Floor n Decor Partner
      When I switch to "ORIGINAL" instance
      When I Switch to "driver" application on "same" devices
      And I enter phoneNumber :9049840253 and  Password :Cci12345
      And I click "Log In" button on Log In screen on driver app
      And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist

      When I request Partner Portal "DUO" Trip for "Floor and Decor - Different Weights" partner
        |Geofence| Bungii Time   | Customer Phone | Customer Name |
        |washingtondc| NEXT_POSSIBLE | 8877661043 | Testcustomertywd_appleMarkAR LutherAR|
      And I wait for "2" mins
      When I open new "Chrome" browser for "ADMIN PORTAL"
      And I navigate to admin portal
      And I log in to admin portal
      And I Select "Scheduled Trip" from admin sidebar
      And I open the trip for "Testcustomertywd_appleMarkAR LutherAR" the customer for delivery details
      And I get the estimated charge "for customer"
      And I get the driver earnings displayed for "duo"
      And I calculate the driver share and check for "duo-different tier"

      When I switch to "ORIGINAL" instance
      When I Switch to "driver" application on "same" devices
      And I Select "AVAILABLE BUNGIIS" from driver App menu
      And I Select Trip from available trip
      Then I verify the driver earnings displayed on driver app for "duo-different tier"
#   Core-2537: Verify whether drivers with low payload capacity are allowed to accept deliveries with high weight
      And I select "Pallet-1" from items
      Then I check inadequate payload pop up is displayed
#  Core-2418: Verify Driver Pricing for Floor n Decor delivery when admin convert duo trip to solo
      When I open new "Chrome" browser for "ADMIN PORTAL"
      And I navigate to admin portal
      And I log in to admin portal
      And I Select "Scheduled Trip" from admin sidebar
      And I open the trip for "Testcustomertywd_appleMarkAR LutherAR" the customer
      And I Select "Edit Trip Details" option
      And I change delivery type from "Duo to Solo"
      And I click on "VERIFY" button
      And the "Your changes are good to be saved." message is displayed
      Then I click on "SAVE CHANGES" button
      Then the "Bungii Saved!" message is displayed
      When I click on "Close" button
      And I wait for "2" mins
      And I Select "Scheduled Trip" from admin sidebar
      And I open the trip for "Testcustomertywd_appleMarkAR LutherAR" the customer for delivery details
      And I get the estimated charge "for customer"
      And I get the driver earnings displayed for "solo"
      And I calculate the driver share and check for "duo to solo conversion"

      When I switch to "ORIGINAL" instance
      When I Switch to "driver" application on "same" devices
      And I tap on "Back" button of android mobile
      And I Select Trip from available trip
      Then I verify the driver earnings displayed on driver app for "solo"
 #   Core-2537: Verify that information of both the pallets are displayed separately on drivers app when a delivery is converted from duo to solo
      Then I check information of both the pallets are displayed separately

#  Driver : 9049840258 Payload capacity : 1111 lbs
#  Core-2546: Verify for DUO delivery when a pallet is already accepted by driver it is not available for other driver
    @ready
#      @testsweta
    Scenario:Verify for DUO delivery when a pallet is already accepted by driver it is not available for other driver
      When I switch to "ORIGINAL" instance
      When I Switch to "driver" application on "same" devices
      And I enter phoneNumber :9049840258 and  Password :Cci12345
      And I click "Log In" button on Log In screen on driver app
      And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist

      When I request Partner Portal "DUO" Trip for "Floor and Decor" partner
        |Geofence| Bungii Time   | Customer Phone | Customer Name |
        |washingtondc| NEXT_POSSIBLE | 8877661092 | Testcustomertywd_appleMarkCO LutherCO|

      When I switch to "ORIGINAL" instance
      When I Switch to "driver" application on "same" devices
      And I Select "AVAILABLE BUNGIIS" from driver App menu
      And I Select Trip from available trip
#   Core-2546: Verify pallet details are displayed on AVAILABLE Bungii menu
      And I select "Pallet-1" from items
      And I check "pallet-1" details are displayed on "available bungii" page
#   Core-2546: Verify driver can accept using AVAILABLE BUNGII menu when driver pallet is equal to payload capacity
      And I tap on "ACCEPT" on driver Trip details Page
      And I Select "SCHEDULED BUNGIIS" from driver App menu
      And I Select Trip from driver scheduled trip
#   Core-2546: Verify pallet details are displayed on SCHEDULE Bungii menu
      And I check "pallet-1" details are displayed on "schedule bungii" page
      And I tap on "Back" button of android mobile

      And I connect to "extra1" using "Driver2" instance
      And I Open "driver" application on "same" devices
      And I am on the LOG IN page on driver app
      And I enter phoneNumber :9766000001 and  Password :Cci12345
      And I click "Log In" button on Log In screen on driver app
      And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
      And I Select "AVAILABLE BUNGIIS" from driver App menu
      And I Select Trip from available trip
      And I select "Pallet-1" from items
      And I tap on "ACCEPT" on driver Trip details Page
      Then I check already accepted pallet pop up is displayed



