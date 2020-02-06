@ios

Feature: Geofence functionality

  @regression1
  Scenario: Minimum scheduled time should be displayed on the date picker of the estimate screen based on whether solo or duo has been selected by the customer. Bungii Type: SOLO
    Given I am on the "LOG IN" page
    And I logged in Customer application using  "valid chicago" user

    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Geofence" from admin sidebar
    And I select "Chicago" geofence
    And I click on the "Settings" Button on "Geofence" Screen
    And I change the value of "Minimum scheduled time for Duo trip" to "45" minutes
    And I change the value of "Minimum scheduled time for Solo trip" to "60" minutes
    And I click on the "Save" Button on "GeofenceSettings" Screen

    When I switch to "ORIGINAL" instance
    And I Switch to "customer" application on "same" devices
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location                           | Drop Location                                      | Geofence  |
      | Solo   | 3200 N Kedzie Ave, Chicago, IL 60618, USA | 63 East Ida B. Wells Drive, Chicago, IL 60605, USA | chicago   |


    And I click "Get Estimate" button on "Home" screen
    Then I should be navigated to "Estimate" screen

    And I select pickup time
    Then correct next available scheduled time should be displayed
    #Then correct details next available scheduled time should be displayed

