@android
@SoloScheduled
@bungii
#These feature will run in kansas geofence
Feature: SoloScheduled Part C
  
  Background:

  @regression
    #stable
  Scenario: Verify That a scheduled Bungii cannot be started more than 1hr before the scheduled Trip start time
    When that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time  |
      | Kansas   | Accepted     | 1.5 hour ahead |
    When I Open "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    Then I click "OFFLINE" button on Home screen on driver app
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I open first Trip from driver scheduled trip
    #And I Select Trip from driver scheduled trip
    And Bungii Driver "Start Schedule Bungii" request
    Then User should see message "60 MINS BEFORE SCHEDULE TRIP TIME" text on the screen
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8888889916     |                 |
    
 
  
  @ready
    #stable
  Scenario: Verify That Solo Scheduled Bungii can be started 1 hour before the Scheduled delivery start time
    When that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time  |
      | Kansas   | Accepted     | 1 hour ahead |
    When I Open "driver" application on "same" devices
    And I wait for "3" mins
    And I am on the LOG IN page on driver app
    And I am logged in as "valid" driver
    And I wait for "4" mins
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I wait for "4" mins
    And I Select Trip from driver scheduled trip
    And I wait for "3" mins
    And Bungii Driver "Start Schedule Bungii" request
    Then Bungii driver should see "Enroute screen"
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8888889916     |                 |
  
  @regression
    #stable
  Scenario: Verify That a Solo scheduled Bungii can be started 30 mins before the scheduled delivery start time
    When that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time  |
      | Kansas   | Accepted     | 0.5 hour ahead |
    When I Switch to "driver" application on "same" devices
    And I wait for "3" mins
    And I am on the LOG IN page on driver app
    And I am logged in as "valid" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from driver scheduled trip
    And Bungii Driver "Start Schedule Bungii" request
    Then Bungii driver should see "Enroute screen"
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8888889916     |                 |
    