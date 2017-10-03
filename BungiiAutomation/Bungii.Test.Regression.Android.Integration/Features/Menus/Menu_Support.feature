Feature: Menu_Support
	Test scenarios related to Support page

Background:
	Given I am logged in as "existing" customer
	When I tap on "Menu" > "Support" link

Scenario: Menu_Support_SendFeedback
	When I enter "valid" text in Support field
	And I tap "Send" on Support page
	Then The user should see "snackbar validation" on Support page

Scenario: Menu_Support_BlankField
	When I enter "space" text in Support field
	Then The user should see "Send button disabled" on Support page
	Then The user should see "validation message" on Support page