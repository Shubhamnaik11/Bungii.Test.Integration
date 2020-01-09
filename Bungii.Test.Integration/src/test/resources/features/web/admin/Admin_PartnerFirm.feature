@web
Feature: Admin_PartnerFirm

  Background:
    Given I am logged in as TestAdmin

  @sanity
  @regression
      #test data created in base
  Scenario: Partner Firm Email Upon Driver acceptance and Remove Research- Duo Scheduled
    When I request "Duo Scheduled" Bungii as a customer in "washingtondc" geofence from a partner location
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 7507800001 | Testcustomertywd_appleWashingtonE Sundar|
    And As a driver "Testdrivertywd_appledc_a_web SundarE1" perform below action with respective "Duo Scheduled" trip
      | driver1 state|
      |Accepted |
    And As a driver "Testdrivertywd_appledc_a_web SundarE2" perform below action with respective "Duo Scheduled" trip
      | driver1 state|
      |Accepted |
    Then Partner firm should receive "Bungii Delivery Pickup Scheduled" email
    When I click on "Edit" link beside scheduled bungii
    And I click on "Remove driver(s) and re-search" radiobutton
    And I select the first driver
    And I click on "Remove Driver" button
    And I click on "Research" button
    Then Pickup should be unassigned from the driver
    And As a driver "Testdrivertywd_appledc_a_web SundarE3" perform below action with respective "Solo Scheduled" trip
      | driver1 state|
      | Accepted  |
    Then Partner firm should receive "Bungii Delivery Pickup Updated" email

  @testing
  @sanity
  @regression
    #test data created in base
  Scenario: Partner Firm Email Upon Driver acceptance and Remove Research - Solo Scheduled
    When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence from a partner location
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 7507800002 | Testcustomertywd_appleWashingtonD Sundar|
    And As a driver "Testdrivertywd_appledc_a_web SundarD1" perform below action with respective "Solo Scheduled" trip
      | driver1 state|
      |Accepted |
    Then Partner firm should receive "Bungii Delivery Pickup Scheduled" email
    When I click on "Edit" link beside scheduled bungii
    And I click on "Remove driver(s) and re-search" radiobutton
    And I select the first driver
    And I click on "Remove Driver" button
    And I click on "Research" button
    Then Pickup should be unassigned from the driver
    And As a driver "Testdrivertywd_appledc_a_web SundarD2" perform below action with respective "Solo Scheduled" trip
      | driver1 state|
      | Accepted  |
    Then Partner firm should receive "Bungii Delivery Pickup Updated" email


  @testing
  @sanity
  @regression
    #test data created in base
  Scenario: Partner Firm Scheduled Email - Solo Ondemand
    When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence from a partner location
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 7507800003 | Testcustomertywd_appleWashingtonC Sundar|
    And As a driver "Testdrivertywd_appledc_a_web SundarC" perform below action with respective "Solo Ondemand" trip
      | driver1 state|
      |Accepted |
    Then Partner firm should receive "Bungii Delivery Pickup Scheduled" email

  @testing
  @sanity
  @regression
    #test data created in base
  Scenario: Partner Email When Cancel Scheduled Bungii As an Admin
    When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence from a partner location
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 7507800004 |Testcustomertywd_appleWashingtonB Sundar|
    And As a driver "Testdrivertywd_appledc_a_web SundarB" perform below action with respective "Solo Scheduled" trip
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
    Then Partner firm should receive "Bungii Delivery Pickup Canceled" email


  @testing
  @sanity
  @regression
  Scenario: Partner Firm Scheduled Email - Ondemand Bulk Trip
    When I click on "Business Users  > Bulk Trips" Menu
    And I select business user "Testcustomertywd_apple-Jd1"
    And I upload image and csv file associated with the "Ondemand" trip
    And I click on "Upload" button on "Bulk Trips" page
    When I click on "Confirm" button on "Bulk Trips" page
    Then the "Trips have been requested successfully." message is displayed
    And I note the Pickupref of trip
    When As a driver "Testdrivertywd_appledc_a_web TestdriverC" perform below action with respective "Solo Ondemand" trip
      | driver1 state|
      | Accepted  |
    Then Partner firm should receive "Bungii Delivery Pickup Scheduled" email


  @testing
  @sanity
  @regression

  Scenario: Partner Firm Scheduled Email - Solo Scheduled Bulk Trip
    When I click on "Business Users  > Bulk Trips" Menu
    And I select business user "Testcustomertywd_apple-Jd1"
    And I upload image and csv file associated with the "Solo Scheduled" trip
    And I click on "Upload" button on "Bulk Trips" page
    When I click on "Confirm" button on "Bulk Trips" page
    Then the "Trips have been requested successfully." message is displayed
    And I note the Pickupref of trip
    When As a driver "Testdrivertywd_appledc_a_web TestdriverC" perform below action with respective "Solo Scheduled" trip
      | driver1 state|
      | Accepted  |
    Then Partner firm should receive "Bungii Delivery Pickup Scheduled" email


  @testing
  @sanity
  @regression
  @testPF
  Scenario: Failed Trip Email - Ondemand Bulk Trip
    When I click on "Business Users  > Bulk Trips" Menu
    And I select business user "Testcustomertywd_apple-jd3"
    And I upload image and csv file associated with the "Ondemand" trip
    And I click on "Upload" button on "Bulk Trips" page
    When I click on "Confirm" button on "Bulk Trips" page
    Then the "Trips have been requested successfully." message is displayed
    And I note the Pickupref of trip
    When I ensure no driver accepts the trip
    Then Admin receives "Failed On-Demand Trips" trip email for "Driver Not Found" status



















