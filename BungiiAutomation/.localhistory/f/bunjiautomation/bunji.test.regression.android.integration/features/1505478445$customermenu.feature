Feature: CustomerMenu
	In Bungii Customer
	As a logged in customer
	I want to check all menu links

Background:
 Given I am on Customer logged in Home page

Scenario: To check Bungii Customer Menu links	
	Given I tap on Menu Icon
	And I open the slider Menu
	And I tap on FAQ link
	Then FAQ page should be opened
	When I tap on Menu Icon
	And I tap on Account link
	Then Account page should be opened
	When I tap on Menu Icon
	And I tap on Payment link
	Then Payment page should be opened
	When I tap on Menu Icon
	And I tap on Support link
	Then Support page should be opened
	When I tap on Menu Icon
	And I tap on Save Money link
	Then Save money page should be opened
	When I tap on Menu Icon
	And I tap on Home link
	Then Home page should be opened
	When I tap on Menu Icon
	And I tap on Logout Link
	Then Customer should be logged out 

	Scenario: To check Bungii Customer Menu links	
	When I tap on "Menu" > "FAQ" link
	Then "FAQ page" should be opened
	When I tap on "Menu" > "Account" link
	Then "Account page" should be opened
	When I tap on "Menu" > "Payment" link
	Then "Payment page" should be opened
	When I tap on "Menu" > "Support" link
	Then "Support" page should be opened
	When I tap on "Menu" > "Save Money" link
	Then "Save money" page should be opened
	When I tap on "Menu" > "Home" link
	Then "Home" page should be opened
	When I tap on "Menu" > "Logout" link
	Then Customer should be logged out 