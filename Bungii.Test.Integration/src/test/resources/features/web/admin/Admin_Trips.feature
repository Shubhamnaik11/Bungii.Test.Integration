@web
Feature: Admin_Trips

  Background:
    Given I am logged in as Admin
    
  @sanity
  @regression
    #test data created in base
    #changed driver name
    #First time promo code added
  Scenario: Verify Driver Removal Research and Cancel As An Admin For Solo Scheduled Pickup
    When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 9284000006 | Testcustomertywd_appleweb CustF|
    And As a driver "Testdrivertywd_appledc_a_web TestdriverE" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      | Accepted  |
    And I view the all Scheduled Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Scheduled |
    When I click on "Edit" link beside scheduled bungii
    And I click on "Remove driver(s) and re-search" radiobutton
    And I select the first driver
    And I click on "Remove Driver" button
    And I click on "Research" button
    Then Pickup should be unassigned from the driver
    And As a driver "Testdrivertywd_appledc_a_web TestdriverE" perform below action with respective "Solo Scheduled Researched" Delivery
      | driver1 state|
      | Accepted  |
    When I wait for 2 minutes
    And I click on "Close" icon
    Then I should be able to see the respective bungii with the below status
      | Status |
      | Scheduled |
    And I click on "Edit" link beside scheduled bungii
    And I click on "Cancel entire Bungii and notify driver(s)" radiobutton
    And I enter cancellation fee and Comments
    And I select "Outside of delivery scope" from the "Cancellation Reason" dropdown
    And I click on "Submit" button
    Then The "Pick up has been successfully canceled." message should be displayed
    When I view All Deliveries list on the admin portal
    Then The Delivery List page should display the delivery in "Admin Canceled" state
    And The first time promo code should get released

  @sanity
  @ready
    #mania needs to be whitelisted
    #test data created in base
  Scenario: Verify Trips List Status Updation For Solo Scheduled Pickup
    When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 9284000001 | Testcustomertywd_appleweb CustA|
   #Temperary Workaround for Today filter by commenting below steps and adding All filter steps
    #And I view the Scheduled Trips list on the admin portal
    And I wait for "2" mins
    And I view the Live Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Assigning Driver(s)|
    When I search the delivery of Customer
    Then I should see the delivery highlighted in "Blue"
    When I view the all Scheduled Deliveries list on the admin portal
    And I search the delivery of Customer
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Assigning Driver(s)|
    And The delivery should not be highlighted in "Blue" for "Scheduled Deliveries"
    And As a driver "Testdrivertywd_appledc_a_web TestdriverA" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      | Accepted |
   #Temperary Workaround for Today filter by commenting below steps and adding All filter steps
    #And I view the Scheduled Trips list on the admin portal
    And I view the all Scheduled Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Scheduled |
    And As a driver "Testdrivertywd_appledc_a_web TestdriverA" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      | Enroute |
    And I view the Live Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      | Status |
      | Trip Started |
    And The delivery should not be highlighted in "Blue" for "Live Deliveries"
    And As a driver "Testdrivertywd_appledc_a_web TestdriverA" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      | Arrived |
    And I view the Live Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      | Status |
      | Driver(s) Arrived |
    And As a driver "Testdrivertywd_appledc_a_web TestdriverA" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      | Loading Item |
    And I view the Live Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Loading Items |
    And As a driver "Testdrivertywd_appledc_a_web TestdriverA" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
         | Driving To Dropoff |
    And I view the Live Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      | Status |
      | Driving To Dropoff |
    And As a driver "Testdrivertywd_appledc_a_web TestdriverA" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      | Unloading Item |
    And I view the Live Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      | Status |
      | Unloading Items |
    And As a driver "Testdrivertywd_appledc_a_web TestdriverA" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      | Bungii Completed |
    And I view the Deliveries list on the admin portal
    Then The Delivery List page should display the delivery in "Payment Successful" state
    And I search the delivery of Customer
    And The delivery should not be highlighted in "Blue" for "All Deliveries"
    And Customer should receive "Your Bungii Receipt" email

  @sanity
  @regression
        #test data created in base (need to update driver geofence)
  Scenario: Verify Editing and Removal of driver from Duo Scheduled Trip Started By Non Controlled Driver
    When I request "duo" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 9999995001 | Testcustomertywd_appleweb CustZ|
   #Temperary Workaround for Today filter by commenting below steps and adding All filter steps
    #And I view the Scheduled Trips list on the admin portal
    And I view the all Scheduled Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Assigning Driver(s)|
    When As a driver "Testdrivertywd_appledc_a_john Smith" and "Testdrivertywd_appledc_a_jack Smith" perform below action with respective "Duo Scheduled" trip
      | driver1 state | driver2 state |
      | Accepted      | Accepted      |
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Scheduled|
    # Non-Control driver starts the trip
    When As a driver "Testdrivertywd_appledc_a_jack Smith" perform below action with respective "Duo" Delivery
      | driver1 state|
      | Enroute |
    When I click on "Edit" link beside scheduled bungii
    And I click on "Remove driver(s) and re-search" radiobutton
    And I remove non control driver "Testdrivertywd_appledc_a_jack Smith"
    Then The driver should get removed successfully
    And I should be able to see the respective bungii with the below status
      |  Status |
      | Driver Removed|

  @regression
    #Failed in Sprint 49
    #test data created in base
  Scenario: Verify Trip Requested and Estimated Count Updation On Customer List For Solo Scheduled Trip
    And I note the Trip Requested count of Customer "Jerome Seinfield"
    When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 9999992024| Jerome Seinfield|
    And I view the Customer list on the admin portal
    Then I should be able to see the Trip Requested count incremented in Customers Grid
    When I view the customer details page of Customer "Jerome Seinfield"
    Then Trip should be listed in the grid

  @regression
    #Failed in Sprint 49
      #test data created in base
  Scenario: Verify Trip Requested and Estimated Count Updation On Customer List For Duo Scheduled Trip
    And I note the Trip Requested count of Customer "Krishna Hoderker"
    When I request "Duo" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 9284174823| Krishna Hoderker|
    And I view the Customer list on the admin portal
    Then I should be able to see the Trip Requested count incremented in Customers Grid
    When I view the customer details page of Customer "Krishna Hoderker"
    Then Trip should be listed in the grid
  
  @regression
    #Failed in Sprint 49
  Scenario: Verify Driver Est. Earnings for for Customer Delivery
    When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 9284000002 | Testcustomertywd_appleweb CustB|
    And As a driver "Testdrivertywd_appledc_a_web TestdriverB" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      |Accepted |
      | Enroute  |
      | Arrived |
      | Loading Item |
      | Driving To Dropoff |
      | Unloading Item |
    And I view the Live Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Unloading Items |
    And I select the scheduled trip on live delivery
    Then I view the correct Driver Est. Earnings for geofence based pricing model
    And As a driver "Testdrivertywd_appledc_a_web TestdriverB" perform below action with respective "Solo Scheduled" Delivery
        | driver1 state|
        | Bungii Completed |
    And I wait for 2 minutes
    And I view All Deliveries list on the admin portal
    Then The Delivery List page should display the delivery in "Payment Successful" state
    And I select the scheduled trip on All Deliveries
    Then I view the correct Driver Earnings for geofence based pricing model
  
  
  @ready
 #stable
  Scenario: Verify Filters shows future deliveries in All deliveries page
	When I request "duo" Bungii as a customer in "washingtondc" geofence
	  | Bungii Time   | Customer Phone | Customer Name |
	  | 3_DAY_LATER | 9284174823       | Krishna Hoderker|
    And I view the all Scheduled Deliveries list on the admin portal
    And I search the delivery of Customer
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Assigning Driver(s)|
    When I change filter to "This Week" on Scheduled deliveries
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Assigning Driver(s)|
    When I change filter to "This Month" on Scheduled deliveries
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Assigning Driver(s)|
    And I click on "Edit" link beside scheduled bungii
    And I click on "Cancel entire Bungii and notify driver(s)" radiobutton
    And I enter cancellation fee and Comments
    And I select "Outside of delivery scope" from the "Cancellation Reason" dropdown
    And I click on "Submit" button
    Then The "Pick up has been successfully canceled." message should be displayed
    And I wait for "2" mins
	And I view All Deliveries list on the admin portal
    And I search the delivery of Customer
    Then The Delivery List page should display the delivery in "Admin Canceled" state
    When I change filter to "The Past Day" on All deliveries
    Then The Delivery List page should display the delivery in "Admin Canceled" state
    When I change filter to "The Past Week" on All deliveries
    Then The Delivery List page should display the delivery in "Admin Canceled" state
    When I change filter to "The Past 4 Weeks" on All deliveries
    Then The Delivery List page should display the delivery in "Admin Canceled" state
    When I change filter to "The Past 3 Months" on All deliveries
    Then The Delivery List page should display the delivery in "Admin Canceled" state
    When I change filter to "The Beginning of Time" on All deliveries
    Then The Delivery List page should display the delivery in "Admin Canceled" state
  
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
    Then The "Pick up has been successfully canceled." message should be displayed
    And I view the Deliveries list on the admin portal
    Then The Delivery List page should display the delivery in "Admin Canceled" state

 #This scenario is no longer valid as manually end bungii link is removed for CORE-3257. Can be reused when coding CORE-3257(Edit Delivery Status - Payment Complete)
#  @sanity
#  @regression
#    #stable
#    #test data created in base
#  Scenario: Verify Manually Ending Bungii As An Admin For Solo Scheduled Pickup
#    When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
#      | Bungii Time   | Customer Phone | Customer Name |
#      | NEXT_POSSIBLE | 9284000002 | Testcustomertywd_appleweb CustB|
#    And As a driver "Testdrivertywd_appledc_a_web TestdriverB" perform below action with respective "Solo Scheduled" Delivery
#      | driver1 state|
#      |Accepted |
#      | Enroute  |
#      | Arrived |
#      | Loading Item |
#    And I view the Live Deliveries list on the admin portal
#    Then I should be able to see the respective bungii with the below status
#      |  Status |
#      | Loading Items |
#    When I view the delivery details
#    Then the Bungii details is displayed successfully
#    And I click on "Manually End Bungii" link
#    And Enter the End Date and Time
#    And Click on "Calculate Cost" button
#    Then the amount is calculated and shown to admin
#    And Click on "Confirm" button
#    And I view the Deliveries list on the admin portal
#    Then The Delivery List page should display the delivery in "Payment Successful" state

 #CORE-2052 : To verify search happens when admin changes from solo to duo when trip was accepted by only 1 drive
  @ready
  Scenario: To verify search happens when admin changes from solo to duo when trip was accepted by only 1 driver
    When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence from a partner location
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 9999999362 | Testcustomertywd_appleWashG Shah|
    And As a driver "Testdrivertywd_appledc_a_web TestdriverE" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      | Accepted  |
    And I wait for 2 minutes
    And I view the all Scheduled Deliveries list on the admin portal
    And  I search the delivery using "Pickup Reference"
    Then I should be able to see the respective bungii with the below status
    |  Status |
    | Scheduled |
    When I click on "Edit" link beside scheduled bungii
    And I click on "Edit Trip Details" radiobutton
    And I change the delivery type from "Solo" to "Duo"
    And I click on "Verify" button on Edit Scheduled bungii popup
    And I click on "Save" button on Edit Scheduled bungii popup
    Then "Bungii Saved!" message should be displayed
    When I wait for 2 minutes
    And I get the new "pickup reference"
    And I view the all Scheduled Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      | Status           |
      | Assigning Driver(s)|
    And  I search the delivery using "Pickup Reference"
    When I click on "Edit" link beside scheduled bungii
    And I click on "Edit Trip Details" radiobutton
    And I click on "Add Driver" and add "Testdrivertywd_appledc_a_drvl WashingtonDC_l" driver
    And I click on "Verify" button on Edit Scheduled bungii popup
    And I click on "Save" button on Edit Scheduled bungii popup
    Then "Bungii Saved!" message should be displayed
    When I wait for 2 minutes
    And I view the all Scheduled Deliveries list on the admin portal
    And I get the latest "pickup Reference"
    And  I search the delivery using "Pickup Reference"
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Scheduled |


 #CORE-2052 : To verify research happens when admin changes from solo to duo and assigns only one driver
@ready
  Scenario: To verify research happens when admin changes from solo to duo and assigns only one driver
  When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence from a partner location
   | Bungii Time   | Customer Phone | Customer Name |
   | NEXT_POSSIBLE | 9999999358 | Testcustomertywd_appleWashC Shah|
  And I wait for 2 minutes
  And I view the all Scheduled Deliveries list on the admin portal
  And  I search the delivery using "Pickup Reference"
  Then I should be able to see the respective bungii with the below status
    | Status           |
    | Assigning Driver(s)|
  When I click on "Edit" link beside scheduled bungii
  And I click on "Edit Trip Details" radiobutton
  And I change the delivery type from "Solo" to "Duo"
  And I click on "Add Driver" and add "Testdrivertywd_appledc_a_web TestdriverB" driver
  And I click on "Verify" button on Edit Scheduled bungii popup
  And I click on "Save" button on Edit Scheduled bungii popup
  Then "Bungii Saved!" message should be displayed
  When I wait for 2 minutes
  And I get the new "pickup reference"
  And I view the all Scheduled Deliveries list on the admin portal
  Then I should be able to see the respective bungii with the below status
    | Status           |
    | Assigning Driver(s)|
  When I click on "Edit" link beside scheduled bungii
  And I click on "Remove driver(s) and re-search" radiobutton
  And I select the first driver
  And I click on "Remove Driver" button
  And I click on "Research" button
  When I wait for 2 minutes
  And I get the latest "pickup Reference"
  And I view the all Scheduled Deliveries list on the admin portal
  And  I search the delivery using "Pickup Reference"
  Then I should be able to see the respective bungii with the below status
    | Status           |
    | Assigning Driver(s)|

  #CORE-2052 : To verify research does not happen when admin changes from duo to solo when bungii was accepted by driver
  @ready
  Scenario: To verify research does not happen when admin changes from duo to solo when bungii was accepted by driver
    When I request "duo" Bungii as a customer in "washingtondc" geofence from a partner location
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 9999999355 | Testcustomertywd_appleWashA Shah|
    When As a driver "Testdrivertywd_appledc_a_web Sundarb" perform below action with respective "Duo Scheduled" Delivery
      | driver1 state|
      | Accepted |
    And I wait for 2 minutes
    And I view the all Scheduled Deliveries list on the admin portal
    And  I search the delivery using "Pickup Reference"
    Then I should be able to see the respective bungii with the below status
      | Status           |
      | Assigning Driver(s)|
    When I click on "Edit" link beside scheduled bungii
    And I click on "Edit Trip Details" radiobutton
    And I change the delivery type from "Duo" to "Solo"
    And I click on "Verify" button on Edit Scheduled bungii popup
    And I click on "Save" button on Edit Scheduled bungii popup
    Then "Bungii Saved!" message should be displayed
    When I wait for 2 minutes
    And I get the new "pickup reference"
    And I view the all Scheduled Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Scheduled |

  #CORE-2052 : To verify search happens when admin changes from duo to solo when no driver has accepted
  @ready
  Scenario: To verify search happens when admin changes from duo to solo when no driver has accepted
    When I request "duo" Bungii as a customer in "washingtondc" geofence from a partner location
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 9999999356 | Testcustomertywd_appleWashB Shah|
    And I wait for 2 minutes
    And I view the all Scheduled Deliveries list on the admin portal
    And  I search the delivery using "Pickup Reference"
    Then I should be able to see the respective bungii with the below status
      | Status           |
      | Assigning Driver(s)|
    When I click on "Edit" link beside scheduled bungii
    And I click on "Edit Trip Details" radiobutton
    And I change the delivery type from "Duo" to "Solo"
    And I click on "Verify" button on Edit Scheduled bungii popup
    And I click on "Save" button on Edit Scheduled bungii popup
    Then "Bungii Saved!" message should be displayed
    When I wait for 2 minutes
    And I get the new "pickup reference"
    And I view the all Scheduled Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
    |  Status |
    | Assigning Driver(s)|

  #CORE-3295:Verify that 'Assigning driver(s)' status with Loading icon is shown when it is searching for driver(s) on Schedule Deliveries screen
  @ready
  Scenario: Verify that 'Assigning driver(s)' status with Loading icon is shown when it is searching for driver(s) on Schedule Deliveries screen
    When I request "duo" Bungii as a customer in "washingtondc" geofence from a partner location
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 8877661088 | Testcustomertywd_appleMarkCK LutherCK|
    And I wait for 2 minutes
    And I view the all Scheduled Deliveries list on the admin portal
    And I click on the filter link and should see "Assigning Driver(s)" checkbox displayed
    And I click on "Apply" button on "All Deliveries" page
    When  I search the delivery using "Pickup Reference"
    Then The delivery should be in "Assigning Driver(s)" state
    Then I should be able to see the respective bungii with the below status
      | Status           |
      | Assigning Driver(s)|
    When As a driver "Testdrivertywd_appledc_a_web Sundarb" perform below action with respective "Duo Scheduled" Delivery
      | driver1 state|
      | Accepted |
    And I wait for 2 minutes
    And I view the all Scheduled Deliveries list on the admin portal
    When  I search the delivery using "Pickup Reference"
    Then The delivery should be in "Assigning Driver(s)" state
    And I view the Live Deliveries list on  admin portal
    When  I search the delivery using "Pickup Reference"
    Then The delivery should be in "Assigning Driver(s)" state
    And As a driver "Testdrivertywd_appledc_a_web TestdriverE" perform below action with respective "Duo Scheduled" Delivery
      | driver1 state|
      | Accepted  |
    And I wait for 2 minutes
    And I view the all Scheduled Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Scheduled |
    And I view the Live Deliveries list on  admin portal
    When  I search the delivery using "Pickup Reference"
    Then I should see the message "No deliveries found." displayed
    And I view the all Scheduled Deliveries list on the admin portal
    When  I search the delivery using "Pickup Reference"
    And I click on "Edit" link beside scheduled bungii
    When I click on "Cancel entire Bungii and notify driver(s)" radiobutton
    And I enter cancellation fee and Comments
    And I select "Duo: Driver not found - one driver" from the "Cancellation Reason" dropdown
    And I click on "Submit" button
    Then The "Pick up has been successfully canceled." message should be displayed
    When I click on "Close" button
    And I wait for 2 minutes
    And I view All Deliveries list on the admin portal
    And  I search the delivery using "Pickup Reference"
    And I click on the filter link and should see "No Driver(s) Found" checkbox displayed
    And I click on "Filter" icon on "All Deliveries" Page
    When I select filter "Statuses" as "Admin Canceled"
    And I click on "Apply" button on "All Deliveries" page
    And  I search the delivery using "Pickup Reference"
    Then The delivery should be in "Admin Canceled - No Driver(s) Found" state
    Then Revive button should be displayed beside the trip
    When I click on "Revive" button
    Then I should see "Are you sure you want to revive the trip?" message on popup with PickupId anad Pickup Origin
    When I click on "Confirm" button on Revival Popup
    And I wait for 2 minutes
    And I view the Live Deliveries list on  admin portal
    And  I search the delivery using "Pickup Reference"
    And I click on "Edit" button
    And I click on "Edit Trip Details" radiobutton
    And I edit the pickup address
    Then I change the pickup address to "New Country Lane"
    And I edit the drop off address
    Then I change the drop off address to "Bethesda Outdoor Pool"
    And I change the customer note to "New Note Added by Admin"
    And I change the delivery type from "Duo" to "Solo"
    And I click on "Verify" button on Edit Scheduled bungii popup
    And I click on "Save" button on Edit Scheduled bungii popup
    Then "Bungii Saved!" message should be displayed
    When I click on "Close" button
    And I wait for 2 minutes
    And I get the new pickup reference generated
    And  I search the delivery using "Pickup Reference"
    And I click on the "Notes & History" link beside scheduled bungii for "Schedule Deliveries"
    And I click on "History" button
    Then I should see the changes done by admin
      | Event                    |                                Old Value                            |   New Value  |
      | Pickup Address Change    |   	Western Avenue Street Chevy Chase Maryland United States 2081  | New Country Lane Hickory Ridge Columbia Maryland United States 21044   |
      | Dropoff Address Change   |   	Unnamed Road Street Street Washington United States 20015      | 6300 Hillandale Road Bethesda Maryland United States 20815    |
      | Duo To Solo              |   	DUO                                                            | SOLO                                                               |

  #CORE-3295:Verify stop searching should change the status to Assigning drivers without loading icon
  @ready
  Scenario: Verify stop searching should change the status to Assigning drivers without loading icon
    When I request "duo" Bungii as a customer in "washingtondc" geofence from a partner location
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 8877661089 | Testcustomertywd_appleMarkCL LutherCL|
    And I wait for 2 minutes
    And I view the all Scheduled Deliveries list on the admin portal
    When  I search the delivery using "Pickup Reference"
    Then The delivery should be in "Assigning Driver(s)" state
    Then I should be able to see the respective bungii with the below status
      | Status           |
      | Assigning Driver(s)|
    When I click on the "Delivery Details" button from the dropdown
    Then I stop searching driver
    And I wait for 2 minutes
    And I view the all Scheduled Deliveries list on the admin portal
    When  I search the delivery using "Pickup Reference"
    Then The delivery should be in "Assigning Driver(s) with no loader" state
    And As a driver "Testdrivertywd_appledc_a_web TestdriverE" perform below action with respective "Duo Scheduled" Delivery
      | driver1 state|
      | Accepted  |
    And I wait for 2 minutes
    And I view the Live Deliveries list on  admin portal
    When  I search the delivery using "Pickup Reference"
    And I click on "Edit" button
    And I select the first driver
    And I click on "Remove Driver" button
    And I click on "Research" button
    When I click on "Close" button
    And I wait for 2 minutes
    And I view the Live Deliveries list on  admin portal
    And I get the new pickup reference generated
    When  I search the delivery using "Pickup Reference"
    And I click on "Edit" button
    When I click on "Cancel entire Bungii and notify driver(s)" radiobutton
    And I enter cancellation fee and Comments
    And I select "Duo: Driver not found - both driver" from the "Cancellation Reason" dropdown
    And I click on "Submit" button
    Then The "Pick up has been successfully canceled." message should be displayed
    When I click on "Close" button
    And I wait for 2 minutes
    And I view All Deliveries list on the admin portal
    And  I search the delivery using "Pickup Reference"
    Then The delivery should be in "Admin Canceled - No Driver(s) Found" state