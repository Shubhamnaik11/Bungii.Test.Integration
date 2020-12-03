@web
Feature: Admin_OndemandTrips

  Background:
    Given I am logged in as Admin

  @sanity
  @regression
    @failed
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
    Then The Delivery List page should display the trip in "Payment Successful" state

  @sanity
  @regression
    @failed
    #test data created in base
    #changed to "Solo Ondemand" from "Solo Scheduled"
  Scenario: Verify Delivery List Status Updation For Solo Ondemand Pickup
    When I request "Solo Ondemand" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 9284000004 | Testcustomertywd_appleweb CustD|
    And I view the Live Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      | Status |
      | Assigning Driver(s)|
    And As a driver "Testdrivertywd_appledc_a_web TestdriverD" perform below action with respective "Solo Ondemand" Delivery
      | driver1 state|
      | Accepted |
    And I view the Live Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      | Status |
      | Trip Started |
    And As a driver "Testdrivertywd_appledc_a_web TestdriverD" perform below action with respective "Solo Ondemand" Delivery
      | driver1 state|
      | Arrived |
    And I view the Live Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      | Status |
      | Driver(s) Arrived |
    And As a driver "Testdrivertywd_appledc_a_web TestdriverD" perform below action with respective "Solo Ondemand" Delivery
      | driver1 state|
      | Loading Item |
    And I view the Live Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Loading Items |
    And As a driver "Testdrivertywd_appledc_a_web TestdriverD" perform below action with respective "Solo Ondemand" Delivery
      | driver1 state|
      | Driving To Dropoff |
    And I view the Live Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      | Status |
      | Driving To Dropoff |
    And As a driver "Testdrivertywd_appledc_a_web TestdriverD" perform below action with respective "Solo Ondemand" Delivery
      | driver1 state|
      | Unloading Item |
    And I view the Live Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      | Status |
      | Unloading Items |
    And As a driver "Testdrivertywd_appledc_a_web TestdriverD" perform below action with respective "Solo Ondemand" Delivery
      | driver1 state|
      | Bungii Completed |
    And I view the Deliveries list on the admin portal
    Then The Delivery List page should display the delivery in "Payment Successful" state
    
  @ready
    @creditcardprocessing
  Scenario: Verify Driver Does Not receive Ondemand requests If He Is Not Assigned To Geofence In Which His Current Location Is
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
  Scenario:  Verify Search On Trip List
    When I click on "Deliveries > All Deliveries" Menu
    And I search by client name "Vishal"
    Then All the clients named "Vishal" should be displayed on the delivery list grid

  @regression
  Scenario: Verify Filters On Trip List
    When I click on "Deliveries > All Deliveries" Menu
    And I click on "Filter" icon on "All Deliveries" Page
    Then All statuses except "Price Estimated" are selected
    And All types and categories are selected
    When I select filter "Statuses" as "Payment Unsuccessful"
    And I click on "Apply" button on "All Deliveries" page
    Then the triplist grid shows the results by type "Payment Unsuccessful Status"
    When I select filter "Statuses" as "Driver Not Arrived"
    And I click on "Apply" button on "All Deliveries" page
    Then the triplist grid shows the results by type "Driver Not Arrived Status"
    When I select filter "Statuses" as "Payment Successful"
    And I click on "Apply" button on "All Deliveries" page
    Then the triplist grid shows the results by type "Payment Successful Status"
    When I select filter "Statuses" as "Customer Cancelled"
    And I click on "Apply" button on "All Deliveries" page
    Then the triplist grid shows the results by type "Customer Cancelled Status"
    When I select filter "Statuses" as "Driver Cancelled"
    And I click on "Apply" button on "All Deliveries" page
    Then the triplist grid shows the results by type "Driver Cancelled Status"
    When I select filter "Statuses" as "Admin Cancelled"
    And I click on "Apply" button on "All Deliveries" page
    Then the triplist grid shows the results by type "Admin Cancelled Status"
    When I select filter "Statuses" as "Pickup with Error"
    And I click on "Apply" button on "All Deliveries" page
    Then the triplist grid shows the results by type "Pickup with Error Status"
    When I select filter "Statuses" as "Price Estimated"
    And I click on "Apply" button on "All Deliveries" page
    Then the triplist grid shows the results by type "Price Estimated Status"
    When I select filter "Statuses" as "Driver(s) Not Found"
    And I click on "Apply" button on "All Deliveries" page
    Then the triplist grid shows the results by type "Driver(s) Not Found Status"
    When I select filter "Statuses" as "Driver Removed"
    And I click on "Apply" button on "All Deliveries" page
    Then the triplist grid shows the results by type "Driver Removed Status"
    When I select filter "Statuses" as "Promoter Payment Pending"
    And I click on "Apply" button on "All Deliveries" page
    Then the triplist grid shows the results by type "Promoter Payment Pending Status"
    When I select filter "Type" as "Solo"
    And I click on "Apply" button on "All Deliveries" page
    Then the triplist grid shows the results by type "Solo Type"
    When I select filter "Type" as "Duo"
    And I click on "Apply" button on "All Deliveries" page
    Then the triplist grid shows the results by type "Duo Type"
    When I select filter "Category" as "On-Demand"
    And I click on "Apply" button on "All Deliveries" page
    Then the triplist grid shows the results by type "On-Demand Category"
    When I select filter "Category" as "Scheduled"
    And I click on "Apply" button on "All Deliveries" page
    Then the triplist grid shows the results by type "Scheduled Category"
