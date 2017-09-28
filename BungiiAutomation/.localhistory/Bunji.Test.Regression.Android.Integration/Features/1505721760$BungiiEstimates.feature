Feature: BungiiEstimates

Background:
Given I am logged in as a customer

Scenario: Bungii Customer Estimates Bungii first time
	When I set correct "pickup" location
	And I set correct "drop off" location
	When I tap on the "Get Estimate" button
	Then the "Get Estimate" page should be displayed
	When I Request a Bungii
	And I click on the "cancel" link
	Then Driver search should be cancelled
