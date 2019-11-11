@web
Feature: Admin_Trips

  Background:
    Given I am logged in as Admin

  @sanity
  @regression
    #test data created in base
  Scenario: Customer List - Solo Scheduled Trip
    And I note the Trip Requested count of Customer "Testcustomertywd_applekrishna Hoderker"
    When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 9766209256 | Testcustomertywd_applekrishna Hoderker|
     And I view the Customer list on the admin portal
    Then I should be able to see the Trip Requested count incremented in Customers Grid
    When I view the customer details page of Customer "Testcustomertywd_applekrishna Hoderker"
    Then Trip should be listed in the grid

  @sanity
  @regression
      #test data created in base
  Scenario: Customer List - Duo Scheduled Trip
    And I note the Trip Requested count of Customer "Krishna Hoderker"
    When I request "Duo Scheduled" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 9284174823| Krishna Hoderker|
    And I view the Customer list on the admin portal
    Then I should be able to see the Trip Requested count incremented in Customers Grid
    When I view the customer details page of Customer "Krishna Hoderker"
    Then Trip should be listed in the grid

  @sanity
  @regression
    #test data created in base
  Scenario: Trips List Statuses - Solo Scheduled
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
    Then I should be able to see the respective bungii with the below status
       | Status |
     | Payment Successful |

  @sanity
  @regression
    #test data created in base
  Scenario: Manually End Bungii As an Admin - Solo Scheduled Pickup
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
    Then I should be able to see the respective bungii with the below status
      | Status |
      | Payment Successful |

  @sanity
  @regression
    #test data created in base
  Scenario: Manually End Bungii As an Admin - Solo Ondemand Pickup
    When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
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
    Then I should be able to see the respective bungii with the below status
      | Status |
      | Payment Successful |

  @sanity
  @regression
    #test data created in base
  Scenario: Trips List Statuses - Solo Ondemand
    When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
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
    Then I should be able to see the respective bungii with the below status
      | Status |
      | Payment Successful |

  @sanity
  @regression
    #test data created in base
  Scenario: Cancel Scheduled Bungii As an Admin
    When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 9284000005 | Testcustomertywd_appleweb CustE|
    And As a driver "Testdrivertywd_appledc_a_web TestdriverE" perform below action with respective "Solo Scheduled" trip
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
    Then I should be able to see the respective bungii with the below status
      | Status |
      | Admin Cancelled |

  @sanity
  @regression
    #test data created in base
  Scenario: Remove driver and Research As an Admin
    When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 9284000006 | Testcustomertywd_appleweb CustF|
    And As a driver "Testdrivertywd_appledc_a_web TestdriverF" perform below action with respective "Solo Scheduled" trip
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
    And As a driver "Testdrivertywd_appledc_a_web TestdriverF" perform below action with respective "Solo Scheduled" trip
      | driver1 state|
      | Accepted  |

