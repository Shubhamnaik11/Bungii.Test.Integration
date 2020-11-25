@android
@duo
@bungii
  #These feature will run in atlanta geofence

Feature: Bungii Duo Scheduled

  #change user login
  @regression
  Scenario: Verify When Customer Cancels Duo Trip Accepted By One Driver Then Driver Gets A Notification Though The App Remains In Background
    Given that duo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   | Customer | Driver1 | Driver2        |
      | atlanta  | Accepted    | NEXT_POSSIBLE | valid    | valid   | valid driver 2 |

    When I Switch to "customer" application on "same" devices
    And I am on customer Log in page
    And I am logged in as "valid atlanta" customer

    And I connect to "extra1" using "Driver1" instance
    When I Switch to "driver" application on "same" devices

    And I am on the LOG IN page on driver app
    And I am logged in as "valid atlanta" driver
    
    #put driver on background
    When I Open "customer" application on "same" devices
    When I Switch to "customer" application on "ORIGINAL" devices
    And I tap on "Menu" > "MY BUNGIIS" link
    And I select already scheduled bungii
    When I Cancel selected Bungii

    When I Switch to "customer" application on "Driver1" devices
    And I click on notification for "CUSTOMER CANCELLED SCHEDULED BUNGII"
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |

  #@regression
  @ready
  Scenario: DONE : Verify When Customer Cancels Duo Trip Accepted By One Driver Then Driver Gets A Notification When The App Remains open
    Given that duo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   | Customer | Driver1 | Driver2        |
      | atlanta  | Accepted    | NEXT_POSSIBLE | valid    | valid   | valid driver 2 |

    When I Switch to "customer" application on "same" devices
    And I am on customer Log in page
    And I am logged in as "valid atlanta" customer

    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid atlanta" driver
    And I Select "SCHEDULED BUNGIIS" from driver App menu
  
    When I Switch to "customer" application on "same" devices
    And I tap on "Menu" > "MY BUNGIIS" link
    And I select already scheduled bungii
    When I Cancel selected Bungii

    When I switch to "Driver1" instance
    Then Alert message with CUSTOMER CANCELLED SCHEDULED BUNGII text should be displayed
    When I click "OK" on alert message
    
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |
    
  @regression
  Scenario: Verify Duo Bungii As An Android Customer
    Given I am logged in as "valid atlanta" customer
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid atlanta" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    
    And I connect to "extra1" using "Driver2" instance
    And I Open "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid driver 2" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I Select "Home" from driver App menu
    
    And I Open "customer" application on "ORIGINAL" devices
    And I enter "atlanta pickup and dropoff locations" on Bungii estimate
    And I tap on "two drivers selector" on Bungii estimate
    Then I should see "two drivers selected" on Bungii estimate
    When I tap on "Get Estimate button" on Bungii estimate
    And I select Bungii Time as "next possible scheduled for duo"
    And I add "1" photos to the Bungii
    And I add loading/unloading time of "30 mins"
    And I get Bungii details on Bungii Estimate
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    And I check if the customer is on success screen
    And I tap on "Done after requesting a Scheduled Bungii" on Bungii estimate
    
    #Step to keep driver 2 instance alive
    And I Switch to "driver" application on "Driver2" devices
    
    And I Switch to "driver" application on "ORIGINAL" devices
    And I tap on "Available Trips link" on Driver Home page
    And I Select Trip from driver available trip
    And I tap on "ACCEPT" on driver Trip details Page
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    
    And I Switch to "driver" application on "Driver2" devices
    And I tap on "Available Trips link" on Driver Home page
    And I Select Trip from driver available trip
    And I tap on "ACCEPT" on driver Trip details Page
    
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from driver scheduled trip
    And Bungii Driver "Start Schedule Bungii" request
    Then Bungii driver should see "Enroute screen"
    
    When I Switch to "driver" application on "ORIGINAL" devices
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from driver scheduled trip
    And Bungii Driver "Start Schedule Bungii" request
    Then Bungii driver should see "Enroute screen"
    
    And I Switch to "driver" application on "Driver2" devices
    When I Switch to "customer" application on "same" devices
    Then for a Bungii I should see "Enroute screen"
    
    When I Switch to "driver" application on "Driver2" devices
    And Bungii Driver "slides to the next state"
    Then Bungii driver should see "Arrived screen"
    
    When I Switch to "driver" application on "ORIGINAL" devices
    And Bungii Driver "slides to the next state"
    Then Bungii driver should see "Arrived screen"
    
    When I Switch to "customer" application on "same" devices
    Then for a Bungii I should see "Arrived screen"
    
    When I Switch to "driver" application on "Driver2" devices
    And Bungii Driver "slides to the next state"
    Then I accept Alert message for "Reminder: both driver at pickup"
    And Bungii driver should see "Loading Item screen"
    
    When I Switch to "driver" application on "ORIGINAL" devices
    And Bungii Driver "slides to the next state"
    Then I accept Alert message for "Reminder: both driver at pickup"
    And Bungii driver should see "Loading Item screen"
    
    When I Switch to "customer" application on "same" devices
    Then for a Bungii I should see "Loading Item screen"
    
    When I Switch to "driver" application on "Driver2" devices
    And Bungii Driver "slides to the next state"
    Then Bungii driver should see "Driving to DropOff screen"
    
    When I Switch to "driver" application on "ORIGINAL" devices
    And Bungii Driver "slides to the next state"
    Then Bungii driver should see "Driving to DropOff screen"
    
    When I Switch to "customer" application on "same" devices
    Then for a Bungii I should see "Driving to DropOff screen"
    
    When I Switch to "driver" application on "Driver2" devices
    And Bungii Driver "slides to the next state"
    Then Bungii driver should see "Unloading Item screen"
    
    When I Switch to "driver" application on "ORIGINAL" devices
    And Bungii Driver "slides to the next state"
    Then Bungii driver should see "Unloading Item screen"
    
    When I Switch to "customer" application on "same" devices
    Then for a Bungii I should see "Unloading Item screen"
    
    When I Switch to "driver" application on "Driver2" devices
    And Bungii Driver "slides to the next state"
    Then I accept Alert message for "Reminder: both driver at drop off"
    
    When I Switch to "driver" application on "ORIGINAL" devices
    And Bungii Driver "slides to the next state"
    Then I accept Alert message for "Reminder: both driver at drop off"
    
    When I Switch to "customer" application on "same" devices
    And Bungii customer should see "correct details" on Bungii completed page
    And I tap on "OK on complete" on Bungii estimate
    And I tap on "No free money" on Bungii estimate
    
    When I Switch to "driver" application on "same" devices
    Then Bungii driver should see "correct details" on Bungii completed page
    Then Bungii Driver "completes Bungii"
    
    When I Switch to "driver" application on "Driver2" devices
    Then Bungii driver should see "correct details" on Bungii completed page
    Then Bungii Driver "completes Bungii"
    And Customer should receive "Your Bungii Receipt" email
  
  
  
  
  @regression
  Scenario: STACK BUNGII: Long Stack : Verify Status Of Current Ondemand Bungii And Stacked pickup And Stack Request Alert Message And Decked Request
    ##############################
    Given I am on customer Log in page
    When I am logged in as "valid" customer
    And I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid atlanta" driver
    And I tap on "Go Online button" on Driver Home page
    
    And I Switch to "customer" application on "ORIGINAL" devices
    And I enter "atlanta pickup and dropoff locations" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I add "1" photos to the Bungii
    And I add loading/unloading time of "30 mins"
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
##############################
    
    And I Open "driver" application on "same" devices
    And Bungii Driver "accepts On Demand Bungii" request
    Then "Enroute screen" page should be opened
    
    When I Switch to "customer" application on "same" devices
    And I tap "OK on Driver Accepted screen" during a Bungii
    Then "Enroute screen" page should be opened
    
    When I request "Solo Ondemand" Bungii as a customer in "atlanta" geofence
      | Bungii Time | Customer Phone | Customer Name                      | Customer label | Customer Password |
      | now         | 9871450107     | Testcustomertywd_apple_AGQFCg Test | 2              | Cci12345          |
    
    And I Open "driver" application on "ORIGINAL" devices
    Then Bungii Driver "verify stack message" request
    And Bungii Driver "accepts stack message" request
    And I accept Alert message for "Alert: Display Stack trip after current trip"
    And stack trip information should be displayed on deck
    And try to finish time should be correctly displayed for long stack trip
    
    And I connect to "extra1" using "customer2" instance
    And I Open "customer" application on "same" devices
    When I Switch to "customer" application on "customer2" devices
    And I am logged in as "valid customer 2" customer
    
   # When  I switch to "customer2" instance
    Then for a Bungii I should see "BUNGII ACCEPTED for Stack screen"
    When I tap "OK on Driver Accepted screen" during a Bungii
    Then for a Bungii I should see "Stack accepted screen"
    
    
    When I Switch to "driver" application on "ORIGINAL" devices
    Then "Enroute screen" page should be opened
    When Bungii Driver "slides to the next state"
    Then "Arrived screen" page should be opened
    And stack trip information should be displayed on deck
    And try to finish time should be correctly displayed for long stack trip
    And Bungii Driver "slides to the next state"
    And Bungii Driver "slides to the next state"
    Then "Driving to DropOff screen" page should be opened
    And stack trip information should be displayed on deck
    And try to finish time should be correctly displayed for long stack trip
    And Bungii Driver "slides to the next state"
    Then "Unloading Item screen" page should be opened
    And stack trip information should be displayed on deck
    And try to finish time should be correctly displayed for long stack trip
    And Bungii Driver "slides to the next state"
    Then Bungii Driver "completes Bungii"
    
    When I Switch to "customer" application on "customer2" devices
    Then "Enroute screen" page should be opened
    
    And I Switch to "driver" application on "ORIGINAL" devices
    And Bungii Driver "slides to the next state"
    Then "Arrived screen" page should be opened
    
    When I Switch to "customer" application on "customer2" devices
    Then "Arrived screen" page should be opened
    
    When I Switch to "driver" application on "ORIGINAL" devices
    And Bungii Driver "slides to the next state"
    Then "Loading Item screen" page should be opened
    
    When I Switch to "customer" application on "customer2" devices
    Then "Loading Item screen" page should be opened
    
    When I Switch to "driver" application on "ORIGINAL" devices
    And Bungii Driver "slides to the next state"
    Then "Driving to DropOff screen" page should be opened
    When I Switch to "customer" application on "customer2" devices
    Then "Driving to DropOff screen" page should be opened
    
    When I Switch to "driver" application on "ORIGINAL" devices
    And Bungii Driver "slides to the next state"
    Then "Unloading Item screen" page should be opened
    
    When I Switch to "customer" application on "customer2" devices
    Then "Unloading Item screen" page should be opened
    When I Switch to "driver" application on "ORIGINAL" devices
    And Bungii Driver "slides to the next state"
    
    And I Switch to "customer" application on "customer2" devices
    And I tap on "OK on complete" on Bungii estimate
    And I tap on "No free money" on Bungii estimate
    And I Switch to "driver" application on "ORIGINAL" devices
    Then Bungii Driver "completes Bungii"
  
  
  @regression
  Scenario:  SHORT STACK BUNGII: Verify Driver can accept and complete short stack Bungii [Altanta Geofence]
    Given that ondemand bungii is in progress
      | geofence | Bungii State   |
      | atlanta  | UNLOADING ITEM |
    
    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid atlanta" driver
    
    #switch to customer so that driver app is in background :Click by notification
    When I Switch to "customer" application on "same" devices
    When I request "Solo Ondemand" Bungii as a customer in "atlanta" geofence
      | Bungii Time | Customer Phone | Customer Name                      | Customer label | Customer Password |
      | now         | 9871450107     | Testcustomertywd_apple_AGQFCg Test | 2              | Cci12345          |
    
    When I Switch to "customer" application on "same" devices
    And I am on customer Log in page
    And I enter customers "9871450107" Phone Number
    And I enter customers "valid" Password
    And I tap on the "Log in" Button on Login screen
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    
    And I Switch to "driver" application on "ORIGINAL" devices
    Then I click on notification for "STACK TRIP"
    And Bungii Driver "accepts stack message" request
    Then I calculate projected driver arrival time
    And I accept Alert message for "Alert: Display Stack trip after current trip"
    
    Then I click on notification for "CUSTOMER -Driver accepted stack Bungii"
    When I tap "OK on Driver Accepted screen" during a Bungii
    
    And I Switch to "driver" application on "ORIGINAL" devices
    And stack trip information should be displayed on deck
    And try to finish time should be correctly displayed for short stack trip
    And Bungii Driver "slides to the next state"
    Then Bungii Driver "completes Bungii"
    
    Then I click on notification for "CUSTOMER -Driver started stack Bungii"
    Then "Enroute screen" page should be opened
    
    When I Switch to "driver" application on "ORIGINAL" devices
    And Bungii Driver "slides to the next state"
    And Bungii Driver "slides to the next state"
    And Bungii Driver "slides to the next state"
    And Bungii Driver "slides to the next state"
    And Bungii Driver "slides to the next state"
    
    When I Switch to "customer" application on "same" devices
    And I tap on "OK on complete" on Bungii estimate
    And I tap on "No free money" on Bungii estimate
    
    And I Switch to "driver" application on "ORIGINAL" devices
    Then Bungii Driver "completes Bungii"
    
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 9871450107     |                 |
  
  @regression
  Scenario: Manually Ending A Bungii For A Driver Who Has Stacked Bungii Should Display Summary And Start With The Stacked Bungii
    Given that ondemand bungii is in progress
      | geofence | Bungii State        |
      | atlanta  | DRIVING TO DROP OFF |
    
    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid atlanta" driver

    #switch to customer so that driver app is in background :Click by notification
    When I Switch to "customer" application on "same" devices
    When I request "Solo Ondemand" Bungii as a customer in "atlanta" geofence
      | Bungii Time | Customer Phone | Customer Name                      | Customer label | Customer Password |
      | now         | 9871450107     | Testcustomertywd_apple_AGQFCg Test | 2              | Cci12345          |
  
    And I Switch to "driver" application on "ORIGINAL" devices
    Then I click on notification for "STACK TRIP"
    And Bungii Driver "accepts stack message" request
    Then I accept Alert message for "Alert: Display Stack trip after current trip"
    And stack trip information should be displayed on deck
    
    When I Open "customer" application on "same" devices
    And I am on customer Log in page
    And I enter customers "9871450107" Phone Number
    And I enter customers "valid" Password
    And I tap on the "Log in" Button on Login screen
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    
    Then for a Bungii I should see "bungii accepted screen"
    
    When I Switch to "driver" application on "same" devices
    When bungii admin manually end bungii created by "CUSTOMER1"
    Then Bungii driver should see "summary" on Bungii completed page
    Then Bungii Driver "tab On to Next"
    Then "Enroute screen" page should be opened
    
    When I Switch to "customer" application on "same" devices
    Then "Enroute screen" page should be opened
    
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      |                | CUSTOMER2_PHONE |
    
 #need to do in atlanta
          #move to top
  @duo1
  @regression
  Scenario: Verify Short Stack Request Acceptance By Control Driver
    Given that duo schedule bungii is in progress
      | geofence | Bungii State       | Bungii Time   | Customer | Driver1 | Driver2        |
      | atlanta  | Driving To Dropoff | NEXT_POSSIBLE | valid    | valid   | valid driver 2 |
    
    And I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid atlanta" driver
      #put driver app in background
    And I Open "customer" application on "same" devices
    
    And I connect to "extra1" using "Driver2" instance
    And I Open "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid driver 2" driver
      #put driver app in background
    And I Open "customer" application on "same" devices
    
    When I Switch to "customer" application on "ORIGINAL" devices
    When I request "Solo Ondemand" Bungii as a customer in "atlanta" geofence
      | Bungii Time | Customer Phone | Customer Name                      | Customer label | Customer Password |
      | now         | 9871450107     | Testcustomertywd_apple_AGQFCg Test | 2              | Cci12345          |
    
    And I Open "driver" application on "Driver2" devices
    Then I click on notification for "STACK TRIP"
    And Bungii Driver "reject stack message" request

          #control driver accept  stack trip
    And I Open "driver" application on "ORIGINAL" devices
    Then I click on notification for "STACK TRIP"
    And Bungii Driver "accepts stack message" request
    Then I accept Alert message for "Alert: Display Stack trip after current trip"
    And stack trip information should be displayed on deck
    And Bungii Driver "slides to the next state"
    And Bungii Driver "slides to the next state"
    Then I accept Alert message for "Reminder: both driver at drop off"
    
    And I Open "driver" application on "Driver2" devices
    And Bungii Driver "slides to the next state"
    And Bungii Driver "slides to the next state"
    Then I accept Alert message for "Reminder: both driver at drop off"
    Then Bungii Driver "tab On to Next"
    
    And I Switch to "driver" application on "ORIGINAL" devices
    Then Bungii Driver "tab On to Next"
    Then "Enroute screen" page should be opened
    
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      |                | CUSTOMER2_PHONE |
  
  
  @regression
  Scenario: Verify Bungii Details - Call SMS
	
	Given that duo schedule bungii is in progress
	  | geofence | Bungii State | Bungii Time   | Customer | Driver1 | Driver2        |
	  | atlanta  | enroute      | NEXT_POSSIBLE | valid    | valid   | valid driver 2 |
	
	And I Switch to "customer" application on "same" devices
	And I am logged in as "valid atlanta" customer
	
	And I Switch to "driver" application on "same" devices
	And I am on the LOG IN page on driver app
	And I am logged in as "valid atlanta" driver
	
	And I connect to "extra1" using "Driver2" instance
	And I Open "driver" application on "same" devices
	And I am on the LOG IN page on driver app
	And I am logged in as "valid driver 2" driver
	
	Then Trip Information should be correctly displayed on "EN ROUTE" status screen for driver
	
	When I Switch to "driver" application on "ORIGINAL" devices
	Then Trip Information should be correctly displayed on "EN ROUTE" status screen for driver
	
	When I Switch to "customer" application on "same" devices
	Then Trip Information should be correctly displayed on "EN ROUTE" status screen for customer
	When I tap "SMS for driver 1" during a Bungii
	Then correct details should be displayed on "Driver 1 SMS" app
	When I tap "Call for driver 1" during a Bungii
	Then correct details should be displayed on "Driver 1 Calling" app
	When I tap "SMS for driver 2" during a Bungii
	Then correct details should be displayed on "Driver 2 SMS" app
	When I tap "Call for driver 2" during a Bungii
	Then correct details should be displayed on "Driver 2 Calling" app
	
	When I Switch to "driver" application on "Driver2" devices
	And Bungii Driver taps "SMS for a driver" during a Bungii
	Then correct details should be displayed to driver on "Driver 2 SMS" app
	When Bungii Driver taps "Call for a driver" during a Bungii
	Then correct details should be displayed to driver on "Driver 2 Calling" app
	When Bungii Driver taps "Contact support for driver" during a Bungii
	Then correct details should be displayed to driver on "Support-SMS" app
	When Bungii Driver taps "SMS for a customer" during a Bungii
	Then correct details should be displayed to driver on "SMS" app
	When Bungii Driver taps "Call for a customer" during a Bungii
	Then correct details should be displayed to driver on "Calling" app
	When Bungii Driver taps "Contact support" during a Bungii
	Then correct details should be displayed to driver on "Support-SMS" app
	When Bungii Driver taps "View items" during a Bungii
	Then Bungii driver should see "Pickup Item"
	
	
	When I Switch to "driver" application on "ORIGINAL" devices
	When Bungii Driver taps "SMS for a driver" during a Bungii
	Then correct details should be displayed to driver on "Driver 1 SMS" app
	When Bungii Driver taps "Call for a driver" during a Bungii
	Then correct details should be displayed to driver on "Driver 1 Calling" app
	When Bungii Driver taps "Contact support for driver" during a Bungii
	Then correct details should be displayed to driver on "Support-SMS" app
	When Bungii Driver taps "SMS for a customer" during a Bungii
	Then correct details should be displayed to driver on "SMS" app
	When Bungii Driver taps "Call for a customer" during a Bungii
	Then correct details should be displayed to driver on "Calling" app
	When Bungii Driver taps "Contact support" during a Bungii
	Then correct details should be displayed to driver on "Support-SMS" app
	When Bungii Driver taps "View items" during a Bungii
	Then Bungii driver should see "Pickup Item"


 

 

