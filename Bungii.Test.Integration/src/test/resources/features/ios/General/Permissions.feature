@ios
Feature: Permissions
  
  @setapppermission
  Scenario: PreStep - Accept Driver App Permissions - Device 1
	Given I Switch to "driver" application on "same" devices
	And I am on the "LOG IN" page on driverApp
	When I enter phoneNumber :{VALID} and  Password :Cci12345
	And I click "Log In" button on "Log In" screen on driverApp
	And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
  
  @setapppermission
  Scenario: PreStep - Accept Driver App Permissions - Device 2
	And I Switch to "driver" application on "Driver2" devices
	When I enter phoneNumber :{VALID} and  Password :Cci12345
	And I click "Log In" button on "Log In" screen on driverApp
	And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
  
  @setapppermission
  Scenario: PreStep - Accept Customer App Permissions - Device 1
	When I Switch to "customer" application on "same" devices
	When I am on the "LOG IN" page
	And I enter Username :{VALID} and  Password :{VALID}
	And I click "Log In" button on "Log In" screen
	And I accept "TERMS AND CONDITION" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
	Then I should able to see "tutorials page 1" on Tutorials screen
	Then I "left" swipe on tutorials page
	Then I "left" swipe on tutorials page
	Then I "left" swipe on tutorials page
	Then I "left" swipe on tutorials page
	Then I "left" swipe on tutorials page
	And I close tutorial Page using close button
	Then User should be successfully logged in to the application
  
  @setapppermission
  Scenario: PreStep - Accept Customer App Permissions - Device 2
	When I Switch to "customer" application on "customer2" devices
	When I am on the "LOG IN" page
	And I enter Username :{VALID} and  Password :{VALID}
	And I click "Log In" button on "Log In" screen
	And I accept "TERMS AND CONDITION" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
	Then I should able to see "tutorials page 1" on Tutorials screen
	Then I "left" swipe on tutorials page
	Then I "left" swipe on tutorials page
	Then I "left" swipe on tutorials page
	Then I "left" swipe on tutorials page
	Then I "left" swipe on tutorials page
	And I close tutorial Page using close button
	Then User should be successfully logged in to the application