@android
@duo
@bungii

Feature: Bungii Duo Scheduled Part D

  Background:
  
  @regression
    #Stable
  Scenario: Verify If Customer Receive Notification Once Required Number Of Drivers Accepts Scheduled Trip - DUO
	When I Switch to "driver" application on "same" devices
	Then As a driver "Testdrivertywd_appleks_ra_four Kent" I log in
	
	And I Switch to "customer" application on "same" devices
	And I request "duo" Bungii as a customer in "Kansas" geofence
	  | Bungii Time   | Customer Phone | Customer Name                    | Customer Password |
	  | NEXT_POSSIBLE | 8805368840     | Testcustomertywd_appleRicha Test | Cci12345          |
	And I login as customer "8805368840" and is on Home Page
	And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
	And I close "Tutorial" if exist
	And I tap on "Menu" > "MY BUNGIIS" link
	When I Switch to "driver" application on "same" devices
	And As a driver "Testdrivertywd_appleks_ra_four Kent" and "Testdrivertywd_appleks_rathree Test" perform below action with respective "DUO SCHEDULED" trip
	  | driver1 state | driver2 state |
	  | Accepted      | Accepted      |
	When I Switch to "customer" application on "same" devices
	Then I click on notification for "SCHEDULED PICKUP ACCEPTED"
	Then I cancel all bungiis of customer
	  | Customer Phone | Customer2 Phone |
	  | 8805368840     |                 |
  
  @regression
    #Stable
  Scenario:Verify Driver Is Not Able To Accept The Request If Trip Is Already Accepted By The Required Number Of Drivers
	When I Switch to "driver" application on "same" devices
	And I am on the LOG IN page on driver app
	And I am logged in as "valid" driver
	And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
	
	And I request "duo" Bungii as a customer in "Kansas" geofence
	  | Bungii Time   | Customer Phone | Customer Name                    | Customer Password |
	  | NEXT_POSSIBLE | 8805368840     | Testcustomertywd_appleRicha Test | Cci12345          |
	
	And I Select "AVAILABLE BUNGIIS" from driver App menu
	And I Select Trip from available trip
	
	And As a driver "Testdrivertywd_appleks_ra_four Kent" and "Testdrivertywd_appleks_rathree Test" perform below action with respective "DUO SCHEDULED" trip
	  | driver1 state | driver2 state |
	  | Accepted      | Accepted      |
	
	And I click "ACCEPT" button on Bungii Request screen
	Then user is alerted for "PICKUP REQUEST NO LONGER AVAILABLE"
	And I cancel all bungiis of customer
	  | Customer Phone | Customer2 Phone |
	  | 8805368840     |                 |
  
  
  
  @regression
	#stable
  Scenario: Verify Status Of Scheduled Bungii In the Scheduled Trip Screen When Only One Driver Accepts The Trip
	And I request "duo" Bungii as a customer in "kansas" geofence
	  | Bungii Time   | Customer Phone | Customer Name                    | Customer Password |
	  | NEXT_POSSIBLE | 8805368840     | Testcustomertywd_appleRicha Test | Cci12345          |
	
	And As a driver "Testdrivertywd_appleks_rathree Test" and "Testdrivertywd_appleks_ra_four Kent" perform below action with respective "DUO SCHEDULED" trip
	  | driver1 state | driver2 state |
	  | Accepted      |               |
	
	When I Switch to "driver" application on "same" devices
	And I am on the LOG IN page on driver app
	And I enter phoneNumber :8888881019 and  Password :Cci12345
	And I click "Log In" button on Log In screen on driver app
	And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
	
	And I Select "SCHEDULED BUNGIIS" from driver App menu
	Then trips status should be "Contacting Other Driver"
	And I Select Trip from driver scheduled trip
	Then I should be navigated to "BUNGII DETAILS" screen
	And "correct duo scheduled trip details" should be displayed on Bungii request screen
	Then I cancel all bungiis of customer
	  | Customer Phone | Customer2 Phone |
	  | 8805368840     |                 |
  
  
  @regression
 #stable
  Scenario:Verify Details In The Bungii Details Screen When Required Number Of Drivers Accept The Trip
	
	When I request "duo" Bungii as a customer in "kansas" geofence
	  | Bungii Time   | Customer Phone | Customer Name                    | Customer Password |
	  | NEXT_POSSIBLE | 8805368840     | Testcustomertywd_appleRicha Test | Cci12345          |
	
	And As a driver "Testdrivertywd_appleks_rathree Test" and "Testdrivertywd_appleks_ra_four Kent" perform below action with respective "DUO SCHEDULED" trip
	  | driver1 state | driver2 state |
	  | Accepted      | Accepted      |
	
	When I Switch to "driver" application on "same" devices
	And I am on the LOG IN page on driver app
	And I enter phoneNumber :8888881019 and  Password :Cci12345
	And I click "Log In" button on Log In screen on driver app
	And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
	
	And I Select "SCHEDULED BUNGIIS" from driver App menu
	Then trips status should be "estimated cost of duo trip"
	And I Select Trip from driver scheduled trip
	Then I should be navigated to "BUNGII DETAILS" screen
	And "correct duo scheduled trip details" should be displayed on Bungii request screen
	Then I cancel all bungiis of customer
	  | Customer Phone | Customer2 Phone |
	  | 8805368840     |                 |

 
  
  @regression
	@nonstable
    #stable
  Scenario: Verify If Researched Driver Can Cancel Trip After Starting The Scheduled Duo Delivery When Other driver has not started the delivery
	When I request "duo" Bungii as a customer in "kansas" geofence
	  | Bungii Time   | Customer Phone | Customer Name                    | Customer Password |
	  | NEXT_POSSIBLE | 8805368840     | Testcustomertywd_appleRicha Test | Cci12345          |
  
	When I Switch to "driver" application on "ORIGINAL" devices
	And As a driver "Testdrivertywd_appleks_ra_four Kent" and "Testdrivertywd_appleks_rathree Test" perform below action with respective "DUO SCHEDULED" trip
	  | driver1 state | driver2 state |
	  | Accepted      | Accepted      |
	
	And I open Admin portal and navigate to "Scheduled Deliveries" page
	And I open the trip for "Testcustomertywd_appleRicha Test" customer
	And I remove current driver and researches Bungii
	
	When I Switch to "driver" application on "ORIGINAL" devices
	And As a driver "Testdrivertywd_appleks_rathree Test" and "Testdrivertywd_appleks_ra_four Kent" perform below action with respective "Duo Scheduled Researched" trip
	  | driver1 state | driver2 state |
	  | Enroute      | Accepted      |
	
	When I Switch to "driver" application on "ORIGINAL" devices
	And I am on the LOG IN page on driver app
	And I am logged in as "Testdrivertywd_appleks_rathree Test" driver
	And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
	Then "Enroute screen" page should be opened
	And I click the "Cancel" button on "update" screen
	Then Alert message with DRIVER CANCEL BUNGII text should be displayed
	When I click "YES" on the alert message
	Then Driver should see "This trip cannot be cancelled as of now since the other driver has not started the trip. Please text the driver support line for cancellation." message
	
	Then I cancel all bungiis of customer
	  | Customer Phone | Customer2 Phone |
	  | 8805368840     |                 |
  
  
  @regression
	#stable
  Scenario: Verify Customer Can See Text Stating That Driver Can Be Contacted On The Bungii Details Screen Only When The Trip Has Been Accepted By Required Number Of Drivers
	When I request "duo" Bungii as a customer in "Kansas" geofence
	  | Bungii Time   | Customer Phone | Customer Name                    | Customer Password |
	  | NEXT_POSSIBLE | 8805368840     | Testcustomertywd_appleRicha Test | Cci12345          |
	And I login as customer "8805368840" and is on Home Page
	And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
	And I close "Tutorial" if exist
	And I tap on "Menu" > "MY BUNGIIS" link
	When I Switch to "driver" application on "same" devices
	And As a driver "Testdrivertywd_appleks_ra_four Kent" and "Testdrivertywd_appleks_rathree Test" perform below action with respective "DUO SCHEDULED" trip
	  | driver1 state | driver2 state |
	  | Accepted      | Accepted      |
	
	And I Switch to "customer" application on "same" devices
	And I tap on "Menu" > "MY BUNGIIS" link
	And I select already scheduled bungii
    #Then I verify that text "You will have the ability to contact your drivers when the Bungii begins" is displayed
	Then I verify that text "Your driver will contact you when they are en-route." is displayed
	Then I cancel all bungiis of customer
	  
	  | Customer Phone | Customer2 Phone |
	  | 8805368840     |                 |
	
	 #@regression
  @ready
  @nonstable
  Scenario: Verify Customer Receives Notification When Control Driver Starts Duo Bungii
	When I clear all notification
	And I request "duo" Bungii as a customer in "kansas" geofence
	  | Bungii Time   | Customer Phone | Customer Name                    | Customer Password |
	  | NEXT_POSSIBLE | 8805368840     | Testcustomertywd_appleRicha Test | Cci12345          |
	Then I Switch to "customer" application on "same" devices
	Given I am on customer Log in page
	When I enter customers "8805368840" Phone Number
	And I enter customers "valid" Password
	And I tap on the "Log in" Button on Login screen
	And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
	And I close "Tutorial" if exist
	
	When I Switch to "driver" application on "same" devices
	And As a driver "Testdrivertywd_appleks_rathree Test" and "Testdrivertywd_appleks_ra_four Kent" perform below action with respective "DUO SCHEDULED" trip
	  | driver1 state | driver2 state |
	  | Enroute       | Enroute      |
	
	When I Switch to "customer" application on "same" devices
	And I click on notification for "Customer" for "DRIVERS ARE ENROUTE"
	
	Then I cancel all bungiis of customer
	  | Customer Phone | Customer2 Phone |
	  | 8805368840     |                 |
