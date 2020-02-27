@web
Feature: Admin_Trips

  Background:
    Given I am logged in as Admin

  @sanity
  @regression
    #test data created in base
  Scenario: Verify Manually Ending Bungii As An Admin For Solo Scheduled Pickup
    When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 9284000002 | Testcustomertywd_appleweb CustB|
    And As a driver "Testdrivertywd_appledc_a_web TestdriverB" perform below action with respective "Solo Scheduled" trip
      | driver1 state|
      |Accepted |
      | Enroute  |
      | Arrived |
      | Loading Item |
    And I view the Live Trips list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Loading Items |
    When I view the trip details
    Then the Bungii details is displayed successfully
    And I click on "Manually End Bungii" link
    And Enter the End Date and Time
    And Click on "Calculate Cost" button
    Then the amount is calculated and shown to admin
    And Click on "Confirm" button
    And I view the Trips list on the admin portal
    Then The Trip List page should display the trip in "Payment Successful" state

  @sanity
  @regression
    #test data created in base
  Scenario: Verify Manually Ending Bungii As An Admin For Solo Ondemand Pickup
    When I request "Solo Ondemand" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 9284000003 | Testcustomertywd_appleweb CustC|
    And As a driver "Testdrivertywd_appledc_a_web TestdriverC" perform below action with respective "Solo Ondemand" trip
      | driver1 state|
       |Accepted |
        | Arrived |
        | Loading Item |
    And I view the Live Trips list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Loading Items |
    When I view the trip details
    Then the Bungii details is displayed successfully
    And I click on "Manually End Bungii" link
    And Enter the End Date and Time
    And Click on "Calculate Cost" button
    Then the amount is calculated and shown to admin
    And Click on "Confirm" button
    And I view the Trips list on the admin portal
    Then The Trip List page should display the trip in "Payment Successful" state

  @sanity
  @regression
  Scenario: Verify Cancellation of Scheduled Bungii As An Admin
    When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 9284000005 | Testcustomertywd_appleweb CustE|
    And As a driver "Testdrivertywd_appledc_a_web TestdriverF" perform below action with respective "Solo Scheduled" trip
      | driver1 state|
      | Accepted  |
    And I view the Scheduled Trips list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Scheduled |
      When I click on "Edit" link beside scheduled bungii
      And I click on "Cancel entire Bungii and notify driver(s)" radiobutton
      And I enter cancellation fee and Comments
      And I click on "Submit" button
    Then The "Pick up has been successfully cancelled." message should be displayed
    When I view the Trips list on the admin portal
    Then The Trip List page should display the trip in "Admin Cancelled" state

  @sanity
  @regression
    #test data created in base
    #changed driver name
    #First time promo code added
  Scenario: Verify Driver Removal Research and Cancel As An Admin For Solo Scheduled Pickup
    When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 9284000006 | Testcustomertywd_appleweb CustF|
    And As a driver "Testdrivertywd_appledc_a_web TestdriverE" perform below action with respective "Solo Scheduled" trip
      | driver1 state|
      | Accepted  |
    And I view the Scheduled Trips list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Scheduled |
    When I click on "Edit" link beside scheduled bungii
    And I click on "Remove driver(s) and re-search" radiobutton
    And I select the first driver
    And I click on "Remove Driver" button
    And I click on "Research" button
    Then Pickup should be unassigned from the driver
    And As a driver "Testdrivertywd_appledc_a_web TestdriverE" perform below action with respective "Solo Scheduled Researched" trip
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
    And I click on "Submit" button
    Then The "Pick up has been successfully cancelled." message should be displayed
    When I view the Trips list on the admin portal
    Then The Trip List page should display the trip in "Admin Cancelled" state
    And The first time promo code should get released

  @sanity
  @regression
    #test data created in base
    #changed to "Solo Ondemand" from "Solo Scheduled"
  Scenario: Verify Trips List Status Updation For Solo Ondemand Pickup
    When I request "Solo Ondemand" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 9284000004 | Testcustomertywd_appleweb CustD|
    And I view the Live Trips list on the admin portal
    Then I should be able to see the respective bungii with the below status
      | Status |
      | Processing Confirmation|
    And As a driver "Testdrivertywd_appledc_a_web TestdriverD" perform below action with respective "Solo Ondemand" trip
      | driver1 state|
      | Accepted |
    And I view the Live Trips list on the admin portal
    Then I should be able to see the respective bungii with the below status
      | Status |
      | Trip Started |
    And As a driver "Testdrivertywd_appledc_a_web TestdriverD" perform below action with respective "Solo Ondemand" trip
      | driver1 state|
      | Arrived |
    And I view the Live Trips list on the admin portal
    Then I should be able to see the respective bungii with the below status
      | Status |
      | Driver(s) Arrived |
    And As a driver "Testdrivertywd_appledc_a_web TestdriverD" perform below action with respective "Solo Ondemand" trip
      | driver1 state|
      | Loading Item |
    And I view the Live Trips list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Loading Items |
    And As a driver "Testdrivertywd_appledc_a_web TestdriverD" perform below action with respective "Solo Ondemand" trip
      | driver1 state|
      | Driving To Dropoff |
    And I view the Live Trips list on the admin portal
    Then I should be able to see the respective bungii with the below status
      | Status |
      | Driving To Dropoff |
    And As a driver "Testdrivertywd_appledc_a_web TestdriverD" perform below action with respective "Solo Ondemand" trip
      | driver1 state|
      | Unloading Item |
    And I view the Live Trips list on the admin portal
    Then I should be able to see the respective bungii with the below status
      | Status |
      | Unloading Items |
    And As a driver "Testdrivertywd_appledc_a_web TestdriverD" perform below action with respective "Solo Ondemand" trip
      | driver1 state|
      | Bungii Completed |
    And I view the Trips list on the admin portal
    Then The Trip List page should display the trip in "Payment Successful" state

  @sanity
  @regression
  @email
    @failed
    #test data created in base
  Scenario: Verify Trips List Status Updation For Solo Scheduled Pickup
    When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 9284000001 | Testcustomertywd_appleweb CustA|
    And I view the Scheduled Trips list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Searching Drivers|
    And As a driver "Testdrivertywd_appledc_a_web TestdriverA" perform below action with respective "Solo Scheduled" trip
      | driver1 state|
      | Accepted |
    And I view the Scheduled Trips list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Scheduled |
    And As a driver "Testdrivertywd_appledc_a_web TestdriverA" perform below action with respective "Solo Scheduled" trip
      | driver1 state|
      | Enroute |
    And I view the Live Trips list on the admin portal
    Then I should be able to see the respective bungii with the below status
      | Status |
      | Trip Started |
    And As a driver "Testdrivertywd_appledc_a_web TestdriverA" perform below action with respective "Solo Scheduled" trip
      | driver1 state|
      | Arrived |
    And I view the Live Trips list on the admin portal
    Then I should be able to see the respective bungii with the below status
      | Status |
      | Driver(s) Arrived |
    And As a driver "Testdrivertywd_appledc_a_web TestdriverA" perform below action with respective "Solo Scheduled" trip
      | driver1 state|
      | Loading Item |
    And I view the Live Trips list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Loading Items |
    And As a driver "Testdrivertywd_appledc_a_web TestdriverA" perform below action with respective "Solo Scheduled" trip
      | driver1 state|
         | Driving To Dropoff |
    And I view the Live Trips list on the admin portal
    Then I should be able to see the respective bungii with the below status
      | Status |
      | Driving To Dropoff |
    And As a driver "Testdrivertywd_appledc_a_web TestdriverA" perform below action with respective "Solo Scheduled" trip
      | driver1 state|
      | Unloading Item |
    And I view the Live Trips list on the admin portal
    Then I should be able to see the respective bungii with the below status
      | Status |
      | Unloading Items |
    And As a driver "Testdrivertywd_appledc_a_web TestdriverA" perform below action with respective "Solo Scheduled" trip
      | driver1 state|
      | Bungii Completed |
    And I view the Trips list on the admin portal
    Then The Trip List page should display the trip in "Payment Successful" state
    And Customer should receive "Your Bungii Receipt" email

  @sanity
  @regression
        #test data created in base (need to update driver geofence)
  Scenario: Verify Editing and Removal of driver from Duo Scheduled Trip Started By Non Controlled Driver
    When I request "duo" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 9999995001 | Testcustomertywd_appleweb CustZ|
    And I view the Scheduled Trips list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Searching Drivers|
    When As a driver "Testdrivertywd_appledc_a_john Smith" and "Testdrivertywd_appledc_a_jack Smith" perform below action with respective "Duo Scheduled" trip
      | driver1 state | driver2 state |
      | Accepted      | Accepted      |
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Scheduled|
    # Non-Control driver starts the trip
    When As a driver "Testdrivertywd_appledc_a_jack Smith" perform below action with respective "Duo" trip
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
  Scenario:  Verify Search On Trip List
    When I click on "Trips > Trips" Menu
    And I search by client name "Vishal"
    Then All the clients named "Vishal" should be displayed on the trip list grid

  @regression
  Scenario: Verify Filters On Trip List
    When I click on "Trips > Trips" Menu
    And I click on "Filter" icon on "Trips" Page
    Then All statuses except "Price Estimated" are selected
    And All types and categories are selected
    When I select filter "Statuses" as "Payment Unsuccessful"
    And I click on "Apply" button on "Trips" page
    Then the triplist grid shows the results by type "Payment Unsuccessful Status"
    When I select filter "Statuses" as "Driver Not Arrived"
    And I click on "Apply" button on "Trips" page
    Then the triplist grid shows the results by type "Driver Not Arrived Status"
    When I select filter "Statuses" as "Payment Successful"
    And I click on "Apply" button on "Trips" page
    Then the triplist grid shows the results by type "Payment Successful Status"
    When I select filter "Statuses" as "Customer Cancelled"
    And I click on "Apply" button on "Trips" page
    Then the triplist grid shows the results by type "Customer Cancelled Status"
    When I select filter "Statuses" as "Driver Cancelled"
    And I click on "Apply" button on "Trips" page
    Then the triplist grid shows the results by type "Driver Cancelled Status"
    When I select filter "Statuses" as "Admin Cancelled"
    And I click on "Apply" button on "Trips" page
    Then the triplist grid shows the results by type "Admin Cancelled Status"
    When I select filter "Statuses" as "Pickup with Error"
    And I click on "Apply" button on "Trips" page
    Then the triplist grid shows the results by type "Pickup with Error Status"
    When I select filter "Statuses" as "Price Estimated"
    And I click on "Apply" button on "Trips" page
    Then the triplist grid shows the results by type "Price Estimated Status"
    When I select filter "Statuses" as "Driver(s) Not Found"
    And I click on "Apply" button on "Trips" page
    Then the triplist grid shows the results by type "Driver(s) Not Found Status"
    When I select filter "Statuses" as "Driver Removed"
    And I click on "Apply" button on "Trips" page
    Then the triplist grid shows the results by type "Driver Removed Status"
    When I select filter "Statuses" as "Promoter Payment Pending"
    And I click on "Apply" button on "Trips" page
    Then the triplist grid shows the results by type "Promoter Payment Pending Status"
    When I select filter "Type" as "Solo"
    And I click on "Apply" button on "Trips" page
    Then the triplist grid shows the results by type "Solo Type"
    When I select filter "Type" as "Duo"
    And I click on "Apply" button on "Trips" page
    Then the triplist grid shows the results by type "Duo Type"
    When I select filter "Category" as "On-Demand"
    And I click on "Apply" button on "Trips" page
    Then the triplist grid shows the results by type "On-Demand Category"
    When I select filter "Category" as "Scheduled"
    And I click on "Apply" button on "Trips" page
    Then the triplist grid shows the results by type "Scheduled Category"


  @sanity
  @regression
  Scenario: Verify Driver Does Not receive On Demand requests If He Is Not Assigned To Geofence In Which His Current Location Is
    When I request "Solo Ondemand" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 9999995002 | Testcustomertywd_appleweb CustY|
    Then The driver "Testdrivertywd_appledc_a_web TestdriverY" should receive On Demand requests as he is assigned to "washingtondc" geofence
    When I cancel "On Demand" of customer "9999995002"
    And I request "Solo Ondemand" Bungii as a customer in "goa" geofence
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 9999995002 | Testcustomertywd_appleweb CustY|
    Then the driver "Testdrivertywd_appledc_a_web TestdriverY" should not receive On Demand requests as he is assigned NOT to "goa" geofence

  @regression
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
    @failed
      #test data created in base
  Scenario: Verify Trip Requested and Estimated Count Updation On Customer List For Duo Scheduled Trip
    And I note the Trip Requested count of Customer "Krishna Hoderker"
    When I request "Duo Scheduled" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 9284174823| Krishna Hoderker|
    And I view the Customer list on the admin portal
    Then I should be able to see the Trip Requested count incremented in Customers Grid
    When I view the customer details page of Customer "Krishna Hoderker"
    Then Trip should be listed in the grid