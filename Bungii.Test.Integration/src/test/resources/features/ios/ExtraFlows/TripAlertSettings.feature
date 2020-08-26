@ios
@scheduled
    # this will run in denver
Feature: Trip Alert Settings
  I want to use request Scheduling Bungii with Solo type
  
	  #Always Last scenario from this feature file since it changes settings for the driver
	  @regression
	  Scenario: Verify Driver Doesnt Receive Scheduled Request If The Request Is Sent Outside Of Time That Is Set In Trip Alert Settings
	  When I clear all notification
	  When I Switch to "driver" application on "same" devices
	  And I am on the "LOG IN" page on driverApp
	  And I am logged in as "valid denver" driver
	  When I Select "ALERT SETTINGS" from driver App menu
	  And I update denvers driver todays trip alert setting to outside current time
	  When I Switch to "customer" application on "same" devices
	  When I request "Solo Scheduled" Bungii as a customer in "denver" geofence
	  | Bungii Time   | Customer Phone | Customer Name                      | Customer Password |
	  | NEXT_POSSIBLE | 8888889917     | Testcustomertywd_appleZTDafc Stark | Cci12345          |
	  And I should not get notification for "driver" for "SCHEDULED PICKUP AVAILABLE"
	  
	  When I Switch to "driver" application on "same" devices
	  And I Select "AVAILABLE BUNGIIS" from driver App menu
	  Then I should be navigated to "AVAILABLE BUNGIIS" screen
	  And I should able to see "zero" available trip
	  And I Select "ALERT SETTINGS" from driver App menu
	  And I update trip setting of "TODAY" to "12:00 AM" to "11:59 PM"
	  Then I cancel all bungiis of customer
	  | Customer Phone | Customer2 Phone |
	  | 8888889917     |                 |