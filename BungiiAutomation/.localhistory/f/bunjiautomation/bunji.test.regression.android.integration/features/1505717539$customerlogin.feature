Feature: Login
	In order to login to bungii
	As a customer and perform functions

Background:
 Given I am on Sign up page

Scenario: Bungii Customer Login
    When I tap on Login Link
    And I enter Customer Phone Number and Password
    And I tap on Log in Button
    Then The user should login

Scenario: Bungii Customer Estimates Bungii first time
    When I tap on Login Link
    And I enter Customer Phone Number and Password
    And I tap on Log in Button
    Then The user should login
	When I set correct pickup location
	And I set correct drop off location
	When I tap on Get Estimate button
	Then Get Estimate page should be displayed
	When I Request a Bungii
	And I click on the cancel link
	Then Driver search should be cancelled


Scenario: Bungii Customer Registration
    When I enter mandatory fields
    And I tap on Sign Up
    When I enter Verification code
	Then I should be logged in

