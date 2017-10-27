Feature: Driver_Documentation

Background: 
	#Given I navigate to "Bungii Driver URL"
	#Then I should be directed to "signup tab" on Driver portal
	#When I enter "valid" details on Signup page
	#And I enter "unique" driver phone number on Signup page
	#And I click "Signup button" on driver portal
	#Then I should be directed to "phone verification page" on Driver portal
	#When I enter "correct" verification code
	#And I click "Submit verification code" on driver portal
	#Then I should be directed to "Verification Successful page" on Driver portal
	#And I should see "Logged in user name" on Driver Registration
	#When I click "Continue Registration" on driver portal
	#Then I should be directed to "Driver Details page" on Driver portal
	#When I enter "valid" data on Driver Details page
	Given I am logged in as driver
	When I click Next on "Driver Details" page
	Then I should be directed to "Pickup Info page" on Driver portal
	When I click Next on "Pickup Information" page
	Then I should be directed to "Documentation page" on Driver portal

Scenario: Driver_Documentation_Valid
	When I enter "valid" data on Documentation page
	And I click Next on "Documentation" page
	Then I should be directed to "Bank Details page" on Driver portal

Scenario: Driver_Documentation_Blank
	When I click Next on "Documentation" page
	Then I should see blank fields validation on "Documentation" page

Scenario: Driver_Documentation_Invalid
	When I enter "invalid date" data on Documentation page
	And I click Next on "Documentation" page
	Then I should see individual field validations on "date on Documentation" page 
	When I enter "invalid" data on Documentation page
	When I click Next on "Documentation" page
	Then I should see individual field validations on "Documentation" page