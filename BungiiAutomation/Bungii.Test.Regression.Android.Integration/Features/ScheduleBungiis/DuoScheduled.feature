Feature: DuoScheduled

Scenario: DuoScheduled_RequestSuccessfully
	Given I am logged in as "existing" driver
	Given I am logged in as "my" customer	
	When I enter "current location in pickup and dropoff fields" on Bungii estimate
	And I tap on "two drivers selector" on Bungii estimate
	Then I should see "two drivers selected" on Bungii estimate
	When I tap on "Get Estimate button" on Bungii estimate
	And I add "1" photos to the Bungii 
	And I add loading/unloading time of "45 mins"
	And I tap on "Request Bungii" on Bungii estimate
	And I tap on "Yes on HeadsUp pop up" on Bungii estimate
	#Then I should see "Bungii posted Success page" on Bungii estimate
	When I tap on "Done after requesting a Scheduled Bungii" on Bungii estimate
	Then I should see "Requested Bungii" on Scheduled List page
	When I tap on "Available Trips link" on Driver Home page
	And I "Accept Bungii" from Available Trips
	Then I should see "Bungii is accepted" on Scheduled List page