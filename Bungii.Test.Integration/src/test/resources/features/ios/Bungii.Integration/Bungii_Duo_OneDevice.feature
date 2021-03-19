@ios
@DUO
@scheduled
@bungii
Feature: Scheduled Bungii on one device
  I want  request Scheduled Bungii with Duo type

  Background:
  When I Switch to "customer" application on "same" devices
  
  @regression
     #stable
  Scenario: STACKING | Verify Driver Can Receive Long Stack Ondemand Request When Ongoing Ondemand Bungii is On Arrived State - Goa Geofence [1 Device]
	Given that ondemand bungii is in progress
	  | geofence | Bungii State |
	  | goa      | ARRIVED      |
	When I Switch to "driver" application on "same" devices
	And I am on the "LOG IN" page on driverApp
	And I am logged in as "valid" driver
	
	When I request another "Solo Ondemand" Bungii as a customer in "goa" geofence
	  | Bungii Time | Customer Phone | Customer Name | Customer label | Customer Password |
	  | now         | 9403960183     | Mark Cuban    |               | Cci12345          |
	
	And I view and accept virtual notification for "second" delivery of "Driver" for "stack trip"
	And stack trip information should be displayed on deck
	
	Then I cancel all bungiis of customer
	  | Customer Phone  | Customer2 Phone |
	  | CUSTOMER1_PHONE      | CUSTOMER2_PHONE      |
  
  @regression
 #stable
  Scenario: STACKING | Verify Driver Can Receive Short Stack Ondemand Request when Ongoing Ondemand Bungii is On Unloading Item State - Goa Geofence [1 Device]
	Given that ondemand bungii is in progress
	  | geofence | Bungii State   |
	  | goa      | UNLOADING ITEM |
	When I Switch to "driver" application on "same" devices
	And I am on the "LOG IN" page on driverApp
	And I am logged in as "valid" driver
	When I request another "Solo Ondemand" Bungii as a customer in "goa" geofence
	  | Bungii Time | Customer Phone | Customer Name | Customer label | Customer Password |
	  | now         | 9403960183     | Mark Cuban    |               | Cci12345          |
	And I view and accept virtual notification for "second" delivery of "Driver" for "stack trip"
	And stack trip information should be displayed on deck
	
	Then I cancel all bungiis of customer
	  | Customer Phone  | Customer2 Phone |
	  | CUSTOMER1_PHONE | CUSTOMER2_PHONE  |
	
  @sanity
  @regression
	#Stable
  Scenario: Verify Scheduled Duo Bungii Can Be Requested As An iOS Customer in Goa Geofence [1 Device]
    And I logged in Customer application using  "customer-duo" user
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location | Drop Location                |
      | Duo    | Margao Railway Overbridge  | Old Goa Road, Velha Goa, Goa |
    And I click "Get Estimate" button on "Home" screen
    
    Then I should be navigated to "Estimate" screen
    When I confirm trip with following details
      | LoadTime | PromoCode | Payment Card | Time          | PickUpImage | Save Trip Info |
      | 30       |           |              | NEXT_POSSIBLE | Default | Yes            |
    Then I should be navigated to "Success" screen
    And I click "Done" button on "Success" screen
  
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | 9403960188      |                 |
    
  @regression
    #stable
  Scenario: Verify Scheduled Duo Bungii can be accepted by drivers and they are shown under displayed under Scheduled List upon accepting [1 Device]
    Given I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid duo driver 1" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
  
    And that duo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   | Customer     | Driver1            | Driver2        |
      | goa      | Requested     | 0.5 hour ahead | customer-duo | valid duo driver 1 | valid driver 2 |
  
    Given I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid duo driver 1" driver
    
    And I Select "AVAILABLE BUNGIIS" from driver App menu
    And I Select Trip from available trip
    Then I should be navigated to "BUNGII DETAILS" screen
    And Driver Bungii Information should be correctly displayed on BUNGII DETAILS screen
    When I accept selected Bungii
	
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select delivery "1" from scheduled deliveries
    Then I should be navigated to "BUNGII DETAILS" screen
  
    When I Select "ACCOUNT > LOGOUT" from driver App menu
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid driver 2" driver
    
    And I Select "AVAILABLE BUNGIIS" from driver App menu
    And I Select Trip from available trip
    Then Driver Bungii Information should be correctly displayed on BUNGII DETAILS screen
    When I accept selected Bungii
	
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select delivery "1" from scheduled deliveries
    Then I should be navigated to "BUNGII DETAILS" screen
  
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | 9403960188      |                 |
    
    
  
  @ready
  Scenario: Verify Customer Can View Ongoing Bungii Progress Screens When Trip Is Started By Control Driver [1 Device]
    Given that duo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   | Customer     | Driver1            | Driver2        |
      | goa      | Accepted     | 0.5 hour ahead | customer-duo | valid duo driver 1 | valid driver 2 |
    
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid duo driver 1" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select delivery "1" from scheduled deliveries
    And I start selected Bungii
    Then I should be navigated to "EN ROUTE" screen
    Then I check ETA of "control driver"

    And I Switch to "customer" application on "same" devices
    When I logged in Customer application using  "customer-duo" user
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    Then I should be navigated to "EN ROUTE" screen
    Then "control driver" eta should be displayed to customer
    
    When I Switch to "driver" application on "ORIGINAL" devices
    And I slide update button on "EN ROUTE" Screen
    Then I should be navigated to "ARRIVED" screen
    And I slide update button on "ARRIVED" Screen
    Then I accept Alert message for "Reminder: both driver at pickup"
    Then I should be navigated to "LOADING ITEM" screen

    When I Switch to "customer" application on "same" devices
    Then I should be navigated to "LOADING ITEM" screen

    When I Switch to "driver" application on "ORIGINAL" devices
    And I slide update button on "LOADING ITEM" Screen
    Then I should be navigated to "DRIVING TO DROP OFF" screen
    Then I check ETA of "control driver"

    When I Switch to "customer" application on "same" devices
    Then I should be navigated to "DRIVING TO DROP OFF" screen
    Then "control driver" eta should be displayed to customer
    
    When I Switch to "driver" application on "ORIGINAL" devices
    And I slide update button on "DRIVING TO DROP OFF" Screen
    Then I should be navigated to "UNLOADING ITEM" screen
    And I slide update button on "UNLOADING ITEM" Screen
    Then I accept Alert message for "Reminder: both driver at drop off"
    Then I should be navigated to "Bungii Completed" screen
    Then I wait for "1" mins
    When I click "On To The Next One" button on "Bungii Completed" screen

    When I Switch to "customer" application on "same" devices
    Then I wait for "2" mins
    Then I should be navigated to "BUNGII COMPLETE" screen
    When I click "CLOSE BUTTON" button on "Bungii Complete" screen
    When I click "I DON'T LIKE FREE MONEY" button on "Promotion" screen
  
  @ready
  Scenario:STACKING | Verify Driver can start the Stack Bungii [1 Device]
    Given that ondemand bungii is in progress
      | geofence | Bungii State   |
      | goa      | UNLOADING ITEM |
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    
    When I Switch to "customer" application on "same" devices
    When I request "Solo Ondemand" Bungii as a customer in "goa" geofence
      | Bungii Time | Customer Phone | Customer Name | Customer label | Customer Password |
      | now         | 9403960183     | Mark Cuban    | 2              | Cci12345          |
  
    And I view and accept virtual notification for "Driver" for "stack trip"
  
    Given I am on the "LOG IN" page
    When I enter Username :9403960183 and  Password :{VALID}
    And I click "Log In" button on "Log In" screen
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    
    When I Switch to "driver" application on "ORIGINAL" devices
    And I slide update button on "UNLOADING ITEM" Screen
    Then I should be navigated to "Bungii Completed" screen
    When I click "On To The Next One" button on "Bungii Completed" screen
    Then I should be navigated to "EN ROUTE" screen
    
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 9403960183     |                 |
  
  
  @ready
  Scenario: STACKING | Verify Manually Ending Bungii For A Driver That Has Stacked Bungii Should See Summary And Start Screen Of The Stacked Bungii [1 Device]
    Given that ondemand bungii is in progress
      | geofence | Bungii State        |
      | goa      | DRIVING TO DROP OFF |
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    
    When I request "Solo Ondemand" Bungii as a customer in "goa" geofence
      | Bungii Time | Customer Phone | Customer Name | Customer label | Customer Password |
      | now         | 9403960183     | Mark Cuban    | 2              | Cci12345          |
    And I view and accept virtual notification for "Driver" for "stack trip"
    And stack trip information should be displayed on deck
    
    When I Switch to "customer" application on "same" devices
    Given I am on the "LOG IN" page
    When I enter Username :9403960183 and  Password :{VALID}
    And I click "Log In" button on "Log In" screen
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    
    When bungii admin manually end bungii created by "CUSTOMER1"
    
    When I Switch to "driver" application on "same" devices
    Then I should be navigated to "Bungii Completed" screen
    When I click "On To The Next One" button on "Bungii Completed" screen
    Then I should be navigated to "EN ROUTE" screen
    
    When I Switch to "customer" application on "same" devices
    Then I should be navigated to "EN ROUTE" screen
    
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      |                | CUSTOMER2_PHONE |
  
  @ready
  @push
  Scenario:Verify Customer Can Cancel Stacked Ondemand Bungii Accepted By a Driver [1 Device]
    Given that ondemand bungii is in progress
      | geofence | Bungii State   |
      | goa      | UNLOADING ITEM |
	
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    
   # When I Switch to "customer" application on "same" devices
    When I request another "Solo Ondemand" Bungii as a customer in "goa" geofence
      | Bungii Time | Customer Phone | Customer Name | Customer label | Customer Password |
      | now         | 9403960183     | Mark Cuban    |               | Cci12345          |
    
    And I view and accept virtual notification for "Driver" for "stack trip"
    
    When I Switch to "customer" application on "same" devices
    Given I am on the "LOG IN" page
    When I enter Username :9403960183 and  Password :{VALID}
    And I click "Log In" button on "Log In" screen
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
  
    #And I Select "MY BUNGIIS" from Customer App menu
    #And I select already scheduled bungii
    #Then I Cancel selected Bungii
    When I Switch to "customer" application on "same" devices
    Then I should be navigated to "BUNGII ACCEPTED" screen
    When I click "CANCEL BUNGII" on bungii accepted screen
    Then I see "Alert: Bungii cancel confirmation" on bungii accepted screen
    When I click "Dismiss on Alert message" on bungii accepted screen
    Then I should be navigated to "BUNGII ACCEPTED" screen
   When I click "CANCEL BUNGII" on bungii accepted screen
    When I click "Cantact Support on Alert message" on bungii accepted screen
   And correct details should be displayed to customer for "customer support-SMS"
    
    When I click "CANCEL BUNGII" on bungii accepted screen
    When I click "CANCEL BUNGII on Alert message" on bungii accepted screen
    Then I see "Alert: Bungii cancel sucessfully" on bungii accepted screen
    When I click "OK" on alert message
    
    Then I should be navigated to "HOME" screen
    
    When I Switch to "driver" application on "same" devices
    And I click on notification for "Driver" for "CUSTOMER CANCEL STACK TRIP"
    And stack trip information should not be displayed on deck
    
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |
  
  