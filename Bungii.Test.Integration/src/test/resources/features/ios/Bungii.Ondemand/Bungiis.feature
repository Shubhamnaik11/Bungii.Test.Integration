@ios
@bungii

Feature: Bungiis
  Background:
	#When I Switch to "customer" application on "same" devices
 
@sanity
@regression
@ondemand
Scenario: Verify Ondemand Bungii Completion
When I Switch to "driver" application on "same" devices
And I login as "valid nashville" driver on "same" device and make driver status as "Online"

And I Switch to "customer" application on "same" devices
Given I am on the "LOG IN" page
When I logged in Customer application using  "valid nashville" user
And I request for  bungii for given pickup and drop location
| Driver | Pickup Location                 | Drop Location                      | geofence  |
| Solo   | Nashville International Airport | Graylynn Drive Nashville Tennessee | nashville |
And I click "Get Estimate" button on "Home" screen
Then I should be navigated to "Estimate" screen
When I confirm trip with following details
| LoadTime | PromoCode | Payment Card | Time | PickUpImage |
| 15       |           |              | Now  | Default     |
Then I should be navigated to "SEARCHING" screen

And I view and accept virtual notification for "Driver" for "on demand trip"

#When I Switch to "driver" application on "same" devices
Then I should be navigated to "EN ROUTE" trip status screen
And I slide update button on "EN ROUTE" Screen
Then I should be navigated to "ARRIVED" trip status screen
  And I slide update button on "ARRIVED" Screen
  Then I should be navigated to "LOADING ITEM" trip status screen
  And I slide update button on "LOADING ITEM" Screen
Then I should be navigated to "DRIVING TO DROP OFF" trip status screen
And I slide update button on "DRIVING TO DROP OFF" Screen
Then I should be navigated to "UNLOADING ITEM" trip status screen
And I slide update button on "UNLOADING ITEM" Screen
Then I should be navigated to "Bungii Completed" screen
When I click "On To The Next One" button on "Bungii Completed" screen

And I Switch to "customer" application on "same" devices
Then I should be navigated to "Bungii Complete" screen
When I click "CLOSE BUTTON" button on "Bungii Complete" screen
Then I should be navigated to "Promotion" screen
When I click "I DON'T LIKE FREE MONEY" button on "Promotion" screen
Then I should be navigated to "Home" screen