@ios
@DUO
@scheduled
@bungii
Feature: Scheduled DUO Bungii
  I want  request Scheduled Bungii with Duo type

  Background:
  When I Switch to "customer" application on "same" devices
  
  
  @failed
  @ready
  Scenario: Verify When Customer Cancel A Scheduled Duo Trip Accepted By One Driver Then Driver Gets Notification When App Is In Background [2 Devices]
    Given that duo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   | Customer     | Driver1            | Driver2        |
      | goa      | Scheduled    | 0.5 hour ahead | customer-duo | valid duo driver 1 | valid driver 2 |

    When I Switch to "customer" application on "same" devices
    Given I am on the "LOG IN" page
    When I logged in Customer application using  "customer-duo" user
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist

    And I connect to "extra1" using "Driver1" instance
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid duo driver 1" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
  
    And I Select "AVAILABLE BUNGIIS" from driver App menu
    And I Select Trip from available trip
    When I accept selected Bungii
    #put driver on background
    When I open "customer" application on "same" devices

    When I Switch to "customer" application on "ORIGINAL" devices
    And I Select "MY BUNGIIS" from Customer App menu
    And I select already scheduled bungii
    When I Cancel selected Bungii

    When I open "customer" application on "Driver1" devices
    And I click on notification for "Driver" for "CUSTOMER CANCELLED SCHEDULED BUNGII"
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |

  @failed
  @ready
  Scenario: DUO DELIVERY CANCELLATION | Verify Customer And Other Driver Is Notified When One Of The Driver Cancels The Scheduled Duo Bungii [2 Devices]
    Given that duo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   | Customer     | Driver1            | Driver2        |
      | goa      | enroute      | 0.5 hour ahead | customer-duo | valid duo driver 1 | valid driver 2 |
	
    When I Switch to "customer" application on "same" devices
    Given I am on the "LOG IN" page
    When I logged in Customer application using  "customer-duo" user
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid duo driver 1" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
	
    And I connect to "extra1" using "Driver1" instance
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid driver 2" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
	
    And I click "Cancel" button on "update" screen
    Then Alert message with DRIVER CANCEL BUNGII text should be displayed
    When I click "YES" on alert message

    When I switch to "ORIGINAL" instance
    #message to driver
    Then Alert message with OTHER DRIVER CANCELLED BUNGII text should be displayed
    When I Switch to "driver" application on "same" devices
    And I click on notification for "Customer" for "DRIVER CANCELLED BUNGII"

  @failed
  @ready
  Scenario: Verify Other Driver Notification In Background When One Of The Driver Cancels Duo Scheduled Bungii [2 Devices]
    Given that duo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   | Customer     | Driver1            | Driver2        |
      | goa      | enroute      | 0.5 hour ahead | customer-duo | valid duo driver 1 | valid driver 2 |

    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid duo driver 1" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
  
    When I Switch to "customer" application on "same" devices

    And I connect to "extra1" using "Driver1" instance
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid driver 2" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
	
    And I click "Cancel" button on "update" screen
    Then Alert message with DRIVER CANCEL BUNGII text should be displayed
    When I click "YES" on alert message
    Then I wait for "1" mins

    When I switch to "ORIGINAL" instance
    And I Switch to "customer" application on "same" devices
    Then I click on notification for "driver" for "OTHER DRIVER CANCELLED BUNGII"
    Then Alert message with OTHER DRIVER CANCELLED BUNGII text should be displayed

  @regression
    #Stable
  Scenario: Verify Other Driver Alert In Foreground When One Of The Driver Cancels Duo Scheduled Bungii
    Given that duo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   | Customer     | Driver1            | Driver2        |
      | goa      | enroute      | 0.5 hour ahead | customer-duo | valid duo driver 1 | valid driver 2 |

    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid duo driver 1" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist

  #driver1 in foregroundground
    And I connect to "extra1" using "Driver1" instance
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid driver 2" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
  
    And I click "Cancel" button on "update" screen
    Then Alert message with DRIVER CANCEL BUNGII text should be displayed
    When I click "YES" on alert message

    When I switch to "ORIGINAL" instance
    Then Alert message with OTHER DRIVER CANCELLED BUNGII text should be displayed

  @FAILED2702
  @ready
  Scenario Outline: Verify Customer Amount Calculation For Scheduled Duo Bungii With Promo Code
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid duo driver 1" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    
    
    And I connect to "extra1" using "Driver2" instance
    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid driver 2" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    
    
    And I Switch to "customer" application on "ORIGINAL" devices
    And I logged in Customer application using  "customer-duo" user
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location | Drop Location           |
      | Duo    | Margao Railway Overbridge  | peerbaugh Rd, Peer wadi |
    And I click "Get Estimate" button on "Home" screen
    
    When I select load time as "15" mins
    And I tap "Promo code" on Estimate screen
    And I should be navigated to "PROMOS" screen
    And I add "<PROMO CODE>" PromoCode
    And I click "ADD" button on "PROMOS" screen
    Then I should able to see expected promo code in available promo code
    #When I tap "Back" on Promos screen
    And I enter following details on "Estimate" screen
      | LoadTime | PromoCode | Payment Card | Time          | PickUpImage |
      |          |           |              | NEXT_POSSIBLE | Default     |
    And I request for bungii using Request Bungii Button
    
    
    When I click "Done" button on "Success" screen
    And I Select "Home" from Customer App menu
    
    And I Switch to "driver" application on "same" devices
    And I Select "AVAILABLE BUNGIIS" from driver App menu
    And I Select Trip from available trip
    When I accept selected Bungii
    
    And I Switch to "driver" application on "Driver2" devices
    And I Select "AVAILABLE BUNGIIS" from driver App menu
    And I Select Trip from available trip
    When I accept selected Bungii
    
    When I Switch to "driver" application on "ORIGINAL" devices
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select delivery "1" from scheduled deliveries
    And I start selected Bungii
    And I slide update button on "EN ROUTE" Screen
    
    When I Switch to "driver" application on "Driver2" devices
    And I slide update button on "EN ROUTE" Screen
    
    When I Switch to "driver" application on "ORIGINAL" devices
    And I slide update button on "ARRIVED" Screen
    And I slide update button on "LOADING ITEM" Screen
    And I slide update button on "DRIVING TO DROP OFF" Screen
    And I slide update button on "UNLOADING ITEM" Screen
    Then I accept Alert message for "Reminder: both driver at drop off"
    
    And I Switch to "customer" application on "ORIGINAL" devices
    Then I should be navigated to "Bungii Complete" screen
    And Bungii customer should see "correct details with promo" on Bungii completed page
    When I rate Bungii Driver  with following details and Press "CLOSE" Button
      | Ratting | Tip |
      | 5       | 5   |
    And I click "I DON'T LIKE FREE MONEY" button on "Promotion" screen
    Then I should be navigated to "Home" screen
    
    And I Switch to "driver" application on "ORIGINAL" devices
    Then Bungii driver should see "correct details" on Bungii completed page
    And I click "On To The Next One" button on "Bungii Completed" screen
    
    Then I wait for "3" mins
    
    And I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "trips" from admin sidebar
    And I select "The Beginning of Time" from search peroid
    And I select trip from all deliveries
    Then On admin trip details page "promo" should be displayed
    
    Examples:
      | PROMO CODE        |
      | PROMO DOLLAR OFF  |
      | PROMO PERCENT OFF |
  
  @FAILED2702
  @ready
  Scenario: Verify Driver Can Contact Customer Of A Requested Scheduled Duo Bungii
    Given that duo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   | Customer     | Driver1            | Driver2        |
      | goa      | Accepted     | 0.5 hour ahead | customer-duo | valid duo driver 1 | valid driver 2 |
    
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid duo driver 1" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    
    And I connect to "extra1" using "Driver2" instance
    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid driver 2" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    
    And I Switch to "customer" application on "ORIGINAL" devices
    When I logged in Customer application using  "customer-duo" user
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    
    And I Switch to "driver" application on "Driver2" devices
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select delivery "1" from scheduled deliveries
    Then I should be navigated to "BUNGII DETAILS" screen
    And I start selected Bungii
    
    And I Switch to "driver" application on "ORIGINAL" devices
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select delivery "1" from scheduled deliveries
    And I start selected Bungii
    
    And I Switch to "customer" application on "same" devices
    Then Customer should be navigated to "EN ROUTE" trip status screen
    Then correct details should be displayed to customer for "DUO DRIVER 1-CALL DRIVER"
    And correct details should be displayed to customer for "DUO DRIVER 1-TEXT DRIVER"
    And correct details should be displayed to customer for "DUO DRIVER 2-CALL DRIVER"
    And correct details should be displayed to customer for "DUO DRIVER 2-TEXT DRIVER"
    
    When I Switch to "driver" application on "same" devices
    Then correct details should be displayed to driver for "DUO CUSTOMER-VIEW ITEM"
    And correct details should be displayed to driver for "DUO CUSTOMER-CALL CUSTOMER"
    And correct details should be displayed to driver for "DUO CUSTOMER-TEXT CUSTOMER"
    And correct details should be displayed to driver for "DUO CUSTOMER-TEXT BUNGII SUPPORT"
    And correct details should be displayed to driver for "DUO DRIVER 2-CALL DRIVER"
    And correct details should be displayed to driver for "DUO DRIVER 2-TEXT DRIVER"
    And correct details should be displayed to driver for "DUO DRIVER-TEXT BUNGII SUPPORT"
    And I slide update button on "EN ROUTE" Screen
    
    When I Switch to "driver" application on "Driver2" devices
    Then correct details should be displayed to driver for "DUO CUSTOMER-VIEW ITEM"
    And correct details should be displayed to driver for "DUO CUSTOMER-CALL CUSTOMER"
    And correct details should be displayed to driver for "DUO CUSTOMER-TEXT CUSTOMER"
    And correct details should be displayed to driver for "DUO CUSTOMER-TEXT BUNGII SUPPORT"
    Then correct details should be displayed to driver for "DUO DRIVER 1-CALL DRIVER"
    And correct details should be displayed to driver for "DUO DRIVER 1-TEXT DRIVER"
    And correct details should be displayed to driver for "DUO DRIVER-TEXT BUNGII SUPPORT"
    When I slide update button on "EN ROUTE" Screen
  
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | 9403960188      |                 |
    
  @ready
  Scenario: Verify Non Control Driver Of Ongoing Bungii Can Accept Short Stack Request [2 Devices]
    
    Given that duo schedule bungii is in progress
      | geofence | Bungii State       | Bungii Time   | Customer     | Driver1            | Driver2        |
      | goa      | Driving To Dropoff | 0.5 hour ahead | customer-duo | valid duo driver 1 | valid driver 2 |
    
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid duo driver 1" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    
    
    And I connect to "extra1" using "Driver2" instance
    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid driver 2" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    
    And I Switch to "customer" application on "same" devices
    
    And I Switch to "customer" application on "ORIGINAL" devices
    
    When I request "Solo Ondemand" Bungii as a customer in "goa" geofence
      | Bungii Time | Customer Phone | Customer Name | Customer label | Customer Password |
      | now         | 9403960183     | Mark Cuban    | 2              | Cci12345          |
    
    And I open "customer" application on "ORIGINAL" devices
    And I view and accept virtual notification for "Driver" for "stack trip"
    
    And stack trip information should be displayed on deck
    And I slide update button on "DRIVING TO DROP OFF" Screen
    And I slide update button on "UNLOADING ITEM" Screen
    And I accept Alert message for "Reminder: both driver at drop off"
    
    And I Switch to "driver" application on "Driver2" devices
    
    When I click "On To The Next One" button on "Bungii Completed" screen
    
    And I open "driver" application on "ORIGINAL" devices
    When I click "On To The Next One" button on "Bungii Completed" screen
    Then I should be navigated to "EN ROUTE" screen
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      |                | CUSTOMER2_PHONE |
  
  
 
