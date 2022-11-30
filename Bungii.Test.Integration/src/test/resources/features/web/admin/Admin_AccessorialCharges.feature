@web
@new
Feature: Admin_Accessorial_Charges
  Background:
	Given I am logged in as Admin

@test
@regression
  #CORE-2447 : issue still exist in qaauto
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
| Amount   | Fee Type         | Comment                           | Driver Cut |
|  10      | Excess Wait Time | Charges due to Excess wait        | 2          |
|   20.5    | Cancelation      | Charges due to Cancelation        | 4.5        |
|  25.65   | Mountainous      | Charges due to mountainous reason | 10.0       |
|  100     | Other            | Charges due to other reasons      | 20         |
Then I should see the following fee type displayed
| Fee Type         |
| Excess Wait Time |
| Cancelation      |
| Mountainous      |
| Other            |
And I should see following details in the Accessorial charges section
| Excess Wait Time | Cancelation | Mountainous | Other | Total   |
| $10              | $20.5       | $25.65      | $100  | $156.15 |
And I click on the Accessorial Charges links and I should see the Drivers cut displayed
| Fee Type         | Driver Cut |
| Excess Wait Time | 2          |
| Cancelation      | 4.5        |
| Mountainious     | 10         |
| Other            | 20         |
 And "accessorial_fee_amount" should show total amount in the triprequest table in Database
 And "business_notes" should show comment without quotes in the trippaymentdetails table in Database
 And I wait for 2 minutes
 And I should see the following fee type displayed in the Report Database
| Fee Type         |
| Excess Wait Time |
| Cancelation      |
| Mountainous      |
| Other            |


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
	  |Amount |Driver Cut|Fee Type         |Comment         | Field | Message |
	  | Blank |     Blank| Blank           | Blank          | Fee Type |Please select fee type. |
	  | Blank |   Blank	|Blank           | This is Comment| Fee Type |Please select fee type. |
	  | Blank |    Blank|Excess Wait Time| Blank          | Amount |Please enter amount. |
	  | Blank |    Blank|Excess Wait Time| This is Comment| Amount |Please enter amount. |
	  | 10	  |    Blank|Excess Wait Time| Blank          | Driver Amount |Please enter driver cut. |
	  | 10	  |    Blank|Excess Wait Time| This is Comment| Driver Amount |Please enter driver cut. |
	  | 10    |         2|Blank           | Blank          | Fee Type |Please select fee type. |
	  | 10    |         2|Blank           | This is Comment| Fee Type |Please select fee type. |
	  | 10    |         2|Excess Wait Time| Blank          | Comment  |Please add a comment.|
	  | 10   |          2|Excess Wait Time| Accessorial charges Comments: Accessorial charges comment having more than 500 characters in Excess Wait Time field column entered to identify whether it causes issues like "CORE-2446 SPRINT43:: QA environment:: Saving Comments of 500 characters for accessorial fees gives Application Error" Please note that If data gets validation message  without any application error then it means that the above issue no longer exists and it is working as expected. !!! Accessorial charges Comments Ends, Thank you!!!!|Comment|Comment exceeds 500 characters.|
  
  
@regression
Scenario: Verify Accessorial Charges Field Validations - invalid Data
When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
  | Bungii Time   | Customer Phone | Customer Name                        |
  | NEXT_POSSIBLE | 9999999223     | Testcustomertywd_appleNewMJ Customer |
 And As a driver "Testdrivertywd_appledc_a_drvc Driver" perform below action with respective "Solo Scheduled" Delivery
| driver1 state      |
| Accepted           |
| Enroute            |
| Arrived            |
| Loading Item       |
| Driving To Dropoff |
| Unloading Item     |
| Bungii Completed   |
And I wait for 2 minutes
And I view the Deliveries list on the admin portal
And I search the delivery of Customer and view it
Then I should see "Accessorial Charges" section displayed
And I should get following error for following accessorial charges fields values when saved
| Amount       | Driver Cut | Fee Type         | Comment                       | Field  | Message                                             |
|100000000000  | 1          | Excess Wait Time | Charges due to excess waiting | Amount | Amount can contain at most 3 digits and 2 decimals. |
| 1000         | 32         | Excess Wait Time | Charges due to excess waiting | Amount | Amount can contain at most 3 digits and 2 decimals. |
| 10.5689      | -100       | Excess Wait Time | Charges due to excess waiting | Amount | Amount can contain at most 3 digits and 2 decimals. |
| -10          | Blank      | Excess Wait Time | Charges due to excess waiting | Amount | Amount can contain at most 3 digits and 2 decimals. |
| -1.56        | 231        | Excess Wait Time | Charges due to excess waiting | Amount | Amount can contain at most 3 digits and 2 decimals. |

#Core 2968 -To verify that admin can add accessorial fees to driver cancelled partner delivery
@regression
Scenario: To verify the revive trip when accessorial charges are added
When I request Partner Portal "SOLO" Trip for "MRFM" partner
|Geofence| Bungii Time   | Customer Phone | Customer Name |
|Kansas  | NEXT_POSSIBLE | 9999999228     | Testcustomertywd_appleNewMO Customer|
And As a driver "Testdrivertywd_appleks_a_drvae Kansas_ae" perform below action with respective "Solo Scheduled" partner portal trip
| driver1 state |
| Accepted      |
| Enroute      |
And I wait for 1 minutes
And As a driver "Testdrivertywd_appleks_a_drvae Kansas_ae" perform below action with respective "Solo Scheduled" partner portal trip
| driver1 state |
| Driver Canceled |
#	Core-4330 Verify terms and privacy policy is displayed on login page
And I login to driver portal on a new tab with driver phone number "9049840210"
And I click on "Driver Details" link to get the total driver earnings value screen and navigate back to admin portal
And I wait for 2 minutes
And I view the Deliveries list on the admin portal
And I should see field name as partner on delivery listing screen
When  I search the delivery using "Pickup Reference"
And I click on the "Delivery details" link beside scheduled bungii for "Completed Deliveries"
And I should see field name as partner on delivery detail screen
Then I should see "Accessorial Charges" section displayed
When I add following accessorial charges and save it
| Amount   | Fee Type         | Comment                           | Driver Cut |
|  10      | Excess Wait Time | Charges due to Excess wait        | 2          |
|   20.5   | Cancelation      | Charges due to Cancelation        | 4.5        |
|  25.65   | Mountainous      | Charges due to mountainous reason | 10       |
|  100     | Other            | Charges due to other reasons      | 20         |
And I should see following details in the Accessorial charges section
| Excess Wait Time | Cancelation | Mountainous | Other | Total   |
| $10              | $20.5       | $25.65      | $100  | $156.15 |
And I view the Deliveries list on the admin portal
When  I search the delivery using "Pickup Reference"
Then Revive button should be displayed beside the trip
When I click on "Revive" button
Then I should see "Are you sure you want to revive the trip?" message on popup with PickupId anad Pickup Origin
When I click on "Confirm" button on Revival Popup
And I wait for 2 minutes
And I view the all Scheduled Deliveries list on the admin portal
When  I search the delivery using "Pickup Reference"
#Core-3295:Verify status is Assigning driver for driver cancelled trip which was revived after adding Accessorial charges
Then The delivery should be in "Assigning Driver(s) with no loader" state
And I click on the "Delivery details" link beside scheduled bungii for "Completed Deliveries"
Then the Accessorial Charges should not be displayed
And I view the Deliveries list on the admin portal
And I use the old pickup reference to search the driver cancelled trip
And I click on the "Delivery details" link beside scheduled bungii for "Completed Deliveries"
Then I should see "Accessorial Charges" section displayed
And I should see following details in the Accessorial charges section
| Excess Wait Time | Cancelation | Mountainous | Other | Total   |
| $10              | $20.5       | $25.65      | $100  | $156.15 |
And I click on the Accessorial Charges links and I should see the Drivers cut displayed
| Fee Type         | Driver Cut |
| Excess Wait Time | 2          |
| Cancelation      | 4.5        |
| Mountainious     | 10         |
| Other            | 20         |
And I login to driver portal on a new tab with driver phone number "9049840210"
Then The accessorial charges cut should be displayed in total earnings

#CORE-3381 : To verify that admin can add accessorial charges for partner canceled deliveries after revival
@ready
Scenario: To verify that admin can add accessorial charges for partner canceled deliveries after revival
   When I request Partner Portal "SOLO" Trip for "MRFM" partner
   |Geofence| Bungii Time   | Customer Phone | Customer Name |
   |Kansas  | NEXT_POSSIBLE | 8877661064     | Testcustomertywd_BppleMarkBM LutherBM|
	Given I navigate to "Partner" portal configured for "normal" URL
	And I enter "valid" password on Partner Portal
	And I click "SIGN IN" button on Partner Portal
	Then I should "be logged in"
	And I click "Track Deliveries" button on Partner Portal
	And I click on the delivery based on customer name
	And I click "Cancel Delivery link" button on Partner Portal
	And I click "Cancel Delivery" button on Partner Portal
	Then I click "OK" button on Partner Portal
    And I am logged in as Admin
	And I wait for 2 minutes
	And I view All Deliveries list on the admin portal
	And I search the delivery using "Pickup Reference"
	Then The "All Deliveries" should be in "Partner Canceled" state
	Then Revive button should be displayed beside the trip
	When I click on "Revive" button
	Then I should see "Are you sure you want to revive the trip?" message on popup with PickupId anad Pickup Origin
	When I click on "Confirm" button on Revival Popup
	And I wait for 2 minutes
	And I view the all Scheduled Deliveries list on the admin portal
	And  I search the delivery using "Pickup Reference"
	Then I should be able to see the respective bungii with the below status
		|  Status |
		| Assigning Driver(s)|
	And I view the Deliveries list on the admin portal
	And I search the delivery using old pickup reference
	And I click on the "Delivery details" link beside scheduled bungii for "Completed Deliveries"
	Then I should see "Accessorial Charges" section displayed
	When I add following accessorial charges and save it
		| Amount   | Fee Type         | Comment                           |
		|  10      | Excess Wait Time | Charges due to Excess wait        |
		|   20.5    | Cancelation      | Charges due to Cancelation        |
		|  25.65   | Mountainous      | Charges due to mountainous reason |
		|  100     | Other            | Charges due to other reasons      |
	And I should see following details in the Accessorial charges section
		| Excess Wait Time | Cancelation | Mountainous | Other | Total   |
		| $10            | $20.5       | $25.65      | $100  | $156.15 |
	And I set the cancelled delivery pickup reference as recent pickup reference to verify data in db
	Then The Below accessorial charges should be present in the db
		| Excess Wait Time | Cancelation | Mountainous | Other |
		| 10.00            | 20.50      | 25.65        | 100.00 |
