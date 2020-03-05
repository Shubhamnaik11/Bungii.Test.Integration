@ios

Feature: Geofence functionality
  
  @regression
  Scenario: Verify Minimum Scheduled Time Should Be Displayed On The Date Picker Of The Estimate Screen Based On When Solo Is Selected By Customer
    Given I am on the "LOG IN" page
    And I logged in Customer application using  "valid chicago" user

    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Geofence" from admin sidebar
    And I select "Chicago" geofence
    And I click on the "Settings" Button on "Geofence" Screen
    And I change the value of "Minimum scheduled time for Solo trip" to "30" minutes
    And I click on the "Save" Button on "GeofenceSettings" Screen

    When I switch to "ORIGINAL" instance
    And I Switch to "customer" application on "same" devices
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location                           | Drop Location                                      | Geofence  |
      | Solo   | 3200 N Kedzie Ave Chicago | 63 East Ida B. Wells Drive, Chicago, IL 60605, USA | chicago   |


    And I click "Get Estimate" button on "Home" screen
    Then I should be navigated to "Estimate" screen

    And I select pickup time
    Then correct next available scheduled time should be displayed
  
  @regression
  Scenario: Verify Minimum Scheduled Time Should Be Displayed On The Date Picker Of The Estimate Screen Based On When Duo Is Selected By Customer
    Given I am on the "LOG IN" page
    And I logged in Customer application using  "valid chicago" user

    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Geofence" from admin sidebar
    And I select "Chicago" geofence
    And I click on the "Settings" Button on "Geofence" Screen
    And I change the value of "Minimum scheduled time for Duo trip" to "45" minutes
    And I click on the "Save" Button on "GeofenceSettings" Screen

    When I switch to "ORIGINAL" instance
    And I Switch to "customer" application on "same" devices
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location                           | Drop Location                                      | Geofence  |
      | Duo   | 3200 N Kedzie Ave, Chicago, IL 60618, USA | 63 East Ida B. Wells Drive, Chicago, IL 60605, USA  | chicago   |


    And I click "Get Estimate" button on "Home" screen
    Then I should be navigated to "Estimate" screen

    And I select pickup time
    Then correct next available scheduled time should be displayed
  @FAILED0203
    #NEED TO VERIFY VALUES OF THESE PARAMETERS SCHEDULE_PICKUP_FROM_TIME and SCHEDULE_PICKUP_TO_TIME
  #this test script will fail as the value of above parameters are set as 30mins and 840mins.
  #It should be 15 mins and 1410 mins, currently validations are put considering 15 and 1410 mins
  @regression
  Scenario: Verify Minimum Scheduled Time For Solo Or Duo Trip Cannot Be More Than The Difference Between SCHEDULE_PICKUP_FROM_TIME And SCHEDULE_PICKUP_TO_TIME
    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Geofence" from admin sidebar
    And I select "Chicago" geofence
    And I click on the "Settings" Button on "Geofence" Screen

    And I change the value of "Minimum scheduled time for Duo trip" to "14" minutes
    And I click on the "Save" Button on "GeofenceSettings" Screen
    Then check if error message is displayed for "duo trip"
    And I change the value of "Minimum scheduled time for Duo trip" to "15" minutes
    And I click on the "Save" Button on "GeofenceSettings" Screen
    Then check if error message is displayed for "duo trip"
    And I change the value of "Minimum scheduled time for Duo trip" to "16" minutes
    And I click on the "Save" Button on "GeofenceSettings" Screen
    Then check if error message is displayed for "duo trip"
    And I change the value of "Minimum scheduled time for Duo trip" to "841" minutes
    And I click on the "Save" Button on "GeofenceSettings" Screen
    Then check if error message is displayed for "duo trip"
    And I change the value of "Minimum scheduled time for Duo trip" to "1410" minutes
    And I click on the "Save" Button on "GeofenceSettings" Screen
    Then check if error message is displayed for "duo trip"
    And I change the value of "Minimum scheduled time for Duo trip" to "1415" minutes
    And I click on the "Save" Button on "GeofenceSettings" Screen
    Then check if error message is displayed for "duo trip"

    And I change the value of "Minimum scheduled time for Solo trip" to "14" minutes
    And I click on the "Save" Button on "GeofenceSettings" Screen
    Then check if error message is displayed for "solo trip"
    And I change the value of "Minimum scheduled time for Solo trip" to "15" minutes
    And I click on the "Save" Button on "GeofenceSettings" Screen
    Then check if error message is displayed for "solo trip"
    And I change the value of "Minimum scheduled time for Solo trip" to "840" minutes
    And I click on the "Save" Button on "GeofenceSettings" Screen
    Then check if error message is displayed for "solo trip"
    And I change the value of "Minimum scheduled time for Solo trip" to "16" minutes
    And I click on the "Save" Button on "GeofenceSettings" Screen
    Then check if error message is displayed for "solo trip"
    And I change the value of "Minimum scheduled time for Solo trip" to "841" minutes
    And I click on the "Save" Button on "GeofenceSettings" Screen
    Then check if error message is displayed for "solo trip"
    And I change the value of "Minimum scheduled time for Solo trip" to "1410" minutes
    And I click on the "Save" Button on "GeofenceSettings" Screen
    Then check if error message is displayed for "solo trip"
    And I change the value of "Minimum scheduled time for Solo trip" to "1415" minutes
    And I click on the "Save" Button on "GeofenceSettings" Screen
    Then check if error message is displayed for "solo trip"

    And I click on the "Cancel" Button on "Geofence" Screen
  @FAILED0203
        #NEED TO VERIFY VALUES OF THIS PARAMETER SCHEDULED_PICKUP_MAX_PROCESSING_TIME
  #In database this value is set as 120 mins, needs to be checked.
  @regression
    Scenario:Verify Minimum Scheduled Time For Solo Or Duo Trip Cannot Be Less Than SCHEDULED_PICKUP_MAX_PROCESSING_TIME
      When I open new "Chrome" browser for "ADMIN PORTAL"
      And I navigate to admin portal
      And I log in to admin portal
      And I Select "Geofence" from admin sidebar
      And I select "Chicago" geofence
      And I click on the "Settings" Button on "Geofence" Screen

      And I change the value of "Minimum scheduled time for Duo trip" to "29" minutes
    And I click on the "Save" Button on "GeofenceSettings" Screen
    Then check if error message is displayed for "duo trip"
      And I change the value of "Minimum scheduled time for Duo trip" to "30" minutes
    And I click on the "Save" Button on "GeofenceSettings" Screen
    Then check if error message is displayed for "duo trip"

      And I change the value of "Minimum scheduled time for Solo trip" to "29" minutes
    And I click on the "Save" Button on "GeofenceSettings" Screen
    Then check if error message is displayed for "solo trip"
      And I change the value of "Minimum scheduled time for Solo trip" to "30" minutes
    And I click on the "Save" Button on "GeofenceSettings" Screen
    Then check if error message is displayed for "solo trip"
      And I click on the "Cancel" Button on "Geofence" Screen

  @regression
  Scenario: Verify Message Displayed On The App When Geofence Is Set off
    Given I am on the "LOG IN" page
    And I logged in Customer application using  "valid chicago" user

    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    Then I log in to admin portal
    When I Select "Geofence" from admin sidebar
    And I select "Chicago" geofence
    And I edit the geofence "Chicago"
    And I select "Geo-Status" as "Inactive"
    Then I click on the "Save" Button on "Geofence" Screen

    When I switch to "ORIGINAL" instance
    And I Switch to "customer" application on "same" devices
    And I enter pickup location
      | Driver | Pickup Location                            |
      | Solo   | 3200 N Kedzie Ave, Chicago, IL 60618, USA  |
    Then driver eta should be "not be displayed"
    And geofence not active message should be displayed

    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    Then I log in to admin portal
    When I Select "Geofence" from admin sidebar
    And I select "Chicago" geofence
    And I edit the geofence "Chicago"
    And I select "Geo-Status" as "Active"
    Then I click on the "Save" Button on "Geofence" Screen