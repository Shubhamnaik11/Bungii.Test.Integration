@web
Feature: Admin_Trips

  Background:
    Given I am logged in as Admin

  # #this is example scenario for api test step.
    ##Need to ensure /handle customer and driver are of same geofence . or these can be parameterise too. Assumed valid user corresponds to kansas

#  @NotCOMPLETE
  Scenario: Customer List - Solo Scheduled Trip
    And I note the Trip Requested count of Customer "Kash Kriss"
    When I request Bungii as a customer
      | geofence | Bungii Type | Bungii Time   | Customer Phone | Customer Name |
      | washingtondc   | solo         | NEXT_POSSIBLE | 8888888082|Kash Kriss|
     And I view the Customer list on the admin portal
    Then I should be able to see the Trip Requested count incremented in Customers Grid
    When I view the customer details page of Customer "Kash Kriss"
    Then Trip should be listed in the grid

  @testing1
  Scenario: Customer List - Duo Scheduled Trip
    And I note the Trip Requested count of Customer "Tammy Rock"
    When I request Bungii as a customer
      | geofence | Bungii Type | Bungii Time   | Customer Phone | Customer Name |
      | washingtondc   | duo         | NEXT_POSSIBLE | 8888882023|Tammy Rock|
    And I view the Customer list on the admin portal
    Then I should be able to see the Trip Requested count incremented in Customers Grid
    When I view the customer details page of Customer "Tammy Rock"
    Then Trip should be listed in the grid


  Scenario: Trips List Statuses - Solo Scheduled
    When I request below Bungiis as a customer
     | geofence | Bungii Type | Bungii Time   | Customer Phone | Customer Name |
     | washingtondc   | solo         | NEXT_POSSIBLE | 9999998193| Gigi Gomes|
    And I view the Scheduled Trips list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Searching Drivers|
    And As a driver perform below action with respective trip
      | driver1 | driver1 state    | driver2 | driver2 state    | Bungii Type |
       | Brad Hilton   |Accepted | NA   | NA | solo         |
    And I view the Scheduled Trips list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Scheduled |
    When As a driver perform below action with respective trip
      | driver1 | driver1 state    | driver2 | driver2 state    | Bungii Type |
      | Brad Hilton   |Enroute | NA   | NA | solo         |
    And I view the Live Trips list on the admin portal
    Then I should be able to see the respective bungii with the below status
      | Status |
      | Trip Started |
    When As a driver perform below action with respective trip
      | driver1 | driver1 state    | driver2 | driver2 state    | Bungii Type |
      | Brad Hilton   | Arrived | NA   | NA | solo         |
    And I view the Live Trips list on the admin portal
    Then I should be able to see the respective bungii with the below status
      | Status |
      | Driver(s) Arrived |
    When As a driver perform below action with respective trip
      | driver1 | driver1 state    | driver2 | driver2 state    | Bungii Type |
      | Brad Hilton   | Loading Item | NA   | NA | solo         |
    And I view the Live Trips list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Loading Items |
    When As a driver perform below action with respective trip
      | driver1 | driver1 state    | driver2 | driver2 state    | Bungii Type |
      | Brad Hilton   | Driving To Dropoff | NA   | NA | solo         |
    And I view the Live Trips list on the admin portal
    Then I should be able to see the respective bungii with the below status
      | Status |
      | Driving To Dropoff |
    When As a driver perform below action with respective trip
      | driver1 | driver1 state    | driver2 | driver2 state    | Bungii Type |
      | Brad Hilton   | Unloading Item | NA   | NA | solo         |
    And I view the Live Trips list on the admin portal
    Then I should be able to see the respective bungii with the below status
       | Status |
       | Unloading Items |
    When As a driver perform below action with respective trip
      | driver1 | driver1 state    | driver2 | driver2 state    | Bungii Type |
      | Brad Hilton   | Bungii Completed | NA   | NA | solo         |
    And I view the Trips list on the admin portal
    Then I should be able to see the respective bungii with the below status
       | Status |
     | Payment Successful |


  Scenario: Manually End Bungii As an Admin - Solo Scheduled Pickup
    When I request below Bungiis as a customer
      | geofence | Bungii Type | Bungii Time   | Customer Phone | Customer Name |
      | washingtondc   | solo         | NEXT_POSSIBLE | 9999998193| Gigi Gomes|
    And As a driver perform below action with respective trip
      | driver1 | driver1 state    | driver2 | driver2 state    | Bungii Type |
      | Brad Hilton   |Accepted | NA   | NA | solo         |
      | Brad Hilton   |Enroute | NA   | NA | solo         |
      | Brad Hilton   | Arrived | NA   | NA | solo         |
      | Brad Hilton   | Loading Item | NA   | NA | solo         |
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


  Scenario: Manually End Bungii As an Admin - Solo Ondemand Pickup
    When I request below Bungiis as a customer
      | geofence | Bungii Type | Bungii Time   | Customer Phone | Customer Name |
      | washingtondc   | ONDEMAND | NEXT_POSSIBLE | 9999998193| Gigi Gomes|
    And As a driver perform below action with respective trip
      | driver1 | driver1 state    | driver2 | driver2 state    | Bungii Type |
      | Brad Hilton   |Accepted | NA   | NA | ONDEMAND         |
      | Brad Hilton   | Arrived | NA   | NA | ONDEMAND         |
      | Brad Hilton   | Loading Item | NA   | NA | ONDEMAND         |
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


  Scenario: Trips List Statuses - Solo Ondemand
    When I request below Bungiis as a customer
      | geofence | Bungii Type | Bungii Time   | Customer Phone | Customer Name |
      | washingtondc   | ONDEMAND         | NEXT_POSSIBLE | 9999998193| Gigi Gomes|
    And I view the Live Trips list on the admin portal
    Then I should be able to see the respective bungii with the below status
      | Status |
      | Processing Confirmation|
    And As a driver perform below action with respective trip
      | driver1 | driver1 state    | driver2 | driver2 state    | Bungii Type |
      | Brad Hilton   |Accepted | NA   | NA | ONDEMAND         |
    And I view the Live Trips list on the admin portal
    Then I should be able to see the respective bungii with the below status
      | Status |
      | Trip Started |
    When As a driver perform below action with respective trip
      | driver1 | driver1 state    | driver2 | driver2 state    | Bungii Type |
      | Brad Hilton   | Arrived | NA   | NA | ONDEMAND         |
    And I view the Live Trips list on the admin portal
    Then I should be able to see the respective bungii with the below status
      | Status |
      | Driver(s) Arrived |
    When As a driver perform below action with respective trip
      | driver1 | driver1 state    | driver2 | driver2 state    | Bungii Type |
      | Brad Hilton   | Loading Item | NA   | NA | ONDEMAND         |
    And I view the Live Trips list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Loading Items |
    When As a driver perform below action with respective trip
      | driver1 | driver1 state    | driver2 | driver2 state    | Bungii Type |
      | Brad Hilton   | Driving To Dropoff | NA   | NA | ONDEMAND         |
    And I view the Live Trips list on the admin portal
    Then I should be able to see the respective bungii with the below status
      | Status |
      | Driving To Dropoff |
    When As a driver perform below action with respective trip
      | driver1 | driver1 state    | driver2 | driver2 state    | Bungii Type |
      | Brad Hilton   | Unloading Item | NA   | NA | ONDEMAND         |
    And I view the Live Trips list on the admin portal
    Then I should be able to see the respective bungii with the below status
      | Status |
      | Unloading Items |
    When As a driver perform below action with respective trip
      | driver1 | driver1 state    | driver2 | driver2 state    | Bungii Type |
      | Brad Hilton   | Bungii Completed | NA   | NA | ONDEMAND         |
    And I view the Trips list on the admin portal
    Then I should be able to see the respective bungii with the below status
      | Status |
      | Payment Successful |

  @testing
  Scenario: Cancel Scheduled Bungii As an Admin
    When I request below Bungiis as a customer
      | geofence | Bungii Type | Bungii Time   | Customer Phone | Customer Name |
      | washingtondc   | solo         | NEXT_POSSIBLE | 9999998193| Gigi Gomes|
    And As a driver perform below action with respective trip
      | driver1 | driver1 state    | driver2 | driver2 state    | Bungii Type |
      | Brad Hilton   |Accepted | NA   | NA | solo         |
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


   #When I request Bungii
    #And I driver perfom this action with Bungii
    #  | driver1 | driver1 state    | driver2 | driver2 state    | Bungii Type |
    #  | valid   | bungii completed | valid   | bungii completed | duo         |
