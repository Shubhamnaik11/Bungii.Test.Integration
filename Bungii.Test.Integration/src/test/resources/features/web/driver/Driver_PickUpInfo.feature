Feature: Driver_PickupInfo

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
  @Web
  Scenario: Driver_PickupInfo_Valid
    When I enter "valid" data on Pickup Information page
    And I click Next on "Pickup Information" page
    Then I should be directed to "Documentation page" on Driver portal
  @Web
  Scenario: Driver_PickupInfo_Blank
    When I click Next on "Pickup Information" page
    Then I should see blank fields validation on "Pickup Information" page
  @Web
  Scenario: Driver_PickupInfo_LessThan3Images
    When I enter "less truck image - i" data on Pickup Information page
    And I click Next on "Pickup Information" page
    Then I should see individual field validations on "Pickup Information - i" page
    When I enter "less truck image - ii" data on Pickup Information page
    And I click Next on "Pickup Information" page
    Then I should see individual field validations on "Pickup Information - ii" page