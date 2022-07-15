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
    And I driver adds photos to the Bungii
    And I slide update button on "ARRIVED" Screen

#    Core-2418 Verify Driver Pricing is recalculated for Floor n Decor delivery when admin edits the address and service level of Live Trip
    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "live trips" from admin sidebar
    And I select the live trip for "Testcustomertywd_appleMarkAS LutherAS" customer
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
    And I driver adds photos to the Bungii
    And I slide update button on "LOADING ITEM" Screen
    And I slide update button on "DRIVING TO DROP-OFF" Screen
    Then Bungii driver should see "Drop-off instructions"
    And I slide update button on "UNLOADING ITEMS" Screen
    And I driver adds photos to the Bungii
    And I slide update button on "UNLOADING ITEMS" Screen
    And I click "Skip This Step" button on "Rate customer" screen
    Then I should be navigated to "Bungii Completed" screen


#  Core-2418: Verify Driver Pricing by weight for Duo delivery with both Pallet weight lies same tier for Floor and Decor Partner
  @ready
  Scenario:Verify Driver Pricing by weight for Duo delivery with both Pallet weight lies same tier for Floor n Decor Partner
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
    And I enter phoneNumber :9766000001 and  Password :Cci12345
    And I click "Log In" button on "Log In" screen on driverApp
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
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

    #  Core-2418: Verify Driver Pricing by weight for Duo delivery with Pallet weight in different tier for Floor n Decor Partner
  @ready
  Scenario:Verify Driver Pricing by weight for Duo delivery with Pallet weight in different tier for Floor n Decor Partner
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
    And I enter phoneNumber :9766000001 and  Password :Cci12345
    And I click "Log In" button on "Log In" screen on driverApp
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I Select "AVAILABLE BUNGIIS" from driver App menu
    And I Select Partner portal Trip from available trip
    Then I verify the driver earnings displayed on driver app for "duo-different tier"

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
    And I calculate the driver share and check for "duo to solo conversion"

    When I switch to "ORIGINAL" instance
    When I Switch to "driver" application on "same" devices
    And I Select "AVAILABLE BUNGIIS" from driver App menu
    And I Select Partner portal Trip from available trip
    And I Select "AVAILABLE BUNGIIS" from driver App menu
    And I Select Partner portal Trip from available trip
    And I verify the driver earnings displayed on driver app for "solo"