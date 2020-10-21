@android
@duo
@bungii

Feature: Scheduled Duo Bungiis - Single Phone
  
  @regression
  Scenario: Verify Minimum Scheduled Time For The Duo Trip
	Given I am on customer Log in page
	When I am logged in as "New" customer
	And I enter "San Francisco pickup and dropoff locations" on Bungii estimate
	And I tap on "two drivers selector" on Bungii estimate
	Then I should see "two drivers selected" on Bungii estimate
	When I tap on "Get Estimate button" on Bungii estimate
	Then I should see the minimum scheduled time displayed on the Estimate page
  
  @regression
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
