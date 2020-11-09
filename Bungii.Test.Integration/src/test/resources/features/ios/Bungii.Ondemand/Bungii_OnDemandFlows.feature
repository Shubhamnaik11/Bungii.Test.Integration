@ios
@bungiiflow
  # this will run in miami
Feature: OnDemandBungii_CancellationFlows
Scenarios where customer requests a Bungii and driver accepts/rejects and cancels the Bungii.
  
  Background:
    And I Switch to "customer" application on "same" devices
  
  @regression
  Scenario: Verify Customer Can Cancel Ondemand Bungii Request in searching state
    And I login as "valid miami" driver on "same" device and make driver status as "Online"
    
    And I Switch to "customer" application on "ORIGINAL" devices
    Given I login as "valid miami" customer and on Home page
    
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location          | Drop Location           | Geofence |
      | Solo   | 7346 coldstream drive miami| 2400 S Bayshore Dr Miami | miami    |
    And I click "Get Estimate" button on "Home" screen
    Then I should be navigated to "Estimate" screen
    And Trip Information should be correctly displayed on Estimate screen
    When I confirm trip with following details
      | LoadTime | PromoCode | Payment Card | Time | PickUpImage | Save Trip Info |
      | 15       |           |              | Now  | Default     | No             |
    Then I should be navigated to "SEARCHING" screen
    
   # When I Switch to "customer" application on "same" devices
    And I click "Cancel" button on "SEARCHING" screen
    Then user is alerted for "CANCEL BUNGII"
    And I should be navigated to "Home" screen
    
  @ready
  Scenario: Verify Driver Can Cancel Ondemand Bungii Request On Enroute State
    Given that ondemand bungii is in progress
      | geofence | Bungii State |
      | miami    | Enroute      |

    When I am on the "LOG IN" page
    When I logged in Customer application using  "valid miami" user
    Then Customer should be navigated to "EN ROUTE" trip status screen

    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid miami" driver
    Then I should be navigated to "EN ROUTE" trip status screen


    And I Switch to "driver" application on "same" devices
    And I click "Cancel" button on "update" screen
    Then Alert message with DRIVER CANCEL BUNGII text should be displayed
    When I click "YES" on alert message
    Then I should be navigated to "Home" screen

    When I Switch to "customer" application on "same" devices
    Then Alert message with DRIVER CANCELLED text should be displayed
    When I click "OK" on alert message
    Then I should be navigated to "Home" screen
 #   And Notification for "Customer" for "DRIVER CANCELLED" should be displayed

  @sanity
  @ready
  Scenario: Verify Driver Can Cancel Ondemand Bungii Request On Arrived State
    Given that ondemand bungii is in progress
      | geofence | Bungii State |
      | miami    | ARRIVED      |
    When I am on the "LOG IN" page
    When I logged in Customer application using  "valid miami" user
    Then Customer should be navigated to "ARRIVED" trip status screen

    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid miami" driver
    Then I should be navigated to "ARRIVED" trip status screen

    When I click "Cancel" button on "update" screen
    Then Alert message with DRIVER CANCEL BUNGII text should be displayed
    When I click "YES" on alert message
    Then I should be navigated to "Home" screen

    When I Switch to "customer" application on "same" devices
    Then Alert message with DRIVER CANCELLED text should be displayed
    When I click "OK" on alert message
    And I should be navigated to "Home" screen
#    And Notification for "Customer" for "DRIVER CANCELLED" should be displayed
  

  @ready
  @pushnotification
  Scenario: Verify Promocode Is Deallocated After Driver Cancels Bungii In Arrived State
    And I login as "valid miami" driver on "same" device and make driver status as "Online"

    And I Switch to "customer" application on "same" devices
    Given I login as "valid miami" customer and on Home page
  
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location          | Drop Location           | Geofence |
      | Solo   | 7346 coldstream drive miami| 2400 S Bayshore Dr Miami | miami    |
    And I click "Get Estimate" button on "Home" screen
    Then I should be navigated to "Estimate" screen
    When I enter following details on "Estimate" screen
      | LoadTime | PromoCode | Payment Card | Time | PickUpImage |
      | 30       |           |              | Now  | Default     |
    And I click "PROMO CODE LINE" button on "Estimate" screen
    And I add "PROMO DOLLAR OFF" PromoCode
    And I click "ADD" button on "PROMOS" screen
    When I tap "Back" on Promos screen
    And I should be navigated to "Estimate" screen
    And I request for bungii using Request Bungii Button
    
    And I view and accept virtual notification for "Driver" for "on demand trip"

    #And I Switch to "customer" application on "same" devices
    #And I click "Ok" button on "BUNGII ACCEPTED" screen

    #And I Switch to "driver" application on "same" devices
    And I slide update button on "EN ROUTE" Screen
    Then I should be navigated to "ARRIVED" screen
    When I click "Cancel" button on "update" screen
    Then Alert message with DRIVER CANCEL BUNGII text should be displayed
    When I click "YES" on alert message

    And I Switch to "customer" application on "same" devices
    Then Alert message with DRIVER CANCELLED text should be displayed
    When I click "OK" on alert message
    And I Select "PROMOS" from Customer App menu
    Then I should able to see expected promo code in available promo code

  @ready
  Scenario: Verify Promocode Is Deallocated When Admin Cancels Bungii Which Was Not Started
    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Promo Code" from admin sidebar
    Then I get promo code for "VALID"
    And I Select "Promo Code" from admin sidebar
    Then I get promo code for "one off"
    

    When I switch to "ORIGINAL" instance
    #And I Switch to "driver" application on "same" devices
    And I login as "valid miami" driver on "same" device and make driver status as "Online"

    And I Switch to "customer" application on "same" devices
    And I am on the "LOG IN" page
    And I logged in Customer application using  "valid miami" user
    
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location          | Drop Location           | Geofence |
      | Solo   | 7346 coldstream drive miami| 2400 S Bayshore Dr Miami | miami    |
    And I click "Get Estimate" button on "Home" screen
    Then I should be navigated to "Estimate" screen
    When I enter following details on "Estimate" screen
      | LoadTime | PromoCode | Payment Card | Time           | PickUpImage |
      | 30       |           |              | NEXT_POSSIBLE  | Default     |
    And I click "PROMO CODE LINE" button on "Estimate" screen
    And I Enter "VALID" value in "Promo Code" field in "Promo" Page
    And I click "ADD" button on "PROMOS" screen
    When I tap "Back" on Promos screen
    And I should be navigated to "Estimate" screen
    Then I save bungii trip time details
    And I request for bungii using Request Bungii Button
    When I click "Done" button on "Success" screen
    
    And I view and accept virtual notification for "Driver" for "SCHEDULED PICKUP AVAILABLE"
    When I Select "SCHEDULED BUNGIIS" from driver App menu
    Then I should be navigated to "SCHEDULED BUNGII" screen
    
    Then I wait for "2" mins
  
    And I open Admin portal and navigate to "Scheduled Deliveries" page
  
    And I Cancel Bungii with following details
      | Charge | Comments |
      | 0      | TEST     |
    Then "Bungii Cancel" message should be displayed on "Scheduled Trips" page
    And Bungii must be removed from the List

    When I switch to "ORIGINAL" instance
    And I Switch to "customer" application on "same" devices
    And I Select "PROMOS" from Customer App menu
    Then I should able to see expected promo code in available promo code
  
  @ready
  Scenario: Verify Promocode Is Deallocated After Driver Cancels Bungii In Enroute State
    When I Switch to "driver" application on "same" devices
    And I login as "valid miami" driver on "same" device and make driver status as "Online"
    
    And I Switch to "customer" application on "same" devices
    Given I login as "valid miami" customer and on Home page
    
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location          | Drop Location           | Geofence |
      | Solo   | 7346 coldstream drive miami| 2400 S Bayshore Dr Miami | miami    |
    And I click "Get Estimate" button on "Home" screen
    Then I should be navigated to "Estimate" screen
    When I enter following details on "Estimate" screen
      | LoadTime | PromoCode | Payment Card | Time | PickUpImage |
      | 30       |           |              | Now  | Default     |
    And I click "PROMO CODE LINE" button on "Estimate" screen
    And I add "PROMO PERCENT OFF" PromoCode
    And I click "ADD" button on "PROMOS" screen
    When I tap "Back" on Promos screen
    And I should be navigated to "Estimate" screen
    And I request for bungii using Request Bungii Button
    Then I should be navigated to "SEARCHING" screen
  
    When I Switch to "driver" application on "same" devices
    And I view and accept virtual notification for "Driver" for "on demand trip"
    
    #And I Switch to "customer" application on "same" devices
    #And I click "Ok" button on "BUNGII ACCEPTED" screen
    
    And I Switch to "driver" application on "same" devices
    Then I should be navigated to "EN ROUTE" screen
    When I click "Cancel" button on "update" screen
    Then Alert message with DRIVER CANCEL BUNGII text should be displayed
    When I click "YES" on alert message
    
    And I Switch to "customer" application on "same" devices
    Then Alert message with DRIVER CANCELLED text should be displayed
    When I click "OK" on alert message
    And I Select "PROMOS" from Customer App menu
    Then I should able to see expected promo code in available promo code




