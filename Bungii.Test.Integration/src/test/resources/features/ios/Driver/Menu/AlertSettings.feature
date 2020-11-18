@ios
Feature: AlertSettings
  In Bungii Driver
  As a logged in driver
  I want to check Alert Settings
  
  Background:
	Given I Switch to "driver" application on "same" devices
	And I am on the "LOG IN" page on driverApp
	And I am logged in as "new driver" driver
	And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
	
@ready
Scenario: Verify Trip Alert Settings On Trip Alerts Tab (Default:7.00AM-9.00PM)
When I Select "ALERT SETTINGS" from driver App menu
When I click "SMS ALERT" button on "ALERT SETTINGS" screen on driverApp
Then I should be able to see default data on "SMS ALERT" page


@regression
Scenario: Verify Correct Data Is Displayed In Trip And Sms Alert Settings Upon Switching Between Trip And SMS Alerts Tabs
When I Select "ALERT SETTINGS" from driver App menu
And I save "DELIVERY ALERT" settings data
When I click "SMS ALERT" button on "ALERT SETTINGS" screen on driverApp
And I update sms setting of "sunday" to "09:00 a.m." to "12:00 p.m."
And I save "SMS ALERT" settings data
When I click "DELIVERY ALERT" button on "ALERT SETTINGS" screen on driverApp
Then previous "DELIVERY ALERT" data should be retained
And I update trip setting of "sunday" to "05:00 a.m." to "12:00 p.m."
When I click "SMS ALERT" button on "ALERT SETTINGS" screen on driverApp
Then previous "SMS ALERT" data should be retained
