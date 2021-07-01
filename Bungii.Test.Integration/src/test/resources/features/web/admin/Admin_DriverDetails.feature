@web
Feature: Admin_DriverDetails

  Background:
    Given I am logged in as Admin
    When I click on "Drivers" Menu
    Then I should be directed to "Drivers Page"

  @regression
  Scenario: Verify Driver Search On Drivers Page
    When I enter "drivers" "first name" in the "Drivers search" box
    Then I should see "driver first name" listed on the "Drivers" page

    When I enter "drivers" "last name" in the "Drivers search" box
    Then I should see "driver last name" listed on the "Drivers" page

  #Commenting out below three scenarion as a part of change in CORE-1453 where SSN field for driver has been removed
  #  @regression
#    Scenario: Verify masking of SSN for Existing driver
#      When I navigate to following pages one by one
#        |Page |
#        | Drivers |
#      And I search driver "Testdrivertywd_appleks_rathree Test"
#      And I click on "Driver Trips" icon
#      And I click on "View Profile"
#      Then I check if driver SSN is masked
#
#      #need to create data on base for following testcase
#  @regression
#  @remove
#  Scenario: Verify masking of SSN for New driver
#    When I navigate to following pages one by one
#      |Page |
#      | Drivers |
#    And I search driver "Testdrivertywd_appleks_rathree Test"
#    And I click on "Driver Trips" icon
#    And I click on "View Profile" Link
#    Then I check if driver SSN is masked
#
#  @regression
#  @remove
#  Scenario: Verify masking of SSN for Existing driver
#    When I navigate to following pages one by one
#      |Page |
#      | Drivers |
#    Then Set the Geofence dropdown to "ALL"
#    And I search driver "Dorry Sail"
#    And I click on "Profile" icon
#    And I edit the Driver
#    And I click on "Save Driver Details" button
#    Then I check if driver SSN is masked

  @regression
  Scenario: Verify Driver Trip List Status Updation for Solo Scheduled Bungii
    When I search driver "Macy Chang"
    And I click on "Driver Trips" icon
    Then The "Trip List" page should be displayed
    And List of trips completed by the driver should be displayed on the Trip List Page
    When I request "Solo Scheduled" Bungii as a customer in "goa" geofence
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 7770081848 | Stephen North|
    And As a driver "Macy Chang" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      | Accepted |
    Then The Driver Trip List page should display the trip in "Driver(s) Accepted" state
    When As a driver "Macy Chang" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      | Enroute |
    Then The Driver Trip List page should display the trip in "Trip Started" state
    When As a driver "Macy Chang" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      | Arrived |
    Then The Driver Trip List page should display the trip in "Driver(s) Arrived" state
    When As a driver "Macy Chang" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      | Loading Item |
    Then The Driver Trip List page should display the trip in "Loading Items" state
    When As a driver "Macy Chang" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      | Driving To Dropoff |
    Then The Driver Trip List page should display the trip in "Driving To Dropoff" state
    When As a driver "Macy Chang" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      | Unloading Item |
    Then The Driver Trip List page should display the trip in "Unloading Items" state
    When As a driver "Macy Chang" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      | Bungii Completed |
    Then The Driver Trip List page should display the trip in "Payment Successful" state

  @regression
    #stable
  Scenario: Verify Driver Search On Various Pages
    When I navigate to following pages one by one
      |Page |
      | Dashboard    |
    And I enter "drivers" "first name" in the "Dashboard search" box
    Then I should see "driver first name" listed on the "Dashboard" page
    And I click on "Dashboard" page
    When I enter "drivers" "last name" in the "Dashboard search" box
    Then I should see "driver last name" listed on the "Dashboard" page

    When I navigate to following pages one by one
      |Page |
      | All Deliveries |
    And I enter "drivers" "first name" in the "Deliveries search" box
    Then I should see "driver first name" listed on the "Deliveries" page

    When I enter "drivers" "last name" in the "Deliveries search" box
    Then I should see "driver last name" listed on the "Deliveries" page

  @ready
  Scenario: Verify Admin can edit and change the driver phone number
    When I search driver "Testdrivertywd_appledc_a_drve Driver"
    And I click "Profile" button for the "Testdrivertywd_appledc_a_drve Driver" driver
    And I click "Edit" button for the "Testdrivertywd_appledc_a_drve Driver" driver
    Then I change the "Testdrivertywd_appledc_a_drve Driver" phone number
    And I click "Save" button for the "Testdrivertywd_appledc_a_drve Driver" driver
    And I enter confirm comment for edited phone and "Save" it
    Then I see updated driver phone number

  @ready
  Scenario: Verify Admin can edit and cancel the driver phone number
    When I search driver "Testdrivertywd_appledc_a_drve Driver"
    And I click "Profile" button for the "Testdrivertywd_appledc_a_drve Driver" driver
    And I click "Edit" button for the "Testdrivertywd_appledc_a_drve Driver" driver
    Then I change the "Testdrivertywd_appledc_a_drve Driver" phone number
    And I click "Cancel" button for the "Testdrivertywd_appledc_a_drve Driver" driver
    Then I see unchanged driver phone number

  @ready
  Scenario: Verify Admin can edit and cancel the driver phone number by unsaving the comment
    When I search driver "Testdrivertywd_appledc_a_drve Driver"
    And I click "Profile" button for the "Testdrivertywd_appledc_a_drve Driver" driver
    And I click "Edit" button for the "Testdrivertywd_appledc_a_drve Driver" driver
    Then I change the "Testdrivertywd_appledc_a_drve Driver" phone number
    And I click "Save" button for the "Testdrivertywd_appledc_a_drve Driver" driver
    And I enter confirm comment for edited phone and "Cancel" it
    And I click "Cancel" button for the "Testdrivertywd_appledc_a_drve Driver" driver
    Then I see unchanged driver phone number
