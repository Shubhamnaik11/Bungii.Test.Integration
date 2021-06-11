@android
@SoloScheduled
@bungii
#These feature will run in kansas geofence
Feature: SoloScheduled Part C
  
  Background:

  @regression
    #stable
  Scenario: Verify That Solo Scheduled Bungii can be started 1 hour before the Scheduled start time
    When that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time  |
      | Kansas   | Accepted     | 1 hour ahead |
    When I Open "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid" driver
    And I wait for 2 minutes
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from driver scheduled trip
    And Bungii Driver "Start Schedule Bungii" request
    Then Bungii driver should see "Enroute screen"
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8888889916     |                 |
    
  @regression
    #stable
  Scenario: Verify That a Solo scheduled Bungii can be started 30 mins before the scheduled Trip start time
    When that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time  |
      | Kansas   | Accepted     | 0.5 hour ahead |
    When I Switch to "driver" application on "same" devices
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
    Then I click "Go Online" button on Home screen on driver app
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I open first Trip from driver scheduled trip
    #And I Select Trip from driver scheduled trip
    And Bungii Driver "Start Schedule Bungii" request
    Then User should see message "60 MINS BEFORE SCHEDULE TRIP TIME" text on the screen
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8888889916     |                 |
    
  @regression
    #stable
  Scenario: Verify driver is able to view pickup note entered in Details when a Solo scheduled bungii is in progress
    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid" driver
    
    And I Switch to "customer" application on "same" devices
    When I am on customer Log in page
    When I am logged in as "valid" customer
    And I enter "valid pickup and dropoff locations" on Bungii estimate
    And I tap on "two drivers selector" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I add loading/unloading time of "15 mins"
    Then I add "1" photos to the Bungii
    When I tap on "Details" on Estimate screen
    And I enter "text" in Additional Notes field
    And I click on "ADD NOTE" button
    And I select Bungii Time as "OLD BUNGII TIME"
    Then "Estimate" page should be opened
    When I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    
    When I Switch to "driver" application on "same" devices
    And I Select "AVAILABLE BUNGIIS" from driver App menu
    And I Select Trip from driver available trip
    And I tap on "ACCEPT" on driver Trip details Page
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from driver scheduled trip
    Then I start selected Bungii
    When I slide update button on "EN ROUTE" Screen
    And I slide update button on "ARRIVED" Screen
    And I click on "MORE" button
    And I click on "DETAILS FROM CUSTOMER" button
    And I should be able to see "Details From Customer" Text
    Then Bungii Driver "cancels Bungii"


