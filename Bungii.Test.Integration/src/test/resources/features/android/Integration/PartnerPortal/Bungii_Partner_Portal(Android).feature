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

#    Core-2418: Verify Driver Pricing by weight for Solo delivery for Floor n Decor Partner
    @ready
#    @testsweta
    Scenario: Verify Driver Pricing by weight for Solo delivery for Floor n Decor Partner
      When I request Partner Portal "SOLO" Trip for "Floor and Decor" partner
        |Geofence| Bungii Time   | Customer Phone | Customer Name |
        |washingtondc| NEXT_POSSIBLE | 9999999127 | Testcustomertywd_appleNewRB Customer|
      And I wait for "2" mins
      When I open new "Chrome" browser for "ADMIN PORTAL"
      And I navigate to admin portal
      And I log in to admin portal
      And I Select "Scheduled Trip" from admin sidebar
      And I open the trip for "Testcustomertywd_appleNewRB Customer" the customer for delivery details
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
      And I tap on "ACCEPT" on driver Trip details Page
      And I Select "SCHEDULED BUNGIIS" from driver App menu
      And I Select Trip from driver scheduled trip

      # Core-2418 Verify Driver Pricing is recalculated for Floor n Decor delivery when admin edits the address and service level of Schedule Trip

      When I open new "Chrome" browser for "ADMIN PORTAL"
      And I navigate to admin portal
      And I log in to admin portal
      And I Select "Scheduled Trip" from admin sidebar
      And I open the trip for "Testcustomertywd_appleNewRB Customer" the customer
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
      And I open the trip for "Testcustomertywd_appleNewRB Customer" the customer for delivery details
      And I get the estimated charge "for customer"
      And I calculate the driver share and check for "changed address and service level"

      When I switch to "ORIGINAL" instance
      When I Switch to "driver" application on "same" devices
      And I Select "SCHEDULED BUNGIIS" from driver App menu
      And I Select Trip from scheduled trip
      And I verify the driver earnings displayed on driver app for "changed address and service level"
      And I start selected Bungii
      Then Bungii driver should see "General Instructions"
      And I slide update button on "EN ROUTE" Screen
      Then Bungii driver should see "Pickup Instructions"
      And I slide update button on "ARRIVED" Screen

#    Core-2418 Verify Driver Pricing is recalculated for Floor n Decor delivery when admin edits the address and service level of Live Trip
      When I open new "Chrome" browser for "ADMIN PORTAL"
      And I navigate to admin portal
      And I log in to admin portal
      And I Select "live trips" from admin sidebar
      And I select the live trip for "Testcustomertywd_appleNewRB Customer" customer
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
      And I select the live trip for "Testcustomertywd_appleNewRB Customer" customer for delivery details
      And I get the estimated charge "for customer"
      And I get the driver earnings displayed for "solo"
      Then I calculate the driver share and check for "solo"


      #  Core-2418: Verify Driver Pricing by weight for Duo delivery with both Pallet weight lies same tier for Floor and Decor Partner
    @ready
#      @testsweta
    Scenario:Verify Driver Pricing by weight for Duo delivery with both Pallet weight lies same tier for Floor n Decor Partner
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
      And I am logged in as "Testdrivertywd_appledc_a_web TestdriverA" driver
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
      And I Select "AVAILABLE BUNGIIS" from driver App menu
      And I Select Trip from available trip
      Then I verify the driver earnings displayed on driver app for "duo"

        #  Core-2418: Verify Driver Pricing by weight for Duo delivery with Pallet weight in different tier for Floor n Decor Partner
    @ready
#  @testsweta
    Scenario:Verify Driver Pricing by weight for Duo delivery with Pallet weight in different tier for Floor n Decor Partner
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
      And I open the trip for "Testcustomertywd_appleMarkAR LutherAR" the customer
      And I Select "Edit Trip Details" option
      And I change delivery type from "Duo to Solo"
      And I click on "VERIFY" button
      And the "Your changes are good to be saved." message is displayed
      Then I click on "SAVE CHANGES" button
      Then the "Bungii Saved!" message is displayed
      When I click on "CLOSE" button
      And I wait for "2" mins
      And I Select "Scheduled Trip" from admin sidebar
      And I open the trip for "Testcustomertywd_appleMarkAR LutherAR" the customer for delivery details
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