@ios
@bungii

Feature: Bungiis
  Background:
	#When I Switch to "customer" application on "same" devices
 
@sanity
@regression
  @failures
Scenario: Verify Ondemand Bungii completion As An iOS User
When I Switch to "driver" application on "same" devices
And I login as "valid nashville" driver on "same" device and make driver status as "Online"

And I Switch to "customer" application on "same" devices
Given I am on the "LOG IN" page
When I logged in Customer application using  "valid nashville" user
And I request for  bungii for given pickup and drop location
| Driver | Pickup Location                 | Drop Location                      | geofence  |
| Solo   | Nashville International Airport | Graylynn Drive Nashville Tennessee | nashville |
And I click "Get Estimate" button on "Home" screen
Then I should be navigated to "Estimate" screen
When I confirm trip with following details
| LoadTime | PromoCode | Payment Card | Time | PickUpImage |
| 15       |           |              | Now  | Default     |
Then I should be navigated to "SEARCHING" screen

And I view and accept virtual notification for "Driver" for "on demand trip"
  
  And I Switch to "customer" application on "same" devices
  Then I should be navigated to "BUNGII ACCEPTED" screen
  When I click "Ok" button on "BUNGII ACCEPTED" screen
  Then Customer should be navigated to "EN ROUTE" trip status screen
  
When I Switch to "driver" application on "same" devices
Then I should be navigated to "EN ROUTE" trip status screen
And I slide update button on "EN ROUTE" Screen
Then I should be navigated to "ARRIVED" trip status screen
  And I slide update button on "ARRIVED" Screen
  Then I should be navigated to "LOADING ITEM" trip status screen
  And I slide update button on "LOADING ITEM" Screen
Then I should be navigated to "DRIVING TO DROP OFF" trip status screen
And I slide update button on "DRIVING TO DROP OFF" Screen
Then I should be navigated to "UNLOADING ITEM" trip status screen
And I slide update button on "UNLOADING ITEM" Screen
Then I should be navigated to "Bungii Completed" screen
When I click "On To The Next One" button on "Bungii Completed" screen

And I Switch to "customer" application on "same" devices
Then I should be navigated to "Bungii Complete" screen
When I click "CLOSE BUTTON" button on "Bungii Complete" screen
Then I should be navigated to "Promotion" screen
When I click "I DON'T LIKE FREE MONEY" button on "Promotion" screen
Then I should be navigated to "Home" screen
  
  
  @ready
  Scenario: Verify Requesting Of Ondemand Bungii With Referral Code
    Given I have customer with referral code
    #And I Switch to "driver" application on "same" devices
    #And I am on the "LOG IN" page on driverApp
    #And I am logged in as "valid nashville" driver
    #And I Select "HOME" from driver App menu
    #Then I change driver status to "Online"
    And I login as "valid nashville" driver on "same" device and make driver status as "Online"
    
    When I Switch to "customer" application on "same" devices
    When I am on the "LOG IN" page
    And I logged in Customer application using  "newly created user" user
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location                 | Drop Location                                        | Geofence  |
      | Solo   | Nashville International Airport | 5629 Nashville Rd, Franklin, KY 42134, United States | nashville |
    And I click "Get Estimate" button on "Home" screen
    Then I should be navigated to "Estimate" screen
    When I select load time as "15" mins
    And I tap "Promo code" on Estimate screen
    Then I should able to see expected promo code in available promo code
    #When I tap "Back" on Promos screen
    And I enter following details on "Estimate" screen
      | LoadTime | PromoCode | Payment Card | Time | PickUpImage |
      |          |           |              | Now  | Default     |
    And I request for bungii using Request Bungii Button
    Then I should be navigated to "SEARCHING" screen
    
    And I view and accept virtual notification for "Driver" for "on demand trip"

    #And I Switch to "customer" application on "same" devices
    #Then I should be navigated to "BUNGII ACCEPTED" screen
    #When I click "Ok" button on "BUNGII ACCEPTED" screen

    #And I Switch to "driver" application on "same" devices
    And I slide update button on "EN ROUTE" Screen
    And I slide update button on "ARRIVED" Screen
    And I slide update button on "LOADING ITEM" Screen
    And I slide update button on "DRIVING TO DROP OFF" Screen
    And I slide update button on "UNLOADING ITEM" Screen
    
    And I should be navigated to "Bungii Completed" screen
    And I Switch to "customer" application on "same" devices
    Then I should be navigated to "Bungii Complete" screen
    And Bungii customer should see "correct details with promo" on Bungii completed page
    And I click "CLOSE BUTTON" button on "Bungii Complete" screen
    Then I should be navigated to "Promotion" screen
    When I click "I DON'T LIKE FREE MONEY" button on "Promotion" screen
    Then I should be navigated to "Home" screen
    
    When I Switch to "driver" application on "same" devices
    Then Bungii driver should see "correct details" on Bungii completed page
    And I click "On To The Next One" button on "Bungii Completed" screen
  
  @ready
  Scenario: Verify Requesting Of Ondemand Bungii With Received Referred Code
    Given I have customer with referral code received
    And I login as "valid nashville" driver on "same" device and make driver status as "Online"
    
    When I Switch to "customer" application on "same" devices
    When I am on the "LOG IN" page
    And I logged in Customer application using  "valid nashville" user
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location                 | Drop Location                                        | Geofence  |
      | Solo   | Nashville International Airport | 5629 Nashville Rd, Franklin, KY 42134, United States | nashville |
    And I click "Get Estimate" button on "Home" screen
    Then I should be navigated to "Estimate" screen
    When I select load time as "15" mins
    And I tap "Promo code" on Estimate screen
    Then I should see "referral code received with out first time tag" on Promos page
    Then I should able to see expected promo code in available promo code
    #When I tap "Back" on Promos screen
    And I enter following details on "Estimate" screen
      | LoadTime | PromoCode | Payment Card | Time | PickUpImage |
      |          |           |              | Now  | Default     |
    And I request for bungii using Request Bungii Button
    Then I should be navigated to "SEARCHING" screen
    
    And I view and accept virtual notification for "Driver" for "on demand trip"

    #And I Switch to "customer" application on "same" devices
    #Then I should be navigated to "BUNGII ACCEPTED" screen
    #When I click "Ok" button on "BUNGII ACCEPTED" screen

    #And I Switch to "driver" application on "same" devices
    And I slide update button on "EN ROUTE" Screen
    And I slide update button on "ARRIVED" Screen
    And I slide update button on "LOADING ITEM" Screen
    And I slide update button on "DRIVING TO DROP OFF" Screen
    And I slide update button on "UNLOADING ITEM" Screen
    And I should be navigated to "Bungii Completed" screen
    
    And I Switch to "customer" application on "same" devices
    Then I should be navigated to "Bungii Complete" screen
    And Bungii customer should see "correct details with promo" on Bungii completed page
    And I click "CLOSE BUTTON" button on "Bungii Complete" screen
    Then I should be navigated to "Promotion" screen
    When I click "I DON'T LIKE FREE MONEY" button on "Promotion" screen
    Then I should be navigated to "Home" screen
    
    When I Switch to "driver" application on "same" devices
    Then Bungii driver should see "correct details" on Bungii completed page
    And I click "On To The Next One" button on "Bungii Completed" screen
  
  
  @regression
  Scenario:Verify Driver Notification For The Tip Amount Received From Customer
    Given that ondemand bungii is in progress
      | geofence  | Bungii State |
      | nashville | UNLOADING ITEM      |
    When I Switch to "customer" application on "same" devices
    
    When I am on the "LOG IN" page
    And I logged in Customer application using  "valid nashville" user
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid nashville" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I slide update button on "UNLOADING ITEM" Screen
    And I click on notification for "customer" for "BUNGII FINISHED -RATE DRIVER"
    
    When I rate Bungii Driver  with following details and Press "OK" Button
      | Ratting | Tip |
      | 5       | 5   |
    And I click on notification for "Driver" for "TIP RECEIVED 5 DOLLAR"
    
    And I click "On To The Next One" button on "Bungii Completed" screen
  
  @sanity
  @regression
  @ondemand
  Scenario: Verify Ondemand Bungii Flow Till Completion
    When I Switch to "driver" application on "same" devices
    And I login as "valid nashville" driver on "same" device and make driver status as "Online"
    
    And I Switch to "customer" application on "same" devices
    Given I am on the "LOG IN" page
   # When I am on Customer logged in Home page
    When I logged in Customer application using  "valid nashville" user
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location                 | Drop Location                      | geofence  |
      | Solo   | Nashville International Airport | Graylynn Drive Nashville Tennessee | nashville |
    And I click "Get Estimate" button on "Home" screen
    Then I should be navigated to "Estimate" screen
    When I confirm trip with following details
      | LoadTime | PromoCode | Payment Card | Time | PickUpImage |
      | 15       |           |              | Now  | Default     |
    Then I should be navigated to "SEARCHING" screen
    
    When I Switch to "driver" application on "same" devices
    And I view and accept virtual notification for "Driver" for "on demand trip"
    
    When I Switch to "customer" application on "same" devices
    Then I should be navigated to "BUNGII ACCEPTED" screen
    When I click "Ok" button on "BUNGII ACCEPTED" screen
    Then Customer should be navigated to "EN ROUTE" trip status screen
    
    When I Switch to "driver" application on "same" devices
    Then I should be navigated to "EN ROUTE" trip status screen
    And I slide update button on "EN ROUTE" Screen
    Then I should be navigated to "ARRIVED" trip status screen
    
    When I Switch to "customer" application on "same" devices
    Then Customer should be navigated to "ARRIVED" trip status screen
    
    When I Switch to "driver" application on "same" devices
    And I slide update button on "ARRIVED" Screen
    Then I should be navigated to "LOADING ITEM" trip status screen
    
    When I Switch to "customer" application on "same" devices
    Then Customer should be navigated to "LOADING ITEM" trip status screen
    
    When I Switch to "driver" application on "same" devices
    And I slide update button on "LOADING ITEM" Screen
    Then I should be navigated to "DRIVING TO DROP OFF" trip status screen
    
    When I Switch to "customer" application on "same" devices
    Then Customer should be navigated to "DRIVING TO DROP OFF" trip status screen
    
    When I Switch to "driver" application on "same" devices
    And I slide update button on "DRIVING TO DROP OFF" Screen
    Then I should be navigated to "UNLOADING ITEM" trip status screen
    
    When I Switch to "customer" application on "same" devices
    Then Customer should be navigated to "UNLOADING ITEM" trip status screen
    
    When I Switch to "driver" application on "same" devices
    And I slide update button on "UNLOADING ITEM" Screen
    Then I should be navigated to "Bungii Completed" screen
    When I click "On To The Next One" button on "Bungii Completed" screen
    
    And I Switch to "customer" application on "same" devices
    Then I should be navigated to "Bungii Complete" screen
    When I click "CLOSE BUTTON" button on "Bungii Complete" screen
    Then I should be navigated to "Promotion" screen
    When I click "I DON'T LIKE FREE MONEY" button on "Promotion" screen
    Then I should be navigated to "Home" screen
  