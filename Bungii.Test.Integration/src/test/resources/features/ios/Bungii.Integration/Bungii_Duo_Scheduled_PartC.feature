@ios
@DUO
@scheduled
@bungii
Feature: Scheduled Bungii on one device
  I want  request Scheduled Bungii with Duo type
  
  Background:
	When I Switch to "customer" application on "same" devices
	
@regression
Scenario:Verify Customer Can Cancel Stacked Ondemand Bungii Accepted By a Driver [1 Device]
Given that ondemand bungii is in progress
| geofence | Bungii State   |
| goa      | UNLOADING ITEM |

When I Switch to "driver" application on "same" devices
And I am on the "LOG IN" page on driverApp
And I am logged in as "valid" driver
And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
   
   # When I Switch to "customer" application on "same" devices
When I request another "Solo Ondemand" Bungii as a customer in "goa" geofence
| Bungii Time | Customer Phone | Customer Name | Customer label | Customer Password |
| now         | 9403960183     | Mark Cuban    |               | Cci12345          |

And I view and accept virtual notification for "Driver" for "stack trip"

When I Switch to "customer" application on "same" devices
Given I am on the "LOG IN" page
When I enter Username :9403960183 and  Password :{VALID}
And I click "Log In" button on "Log In" screen
And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
When I Switch to "customer" application on "same" devices
And I close "Tutorial" if exist

And I Select "MY BUNGIIS" from Customer App menu
And I select already scheduled bungii
Then I Cancel selected Bungii


Then I should be navigated to "HOME" screen

When I Switch to "driver" application on "same" devices
And I click on notification for "Driver" for "CUSTOMER CANCEL STACK TRIP"
And stack trip information should not be displayed on deck

Then I cancel all bungiis of customer
| Customer Phone  | Customer2 Phone |
| CUSTOMER1_PHONE |                 |

@ready
Scenario: STACKING | Verify Manually Ending Bungii For A Driver That Has Stacked Bungii Should See Summary And Start Screen Of The Stacked Bungii [1 Device]
Given that ondemand bungii is in progress
| geofence | Bungii State        |
| goa      | DRIVING TO DROP OFF |
When I Switch to "driver" application on "same" devices
And I am on the "LOG IN" page on driverApp
And I am logged in as "valid" driver
And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist

When I request "Solo Ondemand" Bungii as a customer in "goa" geofence
| Bungii Time | Customer Phone | Customer Name | Customer label | Customer Password |
| now         | 9403960183     | Mark Cuban    | 2              | Cci12345          |
And I view and accept virtual notification for "Driver" for "stack trip"
And stack trip information should be displayed on deck

When I Switch to "customer" application on "same" devices
Given I am on the "LOG IN" page
When I enter Username :9403960183 and  Password :{VALID}
And I click "Log In" button on "Log In" screen
And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
And I close "Tutorial" if exist

When bungii admin manually end bungii created by "CUSTOMER1"

When I Switch to "driver" application on "same" devices
Then I should be navigated to "Bungii Completed" screen
When I click "On To The Next One" button on "Bungii Completed" screen
Then I should be navigated to "EN ROUTE" screen

When I Switch to "customer" application on "same" devices
Then I should be navigated to "EN ROUTE" screen

Then I cancel all bungiis of customer
| Customer Phone | Customer2 Phone |
|                | CUSTOMER2_PHONE |

@ready
Scenario:STACKING | Verify Driver can start the Stack Bungii [1 Device]
Given that ondemand bungii is in progress
| geofence | Bungii State   |
| goa      | UNLOADING ITEM |
When I Switch to "driver" application on "same" devices
And I am on the "LOG IN" page on driverApp
And I am logged in as "valid" driver
And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist

When I Switch to "customer" application on "same" devices
When I request "Solo Ondemand" Bungii as a customer in "goa" geofence
| Bungii Time | Customer Phone | Customer Name | Customer label | Customer Password |
| now         | 9403960183     | Mark Cuban    | 2              | Cci12345          |

And I view and accept virtual notification for "Driver" for "stack trip"

Given I am on the "LOG IN" page
When I enter Username :9403960183 and  Password :{VALID}
And I click "Log In" button on "Log In" screen
And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
And I close "Tutorial" if exist

When I Switch to "driver" application on "ORIGINAL" devices
And I slide update button on "UNLOADING ITEM" Screen
Then I should be navigated to "Bungii Completed" screen
When I click "On To The Next One" button on "Bungii Completed" screen
Then I should be navigated to "EN ROUTE" screen

Then I cancel all bungiis of customer
| Customer Phone | Customer2 Phone |
| 9403960183     |                 |


@regression
    #stable
Scenario: Verify Driver see minutes from pickup address in avaliable trips for Duo Bungii [1 Device]
Given that duo schedule bungii is in progress
| geofence | Bungii State | Bungii Time   | Customer | Driver1 | Driver2        |
| goa      | Scheduled     | 0.5 hour ahead | valid    | valid   | valid driver 2 |

And I Switch to "driver" application on "same" devices
And I am on the "LOG IN" page on driverApp
And I am logged in as "valid duo driver 1" driver
And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist

And I Select "AVAILABLE BUNGIIS" from driver App menu
Then I should see driver minutes to pickup address of "Duo" delivery
Then I cancel all bungiis of customer
| Customer Phone  | Customer2 Phone |
| CUSTOMER1_PHONE |                 |