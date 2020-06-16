@ios

Feature: Bungii Geofence functionality

  @failed
  @ready
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
      | Solo   | Berman Nissan of Chicago | 63 East Ida B. Wells Drive, Chicago, IL 60605, USA | chicago   |


    And I click "Get Estimate" button on "Home" screen
    Then I should be navigated to "Estimate" screen

    And I select pickup time
    Then correct next available scheduled time should be displayed

    @failed
  @ready
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
      | Duo   |  Berman Nissan of Chicago | 63 East Ida B. Wells Drive, Chicago, IL 60605, USA  | chicago   |


    And I click "Get Estimate" button on "Home" screen
    Then I should be navigated to "Estimate" screen

    And I select pickup time
    Then correct next available scheduled time should be displayed

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
      | Solo   | Berman Nissan of Chicago  |
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