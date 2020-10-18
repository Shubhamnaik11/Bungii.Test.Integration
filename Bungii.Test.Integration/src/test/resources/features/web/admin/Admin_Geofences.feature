@web
Feature: Admin_Geofence

  Background:
    Given I am logged in as TestAdmin
    When I click on "Geofences  > Geofences" Menu
    Then I should be directed to "Geofences Page"


  @sanity
  @regression
  Scenario: Verify Default Geofences Attribute settings
   When I click on "Geofences  > Attributes" Menu
    Then I should be directed to "Attributes Page"
    When I go to "Geofence Attributes" page
    Then I verify that the default settings are displayed
    
  @sanity
  @ready
  Scenario: Verify Add Edit New Geofence
    When I click on the "Scale" Button
    And I enter following values in "Geofence" fields
      | Primary                                              | Secondary                   | Geo-Name  | Geo-TimeZone | Geo-Status|
      | e{o~FpctuOjE\|j_Ao\|e@veBfe@mbt@lqe@_rM      | km_}FhtotOznYf~gDcoeDxy]cx@stsBlmoC{orA        | ZONE-<<UniqueNo>> | MST            |Active|
    When I click on the "Save" Button on "Geofence" Screen
    Then the geofence gets saved successfully and it is displayed in the "Geofences" grid
    When I click on the geofence name "ZONE-"
    Then Geofence data is populated correctly
    When I edit the geofence "ZONE-"
    And I enter following values in "Geofence" fields
      | Primary                                              | Secondary                   | Geo-Name  | Geo-TimeZone | Geo-Status|
      | e{o~FpctuOjE\|j_Ao\|e@veBfe@mbt@lqe@_rM      | km_}FhtotOznYf~gDcoeDxy]cx@stsBlmoC{orA        |     | PST            |Inactive|
    When I click on the "Save" Button on "Geofence" Screen
    Then the geofence gets saved successfully and it is displayed in the "Geofences" grid

  @sanity
  @regression
  Scenario: Verify Available Timezones And Statuses On New Geofence
    When I click on the "Scale" Button
    Then the following timezones are listed in the "Geo-TimeZone" dropdown
      |Timezone|
      |MST     |
      |EST     |
      |CST     |
      |PST     |
      |IST     |
    And the following statuses are listed in the "Geo-Status" dropdown
      |Status  |
      |Active  |
      |Inactive|

  @sanity
  @regression
  Scenario: Verify Only Active Timezones Are Listed In Geofence Filter Throughout Application
    When I navigate to following pages one by one
      |Page |
      | Dashboard    |
      | Customers     |
      | Drivers       |
      | All Deliveries          |
      | Scheduled Deliveries |
      | Live Deliveries |
      |Partners    |
    Then I should see active zone in the dropdown on the "respective" page
    And I should not see inactive zone in the dropdown on the "respective" page


  @sanity
  @regression
    Scenario: Verify Settings Behavior Of Solo And Duo Settings on Geofence
    When I click on the geofence "Denver"
    And I click on the "Settings" Button on "Geofence" Screen
    Then I cannot uncheck "Solo" for "Driver(s) for Scheduled trip" settings when "Duo" is checked
    When I "uncheck" option "Duo" for Scheduled trip
    Then I can deselect "Solo" option for Scheduled trip
    When I "check" option "Duo" for Scheduled trip
    Then The "solo" gets selected automatically
    When I uncheck both on demand and Scheduled for a geofence
    And I click on the "Save" Button on "Geofence Settings" Screen
    Then The validation error message is displayed


  @failed
    #NEED TO VERIFY VALUES OF THESE PARAMETERS SCHEDULE_PICKUP_FROM_TIME and SCHEDULE_PICKUP_TO_TIME
  #this test script will fail as the value of above parameters are set as 30 mins and 840 mins.
  #It should be 15 mins and 1410 mins, currently validations are put considering 15 and 1410 mins
  @regression
  Scenario: Verify Minimum Scheduled Time For Solo Or Duo Trip Cannot Be More Than The Difference Between SCHEDULE_PICKUP_FROM_TIME And SCHEDULE_PICKUP_TO_TIME
    When I click on the geofence "Chicago"
    And I click on the "Settings" Button on "Geofence" Screen

    And I change the value of "Minimum scheduled time for Duo trip" to "14" minutes
    And I click on the "Save" Button on "Geofence Settings" Screen
    Then check if error message is displayed for "duo trip"
    And I change the value of "Minimum scheduled time for Duo trip" to "15" minutes
    And I click on the "Save" Button on "Geofence Settings" Screen
    Then check if error message is displayed for "duo trip"
    And I change the value of "Minimum scheduled time for Duo trip" to "16" minutes
    And I click on the "Save" Button on "Geofence Settings" Screen
    Then check if error message is displayed for "duo trip"
    And I change the value of "Minimum scheduled time for Duo trip" to "841" minutes
    And I click on the "Save" Button on "Geofence Settings" Screen
    Then check if error message is displayed for "duo trip"
    And I change the value of "Minimum scheduled time for Duo trip" to "840" minutes
    And I click on the "Save" Button on "Geofence Settings" Screen
    Then Enter value should get saved and error message is not displayed
    And I click on the "Settings" Button on "Geofence" Screen
    #And I change the value of "Minimum scheduled time for Duo trip" to "1415" minutes
    #And I click on the "Save" Button on "Geofence Settings" Screen
    #Then check if error message is displayed for "duo trip"

    And I change the value of "Minimum scheduled time for Solo trip" to "14" minutes
    And I click on the "Save" Button on "Geofence Settings" Screen
    Then check if error message is displayed for "solo trip"
    And I change the value of "Minimum scheduled time for Solo trip" to "15" minutes
    And I click on the "Save" Button on "Geofence Settings" Screen
    Then check if error message is displayed for "solo trip"
    And I change the value of "Minimum scheduled time for Solo trip" to "16" minutes
    And I click on the "Save" Button on "Geofence Settings" Screen
    Then check if error message is displayed for "solo trip"
    And I change the value of "Minimum scheduled time for Solo trip" to "841" minutes
    And I click on the "Save" Button on "Geofence Settings" Screen
    Then check if error message is displayed for "solo trip"
    And I change the value of "Minimum scheduled time for Solo trip" to "840" minutes
    And I click on the "Save" Button on "Geofence Settings" Screen
    Then Enter value should get saved and error message is not displayed
   # And I change the value of "Minimum scheduled time for Solo trip" to "1410" minutes
  # And I click on the "Save" Button on "Geofence Settings" Screen
  #  Then check if error message is displayed for "solo trip"
   # And I change the value of "Minimum scheduled time for Solo trip" to "1415" minutes
   # And I click on the "Save" Button on "Geofence Settings" Screen
    #Then check if error message is displayed for "solo trip"

  @failed
        #NEED TO VERIFY VALUES OF THIS PARAMETER SCHEDULED_PICKUP_MAX_PROCESSING_TIME
  #In database this value is set as 120 mins, needs to be checked.
  @regression
  Scenario:Verify Minimum Scheduled Time For Solo Or Duo Trip Cannot Be Less Than SCHEDULED_PICKUP_MAX_PROCESSING_TIME
    When I click on the geofence "Chicago"
    And I click on the "Settings" Button on "Geofence" Screen

    And I change the value of "Minimum scheduled time for Duo trip" to "29" minutes
    And I click on the "Save" Button on "Geofence Settings" Screen
    Then check if error message is displayed for "duo trip"
    And I change the value of "Minimum scheduled time for Duo trip" to "30" minutes
    And I click on the "Save" Button on "Geofence Settings" Screen
    Then Enter value should get saved and error message is not displayed

    And I click on the "Settings" Button on "Geofence" Screen
    And I change the value of "Minimum scheduled time for Solo trip" to "29" minutes
    And I click on the "Save" Button on "Geofence Settings" Screen
    Then check if error message is displayed for "solo trip"
    And I change the value of "Minimum scheduled time for Solo trip" to "30" minutes
    And I click on the "Save" Button on "Geofence Settings" Screen
    Then Enter value should get saved and error message is not displayed

  @regression
  Scenario: Verify and add new attribute in Geofence Attributes page
    When I load Geofence Attributes Page and Click on New Attributes button
    And I enter following values in "Geofence Attributes" fields
      | Key                                              | Default-Value                   | Description  | Label|
      | BusinessFAQ   | BusinessFAQ        | This is Business FAQ Link | BusinessFAQ |
    And I click on the "Save" Button on "GeofenceAttributes" Screen
    Then The geofence Attributes gets saved successfully and it is displayed in the grid
    When I search by Name "BusinessFAQ" in "GeofenceAttributes" page geofence
    And I check the Searched result is displayed correctly
#      Then I logout of Admin Portal

  @regression
  Scenario: Verify and check attributes are empty
    When I load Geofence Attributes Page and Click on New Attributes button
    And I enter following values in "GeofenceAttributes" fields
      | Key                                              | Default-Value                   | Description  | Label|
      |      |        |  | |
    When I click on the "Save" Button on "GeofenceAttributes" Screen
    Then the "Oops! It looks like you missed something. Please fill out all fields before proceeding." message is displayed  in geofence popup

  @regression
  Scenario: Verify Field Validations on Geofence Attributes page
    When I load Geofence Attributes Page and Click on New Attributes button
    And I click on the "Save" Button on "GeofenceAttributes" Screen
    Then the "Oops! It looks like you missed something. Please fill out all fields before proceeding." message is displayed  in geofence popup
    And I enter following values in "Geofence Attributes" fields
      | Key                                              | Default-Value                   | Description  | Label|
      | BusinessFAQ      | BusinessFAQ        |  | |
    And I click on the "Save" Button on "GeofenceAttributes" Screen
    Then the "Oops! It looks like you missed something. Please fill out all fields before proceeding." message is displayed  in geofence popup
