@web
Feature: Admin_PartnerFirm

  Background:
    Given I am logged in as Admin

  @sanity
  @regression
      #test data created in base
  Scenario: Partner Firm Email Upon Driver acceptance and Remove Research- Duo Scheduled
    And I note the Trip Requested count of Customer "Krishna Hoderker"
    When I request "Duo Scheduled" Bungii as a customer in "washingtondc" geofence from a partner location
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 9284174823| Krishna Hoderker|
    And As a driver "Testdrivertywd_appledc_a_web TestdriverB" perform below action with respective "Duo Scheduled" trip
      | driver1 state|
      |Accepted |
    And As a driver "Testdrivertywd_appledc_a_web TestdriverB" perform below action with respective "Duo Scheduled" trip
      | driver1 state|
      |Accepted |
    Then Partner firm should receive "Bungii Delivery Pickup Scheduled" email
    When I click on "Edit" link beside scheduled bungii
    And I click on "Remove driver(s) and re-search" radiobutton
    And I select the first driver
    And I click on "Remove Driver" button
    And I click on "Research" button
    Then Pickup should be unassigned from the driver
    And As a driver "Testdrivertywd_appledc_a_web TestdriverF" perform below action with respective "Solo Scheduled" trip
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
      | NEXT_POSSIBLE | 9284000002 | Testcustomertywd_appleweb CustB|
    And As a driver "Testdrivertywd_appledc_a_web TestdriverB" perform below action with respective "Solo Scheduled" trip
      | driver1 state|
      |Accepted |
    Then Partner firm should receive "Bungii Delivery Pickup Scheduled" email
    When I click on "Edit" link beside scheduled bungii
    And I click on "Remove driver(s) and re-search" radiobutton
    And I select the first driver
    And I click on "Remove Driver" button
    And I click on "Research" button
    Then Pickup should be unassigned from the driver
    And As a driver "Testdrivertywd_appledc_a_web TestdriverF" perform below action with respective "Solo Scheduled" trip
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
      | NEXT_POSSIBLE | 9284000002 | Testcustomertywd_appleweb CustB|
    And As a driver "Testdrivertywd_appledc_a_web TestdriverB" perform below action with respective "Solo Ondemand" trip
      | driver1 state|
      |Accepted |
    Then Partner firm should receive "Bungii Delivery Pickup Scheduled" email

  @testing
  @sanity
  @regression
  @testPF
    #test data created in base
  Scenario: Partner Email When Cancel Scheduled Bungii As an Admin
    When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence from a partner location
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
    Then Partner firm should receive "Bungii Delivery Pickup Canceled" email





























  @testing
  @sanity
  @regression
    #test data created in base
  Scenario: Remove driver and Research As an Admin
    When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence from a partner location
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


