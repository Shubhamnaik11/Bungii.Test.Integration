Feature: CustomerMenu
	In Bungii Customer
	As a logged in customer
	I want to check all menu links

Background:
 Given I am on Customer logged in Home page

Scenario: Cust_Menu_Home
	When I tap on "Menu" > "Home" link
	Then "Home" page should be opened

Scenario: Cust_Menu_FAQ
	When I tap on "Menu" > "FAQ" link
	Then "FAQ" page should be opened

Scenario: Cust_Menu_Account
	When I tap on "Menu" > "Account" link
	Then "Account" page should be opened

Scenario: Cust_Menu_Payment
	When I tap on "Menu" > "Payment" link
	Then "Payment" page should be opened

Scenario: Cust_Menu_Support
	When I tap on "Menu" > "Support" link
	Then "Support" page should be opened

Scenario: Cust_Menu_SaveMoney
	When I tap on "Menu" > "Save Money" link
	Then "Save money" page should be opened

Scenario: Cust_Menu_Logout
	When I tap on "Menu" > "Logout" link
	Then Customer should be logged out 