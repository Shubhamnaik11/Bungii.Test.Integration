Feature: BungiiEstimates

Background:
Given I am logged in as "my" customer

Scenario: Bungii_Estimate_ExistingUser_FixedPromoCode
	When I enter "valid pickup and dropoff locations" on Bungii estimate
	When I tap on "Get Estimate button" on Bungii estimate
	When I tap on "Promo Code" on Bungii estimate
	And I add "fixed valid" PromoCode
	And I tap "Add" on Save Money page
	And I tap on "desired Promo Code" on Bungii estimate
	Then I should see "all elements" on Bungii estimate
	When I tap on "Add photo" on Bungii estimate
	And I tap on "Request Bungii" on Bungii estimate
	And I tap on "Cancel" on Bungii estimate
	Then I should see "driver cancelled" on Bungii estimate