@ios
@scheduled
@bungii
@critical
    # this will run in miami and goa
Feature: Solo Scheduled Bungii Part I
  
  Background:
	#When I clear all notification
	When I Switch to "customer" application on "same" devices
  
  @regression
    # negative scenario is handle in long haul message scenario . In this scenario verify trip >140 but less than 150 go through
  Scenario: Verify Trip limit (150 miles) For Solo Scheduled Bungii
    #When I am on the "LOG IN" page
    #And I logged in Customer application using  "valid denver" user
	Given I login as "valid miami 2" customer and on Home page
	And I enter pickup location
	  | Driver | Pickup Location |
	  | Solo   | Margao Railway Overbridge  |
	And I enter drop location
	  | Driver | Drop Location       |
	  | Solo   | Devghali Beach road |
	And I click "Get Estimate" button on "Home" screen
	Then I should be navigated to "Estimate" screen
	Then I cancel all bungiis of customer
	  | Customer Phone | Customer2 Phone |
	  | 8888889917     |                 |
	
	
@failed
@ready
Scenario: Verify Promoter Type Promocode Is Correctly Applied After Manually Ending Bungii

When I Switch to "customer" application on "same" devices
And I am on the "LOG IN" page
#And I logged in Customer application using  "valid miami 2" user
And I logged in as "valid miami 2" customer
  And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
  And I close "Tutorial" if exist
  
And I Switch to "driver" application on "same" devices
And I am on the "LOG IN" page on driverApp
And I am logged in as "valid miami" driver
  And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
  And I change driver status to "Online"

When I Switch to "customer" application on "same" devices
And I request for  bungii for given pickup and drop location
| Driver | Pickup Location             | Drop Location            | Geofence |
| Solo   | 7346 coldstream drive miami | 2400 S Bayshore Dr Miami | miami    |
Then I click "Get Estimate" button on "Home" screen
When I select load time as "30" mins

And I click "PROMO CODE LINE" button on "Estimate" screen
And I add "PROMOTER TYPE MULTIPLE PROMO" PromoCode
And I click "ADD" button on "PROMOS" screen
And I tap "Back" on Promos screen

When I enter following details on "Estimate" screen
| LoadTime | PromoCode | Payment Card | Time          | PickUpImage |
|          |           |              | NEXT_POSSIBLE | Default     |

And I should be navigated to "Estimate" screen
Then I save bungii trip time details
And I request for bungii using Request Bungii Button
Then I click "Done" button on "Success" screen
  
  And I view and accept virtual notification for "Driver" for "SCHEDULED PICKUP AVAILABLE"
  When I Select "SCHEDULED BUNGIIS" from driver App menu
  Then I should be navigated to "SCHEDULED BUNGII" screen
  
  #When I click on notification for "Driver" for "SCHEDULED PICKUP AVAILABLE"
#And Alert message with ACCEPT SCHEDULED BUNGII QUESTION text should be displayed
#When I click "View" on alert message
#Then I should be navigated to "BUNGII REQUEST" screen
#When I accept selected Bungii
#Then I should be navigated to "SCHEDULED BUNGII" screen

And I Select Trip from scheduled trip
And I start selected Bungii
Then I should be navigated to "EN ROUTE" screen
And I slide update button on "EN ROUTE" Screen
And I slide update button on "ARRIVED" Screen
And I slide update button on "LOADING ITEM" Screen
And I slide update button on "DRIVING TO DROP OFF" Screen

And I wait for "3" mins
  And I open Admin portal and navigate to "Live Deliveries" page
Then I should be able to see the respective bungii with the below status
| Status          |
| Unloading Items |
When I view the trip details

When I switch to "ORIGINAL" instance
And I open "customer" application on "same" devices
And I switch to "ADMIN" instance
And I click on "Manually End Bungii" link
And Enter the End Date and Time
And Click on "Calculate Cost" button
And Click on "Confirm" button
  #  And I view the Trips list on the admin portal

When I switch to "ORIGINAL" instance
Then I should be navigated to "Bungii Complete" screen
And Bungii customer should see "correct details with promo" on Bungii completed page
When I click "CLOSE BUTTON" button on "Bungii Complete" screen
Then I should be navigated to "Promotion" screen
When I click "I DON'T LIKE FREE MONEY" button on "Promotion" screen
Then I should be navigated to "Home" screen

When I Switch to "driver" application on "same" devices
Then Bungii driver should see "correct details" on Bungii completed page
And I click "On To The Next One" button on "Bungii Completed" screen
  
  @failed
  @ready

    #this test case is from customer signup module. but as this require bungii to be created , moved to this feature file
  Scenario Outline: Verify If Trip Completed Count On Admin Portal Is Updated When Customer Completes A Bungii
	When I Switch to "customer" application on "same" devices
	
	Given I am on the "SIGN UP" page
	When I Enter "<Phone Number>" value in "Phone Number" field in "SIGN UP" Page
	And I Enter "<First Name>" value in "First Name" field in "SIGN UP" Page
	And I Enter "<Last Name>" value in "Last Name" field in "SIGN UP" Page
	And I Enter "<Email ID>" value in "Email" field in "SIGN UP" Page
	And I Enter "<Password>" value in "Password" field in "SIGN UP" Page
	And I Select Referral source as "<Source>"
	And I click "SIGN UP" button on "SIGN UP" screen
	Then I should be navigated to "VERIFICATION" screen
	When I Get SMS CODE for new "Customer"
	And I enter "valid" Verification code
	Then I should be navigated to "Home" screen
	And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
	And I close "Tutorial" if exist
	When I Select "ACCOUNT > PAYMENT" from Customer App menu
	Then I should be navigated to "PAYMENT" screen
	When I click "Add-Button" button on "PAYMENT" screen
	And I enter Card No:<CardNo> and Expiry :<Expiry> on Card Details page
	And I enter postal code :<Postal Code> and Cvv: <Cvv> on Card Details page
	And I click "ADD PAYMENT METHOD" button on "PAYMENT" screen
	Then I should see "new card" on Payment page
	
	When I request "Solo Scheduled" Bungii as a customer in "goa" geofence
	  | Bungii Time   | Customer Phone  | Customer Name |  | Customer Password |
	  | NEXT_POSSIBLE | NEW_USER_NUMBER |               |  | Cci12345          |
	And As a driver "testdriver4 Test" perform below action with respective "Solo Scheduled" trip
	  | driver1 state      |
	  | Accepted           |
	  | Enroute            |
	  | Arrived            |
	  | Loading Item       |
	  | Driving To Dropoff |
	  | Unloading Item     |
	When I Switch to "driver" application on "same" devices
	And I am on the "LOG IN" page on driverApp
	And I enter phoneNumber :9955112203 and  Password :Cci12345
	And I click "Log In" button on "Log In" screen on driverApp
	And I slide update button on "UNLOADING ITEM" Screen
	Then I should be navigated to "Bungii Completed" screen
	And I click "On To The Next One" button on "Bungii Completed" screen
	Then I wait for "2" mins
	And I open Admin portal and navigate to "Customers" page
	Then trips requested count should be "1"
	Examples:
	  | First Name | Last Name       | Email ID                        | Phone Number       | Password | Referral Code | Source   | CardNo        | Expiry | Postal Code       | Cvv       |
	  | Donaldd    | {RANDOM_STRING} | vishal.bagi@creativecapsule.com | {RANDOM_PHONE_NUM} | Cci12345 |               | Facebook | DISCOVER CARD | 12/22  | VALID POSTAL CODE | VALID CVV |
  
  @failed
  @ready
  Scenario: Verify Promo Type Promocode Is Correctly Applied After Manually Ending Bungii
	
	And I am on the "LOG IN" page
#	And I logged in Customer application using  "valid miami" user
    And I logged in as "valid miami" customer
	And I Switch to "driver" application on "same" devices
	And I am on the "LOG IN" page on driverApp
	And I am logged in as "valid miami" driver
	And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
	And I change driver status to "Online"
	
	When I Switch to "customer" application on "same" devices
	And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
	And I close "Tutorial" if exist
	And I request for  bungii for given pickup and drop location
	  | Driver | Pickup Location             | Drop Location            | Geofence |
	  | Solo   | 7346 coldstream drive miami | 2400 S Bayshore Dr Miami | miami    |
	Then I click "Get Estimate" button on "Home" screen
	When I select load time as "30" mins
	And I click "PROMO CODE LINE" button on "Estimate" screen
	And I add "PROMO PERCENT OFF" PromoCode
	And I click "ADD" button on "PROMOS" screen
	And I tap "Back" on Promos screen
	
	When I enter following details on "Estimate" screen
	  | LoadTime | PromoCode | Payment Card | Time          | PickUpImage |
	  | 30       |           |              | NEXT_POSSIBLE | Default     |
	
	And I should be navigated to "Estimate" screen
	Then I save bungii trip time details
	And I request for bungii using Request Bungii Button
	Then I click "Done" button on "Success" screen
	
	#When I click on notification for "Driver" for "SCHEDULED PICKUP AVAILABLE"
	#And Alert message with ACCEPT SCHEDULED BUNGII QUESTION text should be displayed
	#When I click "View" on alert message
	#Then I should be navigated to "BUNGII REQUEST" screen
	#When I accept selected Bungii
	#Then I should be navigated to "SCHEDULED BUNGII" screen
	And I view and accept virtual notification for "Driver" for "SCHEDULED PICKUP AVAILABLE"
	When I Select "SCHEDULED BUNGIIS" from driver App menu
	Then I should be navigated to "SCHEDULED BUNGII" screen
	
	And I Select Trip from scheduled trip
	And I start selected Bungii
	Then I should be navigated to "EN ROUTE" screen
	And I slide update button on "EN ROUTE" Screen
	And I slide update button on "ARRIVED" Screen
	
	And I wait for "3" mins
	And I open Admin portal and navigate to "Live Deliveries" page
	Then I should be able to see the respective bungii with the below status
	  | Status        |
	  | Loading Items |
	When I view the trip details
	When I switch to "ORIGINAL" instance
	And I Switch to "customer" application on "same" devices
	And I switch to "ADMIN" instance
	And I click on "Manually End Bungii" link
	And Enter the End Date and Time
	And Click on "Calculate Cost" button
	And Click on "Confirm" button
 #   And I view the Trips list on the admin portal
	
	When I switch to "ORIGINAL" instance
	Then I should be navigated to "Bungii Complete" screen
	And Bungii customer should see "correct details with promo" on Bungii completed page
	When I click "CLOSE BUTTON" button on "Bungii Complete" screen
	Then I should be navigated to "Promotion" screen
	When I click "I DON'T LIKE FREE MONEY" button on "Promotion" screen
	Then I should be navigated to "Home" screen
	
	When I Switch to "driver" application on "same" devices
	Then Bungii driver should see "correct details" on Bungii completed page
	And I click "On To The Next One" button on "Bungii Completed" screen
  
  @failed
  @ready
  Scenario: Verify One Off Type Promocode Is Correctly Applied After Manually Ending Bungii
	
	When I Switch to "customer" application on "same" devices
	And I am on the "LOG IN" page
#	And I logged in Customer application using  "valid miami" user
    And I logged in as "valid miami" customer
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
	And I close "Tutorial" if exist
	
	And I Switch to "driver" application on "same" devices
	And I am on the "LOG IN" page on driverApp
	And I am logged in as "valid miami" driver
	And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
	And I change driver status to "Online"
	
	When I Switch to "customer" application on "same" devices
	And I request for  bungii for given pickup and drop location
	  | Driver | Pickup Location             | Drop Location            | Geofence |
	  | Solo   | 7346 coldstream drive miami | 2400 S Bayshore Dr Miami | miami    |
	Then I click "Get Estimate" button on "Home" screen
	When I select load time as "30" mins
	And I click "PROMO CODE LINE" button on "Estimate" screen
	And I add "ONE OFF VALID 2" PromoCode
	And I click "ADD" button on "PROMOS" screen
	And I tap "Back" on Promos screen
	
	When I enter following details on "Estimate" screen
	  | LoadTime | PromoCode | Payment Card | Time          | PickUpImage |
	  |          |           |              | NEXT_POSSIBLE | Default     |
	
	
	And I should be navigated to "Estimate" screen
	Then I save bungii trip time details
	
	And I request for bungii using Request Bungii Button
	Then I click "Done" button on "Success" screen
	
	#When I click on notification for "Driver" for "SCHEDULED PICKUP AVAILABLE"
	#And Alert message with ACCEPT SCHEDULED BUNGII QUESTION text should be displayed
	#When I click "View" on alert message
	#Then I should be navigated to "BUNGII REQUEST" screen
	#When I accept selected Bungii
	#Then I should be navigated to "SCHEDULED BUNGII" screen
	And I view and accept virtual notification for "Driver" for "SCHEDULED PICKUP AVAILABLE"
	When I Select "SCHEDULED BUNGIIS" from driver App menu
	Then I should be navigated to "SCHEDULED BUNGII" screen
	
	And I Select Trip from scheduled trip
	And I start selected Bungii
	Then I should be navigated to "EN ROUTE" screen
	And I slide update button on "EN ROUTE" Screen
	And I slide update button on "ARRIVED" Screen
	And I slide update button on "LOADING ITEM" Screen
	
	And I wait for "3" mins
	And I open Admin portal and navigate to "Live Deliveries" page
	Then I should be able to see the respective bungii with the below status
	  | Status             |
	  | Driving To Dropoff |
	When I view the trip details
	
	When I switch to "ORIGINAL" instance
	And I open "customer" application on "same" devices
	And I switch to "ADMIN" instance
	And I click on "Manually End Bungii" link
	And Enter the End Date and Time
	And Click on "Calculate Cost" button
	And Click on "Confirm" button
  #  And I view the Trips list on the admin portal
	
	When I switch to "ORIGINAL" instance
	Then I should be navigated to "Bungii Complete" screen
	And Bungii customer should see "correct details with promo" on Bungii completed page
	When I click "CLOSE BUTTON" button on "Bungii Complete" screen
	Then I should be navigated to "Promotion" screen
	When I click "I DON'T LIKE FREE MONEY" button on "Promotion" screen
	Then I should be navigated to "Home" screen
	
	When I Switch to "driver" application on "same" devices
	Then Bungii driver should see "correct details" on Bungii completed page
	And I click "On To The Next One" button on "Bungii Completed" screen

