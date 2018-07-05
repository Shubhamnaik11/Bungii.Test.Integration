Feature: Bungii

Background:
	Given I am logged in as "my" customer
	When I enter "current location in pickup and dropoff fields" on Bungii estimate
	And I tap on "Get Estimate button" on Bungii estimate
	And I add "1" photos to the Bungii 
	And I add loading/unloading time of "30 mins"
	And I tap on "Request Bungii" on Bungii estimate

Scenario: Bungii_CustomerCancelOnHeadsUp
	When I tap on "Cancel on HeadsUp pop up" on Bungii estimate
	Then for a Bungii I should see "Bungii Estimate page with all details"

Scenario: Bungii_CustomerCancelOnSearch
	When I tap on "Yes on HeadsUp pop up" on Bungii estimate
	Then for a Bungii I should see "Bungii search screen"
	When I tap on "Cancel during search" on Bungii estimate
	Then for a Bungii I should see "Bungii Home page with locations"

Scenario: Bungii_DriverRejects
	When I tap on "Cancel" on Bungii estimate
	Then I should see "driver cancelled" on Bungii estimate

Scenario: Bungii_DriverAcceptsAndThenCancels

#Customer requests an On Demand Bungii. Driver accepts and completes the Bungii.
#Verify call and SMS functionality on all pages on Customer and Driver app while the Bungii is in progress.
Scenario: Bungii_Complete_DriverApp	
	Given I am logged in as "existing" driver
	When I tap on "Online/Offline button" on Driver Home page
	When I tap on "Yes on HeadsUp pop up" on Bungii estimate	
	And Bungii Driver "accepts On Demand Bungii" request
	When I tap "OK on Driver Accepted screen" during a Bungii
	When I tap "SMS for a solo driver" during a Bungii
	Then correct details should be displayed on "SMS" app
	When I tap "Call for a solo driver" during a Bungii
	Then correct details should be displayed on "Calling" app

	When Bungii Driver taps "SMS for a customer" during a Bungii
	Then correct details should be displayed to driver on "SMS" app
	When Bungii Driver taps "Call for a solo driver" during a Bungii
	Then correct details should be displayed to driver on "Calling" app

	Then for a Bungii I should see "Enroute screen"
	When Bungii Driver "slides to the next state"
	When I tap "SMS for a solo driver" during a Bungii
	Then correct details should be displayed on "SMS" app
	When I tap "Call for a solo driver" during a Bungii
	Then correct details should be displayed on "Calling" app
	When Bungii Driver "slides to the next state"
	When I tap "SMS for a solo driver" during a Bungii
	Then correct details should be displayed on "SMS" app
	When I tap "Call for a solo driver" during a Bungii
	Then correct details should be displayed on "Calling" app
	When Bungii Driver "slides to the next state"
	When I tap "SMS for a solo driver" during a Bungii
	Then correct details should be displayed on "SMS" app
	When I tap "Call for a solo driver" during a Bungii
	Then correct details should be displayed on "Calling" app
	When Bungii Driver "slides to the next state"
	When I tap "SMS for a solo driver" during a Bungii
	Then correct details should be displayed on "SMS" app
	When I tap "Call for a solo driver" during a Bungii
	Then correct details should be displayed on "Calling" app
	When Bungii Driver "slides to the next state"
	And I tap on "OK on complete" on Bungii estimate
	And I tap on "No free money" on Bungii estimate
	And Bungii Driver "completes Bungii"
	And Quit Bungii Driver app

Scenario: Bungii_Complete_Simulator
	When simulator driver is "online"
	And Simulator Bungii Driver "accepts Bungii"
	Then for a Bungii I should see "Bungii accepted"
	When I tap "OK on Driver Accepted screen" during a Bungii
	Then for a Bungii I should see "Enroute screen"
	And for a Bungii I should see "Pickup location details"
	And for a Bungii I should see "Driver Details"
	When Simulator Bungii Driver "arrives at pickup location"
	Then for a Bungii I should see "Arrived screen"
	And for a Bungii I should see "Pickup location details"
	And for a Bungii I should see "Driver Details"
	When Simulator Bungii Driver "starts loading items"
	Then for a Bungii I should see "Loading Item screen"
	And for a Bungii I should see "Pickup location details"
	And for a Bungii I should see "Driver Details"
	When Simulator Bungii Driver "starts driving to dropoff"
	Then for a Bungii I should see "Driving to DropOff screen"
	And for a Bungii I should see "Dropoff location details"
	And for a Bungii I should see "Driver Details"
	When Simulator Bungii Driver "starts unloading items"
	Then for a Bungii I should see "Unloading Item screen"
	And for a Bungii I should see "Dropoff location details"
	And for a Bungii I should see "Driver Details"
	When Simulator Bungii Driver "completes Bungii"
	And I tap on "X on complete" on Bungii estimate
	And I tap on "No free money" on Bungii estimate
	And simulator driver is "offline"