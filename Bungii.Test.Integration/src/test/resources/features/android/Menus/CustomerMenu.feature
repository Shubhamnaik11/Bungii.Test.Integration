﻿Feature: CustomerMenu
	In Bungii Customer
	As a logged in customer
	I want to check all menu links

Background:
 Given I am on Customer logged in Home page

@Android
Scenario: Cust_Menu_Home
	When I tap on "Menu" > "Home" link
	Then "Home" page should be opened

@Android
Scenario: Cust_Menu_FAQ
	When I tap on "Menu" > "FAQ" link
	Then "FAQ" page should be opened
	When I tap on "first question" on FAQ page
	Then I should see "first answer dropdown open" on FAQ page
	When I tap on "first question" on FAQ page
	Then I should see "first answer dropdown close" on FAQ page
	Then I should see "last question" on FAQ page
	Then I should see "social media links" on FAQ page

@Android
Scenario: Cust_Menu_Account
	When I tap on "Menu" > "Account" link
	Then "Account" page should be opened
	And logged in Customer details should be displayed

@Android
Scenario: Cust_Menu_Payment
	When I tap on "Menu" > "Payment" link
	Then "Payment" page should be opened

@Android
Scenario: Cust_Menu_Support
	When I tap on "Menu" > "Support" link
	Then "Support" page should be opened

@Android
Scenario: Cust_Menu_SaveMoney
	When I tap on "Menu" > "Save Money" link
	Then "Save money" page should be opened

@Android
Scenario: Cust_Menu_Logout
	When I tap on "Menu" > "Logout" link
	Then Customer should be logged out 