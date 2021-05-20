@web
@new
Feature: Admin_Accessorial_Charges
  
  Background:
	Given I am logged in as Admin
	
@regression
Scenario: Verify Accessorial Charges Can be added to Payment Successful Solo Scheduled Deliveries
When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
| Bungii Time   | Customer Phone | Customer Name |
| NEXT_POSSIBLE | 9999999221 | Testcustomertywd_appleNewMH Customer|
And As a driver "Testdrivertywd_appledc_a_drva Driver" perform below action with respective "Solo Scheduled" Delivery
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
Then I should see "Accessorial Charges" section displayed
When I add following accessorial charges and save it
|Amount|Fee Type         |Comment|
| 10   | Excess Wait Time| Charges due to excess waiting |
| 20.5   | Cancelation | Charges due to Cancelation |
| 25.65  | Mountainous | Charges due to mountainous reason |
| 100   | Other | Charges due to other reasons |
Then I should see following details in the Accessorial charges section
|Excess Wait Time|Cancelation	|Mountainous	| Other | Total |
| $10            |$20.5         |$25.65         |$100  |$156.15|
  And "accessorial_fee_amount" should show total amount in the triprequest table in Database
  
  @regression
  Scenario: Verify Accessorial Charges Field Validations - Blank
	When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
	  | Bungii Time   | Customer Phone | Customer Name |
	  | NEXT_POSSIBLE | 9999999222 | Testcustomertywd_appleNewMI Customer|
	And As a driver "Testdrivertywd_appledc_a_drvb Driver" perform below action with respective "Solo Scheduled" Delivery
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
	Then I should see "Accessorial Charges" section displayed
	And I should get following error for following accessorial charges fields values when saved
	  |Amount |Fee Type         |Comment         | Field | Message |
	  | Blank | Blank           | Blank          | Amount |Please enter an amount. |
	  | Blank | Blank           | This is Comment| Amount |Please enter an amount. |
	  | Blank | Excess Wait Time| Blank          | Amount |Please enter an amount. |
	  | Blank | Excess Wait Time| This is Comment| Amount |Please enter an amount. |
	  | 10    | Blank           | Blank          | Fee Type |Please select fee type. |
	  | 10    | Blank           | This is Comment| Fee Type |Please select fee type. |
	  | 10    | Excess Wait Time| Blank          | Comment |Please add a comment.   |
  
  
  @regression
  Scenario: Verify Accessorial Charges Field Validations - invalid Data
	When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
	  | Bungii Time   | Customer Phone | Customer Name |
	  | NEXT_POSSIBLE | 9999999223 | Testcustomertywd_appleNewMJ Customer|
	And As a driver "Testdrivertywd_appledc_a_drvc Driver" perform below action with respective "Solo Scheduled" Delivery
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
	Then I should see "Accessorial Charges" section displayed
	And I should get following error for following accessorial charges fields values when saved
	  |Amount          |Fee Type         |Comment                        | Field | Message |
	  | 100000000000   | Excess Wait Time| Charges due to excess waiting | Amount |Amount can contain at most 3 digits and 2 decimals.|
	  | 1000           | Excess Wait Time| Charges due to excess waiting | Amount |Amount can contain at most 3 digits and 2 decimals.|
	  | 10.5689        | Excess Wait Time| Charges due to excess waiting | Amount |Amount can contain at most 3 digits and 2 decimals.|
	  | -10            | Excess Wait Time| Charges due to excess waiting | Amount |Amount can contain at most 3 digits and 2 decimals.|
	  | -1.56          | Excess Wait Time| Charges due to excess waiting | Amount |Amount can contain at most 3 digits and 2 decimals.|
