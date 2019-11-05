@web
Feature: Admin_Trips

  Background:
    Given I am logged in as Admin

  @sanity
  @regression
  Scenario: Customer List - Solo Scheduled Trip
    And I note the Trip Requested count of Customer "Kash Kriss"
    When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 8888888082| Kash Kriss  |
     And I view the Customer list on the admin portal
    Then I should be able to see the Trip Requested count incremented in Customers Grid
    When I view the customer details page of Customer "Kash Kriss"
    Then Trip should be listed in the grid
  @sanity
  @regression
  Scenario: Customer List - Duo Scheduled Trip
    And I note the Trip Requested count of Customer "Tammy Rock"
    When I request "Duo Scheduled" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 8888888082| Kash Kriss  |
    And I view the Customer list on the admin portal
    Then I should be able to see the Trip Requested count incremented in Customers Grid
    When I view the customer details page of Customer "Tammy Rock"
    Then Trip should be listed in the grid

  @sanity
  @regression
  Scenario: Trips List Statuses - Solo Scheduled
    When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 8888881021| Testcustomertywd_appleGreg Ganger|
    And I view the Scheduled Trips list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Searching Drivers|
    And As a driver "Testdrivertywd_appledc_a_port Jim" perform below action with respective "Solo Scheduled" trip
      | driver1 state|
      | Accepted |
    And I view the Scheduled Trips list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Scheduled |
    And As a driver "Testdrivertywd_appledc_a_port Jim" perform below action with respective "Solo Scheduled" trip
      | driver1 state|
      | Enroute |
    And I view the Live Trips list on the admin portal
    Then I should be able to see the respective bungii with the below status
      | Status |
      | Trip Started |
    And As a driver "Testdrivertywd_appledc_a_port Jim" perform below action with respective "Solo Scheduled" trip
      | driver1 state|
      | Arrived |
    And I view the Live Trips list on the admin portal
    Then I should be able to see the respective bungii with the below status
      | Status |
      | Driver(s) Arrived |
    And As a driver "Testdrivertywd_appledc_a_port Jim" perform below action with respective "Solo Scheduled" trip
      | driver1 state|
       | Loading Item |
    And I view the Live Trips list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Loading Items |
    And As a driver "Testdrivertywd_appledc_a_port Jim" perform below action with respective "Solo Scheduled" trip
      | driver1 state|
      | Driving To Dropoff |
    And I view the Live Trips list on the admin portal
    Then I should be able to see the respective bungii with the below status
      | Status |
      | Driving To Dropoff |
    And As a driver "Testdrivertywd_appledc_a_port Jim" perform below action with respective "Solo Scheduled" trip
      | driver1 state|
      | Unloading Item |
    And I view the Live Trips list on the admin portal
    Then I should be able to see the respective bungii with the below status
       | Status |
       | Unloading Items |
    And As a driver "Testdrivertywd_appledc_a_port Jim" perform below action with respective "Solo Scheduled" trip
      | driver1 state|
      | Bungii Completed |
    And I view the Trips list on the admin portal
    Then I should be able to see the respective bungii with the below status
       | Status |
     | Payment Successful |

  @sanity
  @regression
  Scenario: Manually End Bungii As an Admin - Solo Scheduled Pickup
    When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 8888881021| Testcustomertywd_appleGreg Ganger|
    And As a driver "Testdrivertywd_appledc_a_port Jim" perform below action with respective "Solo Scheduled" trip
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
  Scenario: Manually End Bungii As an Admin - Solo Ondemand Pickup
    When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 8888881021| Testcustomertywd_appleGreg Ganger|
    And As a driver "Testdrivertywd_appledc_a_port Jim" perform below action with respective "Solo Ondemand" trip
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
  Scenario: Trips List Statuses - Solo Ondemand
    When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 8888881021| Testcustomertywd_appleGreg Ganger|
    And I view the Live Trips list on the admin portal
    Then I should be able to see the respective bungii with the below status
      | Status |
      | Processing Confirmation|
    And As a driver "Testdrivertywd_appledc_a_port Jim" perform below action with respective "Solo Ondemand" trip
      | driver1 state|
      | Accepted |
    And I view the Live Trips list on the admin portal
    Then I should be able to see the respective bungii with the below status
      | Status |
      | Trip Started |
    And As a driver "Testdrivertywd_appledc_a_port Jim" perform below action with respective "Solo Ondemand" trip
      | driver1 state|
      | Arrived |
    And I view the Live Trips list on the admin portal
    Then I should be able to see the respective bungii with the below status
      | Status |
      | Driver(s) Arrived |
    And As a driver "Testdrivertywd_appledc_a_port Jim" perform below action with respective "Solo Ondemand" trip
      | driver1 state|
      | Loading Item |
    And I view the Live Trips list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Loading Items |
    And As a driver "Testdrivertywd_appledc_a_port Jim" perform below action with respective "Solo Ondemand" trip
      | driver1 state|
      | Driving To Dropoff |
    And I view the Live Trips list on the admin portal
    Then I should be able to see the respective bungii with the below status
      | Status |
      | Driving To Dropoff |
    And As a driver "Testdrivertywd_appledc_a_port Jim" perform below action with respective "Solo Ondemand" trip
      | driver1 state|
      | Unloading Item |
    And I view the Live Trips list on the admin portal
    Then I should be able to see the respective bungii with the below status
      | Status |
      | Unloading Items |
    And As a driver "Testdrivertywd_appledc_a_port Jim" perform below action with respective "Solo Ondemand" trip
      | driver1 state|
      | Bungii Completed |
    And I view the Trips list on the admin portal
    Then I should be able to see the respective bungii with the below status
      | Status |
      | Payment Successful |
  @sanity
  @regression
  Scenario: Cancel Scheduled Bungii As an Admin
    When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 8888881021| Testcustomertywd_appleGreg Ganger|
    And As a driver "Testdrivertywd_appledc_a_port Jim" perform below action with respective "Solo Scheduled" trip
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

  @testing
  @sanity
  @regression
  Scenario: Remove driver and Research As an Admin
    When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 8888881021| Testcustomertywd_appleGreg Ganger|
    And As a driver "Testdrivertywd_appledc_a_port Jim" perform below action with respective "Solo Scheduled" trip
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
    And As a driver "Testdrivertywd_appledc_a_port Jim" perform below action with respective "Solo Scheduled" trip
      | driver1 state|
      | Accepted  |


    # test