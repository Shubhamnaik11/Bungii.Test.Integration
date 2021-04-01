@android
@bungii
    #These feature will run in Goa geofence
Feature: Bungii Details
     #Scenarios :; 7
     #Testcustomertywd_appleand_F Android 9999999999
      #driverF.phone.name=Driver_goa_f Android_test 9999999996
  
  
  
  @regression
  Scenario: Verify that for Duo trips if Admin portal displays Application error when one driver is accepted through push notification and other is assigned by ADMIN
    
    Given I am logged in as "Testcustomertywd_appleand_F Android" customer
    
    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "Driver_goa_f Android_test" driver
    And I Select "Home" from driver App menu
    
    And I Open "customer" application on "ORIGINAL" devices
    And I enter "Goa pickup and dropoff location" on Bungii estimate
    And I tap on "two drivers selector" on Bungii estimate
    Then I should see "two drivers selected" on Bungii estimate
    When I tap on "Get Estimate button" on Bungii estimate
    And I add "1" photos to the Bungii
    And I add loading/unloading time of "30 mins"
    And I select Bungii Time as "next possible scheduled for duo"
    And I get Bungii details on Bungii Estimate
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    And I check if the customer is on success screen
    And I tap on "Done after requesting a Scheduled Bungii" on Bungii estimate
    
    And I Switch to "driver" application on "same" devices
    And I tap on "Available Trips link" on Driver Home page
    And I Select Trip from driver available trip
    And I tap on "ACCEPT" on driver Trip details Page
    Then I Select "SCHEDULED BUNGIIS" from driver App menu
    
    And I open Admin portal and navigate to "Scheduled Deliveries" page
    And I open the trip for "Testcustomertywd_appleand_F Android" customer
    And I Select "Edit Trip Details" option
    And I assign driver for the "Solo" trip
    And I click on "VERIFY" button
    And the "Your changes are good to be saved." message is displayed
    Then I click on "SAVE CHANGES" button
    And the "Bungii Saved!" message is displayed
    When I switch to "ORIGINAL" instance
    And I Switch to "customer" application on "same" devices
    And I tap on "Menu" > "MY BUNGIIS" link
    And I select already scheduled bungii
    
  @ready
    #need to work
  Scenario: Verify that changing date_time for a scheduled bungii for which the assigned driver has a conflicting bungii during the newly selected time
    When I request "Solo Scheduled" Bungii as a customer in "goa" geofence
      | Bungii Time   | Customer Phone | Customer Name                       | Customer Password |
      | NEXT_POSSIBLE | 9999999999     | Testcustomertywd_appleand_F Android | Cci12345          |
    And I save the Bungii Time
    And that solo schedule bungii is in progress for customer "Testcustomertywd_appleand_F Android"
      | geofence | Bungii State | Bungii Time    |
      | goa   | Accepted       | 3 hour ahead |
    And I wait for "2" mins
    When I open new "Chrome" browser for "ADMIN"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Scheduled Trip" from admin sidebar
    And I click on "Edit Trip1" button
    And I Select "Edit Trip Details" option
    And I change the "particular trip time" to future time
    And I click on "VERIFY" button
    Then the "It looks like customer already has a Bungii scheduled at this time. Customer can have only one Bungii at a time" message is displayed
    And I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 9999999999     |                 |
  
  
  @ready
  Scenario: Verify that changing date_time for a scheduled bungii for which the customer has a conflicting bungii during the newly selected time
    Given that solo schedule bungii is in progress for customer "Testcustomertywd_appleand_F Android"
      | geofence | Bungii State | Bungii Time     |
      | goa      | Accepted     | 0.5 hour ahead  |
    And I save the Bungii Time
    Given that solo schedule bungii is in progress for customer "Testcustomertywd_appleand_F Android"
      | geofence | Bungii State | Bungii Time     |
      | goa      | Accepted     | 3 hour ahead  |
    And I wait for "2" mins
    When I open new "Chrome" browser for "ADMIN"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Scheduled Trip" from admin sidebar
    And I open the trip for "Testcustomertywd_appleand_F Android" customer
    And I Select "Edit Trip Details" option
    #And I change the "3 hour ahead" to future time
    And I change the "particular trip time" to future time
    And I click on "VERIFY" button
    Then the "It looks like customer already has a Bungii scheduled at this time. Customer can have only one Bungii at a time" message is displayed
    And I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 9999999999     |                 |
  
  
  @ready
  Scenario: Verify that the My Bungii Past trip is visible when admin manually ends bungii
    Given I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "Driver_goa_f Android_test" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I tap on "Go Online button" on Driver Home page
    Then I Switch to "customer" application on "same" devices

    When I am on customer Log in page
    And I am logged in as "Testcustomertywd_appleand_F Android" customer
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    When I enter "Goa pickup and dropoff locations" on Bungii estimate screen
    And I tap on "Get Estimate button" on Bungii estimate
    And I add "1" photos to the Bungii
    And I add loading/unloading time of "15 mins"
	And I select Bungii Time as "NEW BUNGII TIME"
	And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    And I check if the customer is on success screen
    Then I tap on "Done after requesting a Scheduled Bungii" on Bungii estimate

    When I Switch to "driver" application on "same" devices
    And I Select "AVAILABLE BUNGIIS" from driver App menu
    And I Select Trip from available trip
    And I tap on "ACCEPT" on driver Trip details Page
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    Then I Select Trip from driver scheduled trip

    When Bungii Driver "Start Schedule Bungii" request
    And Bungii Driver "slides to the next state"
    And Bungii Driver "slides to the next state"
    And Bungii Driver "slides to the next state"
    When bungii admin manually end bungii created by "CUSTOMER1"
    Then Bungii driver should see "summary" on Bungii completed page

    When I Switch to "customer" application on "same" devices
    And I tap on "Menu" > "MY BUNGIIS" link
    And "MY BUNGIIS" page should be opened
    And I click on "Past" tab
    And I open the trip for "Testdriver_goa_a Android_test" driver
    Then I verify the field "driver name"
    And I verify the field "pickup address"
    And I verify the field "dropoff address"
    And I verify the field "trip cost"

  @ready
  Scenario: Verify that the Pickup note is not displayed as NULL or undefined when customer does not add a pickup note
    When I am on the LOG IN page on driver app
    And I am logged in as "Testdriver_goa_f Android_test" driver
    And I tap on "Go Online button" on Driver Home page

    When I Switch to "customer" application on "same" devices
    And I am logged in as "Testcustomertywd_appleand_F Android" customer
    And I enter "Goa pickup and dropoff location" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I add loading/unloading time of "15 mins"
    Then I add "1" photos to the Bungii
    When I tap on "Details" on Estimate screen
    And I enter "text" in Additional Notes field
    And I click on "ADD NOTE" button
    And I select Bungii Time as "30 MIN DELAY"
    Then "Estimate" page should be opened
    When I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    And I click "Done" button on "Success" screen

    And I click on notification for "driver" for "SCHEDULED PICKUP AVAILABLE"
    Then Alert message with ACCEPT SCHEDULED BUNGII QUESTION text should be displayed
    When I click "View" on alert message
    Then I should be able to see "No Note" Text
    And I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | 9999999999      |                 |
    
  @ready
  Scenario: Verify that application error is not thrown on re-search of trip with apostrophe in Customer notes
    Given I am on customer Log in page
    And I am logged in as "Testcustomertywd_appleand_F Android" customer

    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "Driver_goa_f Android_test" driver
    And I tap on "Go Online button" on Driver Home page
    And I Switch to "customer" application on "same" devices
    And I enter "Goa pickup and dropoff location" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I add loading/unloading time of "15 mins"
    Then I add "1" photos to the Bungii
    When I tap on "Details" on Estimate screen
    And I enter "Customer note should contain apostrophe in the note's." in Additional Notes field
    And I click on "ADD NOTE" button
    Then "Estimate" page should be opened
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate

    When I click on notification for "on demand trip"
    Then Alert message with ACCEPT BUNGII QUESTION text should be displayed
    When I click "YES" button on alert message
    Then I should be able to see "Customer Note" Text
    And I wait for "1" mins

    When I open new "Chrome" browser for "ADMIN"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Scheduled Trip" from admin sidebar
    And I open the trip for "Testcustomertywd_appleand_F Android" the customer
    Then I remove current driver and researches Bungii
    
    And I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | 9999999999      |                 |
  
  @ready
  Scenario: Verify if re-search automatically happens if admin does not add a new driver after removal
    Given that solo schedule bungii is in progress for customer "Testcustomertywd_appleand_F Android"
      | geofence | Bungii State | Bungii Time  |
      | goa      | Accepted     | 0.5 hour ahead |
    When I open new "Chrome" browser for "ADMIN"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Scheduled Trip" from admin sidebar
    Then I remove current driver
    
    And I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | 9999999999      |                 |
  