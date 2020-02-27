@web
Feature: Admin_PartnerFirm

  Background:
    Given I am logged in as TestAdmin


  @regression
  @email
  @failed
  Scenario: Verify Failed Trip Email - Ondemand Trip - No driver accepts
    When I request "Solo Ondemand" Bungii as a customer in "washingtondc" geofence from a partner location
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 9999999361 | Testcustomertywd_appleWashF Shah|
    And I note the Pickupref of trip
    When I ensure no driver accepts the trip
    Then Admin receives "Failed Ondemand Trips" trip email for "No Driver(s) Accepted" status

  @sanity
  @regression
  @email
  @failed
  Scenario: Verify Partner Firm Scheduled Email - Ondemand Bulk Trip
    When I click on "Business Users  > Bulk Trips" Menu
    And I select business user "Testcustomertywd_apple-Jd1"
    And I upload image and csv file associated with the "Ondemand" trip
    And I click on "Upload" button on "Bulk Trips" page
    When I click on "Confirm" button on "Bulk Trips" page
    Then the "Trips have been requested successfully." message is displayed
    And I note the Pickupref of trip
    When As a driver "Testdrivertywd_appledc_a_web Sundarg" perform below action with respective "Solo Ondemand" trip
      | driver1 state|
      | Accepted  |
    Then Partner firm should receive "Bungii Delivery Pickup Scheduled" email
    When I cancel bungii as a driver "Testdrivertywd_appledc_a_web Sundarg"
    Then Partner firm should not receive "Bungii Delivery Pickup Canceled" email

  @regression
  @email
    @failed
  Scenario: Verify Partner Firm Scheduled Email - Solo Ondemand
    When I request "Solo Ondemand" Bungii as a customer in "washingtondc" geofence from a partner location
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 9999999359 | Testcustomertywd_appleWashD Shah|
    And As a driver "Testdrivertywd_appledc_a_web Sundarm" perform below action with respective "Solo Ondemand" trip
      | driver1 state|
      |Accepted |
    Then Partner firm should receive "Bungii Delivery Pickup Scheduled" email
    When I cancel bungii as a driver "Testdrivertywd_appledc_a_web Sundarm"
    Then Partner firm should receive "Bungii Delivery Pickup Canceled" email
    And Admin receives "Failed Scheduled Trips" trip email for "Driver Cancelled" status

  @sanity
  @regression
  @email
  @failed
      #test data created in base
  Scenario: Verify Partner Firm Email Upon Driver Acceptance And Removal Research - Duo Scheduled
    When I request "Duo Scheduled" Bungii as a customer in "washingtondc" geofence from a partner location
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 9999999355 | Testcustomertywd_appleWashA Shah|
    And As a driver "Testdrivertywd_appledc_a_web Sundara" perform below action with respective "Duo Scheduled" trip
      | driver1 state|
      |Accepted |
    And As a driver "Testdrivertywd_appledc_a_web Sundarb" perform below action with respective "Duo Scheduled" trip
      | driver1 state|
      |Accepted |
    Then Partner firm should receive "Bungii Delivery Pickup Scheduled" email
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
    And As a driver "Testdrivertywd_appledc_a_web Sundarc" perform below action with respective "Solo Scheduled" trip
      | driver1 state|
      | Accepted  |
    Then Partner firm should receive "Bungii Delivery Pickup Updated" email

  @sanity
  @regression
  @email
  @failed
      #test data created in base
  Scenario: Verify Partner Firm Cancellation Email - Duo Scheduled
    When I request "Duo Scheduled" Bungii as a customer in "washingtondc" geofence from a partner location
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 9999999356 | Testcustomertywd_appleWashB Shah|
    And As a driver "Testdrivertywd_appledc_a_web Sundard" perform below action with respective "Duo Scheduled" trip
      | driver1 state|
      |Accepted |
    And As a driver "Testdrivertywd_appledc_a_web Sundare" perform below action with respective "Duo Scheduled" trip
      | driver1 state|
      |Accepted |
    Then Partner firm should receive "Bungii Delivery Pickup Scheduled" email
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
    And Partner firm should receive "Bungii Delivery Pickup Canceled" email
    And Admin receives "Failed On-Demand Trips" trip email for "Admin Cancelled" status


  @regression
  @email
    @failed
    #test data created in base
  Scenario: Verify Partner Firm Email Upon Driver Acceptance And Remove Research - Solo Scheduled
    When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence from a partner location
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 9999999358 | Testcustomertywd_appleWashC Shah|
    And As a driver "Testdrivertywd_appledc_a_web Sundarg" perform below action with respective "Solo Scheduled" trip
      | driver1 state|
      |Accepted |
    Then Partner firm should receive "Bungii Delivery Pickup Scheduled" email
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
    And As a driver "Testdrivertywd_appledc_a_web Sundarh" perform below action with respective "Solo Scheduled" trip
      | driver1 state|
      | Accepted  |
    Then Partner firm should receive "Bungii Delivery Pickup Updated" email

  @regression
  @email
    @failed
    #test data created in base
  Scenario: Verify Partner Email When Cancel Scheduled Bungii As An Admin
    When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence from a partner location
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 9999999360 | Testcustomertywd_appleWashE Shah|
    And As a driver "Testdrivertywd_appledc_a_web Sundari" perform below action with respective "Solo Scheduled" trip
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
    And Partner firm should receive "Bungii Delivery Pickup Canceled" email
    And Admin receives "Failed On-Demand Trips" trip email for "Admin Cancelled" status


  @regression
  @email
  @failed
    #Create driver in base
  Scenario: Verify Partner Firm Scheduled Email - Solo Scheduled Bulk Trip
    When I click on "Business Users  > Bulk Trips" Menu
    And I select business user "Testcustomertywd_apple-Jd1"
    And I upload image and csv file associated with the "Solo Scheduled" trip
    And I click on "Upload" button on "Bulk Trips" page
    When I click on "Confirm" button on "Bulk Trips" page
    Then the "Trips have been requested successfully." message is displayed
    And I note the Pickupref of trip
    When As a driver "Testdrivertywd_appledc_a_web Sundark" perform below action with respective "Solo Scheduled" trip
      | driver1 state|
      | Accepted  |
    Then Partner firm should receive "Bungii Delivery Pickup Scheduled" email
     ##################
    And I view the Scheduled Trips list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Scheduled |
    When I click on "Edit" link beside scheduled bungii
    And I click on "Edit Trip Details" radiobutton
    And I update the Scheduled date of the trip by 15 minutes
    And I remove driver "Testdrivertywd_appledc_a_web Sundark" and add the new driver "Testdrivertywd_appledc_a_web Sundarj"
    And I click on "Verify" button on Edit Scheduled bungii popup
    Then Tick mark should be displayed beside driver and scheduled date
    When i click on "Save" button on Edit Scheduled bungii popup
    Then "Bungii Saved" message should be displayed
    Then Partner firm should receive "Bungii Delivery Pickup Updated" email
    #################
    When I cancel bungii as a customer "Testcustomertywd_apple-Jd1" with phone number "9999794897"
    Then Partner firm should receive "Bungii Delivery Pickup Canceled" email

  @regression
  @email
  @failed
    #test data created in base
  Scenario: Verify Partner Firm Email For Long Stacked Bungii - Solo Scheduled
    When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence from a partner location
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 9999999362 | Testcustomertywd_appleWashG Shah|
    And As a driver "Testdrivertywd_appledc_a_web Sundarl" perform below action with respective "Solo Scheduled" trip
      | driver1 state|
       |Accepted |
      | Enroute  |
      | Arrived |
      | Loading Item |
    Then Partner firm should receive "Bungii Delivery Pickup Scheduled" email
    When I request "Solo Ondemand" Bungii as a customer in "washingtondc" geofence from a partner location
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 9999999363 | Testcustomertywd_appleWashI Shah|
    And As a driver "Testdrivertywd_appledc_a_web Sundarl" perform below action with respective "Solo Ondemand" trip
      | driver1 state|
      |Stacked Pickup Accepted |
    Then Partner firm should receive "Bungii Delivery Pickup Scheduled" email
    When I cancel bungii as a customer "Testcustomertywd_appleWashI Shah" with phone number "9999999363"
    Then Partner firm should not receive "Bungii Delivery Pickup Canceled" email

  @regression
  @email
  @failed
    #test data created in base
  Scenario: Verify Partner Firm Email For Short Stacked Bungii - Solo Scheduled
    When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence from a partner location
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 9999999357 | Testcustomertywd_appleWashJ Shah|
    And As a driver "Testdrivertywd_appledc_a_web Sundarm" perform below action with respective "Solo Scheduled" trip
      | driver1 state|
      | Accepted |
      | Enroute  |
      | Arrived |
      | Loading Item |
      | Driving To Dropoff |
    Then Partner firm should receive "Bungii Delivery Pickup Scheduled" email
    When I request "Solo Ondemand" Bungii as a customer in "washingtondc" geofence from a partner location
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 9999999364 | Testcustomertywd_appleWashK Shah|
    And As a driver "Testdrivertywd_appledc_a_web Sundarm" perform below action with respective "Solo Ondemand" trip
      | driver1 state|
      |Stacked Pickup Accepted |
    Then Partner firm should receive "Bungii Delivery Pickup Scheduled" email
    When I cancel bungii as a customer "Testcustomertywd_appleWashK Shah" with phone number "9999999364"
    Then Partner firm should not receive "Bungii Delivery Pickup Canceled" email

  @sanity
  @regression
  @failed
    #test data created in base
    #changed driver name
  Scenario: Verify Partner Firm Driver Removal Research And Cancel As An Admin
    When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence from a partner location
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 9766209256 | Testcustomertywd_applekrishna Hoderker|
    And As a driver "Testdrivertywd_appledc_a_web Sundarn" perform below action with respective "Solo Scheduled" trip
      | driver1 state|
      | Accepted  |
    And I view the Scheduled Trips list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Scheduled |
    And Partner firm should receive "Bungii Delivery Pickup Scheduled" email
    When I click on "Edit" link beside scheduled bungii
    And I click on "Remove driver(s) and re-search" radiobutton
    And I select the first driver
    And I click on "Remove Driver" button
    And I click on "Research" button
    Then Pickup should be unassigned from the driver
    And As a driver "Testdrivertywd_appledc_a_web Sundarn" perform below action with respective "Solo Scheduled Researched" trip
      | driver1 state|
      | Accepted  |
    When I wait for 2 minutes
    And I click on "Close" icon
    Then I should be able to see the respective bungii with the below status
      | Status |
      | Scheduled |
    Then Partner firm should receive "Bungii Delivery Pickup Updated" email
    And I click on "Edit" link beside scheduled bungii
    And I click on "Cancel entire Bungii and notify driver(s)" radiobutton
    And I enter cancellation fee and Comments
    And I click on "Submit" button
    Then The "Pick up has been successfully cancelled." message should be displayed
    When I view the Trips list on the admin portal
    Then The Trip List page should display the trip in "Admin Cancelled" state
    And Partner firm should not receive "Bungii Delivery Pickup Canceled" email

  @regression
  @email
  @failed
  Scenario: Verify Failed Trip Email - Ondemand Trip Cancelled By Customer
    When I request "Solo Ondemand" Bungii as a customer in "washingtondc" geofence from a partner location
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 9999999361 | Testcustomertywd_appleWashF Shah|
    And I note the Pickupref of trip
    When I cancel bungii as a customer "Testcustomertywd_appleWashF Shah" with phone number "9999999361"
    Then Admin receives "Failed Ondemand Trips" trip email for "Customer Cancelled" status
    And Partner firm should not receive "Bungii Delivery Pickup Canceled" email






