@ios
@DUO
@scheduled
@bungii
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
    
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select delivery "1" from scheduled deliveries
    Then I should be navigated to "BUNGII DETAILS" screen
    And I start selected Bungii
    Then I should be navigated to "EN ROUTE" screen
    
    And I connect to "extra1" using "Driver2" instance
    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid driver 2" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select delivery "1" from scheduled deliveries
    Then I should be navigated to "BUNGII DETAILS" screen
    And I start selected Bungii
    Then I should be navigated to "EN ROUTE" screen

    And I Switch to "customer" application on "ORIGINAL" devices
    And I logged in Customer application using  "customer-duo" user
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    Then I should be navigated to "EN ROUTE" screen

    When I Switch to "driver" application on "same" devices
    And I slide update button on "EN ROUTE" Screen
    Then I should be navigated to "ARRIVED" screen

    When I Switch to "driver" application on "Driver2" devices
    And I slide update button on "EN ROUTE" Screen
    Then I should be navigated to "ARRIVED" screen

    When I Switch to "driver" application on "ORIGINAL" devices
    And I slide update button on "ARRIVED" Screen
    Then I accept Alert message for "Reminder: both driver at pickup"
    Then I should be navigated to "LOADING ITEM" screen

    When I Switch to "driver" application on "Driver2" devices
    And I slide update button on "ARRIVED" Screen
    Then I accept Alert message for "Reminder: both driver at pickup"
    Then I should be navigated to "LOADING ITEM" screen

    When I Switch to "customer" application on "ORIGINAL" devices
    Then I should be navigated to "LOADING ITEM" screen

    When I Switch to "driver" application on "same" devices
    And I slide update button on "LOADING ITEM" Screen
    Then I should be navigated to "DRIVING TO DROP OFF" screen

    When I Switch to "driver" application on "Driver2" devices
    And I slide update button on "LOADING ITEM" Screen
    Then I should be navigated to "DRIVING TO DROP OFF" screen

    When I Switch to "driver" application on "ORIGINAL" devices
    And I slide update button on "DRIVING TO DROP OFF" Screen
    Then I should be navigated to "UNLOADING ITEM" screen

    When I Switch to "driver" application on "Driver2" devices
    And I slide update button on "DRIVING TO DROP OFF" Screen
    Then I should be navigated to "UNLOADING ITEM" screen

    When I Switch to "driver" application on "ORIGINAL" devices
    And I slide update button on "UNLOADING ITEM" Screen
    Then I accept Alert message for "Reminder: both driver at drop off"

    When I Switch to "driver" application on "Driver2" devices
    And I slide update button on "UNLOADING ITEM" Screen
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
    Then Bungii driver should see "correct details" on Bungii completed page
    And I click "On To The Next One" button on "Bungii Completed" screen
    And I Select "HOME" from driver App menu
  
    When I Switch to "driver" application on "Driver2" devices
    Then Bungii driver should see "correct details" on Bungii completed page
    When I click "On To The Next One" button on "Bungii Completed" screen
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
      | Solo   | Creative capsule verna | Old Goa Road, Velha Goa, Goa |
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
    Then I should be navigated to "ARRIVED" screen
    And stack trip information should be displayed on deck
    And try to finish time should be correctly displayed for long stack trip

    When I Switch to "customer" application on "same" devices
    Then I should be navigated to "ARRIVED" screen

    When I Switch to "driver" application on "same" devices
    And I slide update button on "ARRIVED" Screen
    Then I should be navigated to "LOADING ITEM" screen
    And stack trip information should be displayed on deck
    And try to finish time should be correctly displayed for long stack trip
    And I slide update button on "LOADING ITEM" Screen
    Then I should be navigated to "DRIVING TO DROP OFF" screen
    And stack trip information should be displayed on deck
    And try to finish time should be correctly displayed for long stack trip
    And I slide update button on "DRIVING TO DROP OFF" Screen
    Then I should be navigated to "UNLOADING ITEM" screen
    And stack trip information should be displayed on deck
    And try to finish time should be correctly displayed for long stack trip
    And I slide update button on "UNLOADING ITEM" Screen
    Then I should be navigated to "Bungii Completed" screen
    When I click "On To The Next One" button on "Bungii Completed" screen

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

    When I Switch to "customer" application on "Customer2" devices
    Then I should be navigated to "UNLOADING ITEM" screen

    When I Switch to "driver" application on "ORIGINAL" devices
    And I slide update button on "UNLOADING ITEM" Screen
    Then I should be navigated to "Bungii Completed" screen
    When I click "On To The Next One" button on "Bungii Completed" screen

    And I Switch to "customer" application on "Customer2" devices
    When I click "CLOSE BUTTON" button on "Bungii Complete" screen
    When I click "I DON'T LIKE FREE MONEY" button on "Promotion" screen

  @regression
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
      | Solo   | Creative capsule verna | Old Goa Road, Velha Goa, Goa |
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
    Then I should be navigated to "Bungii Completed" screen
    When I click "On To The Next One" button on "Bungii Completed" screen

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
    Then I should be navigated to "Bungii Completed" screen
    When I click "On To The Next One" button on "Bungii Completed" screen

    And I Switch to "customer" application on "Customer2" devices
    When I click "CLOSE BUTTON" button on "Bungii Complete" screen
    When I click "I DON'T LIKE FREE MONEY" button on "Promotion" screen
	
  @regression
    #Stable
    @d
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
      | Solo   | Creative capsule verna | Old Goa Road, Velha Goa, Goa |
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
    Then I should be navigated to "Bungii Completed" screen
    When I click "On To The Next One" button on "Bungii Completed" screen

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
    Then I should be navigated to "Bungii Completed" screen
    When I click "On To The Next One" button on "Bungii Completed" screen

    And I Switch to "customer" application on "Customer2" devices
    When I click "CLOSE BUTTON" button on "Bungii Complete" screen
    When I click "I DON'T LIKE FREE MONEY" button on "Promotion" screen
  
  #######################################################Single Device###########################################################
  @regression
    @d
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
      | Solo   | Creative capsule verna | Old Goa Road, Velha Goa, Goa |
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
    Then I should be navigated to "Bungii Completed" screen
    When I click "On To The Next One" button on "Bungii Completed" screen
    
    And I Switch to "customer" application on "same" devices
    Then I should be navigated to "EN ROUTE" screen
    
    When I Switch to "driver" application on "same" devices
    And I slide update button on "EN ROUTE" Screen
    And I slide update button on "ARRIVED" Screen
    And I slide update button on "LOADING ITEM" Screen
    And I slide update button on "DRIVING TO DROP OFF" Screen
    And I slide update button on "UNLOADING ITEM" Screen
    Then I should be navigated to "Bungii Completed" screen
    When I click "On To The Next One" button on "Bungii Completed" screen
  
    And I Switch to "customer" application on "same" devices
    When I click "CLOSE BUTTON" button on "Bungii Complete" screen
    When I click "I DON'T LIKE FREE MONEY" button on "Promotion" screen
    