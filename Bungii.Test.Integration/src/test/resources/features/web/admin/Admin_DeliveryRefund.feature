@web
Feature: Admin_Refund
  
  Background:
	Given I am logged in as Admin
  
  @sanity
  @regression
  Scenario: Verify Cancellation of Scheduled Bungii As An Admin
	When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
	  | Bungii Time   | Customer Phone | Customer Name                  |
	  | NEXT_POSSIBLE | 9284000005     | Testcustomertywd_appleweb CustE|
	And As a driver "Testdrivertywd_appledc_a_web TestdriverF" perform below action with respective "Solo Scheduled" Delivery
	  | driver1 state|
	  | Accepted  |
	And I view the all Scheduled Deliveries list on the admin portal
	Then I should be able to see the respective bungii with the below status
	  |  Status |
	  | Scheduled |
	When I click on "Edit" link beside scheduled bungii
	And I click on "Cancel entire Bungii and notify driver(s)" radiobutton
	And I enter cancellation fee and Comments
	And I select "Outside of delivery scope" from the "Cancellation Reason" dropdown
	And I click on "Submit" button
	Then The "Pick up has been successfully cancelled." message should be displayed
	When I view the Trips list on the admin portal
	Then The Delivery List page should display the delivery in "Admin Canceled" state
	
@regression
Scenario: Verify Partial Refund Calculations of Solo Scheduled Delivery
When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
| Bungii Time   | Customer Phone | Customer Name                  |
| NEXT_POSSIBLE | 9999999601     | Testcustomertywd_appleNewA Customer|
And As a driver "Testdrivertywd_appledc_a_web TestdriverF" perform below action with respective "Solo Scheduled" Delivery
	| driver1 state|
	|Accepted |
	| Enroute  |
	| Arrived |
	| Loading Item |
	| Driving To Dropoff |
	| Unloading Item |
	| Bungii Completed |
  And I wait for 2 minutes
  And I view the Deliveries list on the admin portal
  And I search the delivery of Customer and view it
  When I click on "ISSUE REFUND" button
  Then The "Issue Refund" section should be displayed
  When I select "Partial Refund" radio button
  
  And I enter "Customer Refund Amount" as "5.01" dollars
  Then "Bungii Earnings and percentage" fields should be auto calculated based on Delivery Total and Driver Earnings
  And "Customer Refund Amount Percentage" field should be auto calculated based on Delivery Total and Driver Earnings
  
  When I enter "Customer Refund Amount" as "10" percentage
  Then "Bungii Earnings and percentage" fields should be auto calculated based on Delivery Total and Driver Earnings
  And "Customer Refund Amount" field should be auto calculated based on Delivery Total and Driver Earnings
  
  When I update "Earnings" as "10.00" dollars
  Then "Bungii Earnings and percentage" fields should be auto calculated based on Delivery Total and Driver Earnings
  And "Customer Refund Amount Percentage" field should be auto calculated based on Delivery Total and Driver Earnings
  And Notes text area should be displayed
  
  When I update "Earnings" as origional value of amount
  Then "Bungii Earnings and percentage" fields should be auto calculated based on Delivery Total and Driver Earnings
  And "Customer Refund Amount Percentage" field should be auto calculated based on Delivery Total and Driver Earnings
  And Notes text area should not be displayed
  
  When I update "Earnings" as "5.01" percentage
  Then "Bungii Earnings and percentage" fields should be auto calculated based on Delivery Total and Driver Earnings
  And "Customer Refund Amount" field should be auto calculated based on Delivery Total and Driver Earnings
  And Notes text area should be displayed
  
  When I update "Earnings" as origional value of percentage
  Then "Bungii Earnings and percentage" fields should be auto calculated based on Delivery Total and Driver Earnings
  And "Customer Refund Amount Percentage" field should be auto calculated based on Delivery Total and Driver Earnings
  And Notes text area should not be displayed
  
  @regression
  Scenario: Verify Partial Refund for Solo Scheduled Delivery
	When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
	  | Bungii Time   | Customer Phone | Customer Name                  |
	  | NEXT_POSSIBLE | 9999999602     | Testcustomertywd_appleNewB Customer|
	And As a driver "Testdrivertywd_appledc_a_web TestdriverF" perform below action with respective "Solo Scheduled" Delivery
	  | driver1 state|
	  |Accepted |
	  | Enroute  |
	  | Arrived |
	  | Loading Item |
	  | Driving To Dropoff |
	  | Unloading Item |
	  | Bungii Completed |
	And I view the Deliveries list on the admin portal
	And I wait for 2 minutes
	And I search the delivery of Customer and view it
	When I click on "ISSUE REFUND" button
	Then The "Issue Refund" section should be displayed
	When I select "Partial Refund" radio button
	And I enter "Customer Refund Amount" as "5" dollars
	And I enter "Bungii Internal Notes" as "Internal Note"
	And I click on "Continue" button on Issue Refund popup
	Then I should see "Issue Refund - Confirm Details" popup
     And I should see Original Delivery Charge & Customer Refund & Total Customer Charge
	And I should see breakdown of Before and After Refund earnings
	And I should see Bungii Internal Note
	When I select "Are you sure you want to proceed with refund request ?" checkbox
	And I click on "Process Refund" button on Issue Refund popup
	Then "We are processing your Refund Request. We will let you know once it has been processed successfully." is displayed
	When I click on "OK" button
	And I search the delivery of Customer and view it
	Then The "Issue Refund" button should not be displayed
  
  @regression
  Scenario: Verify Complete Refund for Solo Scheduled Delivery and Full Driver Payment
	When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
	  | Bungii Time   | Customer Phone | Customer Name                  |
	  | NEXT_POSSIBLE | 9999999603     | Testcustomertywd_appleNewC Customer|
	And As a driver "Testdrivertywd_appledc_a_web TestdriverF" perform below action with respective "Solo Scheduled" Delivery
	  | driver1 state|
	  |Accepted |
	  | Enroute  |
	  | Arrived |
	  | Loading Item |
	  | Driving To Dropoff |
	  | Unloading Item |
	  | Bungii Completed |
	And I view the Deliveries list on the admin portal
	And I wait for 2 minutes
	And I search the delivery of Customer and view it
	When I click on "ISSUE REFUND" button
	Then The "Issue Refund" section should be displayed
	When I select "Complete Refund" radio button
	Then I should see Customer Refund Amount and Driver Earnings
	When I enter "Bungii Internal Notes" as "Internal Note"
	And I click on "Continue" button on Issue Refund popup
	Then I should see "Issue Refund - Confirm Details" popup
	And I should see Original Delivery Charge & Customer Refund & Total Customer Charge
	And I should see breakdown of Before and After Refund earnings
	And I should see Bungii Internal Note
	When I select "Are you sure you want to proceed with refund request ?" checkbox
	And I click on "Process Refund" button on Issue Refund popup
	Then "We are processing your Refund Request. We will let you know once it has been processed successfully." is displayed
	When I click on "OK" button
	And I search the delivery of Customer and view it
	Then The "Issue Refund" button should not be displayed
  
  @regression
  Scenario: Verify Complete Refund for Solo Scheduled Delivery and partial Driver payment
	When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
	  | Bungii Time   | Customer Phone | Customer Name                  |
	  | NEXT_POSSIBLE | 9999999604     | Testcustomertywd_appleNewD Customer|
	And As a driver "Testdrivertywd_appledc_a_web TestdriverF" perform below action with respective "Solo Scheduled" Delivery
	  | driver1 state|
	  |Accepted |
	  | Enroute  |
	  | Arrived |
	  | Loading Item |
	  | Driving To Dropoff |
	  | Unloading Item |
	  | Bungii Completed |
	And I view the Deliveries list on the admin portal
	And I wait for 2 minutes
	And I search the delivery of Customer and view it
	When I click on "ISSUE REFUND" button
	Then The "Issue Refund" section should be displayed
	When I select "Complete Refund" radio button
	When I update "Earnings" as "10.00" dollars
	Then I should see Customer Refund Amount and Driver Earnings
	When I enter "Bungii Internal Notes" as "Internal Note"
	When I enter "Notes" as "Driver Note"
	And I click on "Continue" button on Issue Refund popup
	Then I should see "Issue Refund - Confirm Details" popup
	And I should see Original Delivery Charge & Customer Refund & Total Customer Charge
	And I should see breakdown of Before and After Refund earnings
	And I should see Bungii Internal Note
	And I should see Bungii Driver Note
	When I select "Are you sure you want to proceed with refund request ?" checkbox
	And I click on "Process Refund" button on Issue Refund popup
	Then "We are processing your Refund Request. We will let you know once it has been processed successfully." is displayed
	When I click on "OK" button
	And I search the delivery of Customer and view it
	Then The "Issue Refund" button should not be displayed
	And The "Reattempt Payment" button should be displayed
	
  
  @regression
  Scenario: Verify Close, Reset and Go Back on Issue Refund Popup
	When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
	  | Bungii Time   | Customer Phone | Customer Name                  |
	  | NEXT_POSSIBLE | 9999999605     | Testcustomertywd_appleNewE Customer|
	And As a driver "Testdrivertywd_appledc_a_web TestdriverF" perform below action with respective "Solo Scheduled" Delivery
	  | driver1 state|
	  |Accepted |
	  | Enroute  |
	  | Arrived |
	  | Loading Item |
	  | Driving To Dropoff |
	  | Unloading Item |
	  | Bungii Completed |
	And I view the Deliveries list on the admin portal
	And I wait for 2 minutes
	And I search the delivery of Customer and view it
	When I click on "ISSUE REFUND" button
	And I enter "Customer Refund Amount" as "5.01" dollars
	When I update "Earnings" as "5.01" percentage
	When I enter "Bungii Internal Notes" as "Internal Note"
	When I enter "Notes" as "Driver Note"
	And I click on "Continue" button on Issue Refund popup
	And I click on "GO BACK" button
	Then The "Issue Refund" section should be displayed
	And I click on "RESET" button
	Then the values should be reverted to origional value
	And I click on "Close icon" button
	Then The "Issue Refund" section should not be displayed
  
@regression
  Scenario: Verify Complete Refund for manually ended solo scheduled bungii and partial payment for driver
	When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
	  | Bungii Time   | Customer Phone | Customer Name                  |
	  | NEXT_POSSIBLE | 9999999606     | Testcustomertywd_appleNewF Customer|
	And As a driver "Testdrivertywd_appledc_a_web TestdriverF" perform below action with respective "Solo Scheduled" Delivery
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
	And I search the delivery of Customer and view it
	When I click on "ISSUE REFUND" button
	Then The "Issue Refund" section should be displayed
	When I select "Complete Refund" radio button
	When I update "Earnings" as "10.00" dollars
	Then I should see Customer Refund Amount and Driver Earnings
	When I enter "Bungii Internal Notes" as "Internal Note"
	When I enter "Notes" as "Driver Note"
	And I click on "Continue" button on Issue Refund popup
	Then I should see "Issue Refund - Confirm Details" popup
	And I should see Original Delivery Charge & Customer Refund & Total Customer Charge
	And I should see breakdown of Before and After Refund earnings
	And I should see Bungii Internal Note
	When I select "Are you sure you want to proceed with refund request ?" checkbox
	And I click on "Process Refund" button on Issue Refund popup
	Then "We are processing your Refund Request. We will let you know once it has been processed successfully." is displayed
	When I click on "OK" button
	And I search the delivery of Customer and view it
	Then The "Issue Refund" button should not be displayed
	And The "Reattempt Payment" button should be displayed
  
  @regression
  Scenario: Verify Issue Refund button is not displayed for Customer Canceled Delivery
	  When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
		| Bungii Time   | Customer Phone | Customer Name                  |
		| NEXT_POSSIBLE | 9999999607     | Testcustomertywd_appleNewG Customer|
	  And I view the Scheduled Deliveries list on the admin portal
	  Then I should be able to see the respective bungii with the below status
		|  Status |
		|Searching Drivers|
	  When I cancel bungii as a customer "Testcustomertywd_appleNewG Customer" with phone number "9999999607"
	When I view the Deliveries list on the admin portal
	And I wait for 2 minutes
	And I search the delivery of Customer and view it
	  Then The "Issue Refund" button should not be displayed
  
  @regression
  Scenario: Verify Issue Refund button is not displayed for Admin Canceled Delivery
	When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
	  | Bungii Time   | Customer Phone | Customer Name                  |
	  | NEXT_POSSIBLE | 9999999608     | Testcustomertywd_appleNewH Customer|
	  And I view the Scheduled Deliveries list on the admin portal
	  Then I should be able to see the respective bungii with the below status
		|Status |
		|Searching Drivers|
	  When I click on "Edit" link beside scheduled bungii
	  And I click on "Cancel entire Bungii and notify driver(s)" radiobutton
	  And I enter cancellation fee and Comments
	  And I click on "Submit" button
	  Then The "Pick up has been successfully cancelled." message should be displayed
	  When I click on "Close" button
	When I view the Deliveries list on the admin portal
	And I wait for 2 minutes
	Then The Delivery List page should display the delivery in "Admin Canceled" state
	  And I search the delivery of Customer and view it
	  Then The "Issue Refund" button should not be displayed
  
  @regression
      Scenario: Verify Issue Refund button is not displayed for Driver Canceled Delivery
		When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
		  | Bungii Time   | Customer Phone | Customer Name                  |
		  | NEXT_POSSIBLE | 9999999609     | Testcustomertywd_appleNewI Customer|
		And As a driver "Testdrivertywd_appledc_a_web TestdriverF" perform below action with respective "Solo Scheduled" Delivery
		  | driver1 state|
		  |Accepted |
		  | Enroute  |
		When I cancel bungii as a driver "Testdrivertywd_appledc_a_web TestdriverF"
	    And I view the Deliveries list on the admin portal
	And I wait for 2 minutes
	And I search the delivery of Customer and view it
		Then The "Issue Refund" button should not be displayed
  
  @regression
  Scenario: Verify Complete Refund for Solo Ondemand Delivery and partial Driver payment
	When I request "Solo Ondemand" Bungii as a customer in "washingtondc" geofence
	  | Bungii Time   | Customer Phone | Customer Name                  |
	  | NEXT_POSSIBLE | 9999999990     | Testcustomertywd_appleNewJ Customer|
	And As a driver "Testdrivertywd_appledc_a_web TestdriverF" perform below action with respective "Solo Ondemand" Delivery
	  | driver1 state|
	  | Enroute  |
	  | Arrived |
	  | Loading Item |
	  | Driving To Dropoff |
	  | Unloading Item |
	  | Bungii Completed |
	And I view the Deliveries list on the admin portal
	And I wait for 2 minutes
	And I search the delivery of Customer and view it
	When I click on "ISSUE REFUND" button
	Then The "Issue Refund" section should be displayed
	When I select "Complete Refund" radio button
	When I update "Earnings" as "10.00" dollars
	Then I should see Customer Refund Amount and Driver Earnings
	When I enter "Bungii Internal Notes" as "Internal Note"
	When I enter "Notes" as "Driver Note"
	And I click on "Continue" button on Issue Refund popup
	Then I should see "Issue Refund - Confirm Details" popup
	And I should see Original Delivery Charge & Customer Refund & Total Customer Charge
	And I should see breakdown of Before and After Refund earnings
	And I should see Bungii Internal Note
	And I should see Bungii Driver Note
	When I select "Are you sure you want to proceed with refund request ?" checkbox
	And I click on "Process Refund" button on Issue Refund popup
	Then "We are processing your Refund Request. We will let you know once it has been processed successfully." is displayed
	When I click on "OK" button
	And I search the delivery of Customer and view it
	Then The "Issue Refund" button should not be displayed
	And The "Reattempt Payment" button should be displayed