@web
Feature: Admin_Revival
  
  Background:
	Given I am logged in as Admin
  
  @sanity
  @ready
    #test data created in base
  Scenario: Verify Manually Ending Bungii As An Admin For Solo Scheduled Pickup
	When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
	  | Bungii Time   | Customer Phone | Customer Name |
	  | NEXT_POSSIBLE | 9284000002 | Testcustomertywd_appleweb CustB|
	And As a driver "Testdrivertywd_appledc_a_web TestdriverB" perform below action with respective "Solo Scheduled" Delivery
	  | driver1 state|
	  |Accepted |
	  | Enroute  |
	  | Arrived |
	  | Loading Item |
	And I view the Live Deliveries list on the admin portal
	Then I should be able to see the respective bungii with the below status
	  |  Status |
	  | Loading Items |
	When I view the delivery details
	Then the Bungii details is displayed successfully
	And I click on "Manually End Bungii" link
	And Enter the End Date and Time
	And Click on "Calculate Cost" button
	Then the amount is calculated and shown to admin
	And Click on "Confirm" button
	And I view the Deliveries list on the admin portal
	Then The Delivery List page should display the delivery in "Payment Successful" state
	
  @regression
  Scenario: Verify Admin can cancel the Revived Delivery
	When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
	  | Bungii Time   | Customer Phone | Customer Name                  |
	  | NEXT_POSSIBLE | 9999999227     | Testcustomertywd_appleNewMN Customer|
	And As a driver "Testdrivertywd_appledc_a_web TestdriverB" perform below action with respective "Solo Scheduled" Delivery
	  | driver1 state|
	  |Accepted |
	  | Enroute  |
	When I cancel bungii as a driver "Testdrivertywd_appledc_a_web TestdriverB"
	And I wait for 2 minutes
	And I view the Deliveries list on the admin portal
	And I search the delivery of Customer
	Then Revive button should be displayed beside the trip
	When I click on "Revive" button
	Then I should see "Are you sure you want to revive the trip?" message on popup with PickupId anad Pickup Origin
	When I click on "Confirm" button on Revival Popup
	And I wait for 2 minutes
	And I view the all Scheduled Deliveries list on the admin portal
	And I search the delivery of Customer
	Then I should be able to see the respective bungii with the below status
	  |  Status |
	  | Driver(s) Not Found |
	And I click on "Edit" link beside scheduled bungii
	And I click on "Cancel entire Bungii and notify driver(s)" radiobutton
	And I enter cancellation fee and Comments
	And I select "Outside of delivery scope" from the "Cancellation Reason" dropdown
	And I click on "Submit" button
	Then The "Pick up has been successfully cancelled." message should be displayed
	When I view the Deliveries list on the admin portal
	Then The Delivery List page should display the delivery in "Admin Canceled" state
  
  @regression
  Scenario: Verify Admin can Assign driver and assigned driver can complete the Revived Delivery
	When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
	  | Bungii Time   | Customer Phone | Customer Name                  |
	  | NEXT_POSSIBLE | 9999999225     | Testcustomertywd_appleNewML Customer|
	And As a driver "Testdrivertywd_appledc_a_web TestdriverB" perform below action with respective "Solo Scheduled" Delivery
	  | driver1 state|
	  |Accepted |
	  | Enroute  |
	When I cancel bungii as a driver "Testdrivertywd_appledc_a_web TestdriverB"
	And I wait for 2 minutes
	And I view the Deliveries list on the admin portal
	And I search the delivery of Customer
	Then Revive button should be displayed beside the trip
	When I click on "Revive" button
	Then I should see "Are you sure you want to revive the trip?" message on popup with PickupId anad Pickup Origin
	When I click on "Confirm" button on Revival Popup
	And I wait for 2 minutes
	And I view the all Scheduled Deliveries list on the admin portal
	And I search the delivery of Customer
	Then I should be able to see the respective bungii with the below status
	  |  Status |
	  | Driver(s) Not Found |
	And I click on "Edit" link beside scheduled bungii
	When I click on "Edit Trip Details" radiobutton
	And I assign driver "Testdrivertywd_appledc_a_web TestdriverB" for the trip
	And I click on "VERIFY" button
	And the "Your changes are good to be saved." message is displayed
	Then I click on "SAVE CHANGES" button
	And the "Bungii Saved!" message is displayed
	When I click on "Close" button
	And I refresh the page
	And As a driver "Testdrivertywd_appledc_a_web TestdriverB" perform below action with respective "Solo Scheduled Researched" Delivery
	  | driver1 state|
	  | Enroute  |
	  | Arrived |
	  | Loading Item |
	  | Driving To Dropoff |
	  | Unloading Item |
	  | Bungii Completed |
	And I wait for 2 minutes
	And I view All Deliveries list on the admin portal
	Then The Delivery List page should display the delivery in "Payment Successful" state
  
  @regression
  Scenario: Verify Admin can Assign driver and admin can manually end bungii in loading item state of the Revived Delivery
	When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
	  | Bungii Time   | Customer Phone | Customer Name                  |
	  | NEXT_POSSIBLE | 9999999226     | Testcustomertywd_appleNewMM Customer|
	And As a driver "Testdrivertywd_appledc_a_web TestdriverB" perform below action with respective "Solo Scheduled" Delivery
	  | driver1 state|
	  |Accepted |
	  | Enroute  |
	When I cancel bungii as a driver "Testdrivertywd_appledc_a_web TestdriverB"
	And I wait for 2 minutes
	And I view the Deliveries list on the admin portal
	And I search the delivery of Customer
	Then Revive button should be displayed beside the trip
	When I click on "Revive" button
	Then I should see "Are you sure you want to revive the trip?" message on popup with PickupId anad Pickup Origin
	When I click on "Confirm" button on Revival Popup
	And I wait for 2 minutes
	And I view the all Scheduled Deliveries list on the admin portal
	And I search the delivery of Customer
	Then I should be able to see the respective bungii with the below status
	  |  Status |
	  | Driver(s) Not Found |
	And I click on "Edit" link beside scheduled bungii
	When I click on "Edit Trip Details" radiobutton
	And I assign driver "Testdrivertywd_appledc_a_web TestdriverB" for the trip
	And I click on "VERIFY" button
	And the "Your changes are good to be saved." message is displayed
	Then I click on "SAVE CHANGES" button
	And the "Bungii Saved!" message is displayed
	When I click on "Close" button
	And I refresh the page
	And As a driver "Testdrivertywd_appledc_a_web TestdriverB" perform below action with respective "Solo Scheduled Researched" Delivery
	  | driver1 state|
	  | Enroute  |
	  | Arrived |
	  | Loading Item |
	And I view the Live Deliveries list on the admin portal
	Then I should be able to see the respective bungii with the below status
	  |  Status |
	  | Loading Items |
	When I view the delivery details
	Then the Bungii details is displayed successfully
	And I click on "Manually End Bungii" link
	And Enter the End Date and Time
	And Click on "Calculate Cost" button
	Then the amount is calculated and shown to admin
	And Click on "Confirm" button
	And I wait for 2 minutes
	And I view the Deliveries list on the admin portal
	Then The Delivery List page should display the delivery in "Payment Successful" state
	
  @regression
	@test
  Scenario: Verify Revived button is displayed against the Driver canceled Revived Delivery
	When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
	  | Bungii Time   | Customer Phone | Customer Name                  |
	  | NEXT_POSSIBLE | 9999999224     | Testcustomertywd_appleNewMK Customer|
	And As a driver "Testdrivertywd_appledc_a_web TestdriverB" perform below action with respective "Solo Scheduled" Delivery
	  | driver1 state|
	  |Accepted |
	  | Enroute  |
	When I cancel bungii as a driver "Testdrivertywd_appledc_a_web TestdriverB"
	And I wait for 2 minutes
	And I view the Deliveries list on the admin portal
	And I search the delivery of Customer
	Then Revive button should be displayed beside the trip
	When I click on "Revive" button
	Then I should see "Are you sure you want to revive the trip?" message on popup with PickupId anad Pickup Origin
	When I click on "Confirm" button on Revival Popup
	And I wait for 2 minutes
	And I view the all Scheduled Deliveries list on the admin portal
	And I search the delivery of Customer
	Then I should be able to see the respective bungii with the below status
	  |  Status |
	  | Driver(s) Not Found |
	And I click on "Edit" link beside scheduled bungii
	When I click on "Edit Trip Details" radiobutton
	And I assign driver "Testdrivertywd_appledc_a_web TestdriverB" for the trip
	And I click on "VERIFY" button
	And the "Your changes are good to be saved." message is displayed
	Then I click on "SAVE CHANGES" button
	And the "Bungii Saved!" message is displayed
	When I click on "Close" button
	And I refresh the page
	When I cancel bungii as a driver "Testdrivertywd_appledc_a_web TestdriverB"
	And I wait for 2 minutes
	And I view the Deliveries list on the admin portal
	And I search the delivery of Customer
	Then Revive button should be displayed beside the trip