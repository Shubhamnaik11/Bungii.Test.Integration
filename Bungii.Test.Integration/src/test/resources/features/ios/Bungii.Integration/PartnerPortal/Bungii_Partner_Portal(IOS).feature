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
      #  Core - 3113 Verify that Rate customer page UI and elements are displayed correctly to driver
    And I check the elements displayed on rate customer screen
    And I select "4" customer rating
      #  Core - 3113 Verify that comment field on Rate customer page is validated correctly
    And I add comment on rate customer page
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


 #  Core-2411:Verify that driver's status remains Online when his previous status was Online once he starts the schedule trip
  @ready
  Scenario: Verify that driver's status remains Online when his previous status was Online once he starts the schedule tripfor ios
    When I request Partner Portal "SOLO" Trip for "MRFM" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |Kansas| NEXT_POSSIBLE | 8877661017 | Testcustomertywd_appleMarkR LutherR|
    And I Switch to "driver" application on "same" devices
    And I login as "valid partner kansas driver2" driver on "same" device and make driver status as "Offline"
    And Driver status should be "Offline"
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
    And I slide update button on "EN ROUTE" Screen
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



#  Core-2418: Verify Driver Pricing by weight for Solo delivery for Floor n Decor Partner
  @ready
  Scenario: Verify Driver Pricing by weight for Solo delivery for Floor n Decor Partner
    When I request Partner Portal "SOLO" Trip for "Floor and Decor" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |washingtondc| NEXT_POSSIBLE | 8877661044 | Testcustomertywd_appleMarkAS LutherAS|
    And I wait for "2" mins
    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Scheduled Trip" from admin sidebar
    And I open the trip for "Testcustomertywd_appleMarkAS LutherAS" the customer for delivery details
    And I get the estimated charge "for customer"
    And I get the driver earnings displayed for "solo"
    And I calculate the driver share and check for "solo"

    When I switch to "ORIGINAL" instance
    When I Switch to "driver" application on "same" devices
    And I enter phoneNumber :9766000001 and  Password :Cci12345
    And I click "Log In" button on "Log In" screen on driverApp
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I Select "AVAILABLE BUNGIIS" from driver App menu
    And I Select Partner portal Trip from available trip
#    Core-2537 Verify whether driver can accept deliveries which have suitable payload for his vehicle
    When I accept selected Bungii
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from scheduled trip
    And I verify the driver earnings displayed on driver app for "solo"

    # Core-2418 Verify Driver Pricing is recalculated for Floor n Decor delivery when admin edits the address and service level of Schedule Trip
    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Scheduled Trip" from admin sidebar
    And I open the trip for "Testcustomertywd_appleMarkAS LutherAS" the customer
    And I Select "Edit Trip Details" option
    And I edit the drop off address
    Then I change the drop off address to "14800 Carrs Mill Road, Woodbine"
    And I change the service level to "Customer Return - First Threshold" in "Admin" portal
    And I click on "VERIFY" button
    And the "Your changes are good to be saved." message is displayed
    Then I click on "SAVE CHANGES" button
    Then the "Bungii Saved!" message is displayed
    When I click on "CLOSE" button
    And I get the new pickup reference generated
    And I wait for "2" mins
    And I Select "Scheduled Trip" from admin sidebar
    And I open the trip for "Testcustomertywd_appleMarkAS LutherAS" the customer for delivery details
    And I get the estimated charge "for customer"
    And I calculate the driver share and check for "changed address and service level"

    When I switch to "ORIGINAL" instance
    When I Switch to "driver" application on "same" devices
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from scheduled trip
    And I verify the driver earnings displayed on driver app for "changed address and service level"
    And I start selected Bungii for "floor and decor"
    Then I should be navigated to "EN ROUTE" trip status screen on driver
    And I slide update button on "EN ROUTE" Screen
    And I slide update button on "ARRIVED" Screen
    Then Bungii driver should see "Pickup instructions"
    And I slide update button on "ARRIVED" Screen
    And Driver adds photos to the Bungii
    And I slide update button on "ARRIVED" Screen

#    Core-2418 Verify Driver Pricing is recalculated for Floor n Decor delivery when admin edits the address and service level of Live Trip
    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "live trips" from admin sidebar
    And I select the live trip for "Testcustomertywd_appleMarkAS LutherAS" customer
#   Core-2641 Verify alias is displayed for partner portal trips on Live delivery page
    And I verify alias is displayed correctly on "live delivery page"
    And I Select "Edit Trip Details" option
    And I edit the drop off address
    Then I change the drop off address to "3315 Shepherd Street, Chevy Chase, Maryland"
    And I change the service level to "First Threshold" in "Admin" portal
    And I click on "VERIFY" button
    And the "Your changes are good to be saved." message is displayed
    Then I click on "SAVE CHANGES" button
    Then the "Bungii Saved!" message is displayed
    When I click on "CLOSE" button
    And I get the new pickup reference generated
    And I wait for "2" mins
    And I Select "live trips" from admin sidebar
    And I select the live trip for "Testcustomertywd_appleMarkAS LutherAS" customer for delivery details
    And I get the estimated charge "for customer"
    And I get the driver earnings displayed for "solo"
    And I calculate the driver share and check for "solo"

    When I switch to "ORIGINAL" instance
    When I Switch to "driver" application on "same" devices
    And I slide update button on "LOADING ITEM" Screen
    And Driver adds photos to the Bungii
    And I slide update button on "LOADING ITEM" Screen
    And I slide update button on "DRIVING TO DROP-OFF" Screen
    Then Bungii driver should see "Drop-off instructions"
    And I slide update button on "UNLOADING ITEMS" Screen
    And Driver adds photos to the Bungii
    And I slide update button on "UNLOADING ITEMS" Screen
    And I click "Skip This Step" button on "Rate customer" screen
    Then I should be navigated to "Bungii Completed" screen

#  Driver : 9049840255 Payload capacity : 1011 lbs
#  Core-2418: Verify Driver Pricing by weight for Duo delivery with both Pallet weight lies same tier for Floor and Decor Partner
  @ready
  Scenario:Verify Driver Pricing by weight for Duo delivery with both Pallet weight lies same tier for Floor n Decor Partner
    When I switch to "ORIGINAL" instance
    When I Switch to "driver" application on "same" devices
    And I enter phoneNumber :9049840255 and  Password :Cci12345
    And I click "Log In" button on "Log In" screen on driverApp
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist

    When I request Partner Portal "DUO" Trip for "Floor and Decor" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |washingtondc| NEXT_POSSIBLE | 8877661040 | Testcustomertywd_appleMarkAO LutherAO|
    And I wait for "2" mins
    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Scheduled Trip" from admin sidebar
    And I open the trip for "Testcustomertywd_appleMarkAO LutherAO" the customer for delivery details
    And I get the estimated charge "for customer"
    And I get the driver earnings displayed for "duo"
    And I calculate the driver share and check for "duo"

    When I switch to "ORIGINAL" instance
    When I Switch to "driver" application on "same" devices
    And I Select "AVAILABLE BUNGIIS" from driver App menu
    And I Select Partner portal Trip from available trip
    Then I verify the driver earnings displayed on driver app for "duo"

#  Core-2418: Verify Driver Pricing for Floor n Decor delivery when admin edits the  date/time or research Schedule Trip
    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Scheduled Trip" from admin sidebar
    And I open the trip for "Testcustomertywd_appleMarkAO LutherAO" the customer
    And I Select "Edit Trip Details" option
    And I click on the "Time" and select future time
    And I click on "Reason" for change time
    And I click on "Customer initiated" in the dropdown
    And I click on "VERIFY" button
    And the "Your changes are good to be saved." message is displayed
    Then I click on "SAVE CHANGES" button
    Then the "Bungii Saved!" message is displayed
    When I click on "CLOSE" button
    And I wait for "2" mins
    And I Select "Scheduled Trip" from admin sidebar
    And I open the trip for "Testcustomertywd_appleMarkAO LutherAO" the customer for delivery details
    And I get the estimated charge "for customer"
    And I get the driver earnings displayed for "duo"
    And I calculate the driver share and check for "duo"

    When I switch to "ORIGINAL" instance
    When I Switch to "driver" application on "same" devices
    And I Select "AVAILABLE BUNGIIS" from driver App menu
    And I Select Partner portal Trip from available trip
    Then I verify the driver earnings displayed on driver app for "duo"
#   Core-2537: Verify whether driver can accept delivery that are upto 100 lb more then the payload
    And I select "Pallet-1" from items
    And I accept selected Bungii
    Then I should be navigated to "AVAILABLE BUNGIIS" screen
#   Core-2537: Verify whether admin is able to assign the delivery without any validations
    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Scheduled Trip" from admin sidebar
    And I open the trip for "Testcustomertywd_appleMarkAO LutherAO" the customer
    And I Select "Edit Trip Details" option
    And I assign driver "Testdrivertywd_appledc_a_drvH WashingtonH" for the trip
    And I click on "VERIFY" button
    And the "Your changes are good to be saved." message is displayed
    Then I click on "SAVE CHANGES" button
    And the "Bungii Saved!" message is displayed


#  Driver : 9049840254 Payload capacity : 500 lbs
#  Core-2418: Verify Driver Pricing by weight for Duo delivery with Pallet weight in different tier for Floor n Decor Partner
  @ready
  Scenario:Verify Driver Pricing by weight for Duo delivery with Pallet weight in different tier for Floor n Decor Partner
    When I switch to "ORIGINAL" instance
    When I Switch to "driver" application on "same" devices
    And I enter phoneNumber :9049840254 and  Password :Cci12345
    And I click "Log In" button on "Log In" screen on driverApp
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist

    When I request Partner Portal "DUO" Trip for "Floor and Decor - Different Weights" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |washingtondc| NEXT_POSSIBLE | 8877661041 | Testcustomertywd_appleMarkAP LutherAP|
    And I wait for "2" mins
    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Scheduled Trip" from admin sidebar
    And I open the trip for "Testcustomertywd_appleMarkAP LutherAP" the customer for delivery details
    And I get the estimated charge "for customer"
    And I get the driver earnings displayed for "duo"
    And I calculate the driver share and check for "duo-different tier"

    When I switch to "ORIGINAL" instance
    When I Switch to "driver" application on "same" devices
    And I Select "AVAILABLE BUNGIIS" from driver App menu
    And I Select Partner portal Trip from available trip
    Then I verify the driver earnings displayed on driver app for "duo-different tier"
#   Core-2537: Verify whether drivers with low payload capacity are allowed to accept deliveries with high weight
    And I select "Pallet-1" from items
    And I accept selected Bungii
    Then I check inadequate payload pop up is displayed
    And I click "OK" button on alert message
#  Core-2418: Verify Driver Pricing for Floor n Decor delivery when admin convert duo trip to solo
    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Scheduled Trip" from admin sidebar
    And I open the trip for "Testcustomertywd_appleMarkAP LutherAP" the customer
    And I Select "Edit Trip Details" option
    And I change delivery type from "Duo to Solo"
    And I click on "VERIFY" button
    And the "Your changes are good to be saved." message is displayed
    Then I click on "SAVE CHANGES" button
    Then the "Bungii Saved!" message is displayed
    When I click on "CLOSE" button
    And I wait for "2" mins
    And I Select "Scheduled Trip" from admin sidebar
    And I open the trip for "Testcustomertywd_appleMarkAP LutherAP" the customer for delivery details
    And I get the estimated charge "for customer"
    And I get the driver earnings displayed for "solo"
    Then I calculate the driver share and check for "duo to solo conversion"

#   Core-2537: Verify that information of both the pallets are displayed separately on drivers app when a delivery is converted from duo to solo
    When I switch to "ORIGINAL" instance
    When I Switch to "driver" application on "same" devices
    And I Select "AVAILABLE BUNGIIS" from driver App menu
    And I Select Partner portal Trip from available trip
    Then I check information of both the pallets are displayed separately

#  Driver : 9049840258 Payload capacity : 1111 lbs
#  Core-2546: Verify for DUO delivery when a pallet is already accepted by driver it is not available for other driver
  @ready
  Scenario:Verify for DUO delivery when a pallet is already accepted by driver it is not available for other driver
    When I switch to "ORIGINAL" instance
    When I Switch to "driver" application on "same" devices
    And I enter phoneNumber :9049840258 and  Password :Cci12345
    And I click "Log In" button on "Log In" screen on driverApp
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist

    When I request Partner Portal "DUO" Trip for "Floor and Decor" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |washingtondc| NEXT_POSSIBLE | 8877661091 | Testcustomertywd_appleMarkCN LutherCN|

    When I switch to "ORIGINAL" instance
    When I Switch to "driver" application on "same" devices
    And I Select "AVAILABLE BUNGIIS" from driver App menu
    And I Select Partner portal Trip from available trip
#   Core-2546: Verify pallet details are displayed on AVAILABLE Bungii menu
    And I check "pallet-1" details are displayed on "available bungii" page
    And I select "Pallet-1" from items
#   Core-2546: Verify driver can accept using AVAILABLE BUNGII menu when driver pallet is equal to payload capacity
    And I accept selected Bungii
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from scheduled trip
#   Core-2546: Verify pallet details are displayed on SCHEDULE Bungii menu
    And I check "pallet-1" details are displayed on "schedule bungii" page

    When I Select "ACCOUNT > LOGOUT" from driver App menu
    Then I should be able to see data on "LOGOUT" page
    Then I should be navigated to "LOG IN" screen
    And I enter phoneNumber :9049840259 and  Password :Cci12345
    And I click "Log In" button on "Log In" screen on driverApp
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I Select "AVAILABLE BUNGIIS" from driver App menu
    And I Select Partner portal Trip from available trip
    And I select "Pallet-1" from items
    And I accept selected Bungii
    Then I check already accepted pallet pop up is displayed

#CORE-3271:To verify that SOLO lift icon is displayed on driver app for partner delivery that was scheduled without checkbox
  @ready
  Scenario:To verify that SOLO lift icon is displayed on driver app for partner delivery that was scheduled without checkbox
    When I open new "Chrome" browser for "ADMIN PORTAL"
    When I navigate to "Partner" portal configured for "Tile Shop" URL
    #CORE-1735:To verify the pickup time for SOLO and DUO when Partner has not provided Lead time for Partner site
    Then The "SOLO" delivery shoudnt have lead time
    When I enter "valid" password on Partner Portal
    And I click "SIGN IN" button on Partner Portal
    When I click on "Solo" button
    Then The First timeslot should display the time without partner portal lead time
    Then The "DUO" delivery shoudnt have lead time
    When I click on "DUO" button
    Then The First timeslot should display the time without partner portal lead time
    #CORE-3271:To verify that SOLO lift icon is displayed on driver app for partner delivery that was scheduled without che
    When I request Partner Portal "Duo" Trip for "Tile Shop" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |nashville| NEXT_POSSIBLE | 8877661093 | Testcustomertywd_BppleMarkCP LutherCP|

    And I wait for 2 minutes
    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Scheduled Trip" from admin sidebar
    And I open the trip for "Testcustomertywd_BppleMarkCP LutherCP" the customer
    And I click on the "Edit" button from the dropdown
    And I Select "Edit Trip Details" option
    And I change delivery type from "Duo to Solo"
    And I click on "VERIFY" button
    And the "Your changes are good to be saved." message is displayed
    Then I click on "SAVE CHANGES" button
    Then the "Bungii Saved!" message is displayed
    When I click on "CLOSE" button

    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "Testdrivertywd_applens_a_kayQ Stark_nsOnEQ" driver
    And I Select "AVAILABLE BUNGIIS" from driver App menu
    And I Select Partner portal Trip from available trip
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from scheduled trip
    Then I should see "SOLO LIFT" header displayed
    And I start selected Bungii
    And I slide update button on "EN ROUTE" Screen
    And I click on "GOT IT" button
    And I slide update button on "ARRIVED" Screen
    And Driver adds photos to the Bungii
    And I slide update button on "ARRIVED" Screen
    And I slide update button on "LOADING ITEM" Screen
    And Driver adds photos to the Bungii
    And I slide update button on "LOADING ITEM" Screen
    And I slide update button on "DRIVING TO DROP-OFF" Screen
    Then I should see "SOLO LIFT" header displayed
    And I click on "GOT IT" button
    And I slide update button on "UNLOADING ITEMS" Screen
    And Driver adds photos to the Bungii
    And I slide update button on "UNLOADING ITEMS" Screen
    And I click "Skip This Step" button on "Rate customer" screen
    Then I should be navigated to "Bungii completed" screen

#CORE-3271:To verify that SOLO lift with customer Help is displayed on driver app for partner delivery that was scheduled with checkbox selected
  @ready
  Scenario: To verify that SOLO lift with customer Help is displayed on driver app for partner delivery that was scheduled with checkbox selected
    When I open new "Chrome" browser for "ADMIN PORTAL"
    When I navigate to "Partner" portal configured for "Equip-bid" URL
    #CORE-1735:To verify the pickup time for SOLO and DUO when Partner has provided Lead time specific for Partner site
    Then The "Solo" deliveries should have a lead time for "Kansas" partner portal
    When I enter "valid" password on Partner Portal
    And I click "SIGN IN" button on Partner Portal
    When I click on "Solo" button
    Then The first timeslot should display the time including the provided partner portal lead time
    When I click on "DUO" button
    Then The "Duo" deliveries should have a lead time for "Kansas" partner portal
    Then The first timeslot should display the time including the provided partner portal lead time
    #CORE-3271:To verify that SOLO lift with customer Help is displayed on driver app for partner delivery that was scheduled with checkbox selected
    When I request Partner Portal "SOLO" Trip for "Equip-bid" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |kansas| NEXT_POSSIBLE | 8877661094  | Testcustomertywd_appleMarkCQ LutherCQ|
    When I switch to "ORIGINAL" instance
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "Testdrivertywd_appleks_a_drvbg Kansas_bg" driver
    And I Select "AVAILABLE BUNGIIS" from driver App menu
    And I Select Partner portal Trip from available trip
    Then I should see "CUSTOMER HELP" header displayed
    When I accept selected Bungii
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from scheduled trip
    Then I should see "CUSTOMER HELP" header displayed
    And I start selected Bungii
    And I slide update button on "EN ROUTE" Screen
    And I slide update button on "ARRIVED" Screen
    And Driver adds photos to the Bungii
    And I slide update button on "ARRIVED" Screen
    And I slide update button on "LOADING ITEM" Screen
    And Driver adds photos to the Bungii
    And I slide update button on "LOADING ITEM" Screen
    And I slide update button on "DRIVING TO DROP-OFF" Screen
    And I slide update button on "UNLOADING ITEMS" Screen
    And Driver adds photos to the Bungii
    And I slide update button on "UNLOADING ITEMS" Screen
    And I click "Skip This Step" button on "Rate customer" screen
    Then I should be navigated to "Bungii completed" screen
    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "trips" from admin sidebar
    And I open the trip for "Testcustomertywd_appleMarkCQ LutherCQ" the customer
    And I click on the "Delivery details" link beside scheduled bungii for "Completed Deliveries"
    Then "Customer Help" icon should be displayed in all deliveries details page
