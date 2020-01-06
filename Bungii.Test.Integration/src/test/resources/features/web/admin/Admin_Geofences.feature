@web
Feature: Admin_Geofence

  Background:
    Given I am logged in as TestAdmin
    When I click on "Geofences  > Geofences" Menu
    Then I should be directed to "Geofences Page"

  @sanity
  @regression
  Scenario: Admin_AddEditNewGeofence
    When I click on the "Scale" Button
    And I enter following values in "Geofence" fields
      | Primary                                              | Secondary                   | Geo-Name  | Geo-TimeZone | Geo-Status|
      | e{o~FpctuOjE\|j_Ao\|e@veBfe@mbt@lqe@_rM      | km_}FhtotOznYf~gDcoeDxy]cx@stsBlmoC{orA        | ZONE-      | MST            |Active|
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
  Scenario: Admin_Timzones and Status list
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
  Scenario: Admin_Only Active timezones are listed in geofence filter
    When I navigate to following pages one by one
      |Page |
      | Dashboard    |
      | Customers     |
      | Drivers       |
      | Trips          |
      | Scheduled Trips |
      | Live Trips |
      |Partners    |
    Then I should see active zone in the dropdown on the "respective" page
    And I should not see inactive zone in the dropdown on the "respective" page


  @sanity
  @regression
    Scenario: Admin_GeofenceSettings
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



