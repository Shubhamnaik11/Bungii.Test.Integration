@android
@duo
@bungii

Feature: Scheduled Duo Bungiis - Single Phone
    #These feature will runs atlanta and san fransisco geofence [9 scenarios] on one phone
  
  @regression
	#Stable
  Scenario: Verify Minimum Scheduled Time For The Duo Trip
	Given I am on customer Log in page
	When I am logged in as "New" customer
	And I enter "San Francisco pickup and dropoff locations" on Bungii estimate
	And I tap on "two drivers selector" on Bungii estimate
	Then I should see "two drivers selected" on Bungii estimate
	When I tap on "Get Estimate button" on Bungii estimate
	Then I should see the minimum scheduled time displayed on the Estimate page
  

  
  @ready
  Scenario: STACK BUNGII: Verify Driver Can Get Long Stack Request On Arrived State
	Given that ondemand bungii is in progress
	  | geofence | Bungii State |
	  | atlanta  | ARRIVED      |
	When I Switch to "driver" application on "same" devices
	And I am on the LOG IN page on driver app
	And I am logged in as "valid atlanta" driver
        #put driver on background
	When I Switch to "customer" application on "same" devices
	When I request "Solo Ondemand" Bungii as a customer in "atlanta" geofence
	  | Bungii Time | Customer Phone | Customer Name                      | Customer label | Customer Password |
	  | now         | 9871450107     | Testcustomertywd_apple_AGQFCg Test | 2              | Cci12345          |
  
	When I Switch to "driver" application on "same" devices
	Then I click on notification for "STACK TRIP"
	And Bungii Driver "view stack message" request
	Then "correct stack trip details" should be displayed on Bungii request screen
	And I tap on the "ACCEPT" Button on Bungii Request screen
	Then I accept Alert message for "Alert: Display Stack trip after current trip"
	And stack trip information should be displayed on deck
	
	Then I cancel all bungiis of customer
	  | Customer Phone  | Customer2 Phone |
	  | CUSTOMER1_PHONE | CUSTOMER2_PHONE |
  
  @ready
  Scenario: STACK BUNGII: Verify Driver Can Get Long Stack Request On Loading Item State And Cancellation
	Given that ondemand bungii is in progress
	  | geofence | Bungii State |
	  | atlanta  | LOADING ITEM |
	When I Switch to "driver" application on "same" devices
	And I am on the LOG IN page on driver app
	And I am logged in as "valid atlanta" driver

	When I Switch to "customer" application on "same" devices
	When I request "Solo Ondemand" Bungii as a customer in "atlanta" geofence
	  | Bungii Time | Customer Phone | Customer Name                      | Customer label | Customer Password |
	  | now         | 9871450107     | Testcustomertywd_apple_AGQFCg Test | 2              | Cci12345          |
  
	When I Switch to "driver" application on "same" devices
	Then I click on notification for "STACK TRIP"
	Then Bungii Driver "verify stack message" request
	And Bungii Driver "accepts stack message" request
	Then I accept Alert message for "Alert: Display Stack trip after current trip"
	And stack trip information should be displayed on deck
	
	When I Switch to "customer" application on "same" devices
	And I am on customer Log in page
	And I enter customers "9871450107" Phone Number
	And I enter customers "valid" Password
	And I tap on the "Log in" Button on Login screen
	Then for a Bungii I should see "bungii accepted screen"
	When I click "CANCEL BUNGII" on bungii accepted screen
	Then I see "Alert: Bungii cancel confirmation" on bungii accepted screen
	When I click "Dismiss on Alert message" on bungii accepted screen
	
	Then for a Bungii I should see "bungii accepted screen"
	When I click "CANCEL BUNGII" on bungii accepted screen
	When I click "Cantact Support on Alert message" on bungii accepted screen
	Then correct details should be displayed on "customer support-SMS" app
	
	When I click "CANCEL BUNGII" on bungii accepted screen
	When I click "CANCEL BUNGII on Alert message" on bungii accepted screen
	Then for a Bungii I should see "Bungii Home page"
	Then I click on notification for "CUSTOMER CANCEL STACK TRIP"
	And stack trip information should not be displayed on deck
	
	Then I cancel all bungiis of customer
	  | Customer Phone  | Customer2 Phone |
	  | CUSTOMER1_PHONE |                 |
  
  @ready
  @sanity
    #stable
  Scenario: STACK BUNGII : Long Stack : Verify Driver receives Long Stack and accept and complete it [Complete Flow]
	Given I am on customer Log in page
	When I am logged in as "valid" customer
	
	And I Switch to "driver" application on "same" devices
	And I am on the LOG IN page on driver app
	And I am logged in as "valid atlanta" driver
	And I tap on "Go Online button" on Driver Home page
	
	And I connect to "extra1" using "customer2" instance
	And I Open "customer" application on "same" devices
	And I am logged in as "valid customer 2" customer
	
	And I Switch to "customer" application on "ORIGINAL" devices
	And I enter "atlanta pickup and dropoff locations" on Bungii estimate
	And I tap on "Get Estimate button" on Bungii estimate
	And I add "1" photos to the Bungii
	And I add loading/unloading time of "30 mins"
	And I select Bungii Time as "next possible scheduled"
	And I tap on "Request Bungii" on Bungii estimate
	And I tap on "Yes on HeadsUp pop up" on Bungii estimate
	And I check if the customer is on success screen
	And I tap on "Done after requesting a Scheduled Bungii" on Bungii estimate
	
	And I Switch to "driver" application on "same" devices
	And I tap on "Available Trips link" on Driver Home page
	And I Select Trip from driver available trip
	And I tap on "ACCEPT" on driver Trip details Page
	And I Select "SCHEDULED BUNGIIS" from driver App menu
	And I Select Trip from driver scheduled trip
	And Bungii Driver "Start Schedule Bungii" request
	Then "Enroute screen" page should be opened
	
	When I Switch to "customer" application on "same" devices
	Then "Enroute screen" page should be opened
	
	When I Switch to "customer" application on "customer2" devices
	And I enter "atlanta pickup and dropoff locations" on Bungii estimate
	And I tap on "Get Estimate button" on Bungii estimate
	And I add "1" photos to the Bungii
	And I add loading/unloading time of "30 mins"
	And I tap on "Request Bungii" on Bungii estimate
	And I tap on "Yes on HeadsUp pop up" on Bungii estimate
	
	And I Open "driver" application on "ORIGINAL" devices
	And Bungii Driver "accepts stack message" request
	Then I accept Alert message for "Alert: Display Stack trip after current trip"
	And stack trip information should be displayed on deck
	Then try to finish time should be correctly displayed for long stack trip
	
	When  I switch to "customer2" instance
	Then for a Bungii I should see "BUNGII ACCEPTED for Stack screen"
	And I tap "OK on Driver Accepted screen" during a Bungii
	And for a Bungii I should see "Stack accepted screen"
	
	When I Switch to "driver" application on "ORIGINAL" devices
	Then "Enroute screen" page should be opened
	When Bungii Driver "slides to the next state"
	And Bungii Driver "slides to the next state"
	And Bungii Driver "slides to the next state"
	And Bungii Driver "slides to the next state"
	And Bungii Driver "slides to the next state"
	Then Bungii Driver "completes Bungii"
	
	And I Switch to "customer" application on "same" devices
	And I tap on "OK on complete" on Bungii estimate
	And I tap on "No free money" on Bungii estimate
    #Complete second trip
	When I Switch to "customer" application on "customer2" devices
	Then "Enroute screen" page should be opened
	
	When I Switch to "driver" application on "ORIGINAL" devices
	And Bungii Driver "slides to the next state"
	And Bungii Driver "slides to the next state"
	And Bungii Driver "slides to the next state"
	And Bungii Driver "slides to the next state"
	And Bungii Driver "slides to the next state"
	Then Bungii Driver "completes Bungii"
	
	And I Switch to "customer" application on "customer2" devices
	And I tap on "OK on complete" on Bungii estimate
	And I tap on "No free money" on Bungii estimate
	And I Switch to "driver" application on "ORIGINAL" devices
  
  
  @regression
  Scenario: Verify Driver Does Not Receive Long Stacked Request If Drivers Location Is More Than 100 Mins From The Current Location Of Driver To The Pickup Of The Newly Requested Trip
	Given that ondemand bungii is in progress
	  | geofence    | Bungii State | Driver label            |
	  | atlanta.far | LOADING ITEM | far away atlanta driver |
	
	When I Switch to "driver" application on "same" devices
	And I am on the LOG IN page on driver app
	And I am logged in as "valid far away atlanta" driver
	
	And I Switch to "customer" application on "same" devices
	And I am on customer Log in page
	And I am logged in as "valid customer 2" customer
	And I enter "atlanta pickup and dropoff locations away from driver" on Bungii estimate
	And I tap on "Get Estimate button" on Bungii estimate
	And I add "1" photos to the Bungii
	And I add loading/unloading time of "30 mins"
	And I tap on "Request Bungii" on Bungii estimate
	And I tap on "Yes on HeadsUp pop up" on Bungii estimate
	Then for a Bungii I should see "Bungii search screen"
	
	And I Open "driver" application on "ORIGINAL" devices
	When I verify that driver to pickup time is greater than 100 mins for second trip
	Then I should not get notification for stack trip
	Then I cancel all bungiis of customer
	  | Customer Phone  | Customer2 Phone |
	  | CUSTOMER1_PHONE | CUSTOMER2_PHONE |
  
  @regression
  Scenario: Verify Driver Does Not Receive Short Stacked Request If Drivers Location Is More Than 100 Mins From The Current Location Of The Driver To The Pickup Of The Newly Requested Trip
	Given that ondemand bungii is in progress
	  | geofence    | Bungii State   | Driver label            |
	  | atlanta.far | UNLOADING ITEM | far away atlanta driver |
	
	When I Switch to "driver" application on "same" devices
	And I am on the LOG IN page on driver app
	And I am logged in as "valid far away atlanta" driver
	And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
 
	And I Switch to "customer" application on "same" devices
	And I am on customer Log in page
	And I am logged in as "valid customer 2" customer
	And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
	And I close "Tutorial" if exist
	
	And I enter "atlanta pickup and dropoff locations away from driver" on Bungii estimate
	And I tap on "Get Estimate button" on Bungii estimate
	And I add "1" photos to the Bungii
	And I add loading/unloading time of "30 mins"
	And I tap on "Request Bungii" on Bungii estimate
	And I tap on "Yes on HeadsUp pop up" on Bungii estimate
	Then for a Bungii I should see "Bungii search screen"
	
	And I Open "driver" application on "ORIGINAL" devices
	When I verify that driver to pickup time is greater than 100 mins for second trip
	Then I should not get notification for stack trip
	Then I cancel all bungiis of customer
	  | Customer Phone  | Customer2 Phone |
	  | CUSTOMER1_PHONE | CUSTOMER2_PHONE |
  
  @ready
  Scenario: Verify Non-control Driver Does Not Receive Long Stacking Request If Started Before The Controlled Driver - Also Non Control Driver Cannot Cancel Trip If Controlled driver has Not Started
	Given that duo schedule bungii is in progress
	  | geofence | Bungii State | Bungii Time   | Customer | Driver1 | Driver2        |
	  | atlanta  | Accepted     | NEXT_POSSIBLE | valid    | valid   | valid driver 2 |
	
	When I Switch to "driver" application on "same" devices
	And I am on the LOG IN page on driver app
	And I am logged in as "valid driver 2" driver
	And I Select "SCHEDULED BUNGIIS" from driver App menu
	And I Select Trip from driver scheduled trip
    #non control driver start the trip
	And Bungii Driver "Start Schedule Bungii" request
	
	And I Open "customer" application on "same" devices
	When I request "Solo Ondemand" Bungii as a customer in "atlanta" geofence
	  | Bungii Time | Customer Phone | Customer Name                      | Customer label | Customer Password |
	  | now         | 9871450107     | Testcustomertywd_apple_AGQFCg Test | 2              | Cci12345          |
	
	Then I should not get notification for stack trip
	
	When I Switch to "driver" application on "same" devices
	When Bungii Driver "tab on Cancel bungii"
	Then Alert message with TRIP CANNOT BE CANCELED AS CONTROL DRIVER NOT STARTED text should be displayed
	Then Alert should have "cancel,proceed" button
	When I click "Cancel" on alert message
	Then "Enroute screen" page should be opened
	
	Then I cancel all bungiis of customer
	  | Customer Phone  | Customer2 Phone |
	  | CUSTOMER1_PHONE | CUSTOMER2_PHONE |
  
  
  @ready
  Scenario: Verify Customer Can View Ongoing Bungii Progress Screens When Trip Is Started By Only By Control Driver
	Given that duo schedule bungii is in progress
	  | geofence | Bungii State | Bungii Time   | Customer        | Driver1         | Driver2         |
	 #| Kansas   | Accepted     | NEXT_POSSIBLE | Kansas customer | Kansas driver 1 | Kansas driver 2 |
	  | atlanta  | enroute      | NEXT_POSSIBLE | valid        | valid   | valid driver 2             |
  
	And I Switch to "customer" application on "same" devices
	And I am logged in as "valid atlanta" customer
	And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
	And I close "Tutorial" if exist
	
	When I Switch to "driver" application on "same" devices
	And I am on the LOG IN page on driver app
	And I am logged in as "valid atlanta" driver
	And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
	
	And I Select "SCHEDULED BUNGIIS" from driver App menu
	And I Select Trip from driver scheduled trip
	And I start selected Bungii
	Then I should be navigated to "EN ROUTE" screen
	Then I check ETA of "control driver"
	
	And I Switch to "customer" application on "same" devices
	Then I should be navigated to "EN ROUTE" screen
	Then "control driver" eta should be displayed to customer
	
	Then I cancel all bungiis of customer
	  | Customer Phone  | Customer2 Phone |
	  | CUSTOMER1_PHONE |                 |
  

	
  @regression
	#stable
  Scenario: Verify that that Past Trips page correctly displays completed Scheduled Duo Bungii
	Given that duo schedule bungii is in progress
	  | geofence | Bungii State | Bungii Time   | Customer | Driver1 | Driver2        |
	  | atlanta  | Completed      | NEXT_POSSIBLE | valid    | valid   | valid driver 2 |
  
	And I Switch to "customer" application on "same" devices
	And I am logged in as "valid atlanta" customer
	And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
	And I close "Tutorial" if exist
	
	And I tap on "Menu" > "My Bungiis" link
	Then "MY BUNGIIS" page should be opened
	And I click on "Past" tab
	And I open first trip in past trips
	Then I verify driver names and trip cost
	
	And I cancel all bungiis of customer
	  | Customer Phone  | Customer2 Phone |
	  | CUSTOMER1_PHONE |                 |
	
	
  @ready
	@ad
  #Scenario:Verify If Driver Receieve Notification After Admin Researches For Drivers
  Scenario: Verify If Customer Receives Notification After Admin Researches Drivers And Both Drivers Accept It
	Given that duo schedule bungii is in progress
	  | geofence | Bungii State | Bungii Time   | Customer | Driver1 | Driver2        |
	  | atlanta  | Scheduled      | NEXT_POSSIBLE | valid    | valid   | valid driver 2 |
	Then I wait for "3" mins
	
	And I Switch to "customer" application on "same" devices
	And I am logged in as "valid atlanta" customer
	And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
	And I close "Tutorial" if exist
	
	#Then I wait for "2" mins
	When I open new "Chrome" browser for "ADMIN"
	And I navigate to admin portal
	And I log in to admin portal
	And I Select "Scheduled Trip" from admin sidebar
	And I open the trip for "Testcustomertywd_applesDGJnr Stark" the customer
	And I researches Bungii
	And I click on "Close" button
	
	Then I wait for "2" mins
	And I open Admin portal and navigate to "Scheduled Deliveries" page
	And I open the trip for "Testcustomertywd_applesDGJnr Stark" the customer
	And I Select "Edit Trip Details" option
	And I assign driver for the "Duo" trip
	And I click on "VERIFY" button
	And the "Your changes are good to be saved." message is displayed
	Then I click on "SAVE CHANGES" button
	And the "Bungii Saved!" message is displayed
	
	#Then I click on notification for "SCHEDULED PICKUP ACCEPTED"
	And I switch to "ORIGINAL" instance
	And I Switch to "customer" application on "same" devices
	Then I should get "SCHEDULED PICKUP ACCEPTED" notification for customer
	
	Then I cancel all bungiis of customer
	  | Customer Phone | Customer2 Phone |
	  | 8805368840     |                 |
  
  
  @sanity
  @ready
  Scenario: Verify Long Stack Request Acceptance By Non Control Driver
	Given that duo schedule bungii is in progress
	  | geofence | Bungii State | Bungii Time   | Customer | Driver1 | Driver2        |
	  | atlanta  | enroute      | NEXT_POSSIBLE | valid    | valid   | valid driver 2 |
	
	When I Switch to "driver" application on "same" devices
	And I am on the LOG IN page on driver app
	Then I am logged in as "valid driver 2" driver
	
	And I request "Solo Ondemand" Bungii as a customer in "atlanta" geofence
	  | Bungii Time | Customer Phone | Customer Name                      | Customer label | Customer Password |
	  | now         | 9871450107     | Testcustomertywd_apple_AGQFCg Test | 2              | Cci12345          |
	
	When I Switch to "driver" application on "same" devices
	Then I click on notification for "STACK TRIP"
	And Bungii Driver "accepts stack message" request
	Then I accept Alert message for "Alert: Display Stack trip after current trip"
	And stack trip information should be displayed on deck
	
	Then I cancel all bungiis of customer
	  | Customer Phone | Customer2 Phone |
	  |                | CUSTOMER2_PHONE |
  
  
  
  @ready
  Scenario:Verify Driver Can Get Short Stack Request On Unloading Item State
	Given that ondemand bungii is in progress
	  | geofence | Bungii State   |
	  | atlanta  | UNLOADING ITEM |
	When I Switch to "driver" application on "same" devices
	And I am on the LOG IN page on driver app
	And I am logged in as "valid atlanta" driver
	
	When I Switch to "customer" application on "same" devices
	When I request "Solo Ondemand" Bungii as a customer in "atlanta" geofence
	  | Bungii Time | Customer Phone | Customer Name                      | Customer label | Customer Password |
	  | now         | 9871450107     | Testcustomertywd_apple_AGQFCg Test | 2              | Cci12345          |
	
	When I Switch to "driver" application on "same" devices
	Then I click on notification for "STACK TRIP"
	Then Bungii Driver "verify stack message" request
	And Bungii Driver "accepts stack message" request
	Then I accept Alert message for "Alert: Display Stack trip after current trip"
	And stack trip information should be displayed on deck
	
	When I Switch to "customer" application on "same" devices
	And I am on customer Log in page
	And I enter customers "9871450107" Phone Number
	And I enter customers "valid" Password
	And I tap on the "Log in" Button on Login screen
	Then for a Bungii I should see "bungii accepted screen"
	
	When I click "CANCEL BUNGII" on bungii accepted screen
	Then I see "Alert: Bungii cancel confirmation" on bungii accepted screen
	When I click "Dismiss on Alert message" on bungii accepted screen
	Then for a Bungii I should see "bungii accepted screen"
	
	When I click "CANCEL BUNGII" on bungii accepted screen
	When I click "Cantact Support on Alert message" on bungii accepted screen
	Then correct details should be displayed on "customer support-SMS" app
	
	When I click "CANCEL BUNGII" on bungii accepted screen
	When I click "CANCEL BUNGII on Alert message" on bungii accepted screen
	Then for a Bungii I should see "Bungii Home page"
	
	Then I click on notification for "CUSTOMER CANCEL STACK TRIP"
	And stack trip information should not be displayed on deck
	
	Then I cancel all bungiis of customer
	  | Customer Phone  | Customer2 Phone |
	  | CUSTOMER1_PHONE |                 |
  