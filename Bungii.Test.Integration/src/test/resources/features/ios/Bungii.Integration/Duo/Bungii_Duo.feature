@ios
@DUO
@scheduled
@bungii
@DuoWithMultiDevice
    #All Cases are stable in this feature
Feature: Scheduled DUO Bungii
  I want  request Scheduled Bungii with Duo type

  Background:
  When I Switch to "customer" application on "same" devices
	
  @regression
    #Stable
  Scenario: Verify Scheduled Duo Bungii Completion [2 Devices]
    Given that duo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   | Customer     | Driver1            | Driver2        |
      | goa      | Accepted     | 0.5 hour ahead | customer-duo | valid duo driver 1 | valid driver 2 |
  
    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid duo driver 1" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I click "Offline" button on "Home" screen on driverApp
    
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select delivery "1" from scheduled deliveries
    Then I should be navigated to "BUNGII DETAILS" screen
    And I start selected Bungii
   Then Trip Information should be correctly displayed on "EN ROUTE" status screen for driver
    #Then I should be navigated to "EN ROUTE" screen

    And I connect to "extra1" using "Driver2" instance
    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid driver 2" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I click "Offline" button on "Home" screen on driverApp
    
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select delivery "1" from scheduled deliveries
    Then I should be navigated to "BUNGII DETAILS" screen
    And I start selected Bungii
    Then Trip Information should be correctly displayed on "EN ROUTE" status screen for driver
    #Then I should be navigated to "EN ROUTE" screen

    And I Switch to "customer" application on "ORIGINAL" devices
    And I logged in Customer application using  "customer-duo" user
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    Then I should be navigated to "EN ROUTE" screen

    When I Switch to "driver" application on "same" devices
    And I slide update button on "EN ROUTE" Screen
    Then Trip Information should be correctly displayed on "ARRIVED" status screen for driver
    #Then I should be navigated to "ARRIVED" screen

    When I Switch to "driver" application on "Driver2" devices
    And I slide update button on "EN ROUTE" Screen
    Then Trip Information should be correctly displayed on "ARRIVED" status screen for driver
    #Then I should be navigated to "ARRIVED" screen

    When I Switch to "driver" application on "ORIGINAL" devices
    Then I should see "Your duo teammate is on the way" message
    Then I should see "Your duo teammate has arrived at the pickup location. Please coordinate to begin loading" message
   # When I Switch to "driver" application on "ORIGINAL" devices
    And I slide update button on "ARRIVED" Screen
    And Driver adds photos to the Bungii
    And I slide update button on "ARRIVED" Screen
#    Then I accept Alert message for "Reminder: both driver at pickup"
    Then Trip Information should be correctly displayed on "LOADING ITEMS" status screen for driver
    #Then I should be navigated to "LOADING ITEMS" screen

    When I Switch to "driver" application on "Driver2" devices
    Then I should see "Your duo teammate is on the way" message
    Then I should see "Your duo teammate has arrived at the pickup location. Please coordinate to begin loading" message
    And I slide update button on "ARRIVED" Screen
    And Driver adds photos to the Bungii
    And I slide update button on "ARRIVED" Screen
#    Then I accept Alert message for "Reminder: both driver at pickup"
    Then Trip Information should be correctly displayed on "LOADING ITEMS" status screen for driver
    #Then I should be navigated to "LOADING ITEMS" screen

    When I Switch to "customer" application on "ORIGINAL" devices
    Then I should be navigated to "LOADING ITEM" screen

    When I Switch to "driver" application on "same" devices
    And I slide update button on "LOADING ITEMS" Screen
    And Driver adds photos to the Bungii
    And I slide update button on "LOADING ITEMS" Screen
    Then Trip Information should be correctly displayed on "DRIVING TO DROP-OFF" status screen for driver
    #Then I should be navigated to "DRIVING TO DROP-OFF" screen

    When I Switch to "driver" application on "Driver2" devices
    And I slide update button on "LOADING ITEMS" Screen
    And Driver adds photos to the Bungii
    And I slide update button on "LOADING ITEMS" Screen
#   Then Trip Information should be correctly displayed on "DRIVING TO DROP-OFF" status screen for driver
    #Then I should be navigated to "DRIVING TO DROP-OFF" screen

    When I Switch to "driver" application on "ORIGINAL" devices
    And I slide update button on "DRIVING TO DROP-OFF" Screen
    Then Trip Information should be correctly displayed on "UNLOADING ITEMS" status screen for driver
    #Then I should be navigated to "UNLOADING ITEMS" screen

    When I Switch to "driver" application on "Driver2" devices
    And I slide update button on "DRIVING TO DROP-OFF" Screen
    Then Trip Information should be correctly displayed on "UNLOADING ITEMS" status screen for driver
    #Then I should be navigated to "UNLOADING ITEMS" screen
    And I slide update button on "UNLOADING ITEMS" Screen
    And Driver adds photos to the Bungii
    And I slide update button on "UNLOADING ITEMS" Screen
    Then I accept Alert message for "Reminder: both driver at drop off"

    When I Switch to "driver" application on "ORIGINAL" devices
    And I slide update button on "UNLOADING ITEMS" Screen
    And Driver adds photos to the Bungii
    And I slide update button on "UNLOADING ITEMS" Screen
    Then I accept Alert message for "Reminder: both driver at drop off"

    #When I Switch to "driver" application on "Driver2" devices
    #And I slide update button on "UNLOADING ITEM" Screen
    #Then I accept Alert message for "Reminder: both driver at drop off"

    And I Switch to "customer" application on "ORIGINAL" devices
    Then I should be navigated to "Bungii Complete" screen
    And Bungii customer should see "correct details" on Bungii completed page
    When I rate Bungii Driver  with following details and Press "CLOSE" Button
      | Ratting | Tip |
      | 5       | 5   |
    And I click "I DON'T LIKE FREE MONEY" button on "Promotion" screen
    Then I should be navigated to "Home" screen

    And I Switch to "driver" application on "ORIGINAL" devices
    And I click "Skip This Step" button on "Rate customer" screen
    Then Bungii driver should see "correct details" on Bungii completed page
    And I click "On To The Next One" button on "Bungii completed" screen
    And I Select "HOME" from driver App menu
  
    When I Switch to "driver" application on "Driver2" devices
    And I click "Skip This Step" button on "Rate customer" screen
    Then Bungii driver should see "correct details" on Bungii completed page
    When I click "On To The Next One" button on "Bungii completed" screen
    And I Select "HOME" from driver App menu
    
    
  @regression
    @sanity
  #stable
  Scenario: Verify Decked Alert Status And Projected Arrival Time and Time To Finish by Calculations Of Long Stacked Bungii Over Current Ondemand Bungii [2 Devices]
    Given that ondemand bungii is in progress
      | geofence | Bungii State |
      | goa      | Enroute      |
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    
    And I Switch to "customer" application on "same" devices
    And I logged in Customer application using  "existing" user
    
    And I connect to "extra1" using "Customer2" instance
    And I Switch to "customer" application on "same" devices
    And I am on the "LOG IN" page
    And I logged in Customer application using  "valid customer2" user
    
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location        | Drop Location                |
      | Solo   | Creative capsule verna | Margao Railway Overbridge |
    And I click "Get Estimate" button on "Home" screen
    When I confirm trip with following details
      | LoadTime | PromoCode | Payment Card | Time | PickUpImage | Save Trip Info |
      | 15       |           |              | Now  | Default     | No             |
    Then I should be navigated to "SEARCHING" screen
    
    When I Switch to "customer" application on "ORIGINAL" devices
    And I view and accept virtual notification for "Driver" for "stack trip"
    And stack trip information should be displayed on deck
    And try to finish time should be correctly displayed for long stack trip

    When  I switch to "Customer2" instance
    Then correct details should do be displayed on BUNGII ACCEPTED screen for Stack screen
    When I click "Ok" button on "BUNGII ACCEPTED" screen
    Then correct details should do be displayed on BUNGII ACCEPTED with arrival time screen for Stack screen

    When I Switch to "driver" application on "ORIGINAL" devices
    And I slide update button on "EN ROUTE" Screen
    #Then I should be navigated to "ARRIVED" screen
    Then I should be navigated to "ARRIVED" screen on driverApp
    And stack trip information should be displayed on deck
    And try to finish time should be correctly displayed for long stack trip

    When I Switch to "customer" application on "same" devices
    Then I should be navigated to "ARRIVED" screen

    When I Switch to "driver" application on "same" devices
    And I slide update button on "ARRIVED" Screen
    #Then I should be navigated to "LOADING ITEMS" screen
    Then I should be navigated to "LOADING ITEMS" screen on driverApp
    And stack trip information should be displayed on deck
    And try to finish time should be correctly displayed for long stack trip
    And I slide update button on "LOADING ITEMS" Screen
    #Then I should be navigated to "DRIVING TO DROP-OFF" screen
    Then I should be navigated to "DRIVING TO DROP-OFF" screen on driverApp
    And stack trip information should be displayed on deck
    And try to finish time should be correctly displayed for long stack trip
    And I slide update button on "DRIVING TO DROP-OFF" Screen
    #Then I should be navigated to "UNLOADING ITEMS" screen
    Then I should be navigated to "UNLOADING ITEMS" screen on driverApp
    And stack trip information should be displayed on deck
    And try to finish time should be correctly displayed for long stack trip
    And I slide update button on "UNLOADING ITEMS" Screen
    And I click "Skip This Step" button on "Rate customer" screen
    Then I should be navigated to "Bungii completed" screen
    When I click "On To The Next One" button on "Bungii completed" screen

    And I Switch to "customer" application on "same" devices
    When I click "CLOSE BUTTON" button on "Bungii Complete" screen
    When I click "I DON'T LIKE FREE MONEY" button on "Promotion" screen
    Then I should be navigated to "Home" screen

    When I Switch to "customer" application on "Customer2" devices
    Then I should be navigated to "EN ROUTE" screen

    When I Switch to "driver" application on "ORIGINAL" devices
    And I slide update button on "EN ROUTE" Screen
    #Then I should be navigated to "ARRIVED" screen
    Then I should be navigated to "ARRIVED" screen on driverApp
    And I slide update button on "ARRIVED" Screen
    #Then I should be navigated to "LOADING ITEMS" screen
    Then I should be navigated to "LOADING ITEMS" screen on driverApp
    And I slide update button on "LOADING ITEMS" Screen
    #Then I should be navigated to "DRIVING TO DROP-OFF" screen
    Then I should be navigated to "DRIVING TO DROP-OFF" screen on driverApp
    And I slide update button on "DRIVING TO DROP-OFF" Screen
    #Then I should be navigated to "UNLOADING ITEMS" screen
    Then I should be navigated to "UNLOADING ITEMS" screen on driverApp

    When I Switch to "customer" application on "Customer2" devices
    Then I should be navigated to "UNLOADING ITEM" screen

    When I Switch to "driver" application on "ORIGINAL" devices
    And I slide update button on "UNLOADING ITEMS" Screen
    And I click "Skip This Step" button on "Rate customer" screen
    Then I should be navigated to "Bungii completed" screen
    When I click "On To The Next One" button on "Bungii completed" screen

    And I Switch to "customer" application on "Customer2" devices
    When I click "CLOSE BUTTON" button on "Bungii Complete" screen
    When I click "I DON'T LIKE FREE MONEY" button on "Promotion" screen

  @ready
    #stable
  Scenario: Verify Decked Alert Status And Projected Arrival Time and Time To Finish by Calculations Of Long Stacked Bungii Over Current Scheduled Bungii [2 Devices]
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   |
      | goa      | enroute      | 0.5 hour ahead |

    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
  
    And I Switch to "customer" application on "same" devices
    And I logged in Customer application using  "existing" user
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist

    And I connect to "extra1" using "Customer2" instance
    And I Switch to "customer" application on "same" devices
    And I am on the "LOG IN" page
    And I logged in Customer application using  "valid customer2" user
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location        | Drop Location                |
      | Solo   | Creative capsule verna | Margao Railway Overbridge |
    And I click "Get Estimate" button on "Home" screen
    When I confirm trip with following details
      | LoadTime | PromoCode | Payment Card | Time | PickUpImage | Save Trip Info |
      | 15       |           |              | Now  | Default     | No             |
    Then I should be navigated to "SEARCHING" screen

    When I open "customer" application on "ORIGINAL" devices
    And I view and accept virtual notification for "Driver" for "stack trip"
  
    And try to finish time should be correctly displayed for long stack trip

    When  I switch to "Customer2" instance
    Then correct details should do be displayed on BUNGII ACCEPTED screen for Stack screen
    When I click "Ok" button on "BUNGII ACCEPTED" screen
    Then correct details should do be displayed on BUNGII ACCEPTED with arrival time screen for Stack screen

    When I Switch to "driver" application on "ORIGINAL" devices
    And I slide update button on "EN ROUTE" Screen
    Then I should be navigated to "ARRIVED" screen
    And I slide update button on "ARRIVED" Screen
    Then I should be navigated to "LOADING ITEM" screen
    And I slide update button on "LOADING ITEM" Screen
    Then I should be navigated to "DRIVING TO DROP OFF" screen
    And I slide update button on "DRIVING TO DROP OFF" Screen
    Then I should be navigated to "UNLOADING ITEM" screen
    And I slide update button on "UNLOADING ITEM" Screen
    And I click "Skip This Step" button on "Rate customer" screen
    Then I should be navigated to "Bungii completed" screen
    When I click "On To The Next One" button on "Bungii completed" screen

    And I Switch to "customer" application on "same" devices
    When I click "CLOSE BUTTON" button on "Bungii Complete" screen
    When I click "I DON'T LIKE FREE MONEY" button on "Promotion" screen
    Then I should be navigated to "Home" screen

    When I Switch to "customer" application on "Customer2" devices
    Then I should be navigated to "EN ROUTE" screen

    When I Switch to "driver" application on "ORIGINAL" devices
    And I slide update button on "EN ROUTE" Screen
    Then I should be navigated to "ARRIVED" screen
    And I slide update button on "ARRIVED" Screen
    Then I should be navigated to "LOADING ITEM" screen
    And I slide update button on "LOADING ITEM" Screen
    Then I should be navigated to "DRIVING TO DROP OFF" screen
    And I slide update button on "DRIVING TO DROP OFF" Screen
    Then I should be navigated to "UNLOADING ITEM" screen
    And I slide update button on "UNLOADING ITEM" Screen
    And I click "Skip This Step" button on "Rate customer" screen
    Then I should be navigated to "Bungii completed" screen
    When I click "On To The Next One" button on "Bungii completed" screen

    And I Switch to "customer" application on "Customer2" devices
    When I click "CLOSE BUTTON" button on "Bungii Complete" screen
    When I click "I DON'T LIKE FREE MONEY" button on "Promotion" screen

  @ready
    #Stable
  Scenario: Verify Decked Alert Status And Projected Arrival Time and Time To Finish by Calculations Of Short Stacked Bungii Over Current Ondemand Bungii  [2 Devices]
    Given that ondemand bungii is in progress
      | geofence | Bungii State        |
      | goa      | DRIVING TO DROP OFF |
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
  
    And I Switch to "customer" application on "same" devices
    And I logged in Customer application using  "existing" user
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist

    And I connect to "extra1" using "Customer2" instance
    And I Switch to "customer" application on "same" devices
    And I am on the "LOG IN" page
    And I logged in Customer application using  "valid customer2" user
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location        | Drop Location                |
      | Solo   | Creative capsule verna | Margao Railway Overbridge |
    And I click "Get Estimate" button on "Home" screen
    When I confirm trip with following details
      | LoadTime | PromoCode | Payment Card | Time | PickUpImage | Save Trip Info |
      | 15       |           |              | Now  | Default     | No             |
    Then I should be navigated to "SEARCHING" screen

    When I open "customer" application on "ORIGINAL" devices
 
    And I view and accept virtual notification for "Driver" for "stack trip"
    Then I calculate projected driver arrival time
    And stack trip information should be displayed on deck
    And try to finish time should be correctly displayed for short stack trip
    When  I switch to "Customer2" instance
    Then correct details should do be displayed on BUNGII ACCEPTED screen for Stack screen
    When I click "Ok" button on "BUNGII ACCEPTED" screen
    Then correct details should do be displayed on BUNGII ACCEPTED with arrival time screen for Stack screen

    When I Switch to "driver" application on "ORIGINAL" devices
    And I slide update button on "DRIVING TO DROP OFF" Screen
    Then I should be navigated to "UNLOADING ITEM" screen
    And stack trip information should be displayed on deck
    And try to finish time should be correctly displayed for short stack trip
    
    And I slide update button on "UNLOADING ITEM" Screen
    And I click "Skip This Step" button on "Rate customer" screen
    Then I should be navigated to "Bungii completed" screen
    When I click "On To The Next One" button on "Bungii completed" screen

    And I Switch to "customer" application on "same" devices
    When I click "CLOSE BUTTON" button on "Bungii Complete" screen
    When I click "I DON'T LIKE FREE MONEY" button on "Promotion" screen
    Then I should be navigated to "Home" screen

    When I Switch to "customer" application on "Customer2" devices
    Then I should be navigated to "EN ROUTE" screen

    When I Switch to "driver" application on "ORIGINAL" devices
    And I slide update button on "EN ROUTE" Screen
    And I slide update button on "ARRIVED" Screen
    And I slide update button on "LOADING ITEM" Screen
    And I slide update button on "DRIVING TO DROP OFF" Screen
    And I slide update button on "UNLOADING ITEM" Screen
    And I click "Skip This Step" button on "Rate customer" screen
    Then I should be navigated to "Bungii completed" screen
    When I click "On To The Next One" button on "Bungii completed" screen

    And I Switch to "customer" application on "Customer2" devices
    When I click "CLOSE BUTTON" button on "Bungii Complete" screen
    When I click "I DON'T LIKE FREE MONEY" button on "Promotion" screen
  
  #######################################################Single Device###########################################################
  @ready
  Scenario: Verify Decked Alert Status And Projected Arrival Time and Time To Finish by Calculations Of Short Stacked Bungii Over Current Scheduled Bungii [1 Device]
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   |
      | goa      | DRIVING TO DROP OFF      | 0.5 hour ahead |
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    
    And I Switch to "customer" application on "same" devices
    And I am on the "LOG IN" page
    And I logged in Customer application using  "valid customer2" user
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location        | Drop Location                |
      | Solo   | Creative capsule verna | Margao Railway Overbridge |
    And I click "Get Estimate" button on "Home" screen
    When I confirm trip with following details
      | LoadTime | PromoCode | Payment Card | Time | PickUpImage | Save Trip Info |
      | 15       |           |              | Now  | Default     | No             |
    Then I should be navigated to "SEARCHING" screen
    And I wait for 1 minutes
    And I view and accept virtual notification for "Driver" for "stack trip"
    Then I calculate projected driver arrival time
    And stack trip information should be displayed on deck
    And try to finish time should be correctly displayed for short stack trip
    And I Switch to "customer" application on "same" devices
    Then correct details should do be displayed on BUNGII ACCEPTED screen for Stack screen
    When I click "Ok" button on "BUNGII ACCEPTED" screen
    Then correct details should do be displayed on BUNGII ACCEPTED with arrival time screen for Stack screen
    
    When I Switch to "driver" application on "ORIGINAL" devices
    And I slide update button on "DRIVING TO DROP OFF" Screen
    Then I should be navigated to "UNLOADING ITEM" screen
    And stack trip information should be displayed on deck
    And try to finish time should be correctly displayed for short stack trip
    And I slide update button on "UNLOADING ITEM" Screen
    And I click "Skip This Step" button on "Rate customer" screen
    Then I should be navigated to "Bungii completed" screen
    When I click "On To The Next One" button on "Bungii completed" screen

    And I Switch to "customer" application on "same" devices
    Then I should be navigated to "EN ROUTE" screen

    When I Switch to "driver" application on "same" devices
    And I slide update button on "EN ROUTE" Screen
    And I slide update button on "ARRIVED" Screen
    And I slide update button on "LOADING ITEM" Screen
    And I slide update button on "DRIVING TO DROP OFF" Screen
    And I slide update button on "UNLOADING ITEM" Screen
    And I click "Skip This Step" button on "Rate customer" screen
    Then I should be navigated to "Bungii completed" screen
    When I click "On To The Next One" button on "Bungii completed" screen

    And I Switch to "customer" application on "same" devices
    When I click "CLOSE BUTTON" button on "Bungii Complete" screen
    When I click "I DON'T LIKE FREE MONEY" button on "Promotion" screen



#Core-3507 : To verify that for converted trip from solo to duo displays the vehicle info on drivers app -partner trip
  @ready
  Scenario: To verify that for converted trip from solo to duo displays the vehicle info on drivers app
    When I request Partner Portal "Solo" Trip for "MRFM" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |Kansas| NEXT_POSSIBLE | 8877661037 | Testcustomertywd_appleMarkAL LutherAL|
    And I wait for "2" mins
    And I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Scheduled Trip" from admin sidebar
    And I open the trip for "Testcustomertywd_appleMarkAL LutherAL" the customer
    And I Select "Edit Trip Details" option
    And I change delivery type from "Solo to Duo"
    And I click on "VERIFY" button
    Then I click on "SAVE CHANGES" button
    And the "Bungii Saved!" message is displayed
    When I click on "Close" button
    Then I wait for "2" mins
    And I get the new pickup reference generated
    When As a driver "Testdrivertywd_appleks_a_drvay Kansas_ay" and "Testdrivertywd_appleks_a_drvaz Kansas_az" perform below action with respective "Duo Scheduled" partner portal trip
      | driver1 state | driver2 state |
      | Accepted      | Accepted      |
      | Enroute       | Enroute       |
    And I switch to "ORIGINAL" instance
    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "Testdrivertywd_appleks_a_drvay Kansas_ay" driver
    And I click on the Duo teammate image
    Then I should see the driver vehicle information
    And I navigate back
    When I slide update button on "EN ROUTE" Screen
    And I click on the Duo teammate image
    Then I should see the driver vehicle information
    And I navigate back
    And I slide update button on "ARRIVED" Screen
    And I accept Alert message for "Reminder: both driver at pickup"
    When I click on the Duo teammate image
    Then I should see the driver vehicle information
    And I navigate back

    And I connect to "extra1" using "Driver2" instance
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "Testdrivertywd_appleks_a_drvaz Kansas_az" driver
    And I slide update button on "EN ROUTE" Screen
    And I click on the Duo teammate image
    Then I should see the driver vehicle information
    And I navigate back
    And I slide update button on "ARRIVED" Screen
    And I accept Alert message for "Reminder: both driver at pickup"
    And I click on the Duo teammate image
    Then I should see the driver vehicle information
    And I navigate back
    And I slide update button on "LOADING ITEM" Screen
    And I slide update button on "DRIVING TO DROP OFF" Screen
    And I slide update button on "UNLOADING ITEM" Screen
    Then I accept Alert message for "Reminder: both driver at drop off"
    And I should be navigated to "Rate duo teammate" screen

    When I Switch to "driver" application on "ORIGINAL" devices
    And I slide update button on "LOADING ITEM" Screen
    And I click on the Duo teammate image
    Then I should see the driver vehicle information
    And I navigate back
    And I slide update button on "DRIVING TO DROP OFF" Screen
    And I click on the Duo teammate image
    When I should see the driver vehicle information
    And I navigate back
    And I slide update button on "UNLOADING ITEM" Screen
    Then I accept Alert message for "Reminder: both driver at drop off"
    And I should be navigated to "Rate duo teammate" screen

#Core-3507 : To verify that vehicle info is displayed on duo teammate screen for duo customer trip
  @ready
  Scenario: To verify that vehicle info is displayed on duo teammate screen for duo customer trip
    Given that duo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   | Customer     | Driver1            | Driver2        |
      | goa      | enroute     | 0.5 hour ahead | customer-duo | valid duo driver 1 | valid driver 2 |
    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid duo driver 1" driver
    And I click on the Duo teammate image
    Then I should see the driver vehicle information
    And I navigate back
    When I slide update button on "EN ROUTE" Screen
    And I click on the Duo teammate image
    Then I should see the driver vehicle information
    And I navigate back
    And I slide update button on "ARRIVED" Screen
    And I accept Alert message for "Reminder: both driver at pickup"
    When I click on the Duo teammate image
    Then I should see the driver vehicle information
    And I navigate back


    And I connect to "extra1" using "Driver2" instance
    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid driver 2" driver
    And I click on the Duo teammate image
    Then I should see the driver vehicle information
    And I navigate back
    And I slide update button on "EN ROUTE" Screen
    And I click on the Duo teammate image
    Then I should see the driver vehicle information
    And I navigate back
    And I slide update button on "ARRIVED" Screen
    And I accept Alert message for "Reminder: both driver at pickup"
    And I click on the Duo teammate image
    Then I should see the driver vehicle information
    And I navigate back
    And I slide update button on "LOADING ITEM" Screen
    And I click on the Duo teammate image
    Then I should see the driver vehicle information
    And I navigate back
    And I slide update button on "DRIVING TO DROP OFF" Screen
    And I click on the Duo teammate image
    Then I should see the driver vehicle information
    And I navigate back
    And I slide update button on "UNLOADING ITEM" Screen
    Then I accept Alert message for "Reminder: both driver at drop off"
    And I should be navigated to "Rate duo teammate" screen

    When I Switch to "driver" application on "ORIGINAL" devices
    And I slide update button on "LOADING ITEM" Screen
    And I click on the Duo teammate image
    Then I should see the driver vehicle information
    And I navigate back
    And I slide update button on "DRIVING TO DROP OFF" Screen
    And I click on the Duo teammate image
    When I should see the driver vehicle information
    And I navigate back
    And I slide update button on "UNLOADING ITEM" Screen
    Then I accept Alert message for "Reminder: both driver at drop off"
    And I should be navigated to "Rate duo teammate" screen


  @testIos
  Scenario: Verify Driver Pricing by weight for Solo delivery for Floor n Decor Partner

    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "Testdrivertywd_applens_a_kayR Stark_nsOnER" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist

    And I connect to "extra1" using "Driver2" instance
    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "Testdrivertywd_applens_a_kayS Stark_nsOnES" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist

    When I request Partner Portal "Duo" Trip for "Tile Shop" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |nashville| NEXT_POSSIBLE | 9999999127 | Testcustomertywd_appleNewRB Customer|

    When I Switch to "driver" application on "ORIGINAL" devices
    And I Select "AVAILABLE BUNGIIS" from driver App menu
    And I Select Partner portal Trip from available trip
    When I accept selected Bungii
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from scheduled trip
    Then I should see "CUSTOMER HELP" header displayed
    And I start selected Bungii

    And I Switch to "driver" application on "driver2" devices
    And I Select "AVAILABLE BUNGIIS" from driver App menu
    And I Select Partner portal Trip from available trip
    When I accept selected Bungii
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from scheduled trip
    Then I should see "CUSTOMER HELP" header displayed
    And I start selected Bungii

    When I Switch to "driver" application on "ORIGINAL" devices
    When I slide update button on "EN ROUTE" Screen
    And I slide update button on "ARRIVED" Screen
    And I accept Alert message for "Reminder: both driver at pickup"

    And I Switch to "driver" application on "driver2" devices
    When I slide update button on "EN ROUTE" Screen
    And I slide update button on "ARRIVED" Screen
    And I accept Alert message for "Reminder: both driver at pickup"