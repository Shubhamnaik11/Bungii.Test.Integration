@android
@duo
@bungii
  #These feature will run in atlanta and kansas geofence

Feature: Scheduled Duo Bungiis
	
  @regression
  @sanity
	#stable
  Scenario: Verify Customer can request Scheduled Duo Bungii [Atlanta Geofence]
	Given I am logged in as "valid atlanta" customer
	And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
	And I close "Tutorial" if exist
	And I enter "atlanta pickup and dropoff locations" on Bungii estimate
	And I tap on "two drivers selector" on Bungii estimate
	Then I should see "two drivers selected" on Bungii estimate
	When I tap on "Get Estimate button" on Bungii estimate
	And I add "1" photos to the Bungii
	And I select Bungii Time as "next possible scheduled for duo"
	And I add loading/unloading time of "30 mins"
	And I get Bungii details on Bungii Estimate
	And I tap on "Request Bungii" on Bungii estimate
	And I tap on "Yes on HeadsUp pop up" on Bungii estimate
	And I check if the customer is on success screen
	And I tap on "Done after requesting a Scheduled Bungii" on Bungii estimate
	
	Then I cancel all bungiis of customer
	  | Customer Phone  | Customer2 Phone |
	  | CUSTOMER1_PHONE |                 |
  
  @regression
  @sanity
  Scenario: Verify Duo Bungii Completion - Android [Kansas Geofence]
	Given that duo schedule bungii is in progress
	  | geofence | Bungii State | Bungii Time   | Customer        | Driver1         | Driver2         |
	  | Kansas   | Accepted     | NEXT_POSSIBLE | Kansas customer | Kansas driver 1 | Kansas driver 2 |
	
	When I Switch to "customer" application on "same" devices
	And I am logged in as "valid kansas" customer
 
	And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
	And I close "Tutorial" if exist
	
	When I Switch to "driver" application on "same" devices
	And I am on the LOG IN page on driver app
	And I am logged in as "kansas driver 1" driver
	And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
	
	And I Select "SCHEDULED BUNGIIS" from driver App menu
	And I Select Trip from driver scheduled trip
	And Bungii Driver "Start Schedule Bungii" request
	Then Bungii driver should see "Enroute screen"
	And Bungii Driver "slides to the next state"
	Then Bungii driver should see "Arrived screen"
	And Bungii Driver "slides to the next state"
	Then I accept Alert message for "Reminder: both driver at pickup"
	And Bungii driver should see "Loading Item screen"
	
	And I connect to "extra1" using "Driver2" instance
	And I Open "driver" application on "same" devices
	And I am on the LOG IN page on driver app
	And I am logged in as "kansas driver 2" driver
	And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
	
	And I Select "SCHEDULED BUNGIIS" from driver App menu
	And I Select Trip from driver scheduled trip
	And Bungii Driver "Start Schedule Bungii" request
	
	Then for a Bungii I should see "Enroute screen"
	And Bungii Driver "slides to the next state"
	Then Bungii driver should see "Arrived screen"
	And Bungii Driver "slides to the next state"
	Then I accept Alert message for "Reminder: both driver at pickup"
	And Bungii driver should see "Loading Item screen"
	
	When I Switch to "driver" application on "ORIGINAL" devices
	And Bungii Driver "slides to the next state"
	Then Bungii driver should see "Driving to DropOff screen"
	And Bungii Driver "slides to the next state"
	Then Bungii driver should see "Unloading Item screen"
	And Bungii Driver "slides to the next state"
	Then I accept Alert message for "Reminder: both driver at drop off"
	Then Bungii driver should see "correct details" on Bungii completed page
	Then Bungii Driver "completes Bungii"
	
	And I Switch to "driver" application on "Driver2" devices
	And Bungii Driver "slides to the next state"
	Then Bungii driver should see "Driving to DropOff screen"
	And Bungii Driver "slides to the next state"
	Then Bungii driver should see "Unloading Item screen"
	And Bungii Driver "slides to the next state"
	Then I accept Alert message for "Reminder: both driver at drop off"
	Then Bungii driver should see "correct details" on Bungii completed page
	Then Bungii Driver "completes Bungii"
	
	When I Switch to "customer" application on "same" devices
	And Bungii customer should see "correct details" on Bungii completed page
	And I tap on "OK on complete" on Bungii estimate
	And I tap on "No free money" on Bungii estimate
	
  @regression
  Scenario Outline: Verify Customer Amount Calculation in Admin portal For The Scheduled Duo Bungii Having Promocode <PROMO CODE> Applied To It [Kansas Geofence]
	Given I am logged in as "valid kansas" customer
	And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
	And I close "Tutorial" if exist
	
	And I enter "kansas pickup and dropoff locations" on Bungii estimate
	And I tap on "two drivers selector" on Bungii estimate
	Then I should see "two drivers selected" on Bungii estimate
	When I tap on "Get Estimate button" on Bungii estimate
	And I add large image photos to the Bungii
	And I add loading/unloading time of "30 mins"
	And I tap on "Promo Code" on Bungii estimate
	And I add "<PROMO CODE>" PromoCode
	And I tap "Add" on Save Money page
	And I tap on "desired Promo Code" on Bungii estimate
	And I get Bungii details on Bungii Estimate
	And I tap on "Request Bungii" on Bungii estimate
	And I tap on "Yes on HeadsUp pop up" on Bungii estimate
	And I check if the customer is on success screen
	And I tap on "Done after requesting a Scheduled Bungii" on Bungii estimate
	
	And I accept and complete "kansas" geofence trip of "Kansas customer" customer as a "Kansas driver 1" and "Kansas driver 2" driver
	When I Switch to "customer" application on "same" devices
 
	Then I wait for "2" mins
	And I open Admin portal and navigate to "Deliveries" page
	And I select "The Beginning of Time" from search peroid
	And I select trip from trips
	Then On admin trip details page "promo" should be displayed
	
	Examples:
	  | PROMO CODE      |
	  |PROMO DOLLAR OFF |
	  |PROMO PERCENT OFF|
  
  @regression
  Scenario: Verify that Duo scheduled Bungii can be started 1 hr before the scheduled Trip start time
	Given that duo schedule bungii is in progress
	  | geofence | Bungii State | Bungii Time    | Customer | Driver1 | Driver2        |
	  | Kansas   | Accepted     | 1 hour ahead | Kansas customer | Kansas driver 1 | Kansas driver 2 |
	
	And I Switch to "driver" application on "same" devices
	And I am on the LOG IN page on driver app
	And I am logged in as "Kansas driver 1" driver
	And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
	And I Select "SCHEDULED BUNGIIS" from driver App menu
	And I Select Trip from driver scheduled trip
	And Bungii Driver "Start Schedule Bungii" request
	Then Bungii driver should see "Enroute screen"
	Then Trip Information should be correctly displayed on "EN ROUTE" status screen for driver
	
	And I connect to "extra1" using "Driver2" instance
	And I Open "driver" application on "same" devices
	And I am on the LOG IN page on driver app
	Then I am logged in as "Kansas driver 2" driver
	And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
	And I Select "SCHEDULED BUNGIIS" from driver App menu
	And I Select Trip from driver scheduled trip
	And Bungii Driver "Start Schedule Bungii" request
	Then Bungii driver should see "Enroute screen"
	#Then Trip Information should be correctly displayed on "EN ROUTE" status screen for driver
	
	Then I cancel all bungiis of customer
	  | Customer Phone  | Customer2 Phone |
	  | CUSTOMER1_PHONE |                 |
  
  @regression
  Scenario: Verify that Duo scheduled Bungii can be started 30 mins before the scheduled Trip start time
	Given that duo schedule bungii is in progress
	  | geofence | Bungii State | Bungii Time    | Customer | Driver1 | Driver2        |
	  | Kansas   | Accepted     | 0.5 hour ahead | Kansas customer | Kansas driver 1 | Kansas driver 2 |
  
	And I connect to "extra1" using "Driver2" instance
	And I Open "driver" application on "same" devices
	And I am on the LOG IN page on driver app
	And I am logged in as "Kansas driver 2" driver
	And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
	And I Select "SCHEDULED BUNGIIS" from driver App menu
	And I Select Trip from driver scheduled trip
	And Bungii Driver "Start Schedule Bungii" request
	Then Bungii driver should see "Enroute screen"
	Then Trip Information should be correctly displayed on "EN ROUTE" status screen for driver
	
	When I Switch to "driver" application on "ORIGINAL" devices
	And I Switch to "driver" application on "same" devices
	And I am on the LOG IN page on driver app
	And I am logged in as "Kansas driver 1" driver
	And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
	And I Select "SCHEDULED BUNGIIS" from driver App menu
	And I Select Trip from driver scheduled trip
	And Bungii Driver "Start Schedule Bungii" request
	Then Bungii driver should see "Enroute screen"
	#Then Trip Information should be correctly displayed on "EN ROUTE" status screen for driver
	
	Then I cancel all bungiis of customer
	  | Customer Phone  | Customer2 Phone |
	  | CUSTOMER1_PHONE |                 |
  
	
	
	#And I Switch to "driver" application on "same" devices
	#And I am on the LOG IN page on driver app
	#And I am logged in as "valid atlanta" driver
	#And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
 
	#And I tap on "Available Trips link" on Driver Home page
	#And I Select Trip from driver available trip
	#And I tap on "ACCEPT" on driver Trip details Page
	#And I Select "SCHEDULED BUNGIIS" from driver App menu
	#And I Select Trip from driver scheduled trip
	
	#And I connect to "extra1" using "Driver2" instance
	#And I Open "driver" application on "same" devices
	#And I am on the LOG IN page on driver app
	#And I am logged in as "valid driver 2" driver
	#And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
 
	#And I tap on "Available Trips link" on Driver Home page
	#And I Select Trip from driver available trip
	#And I tap on "ACCEPT" on driver Trip details Page
	#And I Select "SCHEDULED BUNGIIS" from driver App menu
	#And I Select Trip from driver scheduled trip
	
	#Then Trip Information should be correctly displayed on "EN ROUTE" status screen for driver
	#And I slide update button on "EN ROUTE" Screen
	#And I slide update button on "ARRIVED" Screen
	#And I slide update button on "LOADING ITEM" Screen
	#And I slide update button on "DRIVING TO DROP OFF" Screen
	#And I slide update button on "UNLOADING ITEM" Screen
	#And I click "On To The Next One" button on the "Bungii Completed" screen
 
	#When I Switch to "driver" application on "ORIGINAL" devices
	#Then Trip Information should be correctly displayed on "EN ROUTE" status screen for driver
	#And I slide update button on "EN ROUTE" Screen
	#And I slide update button on "ARRIVED" Screen
	#And I slide update button on "LOADING ITEM" Screen
	#And I slide update button on "DRIVING TO DROP OFF" Screen
	#And I slide update button on "UNLOADING ITEM" Screen
	#And I click "On To The Next One" button on the "Bungii Completed" screen
  
  
 